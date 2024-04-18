package ch.zhaw.it.pm2.jvmjourney.GameEngine;

public class Player extends MovingObject {



    public Player(int x, int y , String path, int rows, int cols, int speed) {
        super(x,y, path, rows, cols, speed);

    }


    @Override
    public void move() {
        super.move();
        if (inAir) {
            double gravityEffect = currentVelocity.getY() + 1; // Simulate gravity
            currentVelocity = new PositionVector(currentVelocity.getX(), gravityEffect);
        } else {
            position.setY(Math.max(position.getY(), 0)); // Ensure the player doesn't go below ground level
        }

    }

    public void jump() {
        if (!inAir) {
            currentVelocity = new PositionVector(currentVelocity.getX(), -20); // Set an initial upward velocity
            inAir = true;
        }
    }

    public void land() {
        if (position.getY() >= GameConfig.GROUNDLEVEL) { // Check if on the ground
            inAir = false;
            position.setY(GameConfig.GROUNDLEVEL); // Correct the y position in case it's below ground
            currentVelocity = new PositionVector(currentVelocity.getX(), 0); // Stop vertical movement
        }
    }


    public void setInAir(boolean inAir) {
        this.inAir = inAir;
    }

}

