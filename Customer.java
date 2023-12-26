import java.time.LocalDate;
import java.util.Map;

public class Customer {
    String name;
    int age;
    LocalDate purchaseDate;

    Map<String, Double> productPrices;
    Map<String, Integer> productQuantities;

    Customer(String name, int age, LocalDate purchaseDate, Map<String, Double> productPrices, Map<String, Integer> productQuantities) {
        this.name = name;
        this.age = age;
        this.purchaseDate = purchaseDate;
        this.productPrices = productPrices;
        this.productQuantities = productQuantities;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public Map<String, Double> getProductPrices() {
        return productPrices;
    }

    public Map<String, Integer> getProductQuantities() {
        return productQuantities;
    }
    // getting the element of the map
    public Double getPrice(String product) {
        return productPrices.get(product);
    }
    // getting the element of the map
    public Integer getQuantity(String product) {
        return productQuantities.get(product);
    }

}
