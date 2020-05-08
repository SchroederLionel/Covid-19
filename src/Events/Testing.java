package Events;

import Car.Car;

public class Testing extends Event {
    private Car car;

    public Testing(Car car) {
        this.car =car;
    }
    public boolean testingThePatient() {
        if(this.getCar().getArrivesAtTestStation())
            this.getCar().setHasDoneTest(true);
        else return false;
        System.out.println(this.getCar().getIdentifier().getCarId() +": Is getting tested.");
        LeavingTheTestStation leavingTheTestStation =  new LeavingTheTestStation(car);
        leavingTheTestStation.leavingTestStation();
        return true;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
