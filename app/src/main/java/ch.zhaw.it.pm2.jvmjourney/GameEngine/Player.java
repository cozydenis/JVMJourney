package ch.zhaw.it.pm2.jvmjourney.GameEngine;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Player extends MovingObject {

    private static final double GRAVITY = 9.8; // Gravity constant
    private static final double JUMP_STRENGTH = 20; // Initial jump strength
    private static final double FRAME_RATE = 1.0 / 30.0; // Assuming 30 FPS for calculations

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
    public void move() {
        // Call move from the superclass.
        super.move();

        if (inAir) {
            // Apply gravity effect: v = v + a * t
            double gravityEffect = currentVelocity.getY() + GRAVITY * FRAME_RATE;
            currentVelocity = new PositionVector(currentVelocity.getX(), gravityEffect);
            // Apply the velocity to the position: s = s + v * t
            position.setY(position.getY() + currentVelocity.getY() * FRAME_RATE);

            // Check if the player is below ground level and correct it.
            if (position.getY() > GameConfig.GROUNDLEVEL) {
                land();
            }
        }
    }

    public void jump() {
        if (!inAir) {
            // Set the initial upward velocity for the jump.
            currentVelocity = new PositionVector(currentVelocity.getX(), -JUMP_STRENGTH);
            inAir = true;
        }
    }

    public void land() {
        this.inAir = false;
        imageView = walkingSprite;
        position.setY(GameConfig.GROUNDLEVEL);
        currentVelocity = new PositionVector(currentVelocity.getX(), 0);
    }


    public void setInAir(boolean inAir) {
        this.inAir = inAir;
    }


}


