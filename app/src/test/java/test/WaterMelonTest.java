package test;

import ch.zhaw.it.pm2.jvmjourney.GameEngine.*;
import javafx.embed.swing.JFXPanel;
import javafx.scene.image.ImageView;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
class WaterMelonTest {
    private WaterMelon waterMelon;
    private ImageView mockImageView;

    @BeforeAll
    public static void initToolkit() {
        new JFXPanel(); // Initializes the JavaFX environment to handle JavaFX components
    }

    @BeforeEach
    void setUp() {
        mockImageView = mock(ImageView.class);
        waterMelon = spy(new WaterMelon(200, 90, "watermelon1_o.png", 0.15f, Direction.RIGHT, new PositionVector(10, -2)));
        doNothing().when(waterMelon).loadSprite(anyString());
        waterMelon.setImageView(mockImageView);
    }
    @Test
    void testMoveAndBounceOnGround() {
        // Assuming initial Y-velocity is negative as it falls towards the ground
        waterMelon.setCurrentVelocity(new PositionVector(10, -1.5));
        when(waterMelon.hitTheGround()).thenReturn(true);
        waterMelon.move();

        // Verify that the watermelon bounces off the ground, expecting negation of Y-velocity

        assertEquals(1.0, waterMelon.getCurrentVelocity().getY(), "Should bounce on Y-axis");

    }

    @Test
    void testMoveAndBounceOnWall() {
        waterMelon.setPosition(GameConfig.getMaxX() - 5, 100);
        waterMelon.setCurrentVelocity(new PositionVector(10, 0));
        doReturn(true).when(waterMelon).hitTheWall();
        waterMelon.move();

        // Verify that the watermelon bounces off the wall
        assertTrue(waterMelon.getCurrentVelocity().getX() < 0, "Should bounce on X-axis");
    }

    @Test
    void testMoveWithNoBounce() {
        waterMelon.setPosition(200, 90);
        waterMelon.setCurrentVelocity(new PositionVector(5, -1));
        doReturn(false).when(waterMelon).hitTheGround();
        doReturn(false).when(waterMelon).hitTheWall();
        waterMelon.move();

        // Verify normal movement without bouncing
        assertEquals(5, waterMelon.getCurrentVelocity().getX(), "X velocity should remain constant");

        assertEquals(-0.5,waterMelon.getCurrentVelocity().getY(), "Y velocity should decrease to 0 due to gravity");

    }
}
