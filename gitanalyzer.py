import os
import sys
import csv
import json
import shutil
import argparse
import subprocess
import stat
import time
import streamlit as st

from git import Repo
from radon.complexity import cc_visit, cc_rank

# ----------------------------
# Violation Class Definition
# ----------------------------
class Violation:
    def __init__(self, file, line, category, rule, description):
        self.file = file
        self.line = line
        self.category = category  # e.g., 'Pylint' or 'Cyclomatic Complexity'
        self.rule = rule          # for pylint: message id/symbol; for radon: 'Complexity'
        self.description = description

# ----------------------------
# Analysis: Pylint for Python Files
# ----------------------------
def analyze_python_file_pylint(filepath):
    """
    Runs pylint on a single Python file and returns a list of Violations.
    Each violation includes details such as message id, symbol, type, and message.
    """
    command = [sys.executable, "-m", "pylint", "--output-format=json", filepath]
    st.write(f"Running pylint on {filepath}...")
    result = subprocess.run(command, stdout=subprocess.PIPE, stderr=subprocess.PIPE, text=True)
    violations = []
    try:
        issues = json.loads(result.stdout)
        for issue in issues:
            # Use keys like 'path', 'line', 'symbol', and 'message'
            violations.append(Violation(
                file=issue.get("path", filepath),
                line=str(issue.get("line", "")),
                category="Pylint",
                rule=issue.get("symbol", ""),
                description=issue.get("message", "")
            ))
    except Exception as e:
        st.error(f"Error processing pylint output for {filepath}: {e}")
    return violations

# ----------------------------
# Analysis: Radon (Cyclomatic Complexity)
# ----------------------------
def analyze_python_file_radon(filepath):
    """
    Uses radon to analyze the cyclomatic complexity of a Python file.
    For every function/method in the file, it creates a violation record.
    """
    violations = []
    try:
        with open(filepath, "r", encoding="utf-8", errors="ignore") as f:
            source = f.read()
        blocks = cc_visit(source)
        for block in blocks:
            # Create a violation entry for each block.
            desc = (f"Function '{block.name}' has cyclomatic complexity {block.complexity} "
                    f"(Rank: {cc_rank(block.complexity)})")
            violations.append(Violation(
                file=filepath,
                line=str(block.lineno),
                category="Cyclomatic Complexity",
                rule="Complexity",
                description=desc
            ))
    except Exception as e:
        st.error(f"Error processing radon analysis for {filepath}: {e}")
    return violations

# ----------------------------
# Analyze All Python Files in the Repository
# ----------------------------
def analyze_python_repo(repo_path):
    """
    Walks through the repo, running pylint and radon analysis on each Python file.
    Returns a combined list of all detected violations.
    """
    all_violations = []
    for root, _, files in os.walk(repo_path):
        for file in files:
            if file.endswith('.py'):
                file_path = os.path.join(root, file)
                # Pylint analysis
                all_violations.extend(analyze_python_file_pylint(file_path))
                # Radon analysis for cyclomatic complexity
                all_violations.extend(analyze_python_file_radon(file_path))
    st.write(f"Total issues found in Python files: {len(all_violations)}")
    return all_violations

# ----------------------------
# Report Generators
# ----------------------------
def generate_csv_report(violations, output_path):
    """
    Generates a CSV report with columns: File, Line, Category, Rule, Description.
    """
    with open(output_path, 'w', newline='', encoding='utf-8') as csvfile:
        writer = csv.writer(csvfile)
        writer.writerow(["File", "Line", "Category", "Rule", "Description"])
        for v in violations:
            writer.writerow([v.file, v.line, v.category, v.rule, v.description])
    st.write("CSV report generated at:", output_path)
    return output_path

