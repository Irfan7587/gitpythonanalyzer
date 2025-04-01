# Python Code Static Analyzer

This project is a **Static Code Analyzer** for Python files that leverages **Pylint** and **Radon** to detect coding violations and measure cyclomatic complexity. It allows users to analyze Python projects from a Git repository and generate reports in **CSV** and **HTML** formats.

## 🚀 Features
- **Pylint Analysis:** Detects code quality issues based on PEP 8 guidelines.
- **Cyclomatic Complexity:** Uses Radon to measure function complexity.
- **Git Repository Support:** Clones repositories directly for analysis.
- **Report Generation:** Outputs violations in both **CSV** and **HTML** formats with an interactive chart.
- **Two Modes:** Available as both a Jupyter Notebook (**.ipynb**) and a **Streamlit UI-based .py** application.

---

## 📌 Installation

Ensure you have Python **3.7+** installed, then install the required dependencies:

```bash
pip install -r requirements.txt
```

### Dependencies:
- `pylint`
- `radon`
- `gitpython`
- `streamlit`
- `shutil`, `argparse`, `subprocess` (built-in)

---

## 🔧 Usage

### 1️⃣ **Jupyter Notebook Version (`analyzer.ipynb`)**  
This version runs in **Jupyter Notebook** and allows users to enter inputs via `input()` prompts.

#### **Steps to Run:**
1. Open Jupyter Notebook:
   ```bash
   jupyter notebook
   ```
2. Load `analyzer.ipynb` and run the cells.
3. Enter the GitHub repository URL when prompted.
4. The analysis will run, and reports will be generated.

---

### 2️⃣ **Streamlit UI Version (`app.py`)**  
This version provides an **interactive web-based UI** using **Streamlit**.

#### **Steps to Run:**
1. Start the Streamlit app:
   ```bash
   streamlit run gitanalyzer.py
   ```
2. Enter the **GitHub Repository URL**, specify output filenames, and click **Run Analysis**.
3. View the results directly in the app:
   - Download the **CSV** or **HTML** report.
   - See a summary table of detected violations.

---

## 📄 Example Output
### **CSV Report Sample**
| File         | Line | Category              | Rule            | Description                     |
|-------------|------|----------------------|----------------|--------------------------------|
| `script.py` | 10   | Pylint               | `unused-import` | Unused import found           |
| `utils.py`  | 25   | Cyclomatic Complexity | `Complexity`    | Function has complexity of 12 |

### **HTML Report Preview**
- Includes **Google Charts** visualization of issues.

---

## 🎯 Future Improvements
- Support for additional linters (e.g., Flake8, Bandit).
- Allow custom rule configurations.
- Optimize performance for large repositories.

---

## 💡 Author  
Developed by **Mohammed Irfan Battegeri** - Feel free to contribute or reach out for improvements!

```

---

### **How This README Helps**
- Clearly describes the project and its purpose.
- Provides step-by-step instructions for **both versions**.
- Includes **example output** to help users understand results.
- Gives **future directions** to invite collaboration.

Let me know if you want any modifications! 🚀
