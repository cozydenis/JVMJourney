package ch.zhaw.it.pm2.jvmjourney.controllers.statisticsController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ThreadControllerTest {

    ThreadController threadController;

    @BeforeEach
    public void setUp() {
        threadController = new ThreadController();
    }

    @Test
    void testGetThreadCount() {
        int threadCount = threadController.getThreadCount();
        System.out.println("Thread count: " + threadCount);
        assert threadCount >= 0;
    }
}
