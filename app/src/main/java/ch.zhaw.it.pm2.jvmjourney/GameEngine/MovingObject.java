package ch.zhaw.it.pm2.jvmjourney.GameEngine;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MovingObject extends Object {



    protected boolean inAir;
    private boolean goingRight;

    private int speed;

    public MovingObject(int x, int y, String path) {
        super(x, y, path);


    }

    public void loadSprite(String path) {
        super.loadSprite(path);


    }


    public void move() {
        int newX = position.getX() + currentVelocity.getX();
        int newY = position.getY() + currentVelocity.getY();

        if (currentVelocity.getX() > 0) {
            if (!goingRight) {
                goingRight = true;
                flip();
            }

        } else if (currentVelocity.getX() < 0) {
            if (goingRight) {
               goingRight = false;
                flip();
            }
        }



        // Check boundaries for X
        if (newX < GameConfig.MIN_X) {
            newX = GameConfig.MIN_X;  // Prevent moving left beyond the left boundary
        } else if (newX > GameConfig.MAX_X) {
            newX = GameConfig.MAX_X;  // Prevent moving right beyond the right boundary
        }

        position = new PositionVector(newX, newY);
        if (inAir) {
            accelerate(Direction.DOWN);  // Apply gravity if in the air
        }
    }

    public boolean isInAir() {
        return inAir;
    }


    public void accelerate(Direction acceleration) {
        if (acceleration == null) {
            throw new IllegalArgumentException("Illegal acceleration: acceleration cannot be null");
        }
        super.currentVelocity = currentVelocity.add(acceleration.vector);
    }

    public void flip(){
        System.out.println("flip");
        if (imageView.getScaleX() == 1) {
            imageView.setScaleX(-1); // flip to left
        } else {
            imageView.setScaleX(1); // flip to right
        }
    }

    public void update() {
        move();
    }






}
