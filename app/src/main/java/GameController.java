import ch.zhaw.it.pm2.jvmjourney.GameEngine.*;
import ch.zhaw.it.pm2.jvmjourney.GameEngine.Particle;
import javafx.scene.canvas.Canvas;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class GameController implements Initializable {


    public ImageView sprite;
    int level = 0;
    Player player;
    ArrayList <WaterMelon> waterMelon = new ArrayList<>();
    public Canvas gameCanvas;
    public AnchorPane Game;
    KeyPolling keys = KeyPolling.getInstance();


    public GameController() {
        player = new Player(0, 0, "walking.png", 6, 6, 1, 1f);


    }
    public int getRandomIntInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
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

        for (int i = 0; i < 10; i++) {
            waterMelon.add(new WaterMelon(i,200, getRandomIntInRange(0,100), "watermelon1_o.png", 0.15f, Direction.RIGHT, new PositionVector(getRandomIntInRange(0,5),getRandomIntInRange(0,5))));
        }




        player.setPosition(50, GameConfig.GROUNDLEVEL);
        player.setScale(1f);


        renderer.addObject(player);


        for(WaterMelon waterMelon : waterMelon) {
            renderer.addObject(waterMelon);
        }
        GameLoopTimer timer = new GameLoopTimer() {
            @Override
            public void tick(float secondsSinceLastFrame) {
                renderer.prepare();

                updatePlayerMovement(secondsSinceLastFrame);
                player.update();
                player.updatePunchCooldown(secondsSinceLastFrame); // Update the cooldown
                for(WaterMelon waterMelon : waterMelon)
                {waterMelon.update();}
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