def generate_html_report(violations, output_path):
    """
    Generates an HTML report including a pie chart (Google Charts) summarizing issue distribution.
    """
    # Count issues by category and rule.
    counts = {}
    for v in violations:
        key = f"{v.category}: {v.rule}"
        counts[key] = counts.get(key, 0) + 1

    html = []
    html.append("<html><head>")
    html.append("<script src='https://www.gstatic.com/charts/loader.js'></script>")
    html.append("</head><body>")
    html.append("<h2>Python Code Analysis Report</h2>")
    html.append("<div id='chart' style='width: 800px; height: 600px;'></div>")
    html.append("<script>")
    html.append("google.charts.load('current', {packages:['corechart']});")
    html.append("google.charts.setOnLoadCallback(drawChart);")
    html.append("function drawChart() {")
    html.append("var data = new google.visualization.DataTable();")
    html.append("data.addColumn('string', 'Issue');")
    html.append("data.addColumn('number', 'Count');")
    html.append("data.addRows([")
    for key, count in counts.items():
        html.append(f"['{key}', {count}],")
    html.append("]);")
    html.append("var options = {title: 'Issue Distribution', pieHole: 0.4};")
    html.append("var chart = new google.visualization.PieChart(document.getElementById('chart'));")
    html.append("chart.draw(data, options);")
    html.append("}")
    html.append("</script>")
    html.append("</body></html>")
    
    with open(output_path, 'w', encoding='utf-8') as f:
        f.write("\n".join(html))
    st.write("HTML report generated at:", output_path)
    return output_path

# ----------------------------
# Utility: On-Error Remove for Cleanup
# ----------------------------
def on_rm_error(func, path, exc_info):
    os.chmod(path, stat.S_IWRITE)
    func(path)

# ----------------------------
# Git Repository Cloning
# ----------------------------
def clone_repository(repo_url, local_path):
    """
    Clones the provided git repository URL into the specified local directory.
    If the directory exists, it is removed first.
    """
    if os.path.exists(local_path):
        shutil.rmtree(local_path, onerror=on_rm_error)
    st.write(f"Cloning repository from {repo_url} into {local_path}...")
    Repo.clone_from(repo_url, local_path)
    st.write("Repository cloned.")

# ----------------------------
# Streamlit UI
# ----------------------------
def app():
    st.title("Interactive Python Code Analyzer")
    st.markdown("Enter the Git repository URL and desired report filenames to run the static analysis (Pylint & Radon).")

    repo_url = st.text_input("Git Repository URL", value="https://github.com/Irfan7587/streamlit")
    csv_filename = st.text_input("CSV Report Filename", value="results.csv")
    html_filename = st.text_input("HTML Report Filename", value="report.html")

    if st.button("Run Analysis"):
        local_path = "temp_repo"
        try:
            with st.spinner("Cloning repository..."):
                clone_repository(repo_url, local_path)
            st.success("Repository cloned!")
        except Exception as e:
            st.error("Error cloning repository: " + str(e))
            return

        with st.spinner("Analyzing Python files..."):
            violations = analyze_python_repo(local_path)
            time.sleep(1)  # simulate processing time
        st.success(f"Analysis completed! Total issues found: {len(violations)}")

        if violations:
            # Display violations in a table
            data = [{"File": v.file, "Line": v.line, "Category": v.category, "Rule": v.rule, "Description": v.description}
                    for v in violations]
            st.dataframe(data)
        else:
            st.info("No violations found.")

        # Generate CSV report and provide download link
        csv_path = generate_csv_report(violations, csv_filename)
        with open(csv_path, "rb") as f:
            st.download_button("Download CSV Report", f.read(), file_name=csv_filename)

        # Generate HTML report and provide download link
        html_path = generate_html_report(violations, html_filename)
        with open(html_path, "r", encoding="utf-8") as f:
            html_content = f.read()
        st.download_button("Download HTML Report", html_content, file_name=html_filename)
        # Optionally, display the HTML report inline
        st.components.v1.html(html_content, height=600, scrolling=True)

        # Clean up the cloned repository
        shutil.rmtree(local_path, onerror=on_rm_error)

# ----------------------------
# Entry Point
# ----------------------------
if __name__ == "__main__":
    # If running via streamlit, this will be invoked.
    app()
