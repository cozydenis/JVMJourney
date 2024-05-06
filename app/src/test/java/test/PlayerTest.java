package test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import ch.zhaw.it.pm2.jvmjourney.GameEngine.Player;
import ch.zhaw.it.pm2.jvmjourney.GameEngine.PositionVector;
import ch.zhaw.it.pm2.jvmjourney.GameEngine.Direction;
import ch.zhaw.it.pm2.jvmjourney.GameEngine.GameConfig;
import javafx.embed.swing.JFXPanel;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class PlayerTest {

    private Player player;
    private ImageView mockImageView;
    private Image mockImage;

    @BeforeAll
    public static void initToolkit() {
        new JFXPanel(); // Initializes the JavaFX environment
    }

    @BeforeEach
    public void setUp() {
        mockImageView = mock(ImageView.class);
        mockImage = mock(Image.class);
        when(mockImageView.getImage()).thenReturn(mockImage);
        player = new Player(0, GameConfig.GROUNDLEVEL, "walking.png", 6, 6, 1, 1f);
        player.setPunchingSprite(mockImageView);
        player.setImageView(mockImageView);
        player.loadSprite("walking.png");
    }

    @AfterEach
    public void tearDown() {
        Mockito.reset(mockImageView);
    }

    @Test
    public void testJump() {
        assertFalse(player.isInAir());
        player.jump();
        assertTrue(player.isInAir());
    }

    @Test
    public void testMoveRight() {
        player.accelerate(Direction.RIGHT);
        PositionVector expectedVelocity = new PositionVector(0.5, 0); // Assuming acceleration factor in Player is 0.5
        assertEquals(expectedVelocity.getX(), player.getCurrentVelocity().getX());
        assertTrue(player.getCurrentVelocity().getX() > 0);
    }

    @Test
    public void testPunch() {
        assertFalse(player.isPunching());
        player.punch();
        assertTrue(player.isPunching());
        // Now check properties instead of direct object comparison
        ImageView actualImageView = player.getPunchingSprite();
        assertNotNull(actualImageView);

    }

    @Test
    public void testLand() {
        player.jump(); // Make the player jump
        assertTrue(player.isInAir());
        player.land();
        assertFalse(player.isInAir());
        assertEquals(GameConfig.GROUNDLEVEL, player.getPosition().getY());
    }

    @Test
    public void testStopPunch() {
        player.punch();
        assertTrue(player.isPunching());
        player.stopPunch();
        assertFalse(player.isPunching());
        //Mockito.reset(mockImageView);
    }

    @Test
    public void testUpdatePunchCooldown() {
        player.setPunchCooldown(1);
        player.updatePunchCooldown(0.5); // update half second
        assertEquals(0.5, player.getPunchCooldown());
        player.updatePunchCooldown(0.5); // another half second
        assertEquals(0, player.getPunchCooldown());
        assertFalse(player.isPunching());
    }

    @Test
    public void testMoveWithNoVerticalMovementWhenOnGround() {
        player.getPosition().setY(GameConfig.GROUNDLEVEL);
        player.move();
        assertEquals(GameConfig.GROUNDLEVEL, player.getPosition().getY());
    }


    @Test
    public void testImageFlipWhenChangingDirection() {
        // Setup the initial state where the player is facing right.
        player.setGoingRight(true); // initially facing right
        player.loadSprite("walking.png"); // Load initial sprite
        Image initialImage = player.getImage(); // Capture the initial image

        // Change direction to left
        player.setGoingRight(false);
        player.loadSprite("walking.png"); // This should reload or flip the sprite based on new direction
        Image flippedImage = player.getImage(); // Capture the image after direction change


        assertNotSame(initialImage, flippedImage, "The image should change when direction changes.");
    }





}
