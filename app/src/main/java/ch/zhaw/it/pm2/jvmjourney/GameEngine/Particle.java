package ch.zhaw.it.pm2.jvmjourney.GameEngine;


/**
 * The Particle class represents a particle in the game.
 * It extends the MovingObject class and has properties for its life thread and original velocity.
 */
public class Particle extends MovingObject {

    /**
     * The thread that controls the life of the particle.
     */
    private Thread lifeThread;

    /**
     * The original velocity of the particle.
     */
    private PositionVector originalVelocity;

    /**
     * Constructs a new Particle object with the specified position and velocity.
     *
     * @param x The x-coordinate of the particle's position.
     * @param y The y-coordinate of the particle's position.
     * @param velocity The velocity of the particle.
     */
    public Particle(int x, int y, PositionVector velocity) {
        super(x, y, "WaterMelonParticule.png", 1);
        originalVelocity = velocity;
    }

    /**
     * Moves the particle by adding the original velocity to the current position.
     */
    public void move() {
        position = position.add(originalVelocity = originalVelocity.add(new PositionVector(0, 0.1f)));
    }

    /**
     * Updates the state of the particle by moving it.
     */
    @Override
    public void update() {
        move();
    }
}
