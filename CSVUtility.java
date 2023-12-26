import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class CSVUtility {

    public List<Customer> readCsvFile(String fileName) {
        List<Customer> customers = new ArrayList<>();
        try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
            lines.skip(1) // Skip the header
                    .forEach(line -> customers.add(createCustomerFromCSV(line)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return customers;
    }

    private Customer createCustomerFromCSV(String line) {
        String[] data = line.split(",");
        // Assuming the CSV format matches the order of the constructor
        String name = data[0];
        int age = Integer.parseInt(data[1]);
        LocalDate date = LocalDate.parse(data[2]);
        Map<String, Double> productPrices = new HashMap<>();
        Map<String, Integer> productQuantities = new HashMap<>();

        // Assuming that the prices and quantities for products A-E are in indices 3-11
        for (int i = 3; i < data.length; i += 2) {
            String productName = String.valueOf((char)('A' + (i - 3) / 2));
            if (!data[i].isEmpty()) { // Check for non-empty price field
                double price = Double.parseDouble(data[i]);
                productPrices.put(productName, price);
            }
            if (!data[i + 1].isEmpty()) { // Check for non-empty quantity field
                int quantity = Integer.parseInt(data[i + 1]);
                productQuantities.put(productName, quantity);
            }
        }

        return new Customer(name, age, date, productPrices, productQuantities);
    }
}
