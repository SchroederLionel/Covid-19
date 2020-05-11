package Events;

import Car.Car;

import java.util.concurrent.atomic.AtomicInteger;

public class LeavingTheTestStation extends Event implements Runnable {
    private Car car;
    static int carLeft = 0;
    public LeavingTheTestStation(Car c) {
        this.car = c;
        carLeft++;
    }

    public void leavingTestStation() {
        if(this.car != null && this.getCar().getHasDoneTest() && this.getCar().getArrivesAtTestStation()){
            this.getCar().setLeavesTestStation(true);
            System.out.println("5. Leaves the test station: "+this.car.getIdentifier().getCarId() +"Number of Car left:" +carLeft);
            car = null;
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
