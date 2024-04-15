package ch.zhaw.it.pm2.jvmjourney.GameEngine;

public class MovingObject extends Object {


    protected boolean inAir;

    private int speed;

    public MovingObject(int x, int y, String path, int rows, int cols, int speed) {
        super(x, y, path, rows, cols);

    }


    public void move() {
        int newX = position.getX() + currentVelocity.getX();
        int newY = position.getY() + currentVelocity.getY();

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

    public void update() {
        move();
    }






}
