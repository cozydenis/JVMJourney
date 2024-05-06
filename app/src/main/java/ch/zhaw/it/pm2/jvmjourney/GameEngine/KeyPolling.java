package ch.zhaw.it.pm2.jvmjourney.GameEngine;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

import java.util.HashSet;
import java.util.Set;

/**
 * A utility class for polling keyboard input.
 */
public class KeyPolling {

    /** The JavaFX scene to poll for keyboard input. */
    private static Scene scene;
    /** Set of keys currently being pressed. */
    private static final Set<KeyCode> keysCurrentlyDown = new HashSet<>();

    /** Private constructor to prevent instantiation. */
    private KeyPolling() {
    }

    /**
     * Gets the singleton instance of KeyPolling.
     *
     * @return The KeyPolling instance.
     */
    public static KeyPolling getInstance() {
        return new KeyPolling();
    }

    /**
     * Polls the specified scene for keyboard input.
     *
     * @param scene The scene to poll.
     */
    public void pollScene(Scene scene) {
        clearKeys();
        removeCurrentKeyHandlers();
        setScene(scene);
    }

    /** Clears the set of keys currently being pressed. */
    private void clearKeys() {
        keysCurrentlyDown.clear();
    }

    /** Removes the current key event handlers from the scene. */
    private void removeCurrentKeyHandlers() {
        if (scene != null) {
            KeyPolling.scene.setOnKeyPressed(null);
            KeyPolling.scene.setOnKeyReleased(null);
        }
    }

    /**
     * Sets the scene to poll for keyboard input.
     *
     * @param scene The scene to poll.
     */
    private void setScene(Scene scene) {
        KeyPolling.scene = scene;
        KeyPolling.scene.setOnKeyPressed((keyEvent -> {
            keysCurrentlyDown.add(keyEvent.getCode());
        }));
        KeyPolling.scene.setOnKeyReleased((keyEvent -> {
            keysCurrentlyDown.remove(keyEvent.getCode());
        }));
    }

    /**
     * Checks if the specified key is currently being pressed.
     *
     * @param keyCode The KeyCode of the key to check.
     * @return True if the key is currently pressed, false otherwise.
     */
    public boolean isDown(KeyCode keyCode) {
        return keysCurrentlyDown.contains(keyCode);
    }

    /**
     * Returns a string representation of the KeyPolling object.
     *
     * @return A string representation of the KeyPolling object.
     */
    @Override
    public String toString() {
        StringBuilder keysDown = new StringBuilder("KeyPolling on scene (").append(scene).append(")");
        for (KeyCode code : keysCurrentlyDown) {
            keysDown.append(code.getName()).append(" ");
        }
        return keysDown.toString();
    }
}
