import ch.zhaw.it.pm2.jvmjourney.GameEngine.Player;
import javafx.scene.image.ImageView;

public class GameController {

    public ImageView sprite;
    int level = 0;

    public GameController() {
        Player player = new Player(0, 0, "app/src/main/resources/Full.png", 6, 6, 1);
        sprite = new ImageView(player.sprite[0][0]);
    }


}
