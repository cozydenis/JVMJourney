import ch.zhaw.it.pm2.jvmjourney.GameEngine.Direction;
import ch.zhaw.it.pm2.jvmjourney.GameEngine.GameConfig;
import ch.zhaw.it.pm2.jvmjourney.GameEngine.GameLoopTimer;
import ch.zhaw.it.pm2.jvmjourney.GameEngine.KeyPolling;
import ch.zhaw.it.pm2.jvmjourney.GameEngine.Player;
import ch.zhaw.it.pm2.jvmjourney.GameEngine.Renderer;
import javafx.scene.canvas.Canvas;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {


    public ImageView sprite;
    int level = 0;
    Player player;
    public Canvas gameCanvas;
    public AnchorPane Game;
    KeyPolling keys = KeyPolling.getInstance();


    public GameController() {
        player = new Player(0, 0, "walking.png", 6, 6, 1);


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initialiseCanvas();


        Renderer renderer = new Renderer(this.gameCanvas);

        // Initialization code that might not depend on the scene being fully set up
        Game.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                newScene.widthProperty().addListener((observable, oldValue, newValue) -> {
                    GameConfig.setMaxWidth(newValue.intValue());

                    // This will trigger a canvas redraw through the Renderer
                    renderer.prepare();
                    renderer.render();
                });
                newScene.heightProperty().addListener((observable, oldValue, newValue) -> {

                    // This will trigger a canvas redraw through the Renderer
                    renderer.prepare();
                    renderer.render();
                });
            }
        });

        player.setPosition(50, GameConfig.GROUNDLEVEL);
        player.setScale(1f);


        renderer.addObject(player);
        GameLoopTimer timer = new GameLoopTimer() {
            @Override
            public void tick(float secondsSinceLastFrame) {
                renderer.prepare();

                updatePlayerMovement(secondsSinceLastFrame);
                player.update();
                player.updatePunchCooldown(secondsSinceLastFrame); // Update the cooldown
                renderer.render();
            }
        };
        timer.start();

    }

    private void initialiseCanvas() {
        gameCanvas.widthProperty().bind(Game.widthProperty());
        gameCanvas.heightProperty().bind(Game.heightProperty());
    }

    private void updatePlayerMovement(float frameDuration) {
        if (keys.isDown(KeyCode.UP)) {

            player.jump();

        }
        if (keys.isDown(KeyCode.RIGHT)) {
            player.accelerate(Direction.RIGHT);

        } else if (keys.isDown(KeyCode.LEFT)) {
            player.accelerate(Direction.LEFT);
        }

        // Handle punching action
        if (keys.isDown(KeyCode.SPACE)) {
            player.punch();
        } else {
            player.stopPunch(); // This resets the sprite to walking if the spacebar is not pressed
        }
    }
}




