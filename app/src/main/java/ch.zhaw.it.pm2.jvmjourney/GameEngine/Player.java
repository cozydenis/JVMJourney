package ch.zhaw.it.pm2.jvmjourney.GameEngine;

public class Player extends MovingObject {


    public Player(int x, int y , String path, int rows, int cols, int speed) {
        super(x,y, path, rows, cols, speed);

    }

    /*
    public void jump() {
        if (!inAir) {
            // A simple model where jumping changes the velocity upwards
            currentVelocity = currentVelocity.add(new PositionVector(0, 5));
            inAir = true;
        }
    }

    public void fastFall() {
        if (inAir) {
            // Increases downward velocity
            currentVelocity = currentVelocity.add(new PositionVector(0, -2));
        }
    }

    public void endJump() {
        // Reset jump mechanics (if using something like variable jump height)
        inAir = false;
    }
    */




}
