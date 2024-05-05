package ch.zhaw.it.pm2.jvmjourney.GameEngine;

public class MovingObject extends GameObject {

    protected boolean inAir;
    private boolean goingRight;



    protected PositionVector currentVelocity;

    public MovingObject(int x, int y, String path, float scale) {
        super(x, y, path, scale);

        this.currentVelocity = Direction.NONE.vector;


    }

    public void loadSprite(String path) {
        super.loadSprite(path);


    }
    public PositionVector getCurrentVelocity() {
        return currentVelocity;
    }

    public void move() {
        // If on the ground, apply friction to horizontal movement
        if (!inAir) {
            // Represents frictional force, 5% reduction per frame
            double friction = 0.95;
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
        // The rate of acceleration
        double accelerationFactor = 0.5;
        PositionVector accelerationVector = new PositionVector(
                acceleration.vector.getX() * accelerationFactor,
                acceleration.vector.getY() * accelerationFactor
        );

        // Update current velocity (v = u + a)
        currentVelocity = currentVelocity.add(accelerationVector);

        // Limit the velocity to the max speed after acceleration
        // Maximum speed in any direction
        double maxSpeed = 10;
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

    @Override
    public void update() {
        move();
    }


}
