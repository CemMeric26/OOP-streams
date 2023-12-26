import java.time.LocalDate;
import java.util.List;

public class QueryMarket {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java YourProgramName csv_file_name question_no");
            return;
        }
        String csvFileName = args[0];
        int questionNo = Integer.parseInt(args[1]);

        // Create an instance of the class where you have readCsvFile method
        QueryMarket market_query = new QueryMarket();
        CSVUtility CSVUtility = new CSVUtility();
        List<Customer> customers = CSVUtility.readCsvFile(csvFileName);

        DataProcessor processor = new DataProcessor();
        // Based on questionNo, call the relevant method to process the data
        switch (questionNo) {
            case 1:
                // Call method for question 1
                int total = processor.processQuery1(customers);
                System.out.println(total);
                break;
            case 2:
                // Call method for question 2
                // What was the price of most expensive product sold?
                Double most_expensive = processor.processQuery2(customers);
                System.out.println(most_expensive);
                break;
            case 3:
                // Call method for question 3
                // What was the date of the highest paid purchase by a customer?
                LocalDate highest_paid_date = processor.processQuery3(customers);
                System.out.println(highest_paid_date);
                break;

            case 4:
                // Call method for question 4
                // What was the most popular product before 2000, in terms of total number of purchases whose include that item?
                String most_popular_before_2000 = processor.processQuery4(customers);
                System.out.println(most_popular_before_2000);
                break;
            case 5:
                // Call method for question 5
                // What was the least popular product after and including 2000, in terms of total number of items purchased?
                String least_popular_after_2000 = processor.processQuery5(customers);
                System.out.println(least_popular_after_2000);
                break;

            default:
                System.out.println("Question number not recognized.");
                break;
        }
    }
}
