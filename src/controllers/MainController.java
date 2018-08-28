package controllers;


import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import util.UIHelpers;


public class MainController {

    @FXML
    VBox mainVbox;

    @FXML
    public void showPrefs(){
        new UIHelpers().getNewWindowWithBlur("preferences.fxml","Preferences",mainVbox);
    }

}
