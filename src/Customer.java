import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Customer {
    private final int orderNumber;
    private final String customerName;
    private final int customerContactNum;
    private final String customerAddress;
    private final String customerCity;
    private final String customerEmail;

    /**
     * Creates a Customer object with specified attributes.
     * @param orderNumber The order number of the customer.
     * @param customerName The name of the customer.
     * @param customerContactNum The contact number of the customer.
     * @param customerAddress The address of the customer.
     * @param customerCity The city of the customer.
     * @param customerEmail The email address of the customer.
     * @throws IllegalArgumentException if any input parameter is invalid.
     */
    public Customer(int orderNumber, String customerName, int customerContactNum, String customerAddress, String customerCity, String customerEmail) {
        if (customerName == null || customerName.isEmpty()) throw new IllegalArgumentException("Customer Name cannot be empty or null");
        if (customerAddress == null || customerAddress.isEmpty()) throw new IllegalArgumentException("Customer Address cannot be empty or null");
        if (customerCity == null || customerCity.isEmpty()) throw new IllegalArgumentException("Customer City cannot be empty or null");
        if (customerEmail == null || !isValidEmail(customerEmail)) throw new IllegalArgumentException("Invalid email address format. Please enter a valid email address.");
        this.orderNumber = orderNumber;
        this.customerName = customerName;
        this.customerContactNum = customerContactNum;
        this.customerAddress = customerAddress;
        this.customerCity = customerCity;
        this.customerEmail = customerEmail;
    }

    /**
     * Converts the Customer object to a string representation.
     * @return A string containing customer details.
     */
    @Override
    public String toString() {
        return "Order Number: " + orderNumber + "\nCustomer Name: " + customerName + "\nContact Number: " + customerContactNum + "\nAddress: " + customerAddress + "\nCity: " + customerCity + "\nEmail Address: " + customerEmail;
    }

    /**
     * Validates the format of an email address using regular expressions.
     * @param email The email address to validate.
     * @return true if the email address is valid, false otherwise.
     */
    private static boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
