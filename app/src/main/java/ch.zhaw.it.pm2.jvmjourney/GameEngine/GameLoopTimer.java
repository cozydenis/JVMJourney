package ch.zhaw.it.pm2.jvmjourney.GameEngine;


import javafx.animation.AnimationTimer;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;


public abstract class GameLoopTimer extends AnimationTimer {

    long pauseStart;
    long animationStart;
    DoubleProperty animationDuration = new SimpleDoubleProperty(0L);

    long lastFrameTimeNanos;

    boolean isPaused;
    boolean isActive;

    boolean pauseScheduled;
    boolean playScheduled;
    boolean restartScheduled;

    @Override
    public void start() {
        super.start();
        isActive = true;
        restartScheduled = true;
    }

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

    @Override
    public void handle(long now) {
        updateStateBasedOnFlags(now);
        if (!isPaused) {
            updateAnimation(now);
        }
    }

    private void updateStateBasedOnFlags(long now) {
        if (pauseScheduled) {
            handlePause(now);
        } else if (playScheduled) {
            handlePlay(now);
        } else if (restartScheduled) {
            handleRestart(now);
        }
    }

    private void handlePause(long now) {
        pauseStart = now;
        isPaused = true;
        pauseScheduled = false;
        System.out.println("Game paused");
    }

    private void handlePlay(long now) {
        if (isPaused) {  // Only adjust the start if we were paused
            animationStart += (now - pauseStart);
        }
        isPaused = false;
        playScheduled = false;
        System.out.println("Game resumed");
    }

    private void handleRestart(long now) {
        isPaused = false;
        animationStart = now;
        restartScheduled = false;
        System.out.println("Game restarted");
    }

    private void updateAnimation(long now) {
        long animDuration = now - animationStart;
        animationDuration.set(animDuration / 1e9);
        float secondsSinceLastFrame = (float) ((now - lastFrameTimeNanos) / 1e9);
        lastFrameTimeNanos = now;
        tick(secondsSinceLastFrame);
        System.out.println("Animation updated: Duration = " + animationDuration.get());
    }


    public boolean isActive() {
        return isActive;
    }

    public boolean isPaused() {
        return isPaused;
    }

    public DoubleProperty getAnimationDuration() {
        return animationDuration;
    }

    public boolean isRestartScheduled() {
        return restartScheduled;
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

    public abstract void tick(float secondsSinceLastFrame);
}
