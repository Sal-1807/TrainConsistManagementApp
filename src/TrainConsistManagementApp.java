import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TrainConsistManagementApp {

    // ================= UC14: Custom Exception =================
    static class InvalidCapacityException extends Exception {
        InvalidCapacityException(String message) {
            super(message);
        }
    }

    // Passenger Bogie (UPDATED with validation)
    static class Bogie {
        String name;
        int capacity;

        Bogie(String name, int capacity) throws InvalidCapacityException {
            if (capacity <= 0) {
                throw new InvalidCapacityException("Capacity must be greater than zero");
            }
            this.name = name;
            this.capacity = capacity;
        }

        @Override
        public String toString() {
            return name + " (Capacity: " + capacity + ")";
        }
    }

    // Goods Bogie
    static class GoodsBogie {
        String type;
        String cargo;

        GoodsBogie(String type, String cargo) {
            this.type = type;
            this.cargo = cargo;
        }
    }

    public static void main(String[] args) {

        System.out.println("=== Train Consist Management App ===");

        List<Bogie> bogies = new ArrayList<>();

        try {
            // Valid bogies
            bogies.add(new Bogie("Sleeper", 72));
            bogies.add(new Bogie("AC Chair", 50));
            bogies.add(new Bogie("First Class", 24));
            bogies.add(new Bogie("Sleeper", 80));

            // Invalid bogie (will throw exception)
            bogies.add(new Bogie("Invalid", -10));

        } catch (InvalidCapacityException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // ================= UC7 =================
        bogies.sort(Comparator.comparingInt(b -> b.capacity));
        System.out.println("\nSorted:");
        bogies.forEach(System.out::println);

        // ================= UC8 =================
        List<Bogie> filtered = bogies.stream()
                .filter(b -> b.capacity > 60)
                .collect(Collectors.toList());

        System.out.println("\nFiltered:");
        filtered.forEach(System.out::println);

        // ================= UC9 =================
        Map<String, List<Bogie>> grouped = bogies.stream()
                .collect(Collectors.groupingBy(b -> b.name));

        System.out.println("\nGrouped:");
        grouped.forEach((k, v) -> {
            System.out.println(k + ": " + v.size());
        });

        // ================= UC10 =================
        int total = bogies.stream()
                .map(b -> b.capacity)
                .reduce(0, Integer::sum);

        System.out.println("\nTotal Capacity: " + total);

        // ================= UC11 =================
        System.out.println("\nTrain Valid: " + Pattern.matches("TRN-\\d{4}", "TRN-1234"));

        // ================= UC12 =================
        List<GoodsBogie> goods = new ArrayList<>();
        goods.add(new GoodsBogie("Cylindrical", "Petroleum"));
        goods.add(new GoodsBogie("Open", "Coal"));

        boolean safe = goods.stream()
                .allMatch(g -> !g.type.equalsIgnoreCase("Cylindrical")
                        || g.cargo.equalsIgnoreCase("Petroleum"));

        System.out.println("Safety: " + safe);

        // ================= UC13 =================
        long start = System.nanoTime();
        bogies.stream().filter(b -> b.capacity > 60).toList();
        long end = System.nanoTime();

        System.out.println("Execution Time: " + (end - start) + " ns");

        // Program continues
    }
}