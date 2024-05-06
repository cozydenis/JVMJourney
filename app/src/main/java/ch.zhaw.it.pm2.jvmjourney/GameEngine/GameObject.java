package ch.zhaw.it.pm2.jvmjourney.GameEngine;

import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Point2D;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class GameObject {
    protected PositionVector position;

    float rotation = 0.1f;
    float scale;
    ImageView imageView;

    boolean goingRight = false;



    public GameObject(int x, int y, String path, float scale) {
        loadSprite(path);
        this.position = new PositionVector(x, y);
        this.scale = scale;
    }

    public boolean isGoingLeft() {
        return goingRight;
    }

    public Image flipImage() {

            // Convert Image to BufferedImage
            BufferedImage bufferedImage = new BufferedImage((int) imageView.getImage().getWidth(), (int) imageView.getImage().getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics g = bufferedImage.getGraphics();
            g.drawImage(SwingFXUtils.fromFXImage(imageView.getImage(), null), 0, 0, null);
            g.dispose();

            // Perform flip operation
            AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
            tx.translate(-bufferedImage.getWidth(null), 0);
            AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
            bufferedImage = op.filter(bufferedImage, null);

            // Convert BufferedImage back to Image
            Image flippedImage = SwingFXUtils.toFXImage(bufferedImage, null);

            return flippedImage;





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

    public void setPosition(double x, double y) {
        position.setX(x);
        position.setY(y);
    }

    public Point2D getDrawPosition() {
        return new Point2D(position.getX(), position.getY());
    }

    public Image getImage() {
        if(isGoingLeft())
            return flipImage();
        else
            return imageView.getImage();

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

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public void setGoingRight(boolean goingRight) {

        this.goingRight = goingRight;
    }


}

