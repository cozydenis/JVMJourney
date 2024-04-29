package ch.zhaw.it.pm2.jvmjourney.controller.statisticsController;

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

    private void updateChart() {
        ThreadMXBean threadBean = ManagementFactory.getThreadMXBean();
        int currentThreadCount = threadBean.getThreadCount();

        javafx.application.Platform.runLater(() -> {
            if (series.getData().size() >= maxDataPoints) {
                series.getData().removeFirst();
            }
            series.getData().add(new XYChart.Data<>(xSeriesData, currentThreadCount));

            xSeriesData = xSeriesData + UPDATE_PERIOD;

            adjustXAxis();
        });
    }

    private void adjustXAxis() {
        int lowerBound = Math.max(xSeriesData - maxDataPoints + 1, 0);
        xAxis.setLowerBound(lowerBound);
        xAxis.setUpperBound(lowerBound + maxDataPoints - 1);
    }
}
