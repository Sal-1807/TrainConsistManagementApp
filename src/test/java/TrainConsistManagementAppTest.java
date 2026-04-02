import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TrainConsistManagementAppTest {

    // ================= UC14 TESTS =================

    @Test
    public void testException_ValidCapacityCreation() throws Exception {
        TrainConsistManagementApp.Bogie b =
                new TrainConsistManagementApp.Bogie("Sleeper", 50);

        assertNotNull(b);
    }

    @Test
    public void testException_NegativeCapacityThrowsException() {
        Exception ex = assertThrows(
                TrainConsistManagementApp.InvalidCapacityException.class,
                () -> new TrainConsistManagementApp.Bogie("Sleeper", -10)
        );

        assertEquals("Capacity must be greater than zero", ex.getMessage());
    }

    @Test
    public void testException_ZeroCapacityThrowsException() {
        assertThrows(
                TrainConsistManagementApp.InvalidCapacityException.class,
                () -> new TrainConsistManagementApp.Bogie("Sleeper", 0)
        );
    }

    @Test
    public void testException_ExceptionMessageValidation() {
        Exception ex = assertThrows(
                TrainConsistManagementApp.InvalidCapacityException.class,
                () -> new TrainConsistManagementApp.Bogie("Sleeper", -5)
        );

        assertEquals("Capacity must be greater than zero", ex.getMessage());
    }

    @Test
    public void testException_ObjectIntegrityAfterCreation() throws Exception {
        TrainConsistManagementApp.Bogie b =
                new TrainConsistManagementApp.Bogie("AC Chair", 60);

        assertEquals("AC Chair", b.name);
        assertEquals(60, b.capacity);
    }

    @Test
    public void testException_MultipleValidBogiesCreation() throws Exception {
        List<TrainConsistManagementApp.Bogie> list = new ArrayList<>();

        list.add(new TrainConsistManagementApp.Bogie("Sleeper", 70));
        list.add(new TrainConsistManagementApp.Bogie("AC", 50));

        assertEquals(2, list.size());
    }
}