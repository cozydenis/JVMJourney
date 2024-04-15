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
    private Image sprite;
    float rotation = 0.1f;

    float scale = 1;

    public Object(int x, int y, String path, int rows, int cols) {
        this.position = new PositionVector(x, y);
        this.currentVelocity = Direction.NONE.vector;
        //loadSprite(path, rows, cols);
        try {
            sprite = new Image(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // TODO: Make for animation
//    public void loadSprite(String path, int rows, int cols) {
//        try {
//            BufferedImage spriteSheet = ImageIO.read(new File(path));
//            final int width = spriteSheet.getWidth();
//            final int height = spriteSheet.getHeight();
//            sprite = new BufferedImage[rows][cols];
//            for (int i = 0; i < rows; i++) {
//                for (int j = 0; j < cols; j++) {
//                    sprite[i][j] = spriteSheet.getSubimage(j * width/cols, i * height/rows, width/cols, height/rows);
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public void update() {
        position.add(currentVelocity);
    }


    public PositionVector getPosition() {
        return position;
    }

    public void setPosition(int x, int y) {
        position.setX(x);
        position.setY(y);
    }

    public Point2D getDrawPosition() {
        return new Point2D(position.getX(), position.getY());
    }

    public Image getImage() {

        // TODO: Fix the sprite loading
        return sprite;

    }

    public double getWidth() {
        return sprite.getWidth() * scale;
    }

    public double getHeight() {
        return sprite.getHeight() * scale;
    }

    public Point2D getCenter() {
        return new Point2D(position.getX() + getWidth() / 2, position.getY() + getHeight() / 2);
    }

    public double getRotation() {
        return rotation;
    }
}