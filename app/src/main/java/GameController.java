import ch.zhaw.it.pm2.jvmjourney.GameEngine.Player;
import javafx.scene.image.ImageView;

public class GameController {

    public ImageView sprite;
    int level = 0;

    public GameController() {
        Player player = new Player(0, 0, "src/main/resources/player.png", 1, 1, 1);
    }


}
