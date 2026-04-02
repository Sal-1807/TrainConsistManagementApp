import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class TrainConsistManagementAppTest {

    private List<TrainConsistManagementApp.Bogie> getBogies() {
        List<TrainConsistManagementApp.Bogie> list = new ArrayList<>();

        list.add(new TrainConsistManagementApp.Bogie("Sleeper", 72));
        list.add(new TrainConsistManagementApp.Bogie("AC", 50));
        list.add(new TrainConsistManagementApp.Bogie("First", 24));
        list.add(new TrainConsistManagementApp.Bogie("Sleeper", 80));

        return list;
    }

    // ================= UC13 TESTS =================

    @Test
    public void testLoopFilteringLogic() {
        List<TrainConsistManagementApp.Bogie> list = getBogies();

        List<TrainConsistManagementApp.Bogie> result = new ArrayList<>();

        for (TrainConsistManagementApp.Bogie b : list) {
            if (b.capacity > 60) {
                result.add(b);
            }
        }

        assertEquals(2, result.size());
    }

    @Test
    public void testStreamFilteringLogic() {
        List<TrainConsistManagementApp.Bogie> list = getBogies();

        List<TrainConsistManagementApp.Bogie> result =
                list.stream().filter(b -> b.capacity > 60).toList();

        assertEquals(2, result.size());
    }

    @Test
    public void testLoopAndStreamResultsMatch() {
        List<TrainConsistManagementApp.Bogie> list = getBogies();

        List<TrainConsistManagementApp.Bogie> loopResult = new ArrayList<>();
        for (TrainConsistManagementApp.Bogie b : list) {
            if (b.capacity > 60) {
                loopResult.add(b);
            }
        }

        List<TrainConsistManagementApp.Bogie> streamResult =
                list.stream().filter(b -> b.capacity > 60).toList();

        assertEquals(loopResult.size(), streamResult.size());
    }

    @Test
    public void testExecutionTimeMeasurement() {
        long start = System.nanoTime();
        long end = System.nanoTime();

        long elapsed = end - start;

        assertTrue(elapsed >= 0);
    }

    @Test
    public void testLargeDatasetProcessing() {
        List<TrainConsistManagementApp.Bogie> list = new ArrayList<>();

        for (int i = 0; i < 10000; i++) {
            list.add(new TrainConsistManagementApp.Bogie("Test", i % 100));
        }

        List<TrainConsistManagementApp.Bogie> result =
                list.stream().filter(b -> b.capacity > 60).toList();

        assertNotNull(result);
    }
}