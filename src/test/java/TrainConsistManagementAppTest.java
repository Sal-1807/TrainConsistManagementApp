import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class TrainConsistManagementAppTest {

    // Helper method
    private List<TrainConsistManagementApp.Bogie> getSampleBogies() {
        List<TrainConsistManagementApp.Bogie> list = new ArrayList<>();

        list.add(new TrainConsistManagementApp.Bogie("Sleeper", 72));
        list.add(new TrainConsistManagementApp.Bogie("AC Chair", 50));
        list.add(new TrainConsistManagementApp.Bogie("First Class", 24));

        return list;
    }

    // TEST 1
    @Test
    public void testFilter_CapacityGreaterThanThreshold() {
        List<TrainConsistManagementApp.Bogie> list = getSampleBogies();

        List<TrainConsistManagementApp.Bogie> result =
                list.stream().filter(b -> b.capacity > 60).toList();

        assertEquals(1, result.size());
    }

    // TEST 2
    @Test
    public void testFilter_CapacityEqualToThreshold() {
        List<TrainConsistManagementApp.Bogie> list = new ArrayList<>();

        list.add(new TrainConsistManagementApp.Bogie("Test", 60));

        List<TrainConsistManagementApp.Bogie> result =
                list.stream().filter(b -> b.capacity > 60).toList();

        assertTrue(result.isEmpty());
    }

    // TEST 3
    @Test
    public void testFilter_CapacityLessThanThreshold() {
        List<TrainConsistManagementApp.Bogie> list = new ArrayList<>();

        list.add(new TrainConsistManagementApp.Bogie("Test", 40));

        List<TrainConsistManagementApp.Bogie> result =
                list.stream().filter(b -> b.capacity > 60).toList();

        assertTrue(result.isEmpty());
    }

    // TEST 4
    @Test
    public void testFilter_MultipleBogiesMatching() {
        List<TrainConsistManagementApp.Bogie> list = new ArrayList<>();

        list.add(new TrainConsistManagementApp.Bogie("B1", 70));
        list.add(new TrainConsistManagementApp.Bogie("B2", 80));
        list.add(new TrainConsistManagementApp.Bogie("B3", 30));

        List<TrainConsistManagementApp.Bogie> result =
                list.stream().filter(b -> b.capacity > 60).toList();

        assertEquals(2, result.size());
    }

    // TEST 5
    @Test
    public void testFilter_NoBogiesMatching() {
        List<TrainConsistManagementApp.Bogie> list = new ArrayList<>();

        list.add(new TrainConsistManagementApp.Bogie("B1", 10));
        list.add(new TrainConsistManagementApp.Bogie("B2", 20));

        List<TrainConsistManagementApp.Bogie> result =
                list.stream().filter(b -> b.capacity > 60).toList();

        assertTrue(result.isEmpty());
    }

    // TEST 6
    @Test
    public void testFilter_AllBogiesMatching() {
        List<TrainConsistManagementApp.Bogie> list = new ArrayList<>();

        list.add(new TrainConsistManagementApp.Bogie("B1", 70));
        list.add(new TrainConsistManagementApp.Bogie("B2", 80));

        List<TrainConsistManagementApp.Bogie> result =
                list.stream().filter(b -> b.capacity > 60).toList();

        assertEquals(list.size(), result.size());
    }

    // TEST 7
    @Test
    public void testFilter_EmptyBogieList() {
        List<TrainConsistManagementApp.Bogie> list = new ArrayList<>();

        List<TrainConsistManagementApp.Bogie> result =
                list.stream().filter(b -> b.capacity > 60).toList();

        assertTrue(result.isEmpty());
    }

    // TEST 8 ⭐
    @Test
    public void testFilter_OriginalListUnchanged() {
        List<TrainConsistManagementApp.Bogie> list = getSampleBogies();

        int originalSize = list.size();

        List<TrainConsistManagementApp.Bogie> result =
                list.stream().filter(b -> b.capacity > 60).toList();

        assertEquals(originalSize, list.size());
    }
}