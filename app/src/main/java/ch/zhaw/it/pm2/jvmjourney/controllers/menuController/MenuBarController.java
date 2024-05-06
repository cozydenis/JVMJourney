package ch.zhaw.it.pm2.jvmjourney.controllers.menuController;

import ch.zhaw.it.pm2.jvmjourney.controllers.gameController.GameController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuBarController implements Initializable {

    @FXML
    public MenuItem featureListItem;

    @FXML
    public MenuItem activateLegendItem;

    @FXML
    public MenuItem aboutItem;

    @FXML
    private MenuItem exitMenuItem;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        exitMenuItem.setOnAction(event -> System.exit(0));

        aboutItem.setOnAction(event -> {
            GameController.timer.stop();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("About");
            alert.setHeaderText("JVM Journey");
            alert.setContentText("This is a JavaFX application created by Simon Durrer, Denis Djaferi, Colin Dubuis and Umut Oeztuerk.");
            alert.setOnCloseRequest(event1 -> GameController.timer.start());
            alert.show();
        });

        featureListItem.setOnAction(event -> {
            GameController.timer.stop();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Info");
            alert.setHeaderText("Future feature list");
            alert.setContentText("In the future following features will be made available:\n- Back to main menu\n- Reset game\n- Further levels");
            alert.setOnCloseRequest(event1 -> GameController.timer.start());
            alert.show();
        });
    }
}
