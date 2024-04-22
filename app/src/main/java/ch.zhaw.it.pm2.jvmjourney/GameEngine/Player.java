package ch.zhaw.it.pm2.jvmjourney.GameEngine;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.embed.swing.SwingFXUtils;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class Player extends MovingObject {

    private int frameWidth; // Width of each frame
    private int frameHeight; // Height of each frame
    private int numFrames;
    // private ImageView imageView = new ImageView();
    private ImageView walkingSprite;
    private ImageView jumpingSprite;


    public Player(int x, int y, String path, int frameWidth, int frameHeight, int numFrames) {
        super(x, y, path);
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.numFrames = numFrames;

    }

    @Override
    public void loadSprite(String path) {
        super.loadSprite(path);

        walkingSprite = new ImageView(path);
        jumpingSprite = new ImageView("jumping.png");
        imageView = walkingSprite;

        // due to time limitation the implementation of the sprite is chosen to be limited.
//        Image spriteSheet = new Image(path);
//        imageView.setViewport(new javafx.geometry.Rectangle2D(0, 0, frameWidth, frameHeight));
//        imageView.setImage(spriteSheet);


    }

   @Override
public void flip() {
    // Convert Image to BufferedImage
    BufferedImage bufferedImage = new BufferedImage((int)imageView.getImage().getWidth(), (int)imageView.getImage().getHeight(), BufferedImage.TYPE_INT_ARGB);
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
    imageView.setImage(flippedImage);
}


    @Override
    public void move() {
        super.move();  // Apply current velocity
    }

    public void jump() {
        if (!inAir) {
            accelerate(Direction.UP);  // Only jump if not already in the air
            inAir = true;
            imageView = jumpingSprite;
        }
    }

    public void land() {
        this.inAir = false;
        imageView = walkingSprite;
        this.currentVelocity = Direction.NONE.vector;  // Stop vertical movement when landing
    }


    public void setInAir(boolean inAir) {
        this.inAir = inAir;
    }


}


