package ch.zhaw.it.pm2.jvmjourney.GameEngine;

public class GameConfig {
    public static final int MIN_X = 0;
    public static int maxWidth = 750; // Default value

    public static final int GROUNDLEVEL = 80;

    public static int getMaxX() {
        return maxWidth;
    }

    public static void setMaxWidth(int maxWidth) {
        GameConfig.maxWidth = maxWidth - 60;
    }
}