package Events;

import Car.Car;
import Times.Times;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Class which allows to Hand in the papers and checks if they are valid or not.
 */
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
     * Creates a new Thread each time a driver has handed in the papers for furtherprocedure. In this case the actual testing phase.
     * @throws InterruptedException
     */
    public  void handInPapers() throws InterruptedException {
        int i = carQueue.size();
        int k = 0;
        int checkIfTheSame = 0;
        long timeMillis =  System.currentTimeMillis()+Times.carGeneratingTimer;

        while(carQueue.size() == 0){}
        while(carQueue.size() > 0  || System.currentTimeMillis() <= timeMillis ){
            Car saver = null;
            synchronized (carQueue) {
                if(carQueue.size()==1){
                    checkIfTheSame=0;
                }
                if (!carQueue.get(checkIfTheSame).getCurrentStation().contains("Hands")) {
                    carQueue.get(checkIfTheSame).setCurrentStation("Hands in papers");
                    saver = carQueue.get(checkIfTheSame);
                }


                if (Times.enableDebugging)
                    System.out.println("2. Driver hands in the papers: " + carQueue.get(0).getIdentifier().getCarId());
            }


            Thread.sleep(Times.calculateTimeDistributionForHandingInTestNotentification());

            while(isTestingStationEmpty.get()){
                if(k == 0) {
                    if(carQueue.size() > 1)
                        if(Times.enableDebugging) {
                            System.out.println(""+carQueue.get(0).getIdentifier().getCarId() +" : is waiting to drive to the test station.");


                        }
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
                    if(saver == carQueue.get(0))
                    {
                        checkIfTheSame =1;
                    }else checkIfTheSame =0;

                    if(carQueue.size() == 0){
                        break;
                    }
                }
                else {
                    if(Times.enableDebugging)
                        System.out.println("2A.  Car does not have the papers and leaves the queue :"+c.getIdentifier().getCarId());

                    synchronized (carQueue){
                        carQueue.remove(0);
                        Collections.sort(carQueue);}
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
