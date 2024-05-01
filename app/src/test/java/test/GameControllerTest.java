package test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import ch.zhaw.it.pm2.jvmjourney.GameEngine.Direction;
import ch.zhaw.it.pm2.jvmjourney.GameEngine.KeyPolling;
import ch.zhaw.it.pm2.jvmjourney.GameEngine.PositionVector;
import org.junit.jupiter.api.Test;
import javafx.scene.input.KeyCode;
import ch.zhaw.it.pm2.jvmjourney.GameEngine.Player;
import ch.zhaw.it.pm2.jvmjourney.controller.engineController.GameController;
import org.junit.jupiter.api.BeforeAll;
import javafx.embed.swing.JFXPanel;

public class GameControllerTest {

    @BeforeAll
    public static void initToolkit() {
        new JFXPanel(); // Initializes the JavaFX environment
    }

    // Test method for moving left
    @Test
    public void testMoveLeft() {
        // Setup
        KeyPolling mockKeys = mock(KeyPolling.class);
        when(mockKeys.isDown(KeyCode.LEFT)).thenReturn(true);
        when(mockKeys.isDown(KeyCode.RIGHT)).thenReturn(false);

        Player mockPlayer = mock(Player.class);
        when(mockPlayer.getPosition()).thenReturn(new PositionVector(50.0, 135.0));

        GameController controller = new GameController();
        controller.keys = mockKeys;
        controller.player = mockPlayer;

        // Act
        controller.updatePlayerMovement(1.0f);

        // Assert
        verify(mockPlayer).accelerate(Direction.LEFT);
    }

    // Test method for moving right
    @Test
    public void testMoveRight() {
        // Setup
        KeyPolling mockKeys = mock(KeyPolling.class);
        when(mockKeys.isDown(KeyCode.RIGHT)).thenReturn(true);
        when(mockKeys.isDown(KeyCode.LEFT)).thenReturn(false);

        Player mockPlayer = mock(Player.class);

        GameController controller = new GameController();
        controller.keys = mockKeys;
        controller.player = mockPlayer;

        // Act
        controller.updatePlayerMovement(1.0f);

        // Assert
        verify(mockPlayer).accelerate(Direction.RIGHT);
    }

    // Test method for jumping
    @Test
    public void testJump() {
        // Setup
        KeyPolling mockKeys = mock(KeyPolling.class);
        when(mockKeys.isDown(KeyCode.UP)).thenReturn(true);

        Player mockPlayer = mock(Player.class);

        GameController controller = new GameController();
        controller.keys = mockKeys;
        controller.player = mockPlayer;

        // Act
        controller.updatePlayerMovement(1.0f);

        // Assert
        verify(mockPlayer).jump();
    }

    // Test method for no movement
    @Test
    public void testNoMovement() {
        // Setup
        KeyPolling mockKeys = mock(KeyPolling.class);
        when(mockKeys.isDown(KeyCode.LEFT)).thenReturn(false);
        when(mockKeys.isDown(KeyCode.RIGHT)).thenReturn(false);
        when(mockKeys.isDown(KeyCode.UP)).thenReturn(false);

        Player mockPlayer = mock(Player.class);
        when(mockPlayer.getPosition()).thenReturn(new PositionVector(50.0, 135.0)); // Assuming initial position

        GameController controller = new GameController();
        controller.keys = mockKeys;
        controller.player = mockPlayer;

        // Act
        controller.updatePlayerMovement(1.0f);

        // Assert
        verify(mockPlayer, never()).accelerate(any(Direction.class));
        verify(mockPlayer, never()).jump();
        assertEquals(new PositionVector(50.0, 135.0), mockPlayer.getPosition()); // Verify position remains unchanged
    }
}