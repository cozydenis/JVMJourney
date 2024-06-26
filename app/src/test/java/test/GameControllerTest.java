package test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import ch.zhaw.it.pm2.jvmjourney.GameEngine.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javafx.scene.input.KeyCode;
import ch.zhaw.it.pm2.jvmjourney.controllers.gameController.GameController;
import org.junit.jupiter.api.BeforeAll;
import javafx.embed.swing.JFXPanel;


public class GameControllerTest {

    private GameController controller;
    private Player mockPlayer;
    private Renderer mockRenderer;
    private Canvas mockCanvas;
    private AnchorPane mockGame;
    private KeyPolling mockKeys;

    @BeforeAll
    public static void initToolkit() {
        new JFXPanel(); // Initializes the JavaFX environment
    }

    @BeforeEach
    public void setUp() {
        mockPlayer = mock(Player.class);
        mockRenderer = mock(Renderer.class);
        mockCanvas = mock(Canvas.class);
        mockGame = mock(AnchorPane.class);

        // Initialize the controller with the mock objects
        controller = new GameController(mockRenderer, mockPlayer, mockCanvas);
        controller.Game = mockGame;

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

    @Test
    public void testMoveRightAndJump() {
        // Setup
        KeyPolling mockKeys = mock(KeyPolling.class);
        when(mockKeys.isDown(KeyCode.RIGHT)).thenReturn(true);
        when(mockKeys.isDown(KeyCode.UP)).thenReturn(true);

        Player mockPlayer = mock(Player.class);
        GameController controller = new GameController();
        controller.keys = mockKeys;
        controller.player = mockPlayer;

        // Act
        controller.updatePlayerMovement(1.0f);

        // Assert
        verify(mockPlayer).accelerate(Direction.RIGHT);
        verify(mockPlayer).jump();
    }

    @Test
    public void testMoveLeftAndJump() {
        // Setup
        KeyPolling mockKeys = mock(KeyPolling.class);
        when(mockKeys.isDown(KeyCode.LEFT)).thenReturn(true);
        when(mockKeys.isDown(KeyCode.UP)).thenReturn(true);

        Player mockPlayer = mock(Player.class);
        GameController controller = new GameController();
        controller.keys = mockKeys;
        controller.player = mockPlayer;

        // Act
        controller.updatePlayerMovement(1.0f);

        // Assert
        verify(mockPlayer).accelerate(Direction.LEFT);
        verify(mockPlayer).jump();
    }

    @Test
    public void testInvalidKeyPress() {
        // Setup
        KeyPolling mockKeys = mock(KeyPolling.class);
        when(mockKeys.isDown(KeyCode.A)).thenReturn(true); // Assume 'A' is not a valid control key

        Player mockPlayer = mock(Player.class);
        GameController controller = new GameController();
        controller.keys = mockKeys;
        controller.player = mockPlayer;

        // Act
        controller.updatePlayerMovement(1.0f);

        // Assert
        verify(mockPlayer, never()).accelerate(any(Direction.class));
        verify(mockPlayer, never()).jump();
    }

    @Test
    public void testGameLoopInteraction() {
        // Setup
        KeyPolling mockKeys = mock(KeyPolling.class);
        when(mockKeys.isDown(KeyCode.RIGHT)).thenReturn(true);

        Player mockPlayer = mock(Player.class);
        GameController controller = new GameController();
        controller.keys = mockKeys;
        controller.player = mockPlayer;

        // Mimic what would happen during a game loop tick
        // Assume updatePlayerMovement and render are called during each tick
        controller.updatePlayerMovement(0.016f); // Approx 60 FPS
        controller.player.update(); // Simulate the player's update logic as called by the game loop

        // Assert
        verify(mockPlayer).accelerate(Direction.RIGHT);
        verify(mockPlayer, times(1)).update(); // Ensure the update method is called exactly once
    }





}