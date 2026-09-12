

https://github.com/user-attachments/assets/bd5de808-1244-4f87-9c85-245ad5b59711

# IronVault

A file archiver with password-based encryption, built in Java.

Pack an entire directory into a single archive file. Unpack it later with the correct password. Simple GUI, no dependencies.

---

## How it works

IronVault reads each file in a directory, writes a fixed-size header (filename + size), then encrypts the file bytes using a repeating XOR cipher keyed to your password. The result is a single binary archive.

Unpacking reads the headers, decrypts each segment using the same password, and restores the original files.

---

## Supported formats

`.txt` `.pdf` `.docx` `.xlsx` `.csv` `.jpg` `.jpeg` `.png` `.mp4` `.java` `.cpp` `.c` `.py` `.json` `.xml` `.html` `.zip`

---

## Usage

**Run from source**
```bash
javac Pack.java Unpack.java IronVaultGUI.java
java IronVaultGUI
```

---

## Interface

| Field | Description |
|---|---|
| Source Dir / Archive File | Directory to pack, or `.vault` file to unpack |
| New Archive Name | Output filename (packing only) |
| Vault Password | Used to encrypt / decrypt |

---

## Security note

IronVault uses a repeating XOR cipher. It is not suitable for securing sensitive or confidential data. It is designed as a lightweight obfuscation and archiving tool.

---

## Author

Aditya Chavan — [github.com/aditya8242](https://github.com/aditya8242)
