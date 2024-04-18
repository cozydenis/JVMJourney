package ch.zhaw.it.pm2.jvmjourney.GameEngine;

public class MovingObject extends Object {


    protected boolean inAir;

    private int speed;

    public MovingObject(int x, int y, String path, int rows, int cols, int speed) {
        super(x, y, path, rows, cols);

    }


    public void move() {
        double newX = position.getX() + currentVelocity.getX();
        double newY = position.getY() + currentVelocity.getY();

        // Reduce horizontal velocity by 5% each frame
        currentVelocity = new PositionVector(
                currentVelocity.getX() * 0.95,
                currentVelocity.getY()
        );

        int maxX = GameConfig.getMaxX();
        // Check boundaries for X
        int finalNewX = (int) newX;
        if (finalNewX < GameConfig.MIN_X) {
            finalNewX = GameConfig.MIN_X;
        } else if (finalNewX > maxX) {
            finalNewX = maxX;
        }

        position = new PositionVector(finalNewX, (int)newY);
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
