package ch.zhaw.it.pm2.jvmjourney.GameEngine;

import javafx.scene.image.ImageView;

public class Player extends MovingObject {

    private static final double GRAVITY = 9.8; // Gravity constant
    private static final double JUMP_STRENGTH = 20; // Initial jump strength
    private static final double FRAME_RATE = 1.0 / 30.0; // Assuming 30 FPS for calculations

    private int frameWidth; // Width of each frame
    private int frameHeight; // Height of each frame
    // private ImageView imageView = new ImageView();
    private ImageView walkingSprite;
    private ImageView jumpingSprite;
    private ImageView punchingSprite;
    private boolean isPunching = false;

    private double punchCooldown = 0;
    private double punchCooldownDuration = 0.5;



    public Player(int x, int y, String path, int frameWidth, int frameHeight, int numFrames, float scale) {
        super(x, y, path, scale);
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;

    }

    @Override
    public void loadSprite(String path) {
        super.loadSprite(path);

        walkingSprite = new ImageView(path);
        jumpingSprite = new ImageView("jumping.png");
        punchingSprite = new ImageView("punching.png");

        /*
        // Set initial positions to some default, assuming (x, y) is the bottom center of the sprite
        double spriteBaseLine = y - walkingSprite.getImage().getHeight();
        walkingSprite.setLayoutY(spriteBaseLine);
        jumpingSprite.setLayoutY(spriteBaseLine);
        punchingSprite.setLayoutY(spriteBaseLine); // Adjust this as necessary
        */

        imageView = walkingSprite;


    }

    public void punch() {
        if (punchCooldown <= 0) { // Check if cooldown has elapsed
            //System.out.println("Punching");
            isPunching = true;
            imageView = punchingSprite;
            punchCooldown = punchCooldownDuration; // Reset cooldown
        }
    }

    public void updatePunchCooldown(double secondsSinceLastFrame) {
        if (punchCooldown > 0) {
            punchCooldown -= secondsSinceLastFrame;
            if (punchCooldown <= 0) {
                stopPunch(); // Stop punching if cooldown has expired
            }
        }
    }

    public void stopPunch() {
        isPunching = false;
        if (!inAir) {
            imageView = walkingSprite;

        } else {
            imageView = jumpingSprite; // Ensure we revert back to the jumping sprite if still in air

        }
    }




    @Override
    public void move() {

        // Call move from the superclass.
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
        }else {
            if (!isPunching) {
                imageView = walkingSprite;

            }
        }


    }

    public void jump() {
        if (!inAir) {
            // Set the initial upward velocity for the jump.
            currentVelocity = new PositionVector(currentVelocity.getX(), -JUMP_STRENGTH);
            inAir = true;
            imageView = jumpingSprite; // Change to jumping sprite when starting to jump

        }
    }

    public void land() {
        this.inAir = false;
        imageView = walkingSprite;
        position.setY(GameConfig.GROUNDLEVEL);
        currentVelocity = new PositionVector(currentVelocity.getX(), 0);
        if (!isPunching) {
            imageView = walkingSprite;

        }
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


