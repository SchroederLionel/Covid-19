package Events;

import Car.Car;

public class LeavingTheTestStation extends Event implements Runnable {
    private Car car;
    public LeavingTheTestStation(Car c) {
        this.car = c;
    }


    public void leavingTestStation() {
        if(this.car != null && this.getCar().getHasDoneTest() && this.getCar().getArrivesAtTestStation()){
            this.getCar().setLeavesTestStation(true);
            System.out.println("5. Leaves the test station: "+this.car.getIdentifier().getCarId() );
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
