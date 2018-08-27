package util;

import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

public final class UIHelpers {

    //JFX Alert
    public static void showAlert(Window window, Node blurNode, String heading, String body){
        JFXAlert<String> alert = new JFXAlert<>((Stage) window);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.setOverlayClose(false);

        BoxBlur blur = new BoxBlur(4, 4, 3);

        JFXDialogLayout layout = new JFXDialogLayout();
        layout.setHeading(new Label(heading));
        layout.setBody(new VBox(new Label(body)));

        JFXButton cancelButton = new JFXButton("Continue");
        cancelButton.setCancelButton(true);
        EventHandler eventHandler = new EventHandler() {
            @Override
            public void handle(Event event) {
                blurNode.setEffect(null);
                alert.hideWithAnimation();
            }
        };
        cancelButton.addEventHandler(MouseEvent.MOUSE_CLICKED,eventHandler);
        blurNode.setEffect(blur);
        layout.setActions(cancelButton);
        alert.setContent(layout);
        alert.showAndWait();
    }
}
