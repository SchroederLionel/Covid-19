package Events;

import Car.Car;
import Times.Times;

public class LeavingTheTestStation extends Event implements Runnable {
    // Car which is leaving the test station.
    private Car car;
    // Car which left the system after getting tested.
    public static int carLeft = 0;
    // Counts the numbers of passengers per car which are leaving the test station.
    public static int personsInCar =0;
    public LeavingTheTestStation(Car c) {
        this.car = c;
        carLeft++;
        personsInCar += c.getNumberOfPassengers();
    }

    /**
     * Function which allows a car to leave the teststation.
     * Checks if the car has done the test and has handed in the the papers.
     */
    public void leavingTestStation() {
        if (this.car != null && this.getCar().getHasDoneTest() && this.getCar().getArrivesAtTestStation()) {
            this.getCar().setLeavesTestStation(true);
            if (Times.enableDebugging)
                System.out.println("5. Leaves the test station: " + this.car.getIdentifier().getCarId());

            Times.timesList.add(car);
            //System.out.println("Car was waiting: "+car.getIdentifier().getCarId()+" : "+ (car.getStopsWaiting() - car.getStartsWaiting()));
            car = null;
            Times.decrement();
        }

    }

    public Car getCar() {
        return car;
    }


    @Override
    public void run() {
        leavingTestStation();
    }
}
