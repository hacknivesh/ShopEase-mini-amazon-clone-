package com.ecommerce.ui;

import com.ecommerce.dao.UserDAO;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginGUI extends Application {

    UserDAO dao = new UserDAO();

    public void start(Stage stage) {

        Label title = new Label("🛒 ShopEase");
        title.setStyle("-fx-font-size: 20px; -fx-text-fill: white; -fx-font-weight: bold;");

        TextField userField = new TextField();
        userField.setPromptText("Username");

        PasswordField passField = new PasswordField();
        passField.setPromptText("Password");

        // 🔥 FIX TEXT COLORS
        userField.setStyle("-fx-text-fill: white; -fx-control-inner-background: #2b2b2b;");
        passField.setStyle("-fx-text-fill: white; -fx-control-inner-background: #2b2b2b;");

        Button loginBtn = new Button("Login");
        Button signupBtn = new Button("Signup");

        loginBtn.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        signupBtn.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");

        // LOGIN
        loginBtn.setOnAction(e -> {
            if (dao.login(userField.getText(), passField.getText())) {
                try {
                    new MainGUI().start(new Stage());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                stage.close();
            } else {
                new Alert(Alert.AlertType.ERROR, "Invalid credentials").show();
            }
        });

        // SIGNUP
        signupBtn.setOnAction(e -> {
            if (dao.signup(userField.getText(), passField.getText())) {
                new Alert(Alert.AlertType.INFORMATION, "Signup successful").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Signup failed").show();
            }
        });

        VBox root = new VBox(15,
                title,
                userField,
                passField,
                loginBtn,
                signupBtn
        );

        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #1e1e1e; -fx-padding: 25;");

        stage.setScene(new Scene(root, 350, 300));
        stage.setTitle("ShopEase Login");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}