package Car;

import java.util.Date;
import java.util.Random;

public class Car implements Comparable<Car>{
    private int numberOfPassengers;
    private CarUniqueIdentifier identifier;
    private boolean hasTestNotification;
    private boolean hasDoneTest;
    private boolean leavesTestStation;
    private boolean arrivesAtTestStation;
    private String timeStamp;

    public Car(int numberOfPassengers) {
        identifier = new CarUniqueIdentifier();
        this.numberOfPassengers = numberOfPassengers;
        Random random = new Random();
        hasTestNotification = random.nextBoolean();
        hasDoneTest = false;
        leavesTestStation = false;
        arrivesAtTestStation = false;
        timeStamp = new Date().toString();
    }

    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public CarUniqueIdentifier getIdentifier() {
        return identifier;
    }

    public void setNumberOfPassengers(int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }

    public boolean getHasTestNotification() {
        return hasTestNotification;
    }

    public void setHasTestNotification(boolean hasTestNotification) {
        this.hasTestNotification = hasTestNotification;
    }

    public boolean getHasDoneTest() {
        return hasDoneTest;
    }

    public void setHasDoneTest(boolean hasDoneTest) {
        this.hasDoneTest = hasDoneTest;
    }

    public boolean getLeavesTestStation() {
        return leavesTestStation;
    }

    public void setLeavesTestStation(boolean leavesTestStation) {
        this.leavesTestStation = leavesTestStation;
    }

    public boolean getArrivesAtTestStation() {
        return arrivesAtTestStation;
    }

    public void setArrivesAtTestStation(boolean arrivesAtTestStation) {
        this.arrivesAtTestStation = arrivesAtTestStation;
    }

    @Override
    public int compareTo(Car o) {
        return this.timeStamp == o.timeStamp ? 0 : this.timeStamp == null ? -1 : o.timeStamp == null ? 1 : this.timeStamp.compareTo(o.timeStamp);
    }
}
