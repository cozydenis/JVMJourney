package test;
import ch.zhaw.it.pm2.jvmjourney.GameEngine.*;
import javafx.embed.swing.JFXPanel;
import javafx.scene.image.ImageView;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MovingObjectTest {

    private MovingObject movingObject;


    @BeforeAll
    public static void initToolkit() {
        new JFXPanel(); // Initializes the JavaFX environment
    }
    @BeforeEach
    void setUp() {
        ImageView mockImageView = mock(ImageView.class);
        movingObject = new Player(0, GameConfig.GROUNDLEVEL, "walking.png", 6, 6, 1, 1f);

        movingObject.setImageView(mockImageView);
        movingObject.loadSprite("walking.png");
    }

    @Test
    void testMoveNotInAir() {
        movingObject.setCurrentVelocity(new PositionVector(10, 0));
        movingObject.setInAir(false);
        movingObject.move();

        // Check if friction is applied correctly
        assertTrue(movingObject.getCurrentVelocity().getX() < 10);
        assertTrue(movingObject.getPosition().getX() > 0);

        // More precise assertions can be based on expected behavior
        assertEquals(9.5, movingObject.getCurrentVelocity().getX(), 0.1, "Friction not applied correctly");
        assertEquals(9.0, movingObject.getPosition().getX(), 0.1, "Position not updated correctly");
    }

    @Test
    void testMoveInAir() {
        movingObject.setCurrentVelocity(new PositionVector(0, 0)); // Initially stationary
        movingObject.setInAir(true); // Object is in the air

        movingObject.move();

        // Check if gravity is applied
        assertEquals(Direction.DOWN.vector.getY(), movingObject.getCurrentVelocity().getY(), 0.1);
    }

    @Test
    void testHorizontalBoundaryCheck() {
        movingObject.setCurrentVelocity(new PositionVector(1000, 0)); // Moving right at high speed
        movingObject.move();

        // Check if object respects max X boundary
        assertEquals(GameConfig.getMaxX(), movingObject.getPosition().getX());
    }

    @Test
    void testAccelerationAndMaxSpeedLimit() {
        movingObject.accelerate(Direction.RIGHT);

        // Verify velocity is capped at max speed
        assertEquals(0.5, movingObject.getCurrentVelocity().getX());
        assertEquals(0, movingObject.getCurrentVelocity().getY());
    }


}