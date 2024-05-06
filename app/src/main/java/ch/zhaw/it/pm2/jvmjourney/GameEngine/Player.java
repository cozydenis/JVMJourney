package ch.zhaw.it.pm2.jvmjourney.GameEngine;

import javafx.scene.image.ImageView;

/**
 * Represents the player object within the game world, extending the MovingObject class.
 */
public class Player extends MovingObject {

    private static final double GRAVITY = 9.8; // Gravity constant
    private static final double JUMP_STRENGTH = 20; // Initial jump strength
    private static final double FRAME_RATE = 1.0 / 30.0; // Assuming 30 FPS for calculations

    private int frameWidth; // Width of each frame
    private int frameHeight; // Height of each frame
    private ImageView walkingSprite;
    private ImageView jumpingSprite;
    private ImageView punchingSprite;
    private boolean isPunching = false;

    private double punchCooldown = 0;
    private double punchCooldownDuration = 0.5;

    /**
     * Constructs a Player object with the specified position, image path, frame dimensions, and scale.
     *
     * @param x          The initial x-coordinate of the player.
     * @param y          The initial y-coordinate of the player.
     * @param path       The path to the sprite image of the player.
     * @param frameWidth The width of each frame in the sprite sheet.
     * @param frameHeight The height of each frame in the sprite sheet.
     * @param numFrames  The number of frames in the sprite sheet.
     * @param scale      The scale factor of the player object.
     */
    public Player(int x, int y, String path, int frameWidth, int frameHeight, int numFrames, float scale) {
        super(x, y, path, scale);
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
    }

    /**
     * Loads the sprite images for walking, jumping, and punching animations.
     *
     * @param path The path to the sprite sheet.
     */
    @Override
    public void loadSprite(String path) {
        super.loadSprite(path);

        walkingSprite = new ImageView(path);
        jumpingSprite = new ImageView("jumping.png");
        punchingSprite = new ImageView("punching.png");

        imageView = walkingSprite; // Set initial image view to walking sprite
    }

    /**
     * Initiates a punching action if the punch cooldown has elapsed.
     */
    public void punch() {
        if (punchCooldown <= 0) {
            isPunching = true;
            imageView = punchingSprite;
            punchCooldown = punchCooldownDuration;
        }
    }

    /**
     * Updates the punch cooldown timer.
     *
     * @param secondsSinceLastFrame The time elapsed since the last frame, in seconds.
     */
    public void updatePunchCooldown(double secondsSinceLastFrame) {
        if (punchCooldown > 0) {
            punchCooldown -= secondsSinceLastFrame;
            if (punchCooldown <= 0) {
                stopPunch();
            }
        }
    }

    /**
     * Stops the punching action.
     */
    public void stopPunch() {
        isPunching = false;
        if (!inAir) {
            imageView = walkingSprite;
        } else {
            imageView = jumpingSprite;
        }
    }

    /**
     * Moves the player object within the game world.
     */
    @Override
    public void move() {
        super.move();

        if (inAir) {
            imageView = isPunching ? punchingSprite : jumpingSprite;
            double gravityEffect = currentVelocity.getY() + GRAVITY * FRAME_RATE;
            currentVelocity = new PositionVector(currentVelocity.getX(), gravityEffect);
            position.setY(position.getY() + currentVelocity.getY() * FRAME_RATE);
            if (position.getY() > GameConfig.GROUNDLEVEL) {
                imageView = walkingSprite;
                land();
            }
        } else {
            if (!isPunching) {
                imageView = walkingSprite;
            }
        }
    }

    /**
     * Initiates a jump action if the player is not already in the air.
     */
    public void jump() {
        if (!inAir) {
            currentVelocity = new PositionVector(currentVelocity.getX(), -JUMP_STRENGTH);
            inAir = true;
            imageView = jumpingSprite;
        }
    }

    /**
     * Handles landing after a jump.
     */
    public void land() {
        this.inAir = false;
        imageView = walkingSprite;
        position.setY(GameConfig.GROUNDLEVEL);
        currentVelocity = new PositionVector(currentVelocity.getX(), 0);
        if (!isPunching) {
            imageView = walkingSprite;
        }
    }

    // Getters and setters
    public void setInAir(boolean inAir) {
        this.inAir = inAir;
    }

    public boolean isPunching() {
        return isPunching;
    }

    public ImageView getPunchingSprite() {
        return punchingSprite;
    }

    public void setPunchCooldown(double punchCooldown) {
        this.punchCooldown = punchCooldown;
    }

    public double getPunchCooldown() {
        return punchCooldown;
    }

    public void setPunchingSprite(ImageView view) {
        this.punchingSprite = view;
    }
}
