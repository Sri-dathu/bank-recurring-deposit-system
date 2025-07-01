# Bank RD Java App

A desktop GUI application in Java for managing bank recurring deposits.

## Features

✅ Customer Details:
- Name, DOB, Age, Gender
- Contact info (phone, email, address)

✅ Deposit Management:
- Monthly deposit amount
- Tenure in months
- Interest rate
- Calculates maturity amount

✅ GUI:
- Built with Java Swing
- Input forms for customer and deposit details
- Table view of encapsulated deposit details
- Ability to delete or insert new records from the table

✅ File Handling:
- Saves deposit details to CSV file

---

## How to Run

**Compile:**

```bash
javac -d out src/bank/*.java


Linux/Mac-Os:
javac -cp "lib/*:." -d out src/bank/*.java
java -cp "lib/*:out" bank.App

Windows:
javac -cp "lib/*;." -d out src/bank/*.java
java -cp "lib/*;out" bank.App




