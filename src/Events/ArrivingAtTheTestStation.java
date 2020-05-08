package Events;

import Car.Car;

public class ArrivingAtTheTestStation extends Event{
    private Car car;

    public ArrivingAtTheTestStation(Car car) {
        this.car = car;
    }
    public boolean arrivesAtTestSTation() {
        this.car.setArrivesAtTestStation(true);
        System.out.println(this.car.getIdentifier().getCarId()+" Arrives at the testStation and has given the Papers.");
        Testing testing = new Testing(this.car);
        testing.testingThePatient();
        return true;
    }
}
