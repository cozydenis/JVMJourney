import ch.zhaw.it.pm2.jvmjourney.GameEngine.Player;
import ch.zhaw.it.pm2.jvmjourney.GameEngine.PositionVector;
import ch.zhaw.it.pm2.jvmjourney.GameEngine.Direction;
import ch.zhaw.it.pm2.jvmjourney.GameEngine.Object;
import ch.zhaw.it.pm2.jvmjourney.GameEngine.MovingObject;
import ch.zhaw.it.pm2.jvmjourney.GameEngine.GameConfig;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;

public class GameController {

    public static final int MAX_X = 750;  // just an example needs to be adjusted to actual value
    public static final int MIN_X = 0;

    public ImageView sprite;
    int level = 0;
    Player player;


    public GameController() {
        player = new Player(0,0, "src/main/resources/player.png", 1, 1, 1);
        setupKeyboardControls();
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
