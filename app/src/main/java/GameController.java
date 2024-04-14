import ch.zhaw.it.pm2.jvmjourney.GameEngine.Player;
import ch.zhaw.it.pm2.jvmjourney.GameEngine.PositionVector;
import ch.zhaw.it.pm2.jvmjourney.GameEngine.Direction;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;

public class GameController {

    public ImageView sprite;
    int level = 0;
    Player player;

    public GameController() {
        player = new Player(0,0, "src/main/resources/player.png", 1, 1, 1);
    }

    private void setupKeyboardControls() {
        sprite.getScene().setOnKeyPressed(this::handleKeyPressed);
        sprite.getScene().setOnKeyReleased(this::handleKeyReleased);
    }

    private void handleKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case A:
                player.accelerate(Direction.LEFT);
                player.move();
                break;
            case D:
                player.accelerate(Direction.RIGHT);
                player.move();
                break;
            case SPACE:
                //player.jump();
                player.accelerate(Direction.UP);
                player.move();
                break;
            case S:
                //player.fastFall();
                player.accelerate(Direction.DOWN);
                player.move();
                break;
            default:
                break;
        }
    }

    private void handleKeyReleased(KeyEvent event) {
        if (event.getCode() == KeyCode.SPACE) {
            player.endJump();
        }
    }


}
