package Events;

import Car.Car;

public class LeavingTheTestStation extends Event {
    private Car car;
    public LeavingTheTestStation(Car car) {
        this.car = car;
    }

    public boolean leavingTestStation() {
        if(this.getCar().getHasDoneTest() && this.getCar().getArrivesAtTestStation()){
            this.getCar().setLeavesTestStation(true);
            System.out.println("Car with the id: "+this.car.getIdentifier().getCarId() +" leaves the test station.");
            return true;
        }
        return false;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
