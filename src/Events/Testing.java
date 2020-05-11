package Events;

import Car.Car;
import Times.Times;

import java.util.Collections;
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

    /**
     * Function which allows to check if the driver has the appropriate Papers.
     * @throws InterruptedException
     */
    public void handInPapers() throws InterruptedException {
        int i = carQueue.size();
        int k = 0;

        while(this.carQueue.size() > 0){
            if(Times.enableDebugging)
                System.out.println("2. Driver hands in the papers: "+carQueue.get(0).getIdentifier().getCarId());
            Thread.sleep(Times.calculateTimeDistributionForHandingInTestNotentification());

            while(isTestingStationEmpty.get()){
                if(k == 0) {
                    if(carQueue.size() > 1)
                        if(Times.enableDebugging)
                            System.out.println(""+carQueue.get(0).getIdentifier().getCarId() +" : is waiting to drive to the test station.");
                    k++;
                }
            }

            if(carQueue != null && carQueue.size() > 0){
                Car c = carQueue.get(0);
                if(c.getHasTestNotification()) {
                    isTestingStationEmpty.set(true);
                    if(Times.enableDebugging)
                        System.out.println("3. Drives to the Teststation for Covid19 : "+c.getIdentifier().getCarId());
                    Thread t3 = new Thread(new TestingTest(carQueue,isTestingStationEmpty));
                    t3.start();
                    while(i == carQueue.size()){
                        Thread.sleep(1);
                    }
                    i = carQueue.size();
                    while(!isTestingStationEmpty.get()){
                       Thread.sleep(1);
                    }
                    if(carQueue.size() == 0){
                        break;
                    }
                }
                else {
                    if(Times.enableDebugging)
                        System.out.println("2A.  Car does not have the papers and leaves the queue :"+c.getIdentifier().getCarId());
                    carQueue.remove(0);
                    Collections.sort(carQueue);
                }



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
