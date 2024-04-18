package ch.zhaw.it.pm2.jvmjourney.GameEngine;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.ImageView;

public class Object {
    protected PositionVector position;
    protected PositionVector currentVelocity;
    float rotation = 0.1f;


    private  Image imageView;

    float scale = 1;

    public Object(int x, int y, String path) {
        loadSprite(path);
        this.position = new PositionVector(x, y);
        this.currentVelocity = Direction.NONE.vector;



    }

    // TODO: Make for animation
   public void loadSprite(String path) {

       imageView = new Image(path);


    }

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

        return imageView;

    }

    public double getWidth() {
        return imageView.getWidth() * scale;
    }

    public double getHeight() {
        return imageView.getHeight() * scale;
    }

    public Point2D getCenter() {
        return new Point2D(position.getX() + getWidth() / 2, position.getY() + getHeight() / 2);
    }

    public double getRotation() {
        return rotation;
    }
}