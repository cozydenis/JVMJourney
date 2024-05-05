package ch.zhaw.it.pm2.jvmjourney.controller.statisticsController;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryUsage;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MemoryController {
    private final int UPDATE_PERIOD = 5;

    @FXML
    private LineChart<Number, Number> memoryUsageChart;

    @FXML // Reference the axis from FXML, only if separately defined.
    private NumberAxis xAxis;

    private final List<XYChart.Series<Number, Number>> seriesList = new ArrayList<>();
    private int xSeriesData = -5;
    private final int maxDataPoints = 60;

    @FXML
    public void initialize() {
        setupChart();
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(this::updateChart, 0, UPDATE_PERIOD, TimeUnit.SECONDS);
    }

    private void setupChart() {
        if (xAxis == null) { // If xAxis is not separately defined in FXML, get it from the chart
            xAxis = (NumberAxis) memoryUsageChart.getXAxis();
            xAxis.setAutoRanging(false);
            xAxis.setLowerBound(0);
            xAxis.setUpperBound(maxDataPoints - 1);
        }

        List<MemoryPoolMXBean> pools = ManagementFactory.getMemoryPoolMXBeans();
        for (MemoryPoolMXBean pool: pools) {
            if (pool.getType() == java.lang.management.MemoryType.HEAP) {
                XYChart.Series<Number, Number> newSeries = new XYChart.Series<>();
                newSeries.setName(pool.getName());
                seriesList.add(newSeries);
                memoryUsageChart.getData().add(newSeries);
            }
        }
    }

    private void updateChart() {
        List<MemoryPoolMXBean> pools = ManagementFactory.getMemoryPoolMXBeans();

        for (MemoryPoolMXBean pool: pools) {
            if (pool.getType() == java.lang.management.MemoryType.HEAP) {
                MemoryUsage usage = pool.getUsage();
                double usedMB = usage.getUsed() / 1024.0 / 1024.0;
                seriesList.forEach(series -> {
                    if (series.getName().equals(pool.getName())) {
                        javafx.application.Platform.runLater(() -> {
                            if (series.getData().size() >= maxDataPoints) {
                                series.getData().removeFirst();
                            }
                            series.getData().add(new XYChart.Data<>(xSeriesData, usedMB));
                        });
                    }
                });
            }
        }

        xSeriesData = xSeriesData + UPDATE_PERIOD;

        adjustXAxis();
    }

    private void adjustXAxis() {
        int lowerBound = Math.max(xSeriesData - maxDataPoints + 1, 0);
        xAxis.setLowerBound(lowerBound);
        xAxis.setUpperBound(lowerBound + maxDataPoints - 1);
    }
}
