package ch.zhaw.it.pm2.jvmjourney.GameEngine;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Object {
    protected PositionVector position;
    protected PositionVector currentVelocity;
    private BufferedImage[][] sprite;

    public Object(int x, int y, String path, int rows, int cols) {
        this.position = new PositionVector(x, y);
        this.currentVelocity = Direction.NONE.vector;
        loadSprite(path, rows, cols);
    }

    public void loadSprite(String path, int rows, int cols) {
        try {
            BufferedImage spriteSheet = ImageIO.read(new File(path));
            final int width = spriteSheet.getWidth();
            final int height = spriteSheet.getHeight();
            sprite = new BufferedImage[rows][cols];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    sprite[i][j] = spriteSheet.getSubimage(j * width/cols, i * height/rows, width/cols, height/rows);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public PositionVector getPosition() {
        return position;
    }
}