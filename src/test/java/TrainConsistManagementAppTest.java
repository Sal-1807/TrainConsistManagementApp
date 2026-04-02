import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TrainConsistManagementAppTest {

    // Helper
    private List<TrainConsistManagementApp.GoodsBogie> getValidGoods() {
        List<TrainConsistManagementApp.GoodsBogie> list = new ArrayList<>();

        list.add(new TrainConsistManagementApp.GoodsBogie("Cylindrical", "Petroleum"));
        list.add(new TrainConsistManagementApp.GoodsBogie("Open", "Coal"));

        return list;
    }

    // ================= UC12 TESTS =================

    @Test
    public void testSafety_AllBogiesValid() {
        List<TrainConsistManagementApp.GoodsBogie> list = getValidGoods();

        boolean result = list.stream()
                .allMatch(g -> !g.type.equalsIgnoreCase("Cylindrical") ||
                        g.cargo.equalsIgnoreCase("Petroleum"));

        assertTrue(result);
    }

    @Test
    public void testSafety_CylindricalWithInvalidCargo() {
        List<TrainConsistManagementApp.GoodsBogie> list = new ArrayList<>();

        list.add(new TrainConsistManagementApp.GoodsBogie("Cylindrical", "Coal"));

        boolean result = list.stream()
                .allMatch(g -> !g.type.equalsIgnoreCase("Cylindrical") ||
                        g.cargo.equalsIgnoreCase("Petroleum"));

        assertFalse(result);
    }

    @Test
    public void testSafety_NonCylindricalBogiesAllowed() {
        List<TrainConsistManagementApp.GoodsBogie> list = new ArrayList<>();

        list.add(new TrainConsistManagementApp.GoodsBogie("Open", "Coal"));
        list.add(new TrainConsistManagementApp.GoodsBogie("Box", "Grain"));

        boolean result = list.stream()
                .allMatch(g -> !g.type.equalsIgnoreCase("Cylindrical") ||
                        g.cargo.equalsIgnoreCase("Petroleum"));

        assertTrue(result);
    }

    @Test
    public void testSafety_MixedBogiesWithViolation() {
        List<TrainConsistManagementApp.GoodsBogie> list = new ArrayList<>();

        list.add(new TrainConsistManagementApp.GoodsBogie("Cylindrical", "Petroleum"));
        list.add(new TrainConsistManagementApp.GoodsBogie("Cylindrical", "Coal"));

        boolean result = list.stream()
                .allMatch(g -> !g.type.equalsIgnoreCase("Cylindrical") ||
                        g.cargo.equalsIgnoreCase("Petroleum"));

        assertFalse(result);
    }

    @Test
    public void testSafety_EmptyBogieList() {
        List<TrainConsistManagementApp.GoodsBogie> list = new ArrayList<>();

        boolean result = list.stream()
                .allMatch(g -> !g.type.equalsIgnoreCase("Cylindrical") ||
                        g.cargo.equalsIgnoreCase("Petroleum"));

        assertTrue(result);
    }
}