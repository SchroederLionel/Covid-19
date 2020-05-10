package Events;

import Car.Car;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class Testing extends Event implements Runnable{
    private List<Car> carQueue = null;
    private AtomicBoolean isTestingStationEmpty = null;

    public Testing(List<Car> carQueue, AtomicBoolean isHandInStationEmpty) {
        super();
        this.carQueue = carQueue;
        this.isTestingStationEmpty = isHandInStationEmpty;
    }
    public void handInPapers() throws InterruptedException {
        int i = 0;
        while(this.carQueue.size()>0){
            if(carQueue.size() == 1) i =0;
            Car c = carQueue.get(i);
            System.out.println("2. Hands in Test Notentification to Employee :"+c.getIdentifier().getCarId());
            while(isTestingStationEmpty.get()){
                Thread.sleep(4000);
                System.out.println(" "+c.getIdentifier().getCarId() +" : is waiting to drive to the test station.");
            }
            i=0;
            if(carQueue != null && carQueue.size() > 0){
                System.out.println("3. Drives to the Teststation for Covid19 : "+c.getIdentifier().getCarId());
                isTestingStationEmpty.set(true);
                Thread t3 = new Thread(new TestingTest(carQueue,isTestingStationEmpty));
                t3.start();
                i++;
            }
        }
    }

    @Override
    public void run() {
        try {
            handInPapers();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
