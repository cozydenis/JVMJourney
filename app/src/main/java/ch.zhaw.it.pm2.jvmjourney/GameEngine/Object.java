package ch.zhaw.it.pm2.jvmjourney.GameEngine;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Object {
    protected PositionVector position;
    protected PositionVector currentVelocity;
    private BufferedImage[][] sprite;
    float scale = 1;

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

    public Point2D getDrawPosition() {
        return new Point2D(position.getX(), position.getY());
    }

    public Image getImage() {

        return sprite[0][0];
    }

    public double getWidth() {
        return sprite[0][0].getWidth();
    }

    public double getHeight() {
        return sprite[0][0].getHeight();
    }

    public Point2D getCenter() {
    }
}