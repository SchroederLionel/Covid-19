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
    public String timeStamp;
    private String currentStation;
    private long startsWaiting;
    private long stopsWaiting;

    public Car(int numberOfPassengers) {
        identifier = new CarUniqueIdentifier();
        this.numberOfPassengers = numberOfPassengers;
        Random random = new Random();
        hasTestNotification = random.nextBoolean();
        // hasTestNotification = true;
        hasDoneTest = false;
        leavesTestStation = false;
        arrivesAtTestStation = false;
        timeStamp = String.valueOf(System.currentTimeMillis());
        currentStation ="Waiting Lane";
    }

    public String getCurrentStation() {
        return currentStation;
    }

    public synchronized  void setCurrentStation(String stationName){
        this.currentStation = stationName;
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


    public long getStartsWaiting() {
        return startsWaiting;
    }

    public void setStartsWaiting(long startsWaiting) {
        this.startsWaiting = startsWaiting;
    }

    public long getStopsWaiting() {
        return stopsWaiting;
    }

    public void setStopsWaiting(long stopsWaiting) {
        this.stopsWaiting = stopsWaiting;
    }

    @Override
    public int compareTo(Car o) {
        return this.timeStamp == o.timeStamp ? 0 : this.timeStamp == null ? -1 : o.timeStamp == null ? 1 : this.timeStamp.compareTo(o.timeStamp);
    }
}
