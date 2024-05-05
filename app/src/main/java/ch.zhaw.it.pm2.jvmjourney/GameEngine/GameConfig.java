package ch.zhaw.it.pm2.jvmjourney.GameEngine;

public class GameConfig {
    public static final int MIN_X = 0;
    private static final int DEFAULT_MIN_X = 0;
    private static final int DEFAULT_MAX_WIDTH = 750;

    private static int minX = DEFAULT_MIN_X;
    private static int maxWidth = DEFAULT_MAX_WIDTH; // Default value

    public static final int GROUNDLEVEL = 135;

    public static int getMaxX() {
        return maxWidth;
    }

    public static void setMaxWidth(int width) {
        GameConfig.maxWidth = width;
    }

    public static int getMinX() {
        return minX;
    }

    public static void setMinX(int minXValue) {
        GameConfig.minX = minXValue;
    }

    public static void setMaxX(int i) {

    }
}
