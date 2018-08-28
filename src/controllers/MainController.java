package controllers;


import database.DerbyHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import models.User;
import util.UIHelpers;

import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;


public class MainController {

    private User user = null;
    @FXML
    VBox mainVbox;
    @FXML
    Label statusLabel;
    @FXML
    Label statusLabel1;

    public void initialize() {
        this.user = LoginController.getUser();
//        statusLabel.setText(this.user.toString());
        try {
            DatabaseMetaData dbmeta = DerbyHandler.getInstance().getConnection().getMetaData();
            statusLabel1.setText(dbmeta.getDatabaseProductName() + " " + dbmeta.getDatabaseProductVersion());
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @FXML
    public void showPrefs() {
        new UIHelpers().getNewWindowWithBlur("preferences.fxml", "Preferences", mainVbox);
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

}
