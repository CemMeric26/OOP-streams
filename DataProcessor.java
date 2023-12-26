import java.time.LocalDate;
import java.util.List;
import java.util.*;
import java.util.stream.*;
import java.io.*;
import java.util.function.*;

public class DataProcessor {

    public int processQuery1(List<Customer> customers) {
        // What is the total number of products purchased by the customers whose their names
        //start with ’A’?
        return customers.stream()
                // Filter customers whose name starts with 'A'
                .filter(customer -> customer.getName().startsWith("A"))
                // Map each customer to the sum of their product quantities
                .mapToInt(customer -> customer.getProductQuantities().values().stream().mapToInt(Integer::intValue).sum())
                // Sum the quantities of all such customers
                .sum();
    }

    public Double processQuery2(List<Customer> customers){
        // What was the price of most expensive product sold?
        return customers.stream()
                // Map each customer to the price of their most expensive product
                .mapToDouble(customer -> customer.getProductPrices().values().stream().mapToDouble(Double::doubleValue).max().orElse(0))
                // Find the maximum price
                .max().orElse(0);

    }

    public LocalDate processQuery3(List<Customer> customers){
        // What was the date of the highest paid purchase by a customer?

        Optional<Customer> customerWithHighestPurchase = customers.stream()
                // Stream each customer
                .max(Comparator.comparingDouble(
                        // Compare each customer by the sum of their product prices
                        customer -> customer.getProductPrices().entrySet().stream()
                                .mapToDouble(entry -> entry.getValue() * customer.getProductQuantities().get(entry.getKey()))
                                .sum()
                        )
                );

        return customerWithHighestPurchase.map(Customer::getPurchaseDate).orElse(null);
    }

    public String processQuery4(List<Customer> customers){
        //What was the most popular product before 2000, in terms of total number of purchases whose include that item?
        // Solutions including ,but not limited to, using collect with collectors like GroupingBy
        Map<String, Integer> purchaseCounts = new HashMap<>();

        // Accumulate purchase counts for each product before 2000
        customers.stream()
                .filter(customer -> customer.getPurchaseDate().getYear() < 2000)
                .forEach(customer -> {
                    customer.getProductQuantities().forEach((product, quantity) -> {
                        if (quantity > 0) {
                            purchaseCounts.merge(product, 1, Integer::sum);
                        }
                    });
                });

        // Find the most popular product
        return purchaseCounts.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);


    }
    // What was the least popular product after and including 2000, in terms of total
    //number of items purchased?
    public String processQuery5(List<Customer> customers){
        // Solutions including ,but not limited to, using collect with collectors like GroupingBy
        //or other stream methods like count or max are not valid solutions.

        Map<String, Integer> productQuantities = new HashMap<>();

        // Accumulate product quantities for purchases after 2000
        customers.stream()
                .filter(customer -> customer.getPurchaseDate().getYear() >= 2000)
                .forEach(customer -> {
                    customer.getProductQuantities().forEach((product, quantity) -> {
                        productQuantities.merge(product, quantity, Integer::sum);
                    });
                });

        // Find the least popular product
        return productQuantities.entrySet().stream()
                .min(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null); // Return null if no products are found
    }

}
