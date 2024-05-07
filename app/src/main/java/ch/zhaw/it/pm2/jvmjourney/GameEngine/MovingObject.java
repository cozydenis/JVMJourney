package ch.zhaw.it.pm2.jvmjourney.GameEngine;

/**
 * Represents a moving object within the game world.
 * Extends the GameObject class.
 */
public class MovingObject extends GameObject {

    /** Flag indicating whether the object is in the air. */
    protected boolean inAir;

    /** The current velocity of the object. */
    protected PositionVector currentVelocity;

    /**
     * Constructs a MovingObject with the specified position, image path, and scale.
     *
     * @param x     The initial x-coordinate of the object.
     * @param y     The initial y-coordinate of the object.
     * @param path  The path to the sprite image of the object.
     * @param scale The scale factor of the object.
     */
    public MovingObject(int x, int y, String path, float scale) {
        super(x, y, path, scale);
        this.currentVelocity = Direction.NONE.vector;
    }

    /**
     * Loads a sprite image for the object from the given path.
     *
     * @param path The path to the sprite image.
     */
    public void loadSprite(String path) {
        super.loadSprite(path);
    }

    /**
     * Retrieves the current velocity vector of the object.
     *
     * @return The current velocity vector.
     */
    public PositionVector getCurrentVelocity() {
        return currentVelocity;
    }

    /**
     * Moves the object based on its current velocity, applying friction and boundary checks.
     */
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
                flip();
            }

        } else if (currentVelocity.getX() > 0) {
            if (goingRight) {
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

    /**
     * Checks if the object is in the air.
     *
     * @return True if the object is in the air, otherwise false.
     */
    public boolean isInAir() {
        return inAir;
    }

    /**
     * Accelerates the object in the specified direction, applying acceleration and limiting speed.
     *
     * @param acceleration The direction of acceleration.
     * @throws IllegalArgumentException If the acceleration is null.
     */
    public void accelerate(Direction acceleration) {
        if (acceleration == null) {
            throw new IllegalArgumentException("Illegal acceleration: acceleration cannot be null");
        }

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

    /**
     * Flips the object horizontally.
     * To be overridden in subclasses.
     */
    public void flip() {
        goingRight = !goingRight;
    }

    /**
     * Updates the state of the object.
     * Overrides the update() method from the superclass GameObject.
     */
    @Override
    public void update() {
        move();
    }

    public void setCurrentVelocity(PositionVector velocity) {
        this.currentVelocity = velocity;
    }

    public boolean inAir() {
        return inAir;
    }

    public void setInAir(boolean inAir) {
        this.inAir = inAir;
    }

    public void setGoingRight(boolean goingRight) {
        this.goingRight = goingRight;
    }

    public boolean goingRight() {
        return goingRight;
    }
}
