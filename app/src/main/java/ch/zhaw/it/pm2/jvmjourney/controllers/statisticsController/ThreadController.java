package ch.zhaw.it.pm2.jvmjourney.controllers.statisticsController;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadController {
    private final int UPDATE_PERIOD = 5;

    @FXML
    private LineChart<Number, Number> threadCountChart;

    @FXML // Reference the axis from FXML, only if separately defined.
    private NumberAxis xAxis;

    private final XYChart.Series<Number, Number> series = new XYChart.Series<>();
    private int xSeriesData = 0;
    private final int maxDataPoints = 60;

    @FXML
    public void initialize() {
        setupChart();
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(this::updateChart, 0, UPDATE_PERIOD, TimeUnit.SECONDS);
    }

    private void setupChart() {
        if (xAxis == null) { // If xAxis is not separately defined in FXML, get it from the chart
            xAxis = (NumberAxis) threadCountChart.getXAxis();
            xAxis.setAutoRanging(false);
            xAxis.setLowerBound(0);
            xAxis.setUpperBound(maxDataPoints - 1);
        }

        series.setName("Current Thread Count");
        threadCountChart.getData().add(series);
    }

    /**
     * Update the chart with the current thread count.
     * Calculating -15 to exclude the JavaFX and java native threads.
     */
    private void updateChart() {
        javafx.application.Platform.runLater(() -> {
            if (series.getData().size() >= maxDataPoints) {
                series.getData().removeFirst();
            }
            series.getData().add(new XYChart.Data<>(xSeriesData, getThreadCount() - 15));

            xSeriesData = xSeriesData + UPDATE_PERIOD;

            adjustXAxis();
        });
    }

    /**
     * Get the current thread count.
     *
     * @return The current thread count.
     */
    public int getThreadCount() {
        ThreadMXBean threadBean = ManagementFactory.getThreadMXBean();
        return threadBean.getThreadCount();
    }

    private void adjustXAxis() {
        int lowerBound = Math.max(xSeriesData - maxDataPoints + 1, 0);
        xAxis.setLowerBound(lowerBound);
        xAxis.setUpperBound(lowerBound + maxDataPoints - 1);
    }
}
