import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
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

        // Create bogies
        List<Bogie> bogies = new ArrayList<>();

        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 50));
        bogies.add(new Bogie("First Class", 24));
        bogies.add(new Bogie("Sleeper", 80));

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

        // Create larger dataset
        List<Bogie> bigList = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            bigList.add(new Bogie("Sleeper", i % 100));
        }

        // LOOP-BASED FILTERING
        long startLoop = System.nanoTime();

        List<Bogie> loopResult = new ArrayList<>();
        for (Bogie b : bigList) {
            if (b.capacity > 60) {
                loopResult.add(b);
            }
        }

        long endLoop = System.nanoTime();
        long loopTime = endLoop - startLoop;

        // STREAM-BASED FILTERING
        long startStream = System.nanoTime();

        List<Bogie> streamResult = bigList.stream()
                .filter(b -> b.capacity > 60)
                .collect(Collectors.toList());

        long endStream = System.nanoTime();
        long streamTime = endStream - startStream;

        // Display results
        System.out.println("\nLoop Filtering Time: " + loopTime + " ns");
        System.out.println("Stream Filtering Time: " + streamTime + " ns");

        System.out.println("Loop Result Size: " + loopResult.size());
        System.out.println("Stream Result Size: " + streamResult.size());

        // Program continues
    }
}