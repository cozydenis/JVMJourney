package ch.zhaw.it.pm2.jvmjourney.GameEngine;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Player extends MovingObject {

    private  int frameWidth; // Width of each frame
    private  int frameHeight; // Height of each frame
    private  int numFrames;
   // private ImageView imageView = new ImageView();
    private Image walkingSprite;
    private Image jumpingSprite;
    private Image currentSprite;






    public Player(int x, int y , String path, int frameWidth, int frameHeight, int numFrames) {
        super(x,y, path);
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.numFrames = numFrames;

    }

    @Override
    public void loadSprite(String path) {
        super.loadSprite(path);

        walkingSprite = new Image(path);
        jumpingSprite = new Image("jumping.png");
        currentSprite = walkingSprite;

        // due to time limitation the implementation of the sprite is chosen to be limited.
//        Image spriteSheet = new Image(path);
//        imageView.setViewport(new javafx.geometry.Rectangle2D(0, 0, frameWidth, frameHeight));
//        imageView.setImage(spriteSheet);


    }


    @Override
    public void move() {
        super.move();  // Apply current velocity
    }

    public void jump() {
        if (!inAir) {
            accelerate(Direction.UP);  // Only jump if not already in the air
            inAir = true;
            currentSprite = jumpingSprite;
        }
    }

    public void land() {
        this.inAir = false;
        currentSprite = walkingSprite;
        this.currentVelocity = Direction.NONE.vector;  // Stop vertical movement when landing
    }



    public void setInAir(boolean inAir) {
        this.inAir = inAir;
    }

    @Override
    public Image getImage() {

        return currentSprite;

    }

    @Override
    public double getWidth() {
        return currentSprite.getWidth() * scale;
    }

    @Override
    public double getHeight() {
        return currentSprite.getHeight() * scale;
    }

    @Override
    public Point2D getCenter() {
        return new Point2D(position.getX() + getWidth() / 2, position.getY() + getHeight() / 2);
    }

    @Override
    public double getRotation() {
        return rotation;
    }
}

