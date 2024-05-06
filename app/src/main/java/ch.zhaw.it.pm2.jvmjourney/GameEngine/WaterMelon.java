package ch.zhaw.it.pm2.jvmjourney.GameEngine;

public class WaterMelon extends ch.zhaw.it.pm2.jvmjourney.GameEngine.MovingObject {

    float density = 0.5f;
    Direction direction;
    private Thread thread;

    public WaterMelon(int x, int y, String path, float scale, Direction initialDirection, PositionVector initialVelocity) {
        super(x, y, path, scale);
        this.direction = initialDirection;
        this.currentVelocity = initialVelocity;
        this.inAir = true;
        thread = new Thread(() -> {
            try {
                Thread.sleep(Long.MAX_VALUE);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        thread.start();
    }

    @Override
    public void move() {
        super.move();
        if (hitTheGround()) {
            currentVelocity = this.currentVelocity.bounceOnTheGround();
        }
        if (hitTheWall()) {
            currentVelocity = this.currentVelocity.bounceOntheWall();
        }


    }

    public boolean hitTheGround() {
        return position.getY() >= GameConfig.GROUNDLEVEL + 40;
    }

    public boolean hitTheWall() {
        boolean hit = false;
        if (position.getX() <= GameConfig.MIN_X) {

            hit = true;
        }
        if (position.getX() >= GameConfig.getMaxX()) {
            hit = true;
        }
        return hit;

    }

    public void getHit(){
        System.out.println("Sqhash");



    }
}
