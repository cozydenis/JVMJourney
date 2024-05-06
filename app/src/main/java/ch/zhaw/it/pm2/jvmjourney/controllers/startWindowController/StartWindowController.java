package ch.zhaw.it.pm2.jvmjourney.controllers.startWindowController;

import ch.zhaw.it.pm2.jvmjourney.GameEngine.KeyPolling;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class StartWindowController {

    @FXML
    private Button startButton;

    @FXML
    private Button exitButton;

    @FXML
    private void handleStart() throws Exception {
        Stage stage = (Stage) startButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/MainWindow.fxml"));
        Scene scene = new Scene(loader.load());

        KeyPolling.getInstance().pollScene(scene);
        stage.setScene(scene);
    }

    @FXML
    private void handleExit() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
        System.exit(0);
    }
}
