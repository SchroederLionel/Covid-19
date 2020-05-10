package Events;

import Car.Car;

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
    public  void testingThePatient() throws InterruptedException {

        while(this.carQueue.size()>0){
            Thread.sleep(3000);
            if(carQueue != null && carQueue.size() > 0){
                Car c = carQueue.get(0);
                System.out.println("4. Is getting Tested :"+c.getIdentifier().getCarId());
                Thread.sleep(10000);
                c.setHasDoneTest(true);
                carQueue.remove(0);
                Collections.sort(carQueue);
                isTestingStationEmpty.set(false);
                LeavingTheTestStation leavingTheTestStation  = new LeavingTheTestStation(c);
                leavingTheTestStation.leavingTestStation();
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
