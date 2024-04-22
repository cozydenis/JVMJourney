import ch.zhaw.it.pm2.jvmjourney.GameEngine.*;
import ch.zhaw.it.pm2.jvmjourney.GameEngine.Object;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    public static final int MAX_X = 750;  // just an example needs to be adjusted to actual value
    public static final int MIN_X = 0;

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


//

       renderer.addObject(player);
//
        GameLoopTimer timer = new GameLoopTimer() {
            @Override
            public void tick(float secondsSinceLastFrame) {
                renderer.prepare();
//
                updatePlayerMovement(secondsSinceLastFrame);
                player.update();
                renderer.render();
            }
        };
        timer.start();

        //setupKeyboardControls();
    }

    private void initialiseCanvas() {
        gameCanvas.widthProperty().bind(Game.widthProperty());
        gameCanvas.heightProperty().bind(Game.heightProperty());
    }

//    private void setupKeyboardControls() {
//        sprite.getScene().setOnKeyPressed(this::handleKeyPressed);
//        sprite.getScene().setOnKeyReleased(this::handleKeyReleased);
//    }

    private void updatePlayerMovement(float frameDuration) {
        if (keys.isDown(KeyCode.UP)) {
            player.jump();

        } else if (keys.isDown(KeyCode.DOWN)) {
            player.accelerate(Direction.DOWN);
        }
        if (keys.isDown(KeyCode.RIGHT)) {
            player.accelerate(Direction.RIGHT);

        } else if (keys.isDown(KeyCode.LEFT)) {
            player.accelerate(Direction.LEFT);
        }
    }
}

//    private void handleKeyPressed(KeyEvent event) {
//        if (!player.isInAir() || event.getCode() == KeyCode.SPACE) { // Allow horizontal movement always, vertical only if not in air or jumping
//            switch (event.getCode()) {
//                case A:
//                    if (player.getPosition().getX() > GameConfig.MIN_X) {
//                        player.accelerate(Direction.LEFT);
//                    }
//                    break;
//                case D:
//                    if (player.getPosition().getX() < GameConfig.MAX_X) {
//                        player.accelerate(Direction.RIGHT);
//                    }
//                    break;
//                case SPACE:
//                    player.jump();
//                    break;
//                case S:
//                    player.accelerate(Direction.DOWN);
//                    break;
//                default:
//                    break;
//            }
//        }
//    }
//
//    private void handleKeyReleased(KeyEvent event) {
//        if (event.getCode() == KeyCode.SPACE) {
//            player.setInAir(true);  // Player remains in air after jumping until landing logic is triggered
//        }
//    }



