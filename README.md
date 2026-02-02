# 📂 Smart File Organizer

> **A lightning-fast Java automation tool that declutters your Downloads folder instantly.**

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![IntelliJ IDEA](https://img.shields.io/badge/IntelliJ_IDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)

## 💡 The Problem
We all have that one folder (usually **Downloads**) that is a mess of thousands of mixed files. Sorting them manually takes hours.

## 🚀 The Solution
This tool runs in the background and automatically detects file types, creating specific folders (`PDF`, `Images`, `Documents`, `Zips`, `ExecutableFiles`) and moving files into them.

**Key Features:**
* ✅ **Instant Sorting:** Uses Java Streams to process thousands of files in milliseconds.
* ✅ **Conflict Resolution:** Never overwrites your files. If `resume.pdf` exists, it intelligently renames the new file to `resume (1).pdf`.
* ✅ **Smart Folder Creation:** Automatically creates category folders only if they are needed.
* ✅ **Cross-Platform:** Works seamlessly on Windows, macOS, and Linux.

## 🛠️ How It Works
The program utilizes **Java NIO (New I/O)** for non-blocking file handling and **Java Streams** for efficient list processing.

1.  Scans the user's `Downloads` directory.
2.  Creates a main `FilesDrawer` directory.
3.  Checks file extensions (e.g., `.pdf`, `.jpg`, `.docx`).
4.  Moves files to their respective sub-folders.
5.  If a file name conflict occurs, it appends a numerical index `(n)` to preserve both files.

## 💻 Usage

### Option 1: Run the Executable (Easy)
1.  Download the latest `FileOrganizer.jar` from the [Releases](link-to-your-releases) tab.
2.  Double-click the file (or run via command line).
3.  A popup will confirm when the sorting is complete.

### Option 2: Build from Source
1.  Clone the repository:
    ```bash
    git clone [https://github.com/aryan1723/FileOrganizer.git](https://github.com/aryan1723/FileOrganizer.git)
    ```
2.  Open in **IntelliJ IDEA**.
3.  Set Project SDK to **Java 8** or higher.
4.  Run the `Main` class.

## 🔧 Technical Highlights
* **Language:** Java 8+
* **Libraries:** `java.nio.file`, `java.util.stream`, `javax.swing`
* **Pattern:** Logic separated into modular methods for clean code.

---
*Created by [Aryan Solanki]*
