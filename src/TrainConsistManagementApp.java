import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

public class TrainConsistManagementApp {

    // Passenger Bogie
    static class Bogie {
        String name;
        int capacity;

        Bogie(String name, int capacity) {
            this.name = name;
            this.capacity = capacity;
        }

        @Override
        public String toString() {
            return name + " (Capacity: " + capacity + ")";
        }
    }

    // Goods Bogie (NEW for UC12)
    static class GoodsBogie {
        String type;   // Cylindrical / Open / Box
        String cargo;  // Petroleum / Coal / Grain

        GoodsBogie(String type, String cargo) {
            this.type = type;
            this.cargo = cargo;
        }

        @Override
        public String toString() {
            return type + " (Cargo: " + cargo + ")";
        }
    }

    public static void main(String[] args) {

        System.out.println("=== Train Consist Management App ===");

        // ================= EXISTING UCs =================
        List<Bogie> bogies = new ArrayList<>();

        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 50));
        bogies.add(new Bogie("First Class", 24));
        bogies.add(new Bogie("Sleeper", 80));

        // UC7
        bogies.sort(Comparator.comparingInt(b -> b.capacity));
        System.out.println("\nBogies sorted by capacity:");
        bogies.forEach(System.out::println);

        // UC8
        List<Bogie> filtered = bogies.stream()
                .filter(b -> b.capacity > 60)
                .collect(Collectors.toList());

        System.out.println("\nFiltered Bogies (Capacity > 60):");
        filtered.forEach(System.out::println);

        // UC9
        Map<String, List<Bogie>> grouped = bogies.stream()
                .collect(Collectors.groupingBy(b -> b.name));

        System.out.println("\nGrouped Bogies:");
        grouped.forEach((k, v) -> {
            System.out.println(k + ":");
            v.forEach(b -> System.out.println("  " + b));
        });

        // UC10
        int total = bogies.stream()
                .map(b -> b.capacity)
                .reduce(0, Integer::sum);

        System.out.println("\nTotal Capacity: " + total);

        // UC11
        Pattern trainPattern = Pattern.compile("TRN-\\d{4}");
        Pattern cargoPattern = Pattern.compile("PET-[A-Z]{2}");

        System.out.println("\nTrain ID Valid: " + trainPattern.matcher("TRN-1234").matches());
        System.out.println("Cargo Code Valid: " + cargoPattern.matcher("PET-AB").matches());

        // ================= UC12 =================

        List<GoodsBogie> goodsBogies = new ArrayList<>();

        goodsBogies.add(new GoodsBogie("Cylindrical", "Petroleum"));
        goodsBogies.add(new GoodsBogie("Open", "Coal"));
        goodsBogies.add(new GoodsBogie("Box", "Grain"));

        // Safety Rule using allMatch
        boolean isSafe = goodsBogies.stream()
                .allMatch(g ->
                        !g.type.equalsIgnoreCase("Cylindrical") ||
                                g.cargo.equalsIgnoreCase("Petroleum")
                );

        System.out.println("\nTrain Safety Compliance: " + (isSafe ? "SAFE" : "UNSAFE"));

        // Program continues
    }
}