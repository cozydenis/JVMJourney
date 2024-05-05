package test;

import ch.zhaw.it.pm2.jvmjourney.controller.statisticsController.MemoryController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryType;
import java.lang.management.MemoryUsage;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

class MemoryControllerTest {
    @Mock
    private LineChart<Number, Number> mockChart;
    @Mock
    private NumberAxis mockXAxis;
    @Mock
    private ObservableList<XYChart.Series<Number, Number>> mockSeriesList;

    private MemoryController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        new JFXPanel(); // Initialize JavaFX runtime
        when(mockChart.getData()).thenReturn(mockSeriesList);  // Ensure getData() returns a mocked list
        controller = new MemoryController();
        controller.setMemoryUsageChart(mockChart);
        controller.setxAxis(mockXAxis);
    }

    @Test
    void testSetupChart() {
        MemoryPoolMXBean mockPool = mock(MemoryPoolMXBean.class);
        when(mockPool.getType()).thenReturn(MemoryType.HEAP);
        when(mockPool.getName()).thenReturn("Eden Space");
        //when(ManagementFactory.getMemoryPoolMXBeans()).thenReturn(Collections.singletonList(mockPool));

        controller.setupChart();

        //verify(mockSeriesList, times(1)).add(any(XYChart.Series.class));
    }
    @Test
    void testUpdateChart() {
        // Mock the necessary parts
        MemoryPoolMXBean mockPool = mock(MemoryPoolMXBean.class);
        MemoryUsage mockUsage = mock(MemoryUsage.class);

        when(mockPool.getUsage()).thenReturn(mockUsage);
        when(mockUsage.getUsed()).thenReturn(50000000L); // returns used memory in bytes

        // Prepare the chart and series mocks
        LineChart<Number, Number> mockChart = mock(LineChart.class);
        NumberAxis mockXAxis = mock(NumberAxis.class);
        ObservableList<XYChart.Series<Number, Number>> mockSeriesList = FXCollections.observableArrayList();

        when(mockChart.getData()).thenReturn(mockSeriesList);
        //when(controller.getMemoryUsageChart()).thenReturn(mockChart);
        //when(controller.getxAxis()).thenReturn(mockXAxis);

        // Execute the method under test
        controller.updateChart();

        // Assertions and verifications
        //verify(mockXAxis, times(1)).setLowerBound(anyDouble());
        //verify(mockXAxis, times(1)).setUpperBound(anyDouble());
    }


}
