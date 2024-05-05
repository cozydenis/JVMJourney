package ch.zhaw.it.pm2.jvmjourney;

import java.io.IOException;

import ch.zhaw.it.pm2.jvmjourney.GameEngine.KeyPolling;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JVMJourney extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        mainWindow(primaryStage);
    }

    private void mainWindow(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/MainWindow.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

           KeyPolling.getInstance().pollScene(scene);

            primaryStage.setTitle("JVM Journey");

            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println("Error from mainWindow: " + e);
        }

    }
}
