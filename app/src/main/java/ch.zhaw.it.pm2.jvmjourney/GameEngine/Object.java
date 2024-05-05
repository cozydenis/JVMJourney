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

    float rotation = 0.1f;
    float scale;


    ImageView imageView;



    public Object(int x, int y, String path, float scale) {
        loadSprite(path);
        this.position = new PositionVector(x, y);
        this.scale = scale;



    }

    // TODO: Make for animation
   public void loadSprite(String path) {

       imageView = new ImageView(path);


    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public void update() {

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

    public ImageView getImage() {

        return imageView;

    }

    public double getWidth() {
        return imageView.getImage().getWidth() * scale;
    }

    public double getHeight() {
        return imageView.getImage().getHeight() * scale;
    }

    public Point2D getCenter() {
        return new Point2D(position.getX() + getWidth() / 2, position.getY() + getHeight() / 2);
    }

    public double getRotation() {
        return rotation;
    }

    public javafx.geometry.Bounds getBounds() {
        return imageView.getBoundsInParent();
    }

}

