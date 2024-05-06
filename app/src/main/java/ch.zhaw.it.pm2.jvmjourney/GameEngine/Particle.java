package ch.zhaw.it.pm2.jvmjourney.GameEngine;



public class Particle extends MovingObject {

    private boolean alive = true;
    private Thread lifeThread;
    private PositionVector originalVelocity;
    private PositionVector currentVelocity;


    public Particle(int x, int y, PositionVector velocity) {
        super(x, y, "WaterMelonParticule.png", 1);
        startLifeThread(2);
        originalVelocity = velocity;
    }

    public void move() {
        if(isAlive())
        {

            position = position.add(originalVelocity= originalVelocity.add(new PositionVector(-1, -0.1f)));

        }

    }

    private void startLifeThread(int seconds) {
        lifeThread = new Thread(() -> {
            System.out.println("Thread started");
            try {
                Thread.sleep(seconds * 1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread ended");
            alive = false; // set alive to false after 2 seconds
        });
        lifeThread.start();
    }

    public boolean isAlive() {
        return alive;
    }


}
