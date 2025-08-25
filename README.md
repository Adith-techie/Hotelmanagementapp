# Hotelmanagementapp
A hotel management system desktop app build using java

---

## 🚀 Features
- Hotel search by location & availability
- Room booking and cancellation
- User login & registration
- Room reviews & ratings
- Invoice generation (PDF using iText)
- Booking history & management

---

## 🛠 Requirements
- Java JDK 11 or above
- JavaFX SDK (download from [Gluon](https://gluonhq.com/products/javafx/))
- SQLite JDBC Driver
- IDE (VS Code, IntelliJ, or Eclipse)

---

## ⚙️ Setup Instructions

1. **Clone the repository**
   ```sh
   git clone https://github.com/your-username/hoteleaze.git
   cd hoteleaze
2. Install any java ide
3. create a launch.json file
   ```sh
   {
    "version": "0.2.0",
    "configurations": [
        {
            "type": "java",
            "name": "Launch JavaFX App",
            "request": "launch",
            "mainClass": "App",
            "vmArgs": "--module-path \"C:\\javafx\\lib\" --add-modules javafx.controls,javafx.fxml --enable-native-access=ALL-UNNAMED -cp bin;lib/sqlite-jdbc-3.50.2.0.jar"
        }
    ] }
4. Run the application
