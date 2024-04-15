import ch.zhaw.it.pm2.jvmjourney.GameEngine.*;
import ch.zhaw.it.pm2.jvmjourney.GameEngine.Object;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;

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
        player = new Player(0, 0, "app/src/main/resources/Basic_human_drawing.png", 1, 1, 1);
        sprite = new ImageView(player.getImage());
        //setupKeyboardControls();
    }

      @Override
   public void initialize(URL location, ResourceBundle resources) {
//
        initialiseCanvas();
//
        player.setPosition(350, 200);
//        //player.setScale(0.5f);
//
       Renderer renderer = new Renderer(this.gameCanvas);
       renderer.addObject(player);
//
        GameLoopTimer timer = new GameLoopTimer() {
            @Override
            public void tick(float secondsSinceLastFrame) {
                renderer.prepare();
//
//                //updatePlayerMovement(secondsSinceLastFrame);
//
                renderer.render();
            }
        };
        timer.start();
    }

    private void initialiseCanvas() {
        gameCanvas.widthProperty().bind(Game.widthProperty());
        gameCanvas.heightProperty().bind(Game.heightProperty());
    }

    private void setupKeyboardControls() {
        sprite.getScene().setOnKeyPressed(this::handleKeyPressed);
        sprite.getScene().setOnKeyReleased(this::handleKeyReleased);
    }

    private void handleKeyPressed(KeyEvent event) {
        if (!player.isInAir() || event.getCode() == KeyCode.SPACE) { // Allow horizontal movement always, vertical only if not in air or jumping
            switch (event.getCode()) {
                case A:
                    if (player.getPosition().getX() > GameConfig.MIN_X) {
                        player.accelerate(Direction.LEFT);
                    }
                    break;
                case D:
                    if (player.getPosition().getX() < GameConfig.MAX_X) {
                        player.accelerate(Direction.RIGHT);
                    }
                    break;
                case SPACE:
                    player.jump();
                    break;
                case S:
                    player.accelerate(Direction.DOWN);
                    break;
                default:
                    break;
            }
        }
    }

    private void handleKeyReleased(KeyEvent event) {
        if (event.getCode() == KeyCode.SPACE) {
            player.setInAir(true);  // Player remains in air after jumping until landing logic is triggered
        }
    }


}
