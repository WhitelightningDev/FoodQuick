import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter Customer Details:");
            int orderNumber = getIntInput(scanner, "Order Number: ");
            String customerName = getStringInput(scanner, "Customer Name: ");
            int customerContactNum = getIntInput(scanner, "Contact Number: ");
            String address = getStringInput(scanner, "Address: ");
            String customerCity = getStringInput(scanner, "City: ");
            String customerEmail = getEmailInput(scanner);
            Customer customer = new Customer(orderNumber, customerName, customerContactNum, address, customerCity, customerEmail);

            System.out.println("\nEnter Restaurant Details:");
            String restName = getStringInput(scanner, "Restaurant Name: ");
            String restLocation = getStringInput(scanner, "Location: ");
            int restContactNumber = getIntInput(scanner, "Contact Number: ");
            int numberOfMeals = getIntInput(scanner, "Number of meals: ");
            List<String> meals = getMealItems(scanner);
            double mealPrice = getDoubleInput(scanner, "Meal Price R: ");
            String specialPrepInstruction = getStringInput(scanner, "Special preparation instructions: ");
            double totalAmount = getDoubleInput(scanner, "Total amount R: ");

            Restaurant restaurant = new Restaurant(restName, restLocation, restContactNumber, numberOfMeals, meals.toString(), mealPrice, specialPrepInstruction, totalAmount);

            List<Driver> drivers = readDriverData();

            Driver selectedDriver = findClosestDriverWithSmallestLoad(drivers, restLocation);

            String invoiceText = generateInvoice(customer, restaurant, selectedDriver);

            writeInvoiceToFile(invoiceText);
        }
    }

    /**
     * Prompt the user for an email address and validate it.
     * @param scanner Scanner object for input.
     * @return Validated email address.
     */
    private static String getEmailInput(Scanner scanner) {
        String input = "";
        boolean validInput = false;
        while (!validInput) {
            System.out.print("Email Address (example@gmail.com): ");
            input = scanner.nextLine();
            if (isValidEmail(input)) {
                validInput = true;
            } else {
                System.out.println("Invalid email address format. Please enter a valid email address (example@gmail.com).");
            }
        }
        return input;
    }

    /**
     * Check if a given string is a valid email address.
     * @param email The email address to validate.
     * @return True if the email address is valid, false otherwise.
     */
    private static boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * Prompt the user for an integer input and validate it.
     * @param scanner Scanner object for input.
     * @param prompt   The prompt message.
     * @return Validated integer input.
     */
    private static int getIntInput(Scanner scanner, String prompt) {
        int input = 0;
        boolean validInput = false;
        while (!validInput) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                scanner.nextLine(); // Consume the newline
                validInput = true;
            } else {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine(); // Consume invalid input
            }
        }
        return input;
    }

    /**
     * Prompt the user for a double input and validate it.
     * @param scanner Scanner object for input.
     * @param prompt   The prompt message.
     * @return Validated double input.
     */
    private static double getDoubleInput(Scanner scanner, String prompt) {
        double input = 0;
        boolean validInput = false;
        while (!validInput) {
            System.out.print(prompt);
            if (scanner.hasNextDouble()) {
                input = scanner.nextDouble();
                scanner.nextLine(); // Consume the newline
                validInput = true;
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Consume invalid input
            }
        }
        return input;
    }

    /**
     * Prompt the user for a string input.
     * @param scanner Scanner object for input.
     * @param prompt   The prompt message.
     * @return The string input.
     */
    private static String getStringInput(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    /**
     * Prompt the user for a list of meal items and split them into a list.
     * @param scanner Scanner object for input.
     * @return List of meal items.
     */
    private static List<String> getMealItems(Scanner scanner) {
        System.out.println("Enter Meal Items (comma-separated): ");
        String mealItemsInput = scanner.nextLine();
        return Arrays.asList(mealItemsInput.split(","));
    }

    /**
     * Read driver data from a file and return a list of drivers.
     * @return List of drivers.
     */
    private static List<Driver> readDriverData() {
        List<Driver> drivers = new ArrayList<>();
        try (Scanner scanner = new Scanner(new FileInputStream("drivers.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] driverDetails = line.split(", ");

                if (driverDetails.length == 3) {
                    String driverID = driverDetails[0];
                    String location = driverDetails[1].trim();
                    int currentLoad = Integer.parseInt(driverDetails[2].trim());
                    drivers.add(new Driver(driverID, location, currentLoad));
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error: drivers.txt not found.");
        }
        return drivers;
    }

    /**
     * Find the closest driver with the smallest load for a given location.
     * @param drivers   List of drivers.
     * @param location  The location to deliver to.
     * @return The selected driver.
     */
    private static Driver findClosestDriverWithSmallestLoad(List<Driver> drivers, String location) {
        Driver closestDriver = null;
        int minLoad = Integer.MAX_VALUE;

        for (Driver driver : drivers) {
            String driverLocation = driver.getLocation();
            String trimmedDriverLocation = driverLocation.trim();
            System.out.println("Driver Location: [" + driverLocation + "]");
            System.out.println("Trimmed Driver Location: [" + trimmedDriverLocation + "]");

            if (trimmedDriverLocation.equalsIgnoreCase(location)) {
                if (driver.getCurrentLoad() < minLoad) {
                    minLoad = driver.getCurrentLoad();
                    closestDriver = driver;
                }
            }
        }

        return closestDriver;
    }

    /**
     * Generate an invoice text.
     * @param customer  Customer information.
     * @param restaurant Restaurant information.
     * @param driver    Driver information.
     * @return The generated invoice text.
     */
    private static String generateInvoice(Customer customer, Restaurant restaurant, Driver driver) {
        StringBuilder invoiceText = new StringBuilder();
        invoiceText.append("Invoice:\n\n");
        invoiceText.append("Customer Details:\n").append(customer.toString()).append("\n\n");
        invoiceText.append("Restaurant Details:\n").append(restaurant.toString()).append("\n\n");

        if (driver != null && driver.getLocation().equals(restaurant.getLocation())) {
            invoiceText.append("Driver Information:\n").append(driver).append("\n");
        } else {
            invoiceText.append("Sorry! Our drivers are too far away from you to be able to deliver to your location.");
        }

        return invoiceText.toString();
    }

    /**
     * Write the invoice text to a file.
     * @param invoiceText The invoice text to write.
     */
    private static void writeInvoiceToFile(String invoiceText) {
        try (PrintWriter writer = new PrintWriter("invoice.txt")) {
            writer.println(invoiceText);
            System.out.println("\nInvoice saved to invoice.txt");
        } catch (FileNotFoundException e) {
            System.err.println("Error: Unable to save the invoice.");
        }
    }
}
