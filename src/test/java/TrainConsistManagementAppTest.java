import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class TrainConsistManagementAppTest {

    private List<TrainConsistManagementApp.Bogie> getSampleBogies() {
        List<TrainConsistManagementApp.Bogie> list = new ArrayList<>();

        list.add(new TrainConsistManagementApp.Bogie("Sleeper", 72));
        list.add(new TrainConsistManagementApp.Bogie("AC Chair", 50));
        list.add(new TrainConsistManagementApp.Bogie("First Class", 24));
        list.add(new TrainConsistManagementApp.Bogie("Sleeper", 80));

        return list;
    }

    // ================= UC10 TESTS =================

    @Test
    public void testReduce_TotalSeatCalculation() {
        List<TrainConsistManagementApp.Bogie> list = getSampleBogies();

        int total = list.stream()
                .map(b -> b.capacity)
                .reduce(0, Integer::sum);

        assertEquals(226, total);
    }

    @Test
    public void testReduce_MultipleBogiesAggregation() {
        List<TrainConsistManagementApp.Bogie> list = getSampleBogies();

        int total = list.stream()
                .map(b -> b.capacity)
                .reduce(0, Integer::sum);

        assertTrue(total > 0);
    }

    @Test
    public void testReduce_SingleBogieCapacity() {
        List<TrainConsistManagementApp.Bogie> list = new ArrayList<>();
        list.add(new TrainConsistManagementApp.Bogie("Sleeper", 100));

        int total = list.stream()
                .map(b -> b.capacity)
                .reduce(0, Integer::sum);

        assertEquals(100, total);
    }

    @Test
    public void testReduce_EmptyBogieList() {
        List<TrainConsistManagementApp.Bogie> list = new ArrayList<>();

        int total = list.stream()
                .map(b -> b.capacity)
                .reduce(0, Integer::sum);

        assertEquals(0, total);
    }

    @Test
    public void testReduce_CorrectCapacityExtraction() {
        List<TrainConsistManagementApp.Bogie> list = getSampleBogies();

        List<Integer> capacities = list.stream()
                .map(b -> b.capacity)
                .toList();

        assertTrue(capacities.contains(72));
        assertTrue(capacities.contains(50));
    }

    @Test
    public void testReduce_AllBogiesIncluded() {
        List<TrainConsistManagementApp.Bogie> list = getSampleBogies();

        int total = list.stream()
                .map(b -> b.capacity)
                .reduce(0, Integer::sum);

        assertEquals(226, total);
    }

    @Test
    public void testReduce_OriginalListUnchanged() {
        List<TrainConsistManagementApp.Bogie> list = getSampleBogies();

        int size = list.size();

        list.stream()
                .map(b -> b.capacity)
                .reduce(0, Integer::sum);

        assertEquals(size, list.size());
    }
}