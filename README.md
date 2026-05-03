# Caesar Cipher Decryption Tool 

Simple console-based Java tool that **automatically decrypts Caesar cipher text files** where **each paragraph uses a different unknown key**.

The program:
- Brute-forces all possible shifts over a custom alphabet  
- Detects the correct key using common English words  
- Writes the decrypted paragraphs and their keys to a new output file  

---

## Features

- Menu-driven interface:
  - **Decrypt a file**
  - **Display a decrypted file**
- Supports multiple encrypted input files:
  - `file-e-1.txt`
  - `file-e-2.txt`
  - `file-e-3.txt`
  - `file-e-4.txt`
- Each encrypted file is converted to:
  - `file-d-1.txt`
  - `file-d-2.txt`
  - `file-d-3.txt`
  - `file-d-4.txt`
- Custom alphabet:

  `abcdefghijklmnopqrstuvwxyz0123456789,./?!`

- Per-paragraph key discovery:
  - Brute-force all keys `0..ALPHABET.length()-1`
  - Check for common English words in the decrypted text
  - Once a likely key is found, store:
    - `Key = <key>`
    - Decrypted paragraph text

---

## How It Works

1. **Menu loop**  
   - `1` → Decrypt a file  
   - `2` → Display a decrypted file  

2. **Decrypt a file**
   - User chooses one of the encrypted files (`file-e-#.txt`)
   - Program:
     - Reads all lines into an `ArrayList<String>` (each treated as a paragraph)
     - For each paragraph:
       - Tries all possible keys
       - Decrypts using:

         ```java
         int newIndex = (index - key + ALPHABET.length()) % ALPHABET.length();
         ```

       - Checks if the result contains common words like `the`, `and`, `that`, etc.
       - When a match is found, that key is assumed correct
       - Writes:

         ```text
         Key = <key>
         <decrypted paragraph>
         ```

         into the corresponding `file-d-#.txt`

3. **Display a decrypted file**
   - User chooses one of the `file-d-#.txt` files
   - If the file exists, the program prints the full decrypted content to the console
   - If it doesn’t exist yet, it shows a simple error message

---

## Requirements

- Java 8+ (or any reasonably recent JDK)
- The encrypted input files:
  - `file-e-1.txt`
  - `file-e-2.txt`
  - `file-e-3.txt`
  - `file-e-4.txt`
- All files (source + input) in the **same working directory** when running

---

## Running the Project

1. **Compile**

   ```bash
   javac ceasarcipher/CeasarCipher.java
