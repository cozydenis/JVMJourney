package ch.zhaw.it.pm2.jvmjourney.controller.engineController;

import ch.zhaw.it.pm2.jvmjourney.GameEngine.*;
import javafx.scene.canvas.Canvas;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.*;

public class GameController implements Initializable {


    public ImageView sprite;
    int level = 0;
    ArrayList<WaterMelon> waterMelon = new ArrayList<>();
    public Player player;
    public Canvas gameCanvas;
    public AnchorPane Game;
    public KeyPolling keys = KeyPolling.getInstance();
    private Renderer renderer;


    public GameController() {
        player = new Player(0, 0, "walking.png", 6, 6, 1, 1f);
    }

    // Dependency Injection via Constructor
    public GameController(Renderer renderer, Player player, Canvas gameCanvas) {
        this.renderer = renderer;
        this.player = player;
        this.gameCanvas = gameCanvas;
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


        renderer = new Renderer(this.gameCanvas);

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

        for (int i = 0; i < 5; i++) {
            waterMelon.add(new WaterMelon(200, getRandomIntInRange(0, 100), "watermelon1_o.png", 0.15f, Direction.RIGHT, new PositionVector(getRandomIntInRange(0, 5), getRandomIntInRange(0, 5))));
        }

        player.setPosition(50, GameConfig.GROUNDLEVEL);
        player.setScale(1f);

        renderer.addObject(player);
        for (WaterMelon waterMelon: waterMelon) {
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

    public void detectAndHandleCollisions() {
        // Define the range around the player's position
        double offsetX = 4; // Example: 10 pixels to the left and right
        double offsetY = 2; // Example: 10 pixels above and below

        // Calculate the boundaries of the hitbox
        double playerLeft = player.getPosition().getX() - offsetX - player.getWidth() / 2;
        double playerRight = player.getPosition().getX() + player.getWidth() / 2 + offsetX;

        Iterator<GameObject> iterator = renderer.getEntities().iterator();
        while (iterator.hasNext()) {
            GameObject entity = iterator.next();
            if (entity != player) {
                if(player.isGoingLeft())
                {
                    if (playerLeft < entity.getPosition().getX() + entity.getWidth() && player.getPosition().getX() > entity.getPosition().getX() ) {
                    //System.out.println("Collision detected");

                    //waterMelon
                    waterMelon.remove(entity);
                    iterator.remove();
                }
                }
                else
                {
                    if(playerRight > entity.getPosition().getX() && player.getPosition().getX() < entity.getPosition().getX())
                    {
                        waterMelon.remove(entity);
                        iterator.remove();
                    }
                }
            }
        }
    }

    public void updatePlayerMovement(float frameDuration) {
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
            detectAndHandleCollisions();
        } else {
            player.stopPunch(); // This resets the sprite to walking if the spacebar is not pressed
        }
    }
}
