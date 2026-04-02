import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

public class TrainConsistManagementApp {

    // Bogie class
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

    public static void main(String[] args) {

        System.out.println("=== Train Consist Management App ===");

        // Create list
        List<Bogie> bogies = new ArrayList<>();

        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 50));
        bogies.add(new Bogie("First Class", 24));
        bogies.add(new Bogie("Sleeper", 80));

        // ================= UC7 =================
        bogies.sort(Comparator.comparingInt(b -> b.capacity));

        System.out.println("\nBogies sorted by capacity:");
        bogies.forEach(System.out::println);

        // ================= UC8 =================
        List<Bogie> filteredBogies = bogies.stream()
                .filter(b -> b.capacity > 60)
                .collect(Collectors.toList());

        System.out.println("\nFiltered Bogies (Capacity > 60):");
        filteredBogies.forEach(System.out::println);

        // ================= UC9 =================
        Map<String, List<Bogie>> groupedBogies = bogies.stream()
                .collect(Collectors.groupingBy(b -> b.name));

        System.out.println("\nGrouped Bogies by Type:");
        groupedBogies.forEach((type, list) -> {
            System.out.println(type + ":");
            list.forEach(b -> System.out.println("  " + b));
        });

        // ================= UC10 =================
        int totalCapacity = bogies.stream()
                .map(b -> b.capacity)
                .reduce(0, Integer::sum);

        System.out.println("\nTotal Seating Capacity: " + totalCapacity);

        // ================= UC11 =================

        String trainId = "TRN-1234";
        String cargoCode = "PET-AB";

        // Regex patterns
        Pattern trainPattern = Pattern.compile("TRN-\\d{4}");
        Pattern cargoPattern = Pattern.compile("PET-[A-Z]{2}");

        // Matchers
        Matcher trainMatcher = trainPattern.matcher(trainId);
        Matcher cargoMatcher = cargoPattern.matcher(cargoCode);

        boolean isTrainValid = trainMatcher.matches();
        boolean isCargoValid = cargoMatcher.matches();

        System.out.println("\nTrain ID Validation: " + (isTrainValid ? "Valid" : "Invalid"));
        System.out.println("Cargo Code Validation: " + (isCargoValid ? "Valid" : "Invalid"));

        // Program continues
    }
}