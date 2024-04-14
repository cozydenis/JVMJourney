package ch.zhaw.it.pm2.jvmjourney.GameEngine;

public class MovingObject extends Object {



    private int speed;

    public MovingObject(int x, int y, String path, int rows, int cols, int speed) {
        super(x, y, path, rows, cols);

    }


    public void move() {
        int newX = position.getX() + currentVelocity.getX();
        int newY = position.getY() + currentVelocity.getY();
        position = new PositionVector(newX, newY);


        // Implement logic to check if player is touching the ground
        // and set inAir to false accordingly
    }


    public void accelerate(Direction acceleration) {
        if (acceleration == null) {
            throw new IllegalArgumentException("Illegal acceleration: acceleration cannot be null");
        }
        super.currentVelocity = currentVelocity.add(acceleration.vector);
    }






}
