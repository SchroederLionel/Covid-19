package Events;

import Car.Car;
import Times.Times;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class TestingTest implements Runnable{
    private List<Car> carQueue = null;
    private AtomicBoolean isTestingStationEmpty;

    public TestingTest(List<Car> carQueue, AtomicBoolean isTestingStationEmpty) {
        super();
        this.carQueue = carQueue;
        this.isTestingStationEmpty = isTestingStationEmpty;
    }

    /**
     * Function which allows to test a patient.
     * Removes the car from the car queue so that a car can join again the waiting queue.
     */
    public  void testingThePatient() throws InterruptedException {
        synchronized (isTestingStationEmpty){
            if(this.carQueue.size()>0){
                if(carQueue != null && carQueue.size() > 0){
                    Car c = carQueue.remove(0);
                    if(Times.enableDebugging)
                        System.out.println("4. Is getting Tested :"+c.getIdentifier().getCarId());
                    Thread.sleep(Times.testForCovid*c.getNumberOfPassengers());
                    c.setHasDoneTest(true);
                    Collections.sort(carQueue);
                    LeavingTheTestStation leavingTheTestStation  = new LeavingTheTestStation(c);
                    leavingTheTestStation.leavingTestStation();
                    isTestingStationEmpty.set(false);
                }
            }
        }
    }

    @Override
    public void run() {
        try {
            testingThePatient();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
