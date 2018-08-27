package util;

import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

public final class UIHelpers {

    //JFX Alert
    public static void showAlert(Window window, String heading, String body){
        JFXAlert<String> alert = new JFXAlert<>((Stage) window);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.setOverlayClose(false);

        // Create the content of the JFXAlert with JFXDialogLayout
        JFXDialogLayout layout = new JFXDialogLayout();
        layout.setHeading(new Label(heading));
        layout.setBody(new VBox(new Label(body)));

        JFXButton cancelButton = new JFXButton("Continue");
        cancelButton.setCancelButton(true);
        cancelButton.setOnAction(closeEvent -> alert.hideWithAnimation());

        layout.setActions(cancelButton);
        alert.setContent(layout);
        alert.showAndWait();
    }
}
