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

    // ================= UC8 TESTS =================

    @Test
    public void testFilter_CapacityGreaterThanThreshold() {
        List<TrainConsistManagementApp.Bogie> list = getSampleBogies();

        List<TrainConsistManagementApp.Bogie> result =
                list.stream().filter(b -> b.capacity > 60).toList();

        assertEquals(2, result.size());
    }

    @Test
    public void testFilter_NoBogiesMatching() {
        List<TrainConsistManagementApp.Bogie> list = new ArrayList<>();

        list.add(new TrainConsistManagementApp.Bogie("Test", 20));

        List<TrainConsistManagementApp.Bogie> result =
                list.stream().filter(b -> b.capacity > 60).toList();

        assertTrue(result.isEmpty());
    }

    @Test
    public void testFilter_OriginalListUnchanged() {
        List<TrainConsistManagementApp.Bogie> list = getSampleBogies();

        int size = list.size();

        list.stream().filter(b -> b.capacity > 60).toList();

        assertEquals(size, list.size());
    }

    // ================= UC9 TESTS =================

    @Test
    public void testGrouping_BogiesGroupedByType() {
        List<TrainConsistManagementApp.Bogie> list = getSampleBogies();

        Map<String, List<TrainConsistManagementApp.Bogie>> result =
                list.stream().collect(Collectors.groupingBy(b -> b.name));

        assertTrue(result.containsKey("Sleeper"));
    }

    @Test
    public void testGrouping_MultipleBogiesInSameGroup() {
        List<TrainConsistManagementApp.Bogie> list = getSampleBogies();

        Map<String, List<TrainConsistManagementApp.Bogie>> result =
                list.stream().collect(Collectors.groupingBy(b -> b.name));

        assertEquals(2, result.get("Sleeper").size());
    }

    @Test
    public void testGrouping_DifferentBogieTypes() {
        List<TrainConsistManagementApp.Bogie> list = getSampleBogies();

        Map<String, List<TrainConsistManagementApp.Bogie>> result =
                list.stream().collect(Collectors.groupingBy(b -> b.name));

        assertEquals(3, result.size());
    }

    @Test
    public void testGrouping_EmptyBogieList() {
        List<TrainConsistManagementApp.Bogie> list = new ArrayList<>();

        Map<String, List<TrainConsistManagementApp.Bogie>> result =
                list.stream().collect(Collectors.groupingBy(b -> b.name));

        assertTrue(result.isEmpty());
    }

    @Test
    public void testGrouping_OriginalListUnchanged() {
        List<TrainConsistManagementApp.Bogie> list = getSampleBogies();

        int size = list.size();

        list.stream().collect(Collectors.groupingBy(b -> b.name));

        assertEquals(size, list.size());
    }
}