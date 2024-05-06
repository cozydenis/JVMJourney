package ch.zhaw.it.pm2.jvmjourney.GameEngine;

/**
 * The GameConfig class holds the configuration values for the game.
 * It includes properties for the minimum x-coordinate, maximum width, and ground level.
 */
public class GameConfig {
    /**
     * The minimum x-coordinate in the game.
     */
    public static final int MIN_X = 0;

    /**
     * The maximum width of the game area. Default value is 750.
     */
    public static int maxWidth = 750;

    /**
     * The y-coordinate representing the ground level in the game.
     */
    public static final int GROUNDLEVEL = 135;

    /**
     * Returns the maximum x-coordinate in the game.
     *
     * @return The maximum x-coordinate.
     */
    public static int getMaxX() {
        return maxWidth;
    }

    /**
     * Sets the maximum width of the game area.
     *
     * @param maxWidth The new maximum width.
     */
    public static void setMaxWidth(int maxWidth) {
        GameConfig.maxWidth = maxWidth - 60;
    }
}