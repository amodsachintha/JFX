package sample;

import com.jfoenix.controls.*;
import database.DerbyHandler;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import models.User;
import util.MD5;
import util.UIHelpers;

import java.util.concurrent.TimeUnit;


public class LoginController {

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


    @FXML
    public void exit() {
        System.out.println("Shutting down database!!");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    @FXML
    public void attemptLogin(){
        String username = this.username.getText().trim();
        String password = this.password.getText().trim();
        if (username.equals("")){
            this.username.setStyle("-jfx-unfocus-color: red");
            UIHelpers.showAlert(anchorPane.getScene().getWindow(),"Username Empty!","Please enter username to log in");
            return;
        }
        if (password.equals("")){
            this.password.setStyle("-jfx-unfocus-color: red");
            UIHelpers.showAlert(anchorPane.getScene().getWindow(),"Password Empty!","Please enter the password to log in");
            return;
        }
        User user = DerbyHandler.getInstance().getUser(username,MD5.getHash(password));
        if(user == null){
            UIHelpers.showAlert(anchorPane.getScene().getWindow(),"Invalid Credentials!","Please enter the correct username and password!");
        }
        else {
            System.out.println(user.toString());
            this.username.getScene().getWindow().hide();
        }


    }


}
