package ch.zhaw.it.pm2.jvmjourney.GameEngine;

import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

/**
 * Represents a game object within the game world.
 */
public class GameObject {

    /** The position of the game object. */
    protected PositionVector position;

    /** The rotation angle of the game object. */
    float rotation = 0.1f;
    /** The scale factor of the game object. */
    float scale;
    /** The image view representing the game object. */
    ImageView imageView;
    /** Indicates the direction of movement. */
    boolean goingRight = false;
    /** The thread associated with the game object. */
    protected Thread thread;

    /**
     * Constructs a game object with the specified position, image path, and scale.
     *
     * @param x     The x-coordinate of the game object.
     * @param y     The y-coordinate of the game object.
     * @param path  The path to the image file representing the game object.
     * @param scale The scale factor of the game object.
     */
    public GameObject(int x, int y, String path, float scale) {
        loadSprite(path);
        this.position = new PositionVector(x, y);
        this.scale = scale;
    }

    /**
     * Checks if the game object is moving to the right.
     *
     * @return True if the game object is moving to the right, false otherwise.
     */
    public boolean isGoingRight() {
        return goingRight;
    }

    /**
     * Flips the image horizontally.
     *
     * @return The flipped image.
     */
    public Image flipImage() {
        BufferedImage bufferedImage = new BufferedImage((int) imageView.getImage().getWidth(), (int) imageView.getImage().getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics g = bufferedImage.getGraphics();
        g.drawImage(SwingFXUtils.fromFXImage(imageView.getImage(), null), 0, 0, null);
        g.dispose();

        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-bufferedImage.getWidth(null), 0);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        bufferedImage = op.filter(bufferedImage, null);

        return SwingFXUtils.toFXImage(bufferedImage, null);
    }

    /**
     * Handles the event when the game object is hit.
     *
     * @param renderer The renderer associated with the game object.
     */
    public void getHit(Renderer renderer) {
        thread.interrupt();
        System.out.println(thread.getName());
        renderer.removeObject(this);
        ParticlesController.explosion((int) position.getX(), (int) position.getY(), 10, renderer);
    }

    // TODO: Make for animation

    /**
     * Loads the sprite image of the game object from the specified path.
     *
     * @param path The path to the image file.
     */
    public void loadSprite(String path) {
        imageView = new ImageView(path);
    }

    /**
     * Sets the scale factor of the game object.
     *
     * @param scale The scale factor.
     */
    public void setScale(float scale) {
        this.scale = scale;
    }

    /**
     * Updates the game object.
     */
    public void update() {
        // Implement update logic here
    }

    /**
     * Gets the position vector of the game object.
     *
     * @return The position vector.
     */
    public PositionVector getPosition() {
        return position;
    }

    /**
     * Sets the position of the game object.
     *
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     */
    public void setPosition(double x, double y) {
        position.setX(x);
        position.setY(y);
    }

    /**
     * Gets the drawing position of the game object.
     *
     * @return The drawing position.
     */
    public Point2D getDrawPosition() {
        return new Point2D(position.getX(), position.getY());
    }

    /**
     * Gets the image of the game object.
     *
     * @return The image.
     */
    public Image getImage() {
        if (isGoingRight())
            return flipImage();
        else
            return imageView.getImage();
    }

    /**
     * Gets the width of the game object.
     *
     * @return The width.
     */
    public double getWidth() {
        return imageView.getImage().getWidth() * scale;
    }

    /**
     * Gets the height of the game object.
     *
     * @return The height.
     */
    public double getHeight() {
        return imageView.getImage().getHeight() * scale;
    }

    /**
     * Gets the center position of the game object.
     *
     * @return The center position.
     */
    public Point2D getCenter() {
        return new Point2D(position.getX() + getWidth() / 2, position.getY() + getHeight() / 2);
    }

    /**
     * Gets the rotation angle of the game object.
     *
     * @return The rotation angle.
     */
    public double getRotation() {
        return rotation;
    }

    /**
     * Sets the image view of the game object.
     *
     * @param imageView The image view.
     */
    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }
}
