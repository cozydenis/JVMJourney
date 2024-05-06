package ch.zhaw.it.pm2.jvmjourney.controllers.gameController;

import ch.zhaw.it.pm2.jvmjourney.GameEngine.*;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.*;

import static ch.zhaw.it.pm2.jvmjourney.controllers.gameController.LEVEL.*;

public class GameController implements Initializable {
    private LEVEL level = INTRO;
    private final ArrayList<WaterMelon> waterMelon = new ArrayList<>();
    private final Map<WaterMelon, Thread> melonThreads = new HashMap<>();
    private Renderer renderer;
    public static GameLoopTimer timer;
    public Player player;
    public Canvas gameCanvas;
    public AnchorPane Game;
    public KeyPolling keys = KeyPolling.getInstance();
    EnumSet<KeyCode> pressedKeys = EnumSet.noneOf(KeyCode.class);
    private boolean tutorialTriggered = false;
    private float timeSinceLastSpawn = 0;

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

//        for (int i = 0; i < 5; i++) {
//            waterMelon.add(new WaterMelon(200, getRandomIntInRange(0, 100), "watermelon1_o.png", 0.15f, Direction.RIGHT, new PositionVector(getRandomIntInRange(0, 5), getRandomIntInRange(0, 5))));
//        }

        player.setPosition(50, GameConfig.GROUNDLEVEL);
        player.setScale(1f);

        renderer.addObject(player);
        for (WaterMelon waterMelon: waterMelon) {
            renderer.addObject(waterMelon);
        }

        timer = new GameLoopTimer() {
            @Override
            public void tick(float secondsSinceLastFrame) {
                renderer.prepare();

                updatePlayerMovement(secondsSinceLastFrame);
                player.update();
                player.updatePunchCooldown(secondsSinceLastFrame); // Update the cooldown

                timeSinceLastSpawn += secondsSinceLastFrame;
                if (tutorialTriggered && timeSinceLastSpawn >= 1.0) {
                    spawnWaterMelon();
                    timeSinceLastSpawn = 0; // Reset the timer
                }

                for (WaterMelon waterMelon: waterMelon) {
                    waterMelon.update();
                }
                renderer.render();
            }
        };
        timer.start();
        triggerTutorial();
    }

    private void spawnWaterMelon() {
        // Creating a new Watermelon object and adding it to the game
        WaterMelon newMelon = new WaterMelon(getRandomIntInRange(100, 300), GameConfig.GROUNDLEVEL, "watermelon1_o.png", 0.15f, Direction.RIGHT, new PositionVector(getRandomIntInRange(0, 5), getRandomIntInRange(0, 5)));
        waterMelon.add(newMelon);
        renderer.addObject(newMelon);

        // Start a dummy thread that waits indefinitely
        Thread dummyThread = new Thread(() -> {
            try {
                // Thread waits indefinitely until interrupted
                Thread.sleep(Long.MAX_VALUE);
            } catch (InterruptedException e) {
                // Thread interrupted
                System.out.println("Thread for " + newMelon + " was interrupted and is now terminating.");
            }
        });
        dummyThread.start();
        melonThreads.put(newMelon, dummyThread);
    }

    private void triggerTutorial() {
        switch (level) {
            case INTRO:
                showTutorial1();
                break;
            case TUTORIAL2:
                showTutorial2();
                break;
            case TUTORIAL3:
                showTutorial3();
                break;
        }
    }

    private void showTutorial3() {
        Alert alert = getAlert("Tutorial 2/2", """
                Congratulations! You have completed the tutorial.\s
                You can now play the game.\s
                Watermelons will come at you. Each watermelon represents a thread.\s
                Try to destroy them by punching and keep the thread count low!\s
                Have fun!""");
        alert.show();
    }

    private void showTutorial2() {
        Alert alert = getAlert("Tutorial 1/2", """
                In this tutorial you will learn how to move and jump and punch.\s
                Use the arrow keys to move and jump.\s
                Use the space bar to punch.\s
                               \s
                Punching will be used to destroy the watermelons!\s
                Press all arrow keys and space bar to continue to the next tutorial.\s""");
        alert.show();
    }

    private void showTutorial1() {
        Alert alert = getAlert("Welcome to JVM Journey!", """
                This tutorial will guide you through the game.\s

                In the top bar you can see diagnostics about the JVM.\s
                The metrics will change according to actions happening in the game.\s
                               \s
                The bottom bar contains the game panel.\s
                Interactions happening in the game will directly influence the metrics in the top bar.\s
                Try to monitor the metrics while playing the game!\s

                Have fun!""");
        alert.show();
        alert.setOnCloseRequest(event -> {
            level = TUTORIAL2;
            triggerTutorial();
        });
    }

    private static Alert getAlert(String header, String content) {
        GameController.timer.stop();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Tutorial");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.setOnCloseRequest(event1 -> GameController.timer.start());
        return alert;
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
        double playerTop = player.getPosition().getY() - offsetY - player.getHeight() / 2;
        double playerBottom = player.getPosition().getY() + player.getHeight() / 2 + offsetY;

        Iterator<GameObject> iterator = renderer.getEntities().iterator();
        while (iterator.hasNext()) {
            GameObject entity = iterator.next();
            if (entity != player) {
                if (playerLeft < entity.getPosition().getX() + entity.getWidth() &&
                        playerRight > entity.getPosition().getX() &&
                        playerTop < entity.getPosition().getY() + entity.getHeight() &&
                        playerBottom > entity.getPosition().getY()) {
                    //noinspection SuspiciousMethodCalls
                    waterMelon.remove(entity);
                    iterator.remove();
                    Thread threadToInterrupt = melonThreads.get(entity);
                    if (threadToInterrupt != null) {
                        threadToInterrupt.interrupt();
                        melonThreads.remove(entity);
                    }
                }
            }
        }
    }

    public void updatePlayerMovement(float frameDuration) {
        if (!tutorialTriggered && pressedKeys.containsAll(EnumSet.of(KeyCode.UP, KeyCode.LEFT, KeyCode.RIGHT, KeyCode.SPACE))) {
            System.out.println("All arrow keys pressed!");
            level = TUTORIAL3;
            triggerTutorial();
            tutorialTriggered = true;
        }

        if (keys.isDown(KeyCode.UP)) {
            player.jump();
            pressedKeys.add(KeyCode.UP);
        }

        if (keys.isDown(KeyCode.RIGHT)) {
            player.accelerate(Direction.RIGHT);
            pressedKeys.add(KeyCode.RIGHT);

        } else if (keys.isDown(KeyCode.LEFT)) {
            player.accelerate(Direction.LEFT);
            pressedKeys.add(KeyCode.LEFT);
        }

        // Handle punching action
        if (keys.isDown(KeyCode.SPACE)) {
            player.punch();
            detectAndHandleCollisions();
            pressedKeys.add(KeyCode.SPACE);
        } else {
            player.stopPunch(); // This resets the sprite to walking if the spacebar is not pressed
        }
    }


}
