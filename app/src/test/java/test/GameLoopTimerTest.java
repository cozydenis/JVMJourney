package test;

import ch.zhaw.it.pm2.jvmjourney.GameEngine.GameLoopTimer;
import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GameLoopTimerTest {

    private static class TestGameLoopTimer extends GameLoopTimer {
        @Override
        public void tick(float secondsSinceLastFrame) {
            // Mock behavior or simply log the input
        }
    }

    @BeforeAll
    static void setup() {
        // Initializes the JavaFX environment
        new JFXPanel();
    }

    @Test
    void testStart() {
        TestGameLoopTimer timer = new TestGameLoopTimer();
        timer.start();
        assertTrue(timer.isActive());
        assertTrue(timer.isRestartScheduled());
        assertFalse(timer.isPaused());
    }

    @Test
    void testStop() {
        TestGameLoopTimer timer = new TestGameLoopTimer();
        timer.start();  // First start to change initial states

        // After starting, you might need to simulate some passage of time and handling
        timer.handle(System.nanoTime() + 1000000000);  // Simulate 1 second passing

        timer.stop();
        assertFalse(timer.isActive());  // Accessing the boolean property with .get()
        assertFalse(timer.isPaused());  // Accessing the boolean property with .get()
        assertEquals(0, timer.getAnimationDuration().get(), 0.001);  // Accessing the double property with .get()
    }
    @Test
    void testHandlePauseAndPlay() {
        TestGameLoopTimer timer = new TestGameLoopTimer();
        timer.start();
        long initialTime = System.nanoTime();

        // Simulate pause
        timer.setPauseScheduled(true);
        timer.handle(initialTime + 5000); // Assuming handle checks and acts on pauseScheduled
        assertTrue(timer.isPaused(), "Timer should be paused");

        // Simulate resume
        timer.setPlayScheduled(true);
        timer.handle(initialTime + 10000); // Assuming handle checks and acts on playScheduled
        assertFalse(timer.isPaused(), "Timer should be resumed");
    }

    @Test
    void testHandleRestart() {
        TestGameLoopTimer timer = new TestGameLoopTimer();
        timer.setRestartScheduled(true);
        long restartTime = System.nanoTime();
        timer.handle(restartTime);
        assertFalse(timer.isPaused());
        assertEquals(0, timer.getAnimationDuration().get(), 0.001);
    }
}
