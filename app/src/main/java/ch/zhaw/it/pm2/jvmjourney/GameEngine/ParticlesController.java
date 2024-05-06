package ch.zhaw.it.pm2.jvmjourney.GameEngine;

/**
 * The ParticlesController class is responsible for creating and managing particles in the game.
 * It provides a method for creating an explosion of particles at a specified location.
 */
public final class ParticlesController {

    /**
     * Creates an explosion of particles at the specified location.
     *
     * @param x The x-coordinate of the explosion's location.
     * @param y The y-coordinate of the explosion's location.
     * @param numberOfParticles The number of particles to create in the explosion.
     * @param renderer The renderer responsible for drawing the particles.
     */
    public static void explosion(int x, int y, int numberOfParticles, Renderer renderer) {
        Particle[] particles = new Particle[numberOfParticles];
        for (int i = 0; i < numberOfParticles; i++) {
            particles[i] = new Particle(x, y, new PositionVector(Math.cos(i*(360/numberOfParticles)), Math.sin(i*(360/numberOfParticles))));
            renderer.addObject(particles[i]);
        }
    }
}