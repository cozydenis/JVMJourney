package ch.zhaw.it.pm2.jvmjourney.GameEngine;

import ch.zhaw.it.pm2.jvmjourney.Logger.LOGGINGLEVEL;
import ch.zhaw.it.pm2.jvmjourney.Logger.Logger;

/**
 * The WaterMelon class represents a watermelon in the game.
 * It extends the MovingObject class and has properties for its direction.
 */
public class WaterMelon extends MovingObject {

    /**
     * Constructs a new WaterMelon object with the specified position, image path, scale, initial direction, and initial velocity.
     *
     * @param x The x-coordinate of the watermelon's position.
     * @param y The y-coordinate of the watermelon's position.
     * @param path The path to the image representing the watermelon.
     * @param scale The scale of the watermelon.
     * @param initialDirection The initial direction of the watermelon.
     * @param initialVelocity The initial velocity of the watermelon.
     */
    public WaterMelon(int x, int y, String path, float scale, Direction initialDirection, PositionVector initialVelocity) {
        super(x, y, path, scale);

        this.currentVelocity = initialVelocity;
        this.inAir = true;
        this.thread = new Thread(() -> {

            try {
                Thread.sleep(Long.MAX_VALUE);
            } catch (InterruptedException e) {
                Logger.log(LOGGINGLEVEL.ERROR, e.getMessage());
            }
        });
        thread.start();
    }

    /**
     * Moves the watermelon by calling the superclass's move method and then checking for collisions with the ground and walls.
     */
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

    /**
     * Checks if the watermelon has hit the ground.
     *
     * @return true if the watermelon has hit the ground, false otherwise.
     */
    public boolean hitTheGround() {
        return position.getY() >= GameConfig.GROUNDLEVEL + 40;
    }

    /**
     * Checks if the watermelon has hit a wall.
     *
     * @return true if the watermelon has hit a wall, false otherwise.
     */
    public boolean hitTheWall() {
        boolean hit = position.getX() <= GameConfig.MIN_X;
        if (position.getX() >= GameConfig.getMaxX()) {
            hit = true;
        }
        return hit;
    }
}