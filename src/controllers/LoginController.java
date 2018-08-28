package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import database.DerbyHandler;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import models.User;
import util.MD5;
import util.UIHelpers;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;


public class LoginController {

    private static User userObject;

    @FXML
    JFXTextField username;
    @FXML
    JFXPasswordField password;
    @FXML
    JFXButton login;
    @FXML
    JFXButton exit;
    @FXML
    AnchorPane anchorPane;

    public void initialize() {

    }

    @FXML
    public void exit() {
        try {
            DriverManager.getConnection("jdbc:derby:;shutdown=true");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Shutting down database!!");
        }
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    @FXML
    public void attemptLogin() {
        String username = this.username.getText().trim();
        String password = this.password.getText().trim();
        if (username.equals("")) {
            this.username.setStyle("-jfx-unfocus-color: red");
            UIHelpers.showAlert(anchorPane.getScene().getWindow(), anchorPane, "Username Empty!", "Please enter username to log in");
            return;
        }
        if (password.equals("")) {
            this.password.setStyle("-jfx-unfocus-color: red");
            UIHelpers.showAlert(anchorPane.getScene().getWindow(), anchorPane, "Password Empty!", "Please enter the password to log in");
            return;
        }
        User user = DerbyHandler.getInstance().getUser(username, MD5.getHash(password));
        userObject = user;
        if (user == null) {
            UIHelpers.showAlert(anchorPane.getScene().getWindow(), anchorPane, "Invalid Credentials!", "Please enter the correct username and password!");
        } else {
            System.out.println(user.toString());
            new UIHelpers().getNewWindowAndHide("main.fxml", "Stock Management System: Dickwella Division", anchorPane);
        }

    }

    protected static User getUser() {
        return userObject;
    }


}
