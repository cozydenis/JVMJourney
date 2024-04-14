package ch.zhaw.it.pm2.jvmjourney.GameEngine;

public class Player extends MovingObject {



    public Player(int x, int y , String path, int rows, int cols, int speed) {
        super(x,y, path, rows, cols, speed);

    }


    @Override
    public void move() {
        super.move();  // Apply current velocity
    }

    public void jump() {
        if (!inAir) {
            accelerate(Direction.UP);  // Only jump if not already in the air
            inAir = true;
        }
    }

    public void land() {
        this.inAir = false;
        this.currentVelocity = Direction.NONE.vector;  // Stop vertical movement when landing
    }



    public void setInAir(boolean inAir) {
        this.inAir = inAir;
    }
}

