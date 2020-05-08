package Car;

public class CarUniqueIdentifier {
    private static int id = 0;
    private static String carIdCountry = "LU";
    private String carId;

    public CarUniqueIdentifier () {
        this.id++;
        carId = carIdCountry + id;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }
}
