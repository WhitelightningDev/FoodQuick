public class Driver {
    private final String driverID;
    private final String location;
    private final int currentLoad;

    public Driver(String driverID, String location, int currentLoad) {
        if (driverID == null || driverID.isEmpty()) {
            throw new IllegalArgumentException("Driver ID cannot be empty or null");
        }

        if (location == null || location.isEmpty()) {
            throw new IllegalArgumentException("Location cannot be empty or null");
        }

        if (currentLoad < 0) {
            throw new IllegalArgumentException("Current load cannot be negative");
        }

        this.driverID = driverID;
        this.location = location;
        this.currentLoad = currentLoad;
    }

    @Override
    public String toString() {
        return "Driver ID: " + driverID +
                "\nLocation: " + location +
                "\nCurrent Load: " + currentLoad;
    }

    public int getCurrentLoad() {
        return currentLoad;
    }

    public String getLocation() {
        return location;
    }
}
