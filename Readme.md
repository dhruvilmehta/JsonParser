# Java JSON Processor

This project is a Java application that processes JSON files. It takes a JSON file as input and performs operations as defined in the `Main` class. Currently, it is taking the json file as input and creating an intermediate code and eventually I am to convert that intermediate code into any file format (eg: protobuf, yaml, etc)

---

## How to Run

### Steps to Compile and Run

1. **Compile the Code:**  
   Use the following command to compile all `.java` files in the `src` directory. Compiled `.class` files will be stored in the `classes` directory:
   ```bash
   javac -d classes $(find src -name "*.java")
   ```
2. **Run the Application:**

   Execute the Main class with the input JSON file:

   ```bash
   java -cp classes src.Main <input.json>
   ```
    ```bash
   java -cp classes src.Main -i <input.json>

   The -i flag is meant to print the Syntax tree formed at the intermediate level.
   ```
## Example input and the corresponding syntax tree output.
   ![alt text](<Screenshot from 2024-11-17 18-45-08.png>)