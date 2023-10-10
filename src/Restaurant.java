/**
 * The Restaurant class represents a restaurant entity with various attributes.
 */
public class Restaurant {
    // Attributes of the restaurant
    String restName;
    String restLocation;
    int restContactNumber;
    int numberOfMeals;
    String meals;
    double mealPrice;
    String specialPrepInstruction;
    double totalAmount;

    /**
     * Constructor to create a Restaurant object with specified attributes.
     *
     * @param restName               The name of the restaurant.
     * @param restLocation           The location of the restaurant.
     * @param restContactNumber      The contact number of the restaurant.
     * @param numberOfMeals          The number of meals offered by the restaurant.
     * @param meals                  The list of meals offered by the restaurant.
     * @param mealPrice              The price of a meal.
     * @param specialPrepInstruction Special preparation instructions for meals.
     * @param totalAmount            The total amount for the order.
     */
    public Restaurant(
            String restName,
            String restLocation,
            int restContactNumber,
            int numberOfMeals,
            String meals,
            double mealPrice,
            String specialPrepInstruction,
            double totalAmount) {
        // Initialize the attributes with provided values
        this.restName = restName;
        this.restLocation = restLocation;
        this.restContactNumber = restContactNumber;
        this.numberOfMeals = numberOfMeals;
        this.meals = meals;
        this.mealPrice = mealPrice;
        this.specialPrepInstruction = specialPrepInstruction;
        this.totalAmount = totalAmount;
    }

    /**
     * Convert the Restaurant object to a string representation.
     *
     * @return A string containing restaurant details.
     */
    @Override
    public String toString() {
        return "Restaurant Name: " + restName +
                "\nLocation: " + restLocation +
                "\nContact Number: " + restContactNumber +
                "\nNumber of Meals: " + numberOfMeals +
                "\nMeals: " + meals +
                "\nMeal Price: " + mealPrice +
                "\nSpecial Preparation Instruction: " + specialPrepInstruction +
                "\nTotal Amount: " + totalAmount;
    }

    /**
     * Get the location of the restaurant.
     *
     * @return The location of the restaurant.
     */
    public String getLocation() {
        return restLocation;
    }
}
