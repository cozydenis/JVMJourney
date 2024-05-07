package ch.zhaw.it.pm2.jvmjourney.GameEngine;

import javafx.animation.AnimationTimer;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * An abstract class representing a game loop timer. Extending this class allows for the implementation
 * of custom game loops. This class extends {@link AnimationTimer}.
 */
public abstract class GameLoopTimer extends AnimationTimer {

    /** The start time of the pause. */
    long pauseStart;
    /** The start time of the animation. */
    long animationStart;
    /** The duration of the animation. */
    DoubleProperty animationDuration = new SimpleDoubleProperty(0L);

    /** The time of the last frame in nanoseconds. */
    long lastFrameTimeNanos;

    /** Indicates whether the game loop is currently paused. */
    boolean isPaused;
    /** Indicates whether the game loop is active. */
    boolean isActive;

    /** Indicates if a pause is scheduled. */
    boolean pauseScheduled;
    /** Indicates if a play is scheduled. */
    boolean playScheduled;
    /** Indicates if a restart is scheduled. */
    boolean restartScheduled;

    /**
     * Starts the game loop timer.
     */
    @Override
    public void start() {
        super.start();
        isActive = true;
        restartScheduled = true;
    }

    /**
     * Stops the game loop timer.
     */
    @Override
    public void stop() {
        super.stop();
        pauseStart = 0;
        isPaused = false;
        isActive = false;
        pauseScheduled = false;
        playScheduled = false;
        animationDuration.set(0);
    }

    /**
     * Handles the game loop ticks.
     *
     * @param now The current time in nanoseconds.
     */
    @Override
    public void handle(long now) {
        if (pauseScheduled) {
            pauseStart = now;
            isPaused = true;
            pauseScheduled = false;
        }

        if (playScheduled) {
            animationStart += (now - pauseStart);
            isPaused = false;
            playScheduled = false;
        }

        if (restartScheduled) {
            isPaused = false;
            animationStart = now;
            restartScheduled = false;
        }

        if (!isPaused) {
            long animDuration = now - animationStart;
            animationDuration.set(animDuration / 1e9);

            float secondsSinceLastFrame = (float) ((now - lastFrameTimeNanos) / 1e9);
            lastFrameTimeNanos = now;
            tick(secondsSinceLastFrame);
        }
    }

    /**
     * Abstract method to be implemented by subclasses for processing game logic for each frame.
     *
     * @param secondsSinceLastFrame The time in seconds since the last frame.
     */
    public abstract void tick(float secondsSinceLastFrame);

    public boolean isPaused() {
        return isPaused;
    }

    public boolean isActive() {
        return isActive;
    }

    public boolean isRestartScheduled() {
        return restartScheduled;
    }

    public DoubleProperty getAnimationDuration() {
        return animationDuration;
    }

    public void setPauseScheduled(boolean pauseScheduled) {
        this.pauseScheduled = pauseScheduled;
    }

    public void setPlayScheduled(boolean playScheduled) {
        this.playScheduled = playScheduled;
    }

    public void setRestartScheduled(boolean restartScheduled) {
        this.restartScheduled = restartScheduled;
    }
}
