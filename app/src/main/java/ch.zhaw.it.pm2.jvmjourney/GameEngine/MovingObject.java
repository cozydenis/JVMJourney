package ch.zhaw.it.pm2.jvmjourney.GameEngine;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MovingObject extends Object {

    private double friction = 0.95; // Represents frictional force, 5% reduction per frame
    private double maxSpeed = 10; // Maximum speed in any direction
    private double accelerationFactor = 0.5; // The rate of acceleration

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
        // If on the ground, apply friction to horizontal movement
        if (!inAir) {
            double horizontalVelocity = currentVelocity.getX() * friction;

            // Set horizontal velocity to zero if it's less than the threshold
            if (Math.abs(horizontalVelocity) < 0.4) {
                horizontalVelocity = 0;
            }

            currentVelocity = new PositionVector(horizontalVelocity, currentVelocity.getY());
        }

        double newX = position.getX() + currentVelocity.getX();
        double newY = position.getY() + currentVelocity.getY();


        // Apply the current velocity to the position (s = s + v * t)
        position.add(currentVelocity);

        if (currentVelocity.getX() < 0) {
            if (!goingRight) {
                goingRight = true;
                flip();
            }

        } else if (currentVelocity.getX() > 0) {
            if (goingRight) {
                goingRight = false;
                flip();
            }
        }
        int maxX = GameConfig.getMaxX();
        // Check boundaries for X
        int finalNewX = (int) newX;
        if (finalNewX < GameConfig.MIN_X) {
            finalNewX = GameConfig.MIN_X;
        } else if (finalNewX > maxX) {
            finalNewX = maxX;
        }

        position = new PositionVector(finalNewX, (int) newY);
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
        //super.currentVelocity = currentVelocity.add(acceleration.vector);

        // Apply acceleration factor (a = F/m)
        PositionVector accelerationVector = new PositionVector(
                acceleration.vector.getX() * accelerationFactor,
                acceleration.vector.getY() * accelerationFactor
        );

        // Update current velocity (v = u + a)
        currentVelocity = currentVelocity.add(accelerationVector);

        // Limit the velocity to the max speed after acceleration
        if (Math.abs(currentVelocity.getX()) > maxSpeed) {
            currentVelocity = new PositionVector(
                    Math.signum(currentVelocity.getX()) * maxSpeed,
                    currentVelocity.getY()
            );
        }
        if (Math.abs(currentVelocity.getY()) > maxSpeed) {
            currentVelocity = new PositionVector(
                    currentVelocity.getX(),
                    Math.signum(currentVelocity.getY()) * maxSpeed
            );
        }

    }

    // to override in subclasses
    public void flip() {
    }

    public void update() {
        move();
    }


}
