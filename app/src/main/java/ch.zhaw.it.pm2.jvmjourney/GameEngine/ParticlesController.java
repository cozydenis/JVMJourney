package ch.zhaw.it.pm2.jvmjourney.GameEngine;

public final class ParticlesController {

    private Renderer renderer;
    private static Particle[] particles;

    public static void explosion(int x, int y, int numberOfParticles, Renderer renderer) {
        particles = new Particle[numberOfParticles];
        for (int i = 0; i < numberOfParticles; i++) {
            particles[i] = new Particle(x, y, new PositionVector(i, -i));
            renderer.addObject(particles[i]);
        }
    }


}
