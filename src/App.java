import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.sql.*;

public class App extends Application {

    Connection conn;

    @Override
    public void start(Stage primaryStage) {
        // UI Elements
        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();

        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();

        Button submitButton = new Button("Submit");
        Label resultLabel = new Label();

        // Submit Button Action
        submitButton.setOnAction(e -> {
            String name = nameField.getText();
            String email = emailField.getText();
            try {
                if (checkEmailExists(email)) {
                    resultLabel.setText("Email already exists!");
                } else {
                    insertUser(name, email);
                    resultLabel.setText("User added successfully.");
                }
            } catch (SQLException ex) {
                resultLabel.setText("Database error: " + ex.getMessage());
                ex.printStackTrace();
            }
        });

        VBox layout = new VBox(10, nameLabel, nameField, emailLabel, emailField, submitButton, resultLabel);
        Scene scene = new Scene(layout, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.setTitle("User Form");
        primaryStage.show();

        // Setup DB
        connectDB();
        createTable();
    }

    public void connectDB() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:users.db");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable() {
        try {
            String sql = """
            CREATE TABLE IF NOT EXISTS users (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL,
                email TEXT NOT NULL UNIQUE
            )
            """;
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean checkEmailExists(String email) throws SQLException {
        String sql = "SELECT COUNT(*) FROM users WHERE email = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, email);
        ResultSet rs = stmt.executeQuery();
        return rs.next() && rs.getInt(1) > 0;
    }

    public void insertUser(String name, String email) throws SQLException {
        String sql = "INSERT INTO users(name, email) VALUES(?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, name);
        stmt.setString(2, email);
        stmt.executeUpdate();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
