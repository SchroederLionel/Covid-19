package Events;

import Car.Car;
import Times.Times;

import java.util.List;

/**
 * Class which allows to generate cars. Whenever the queue hits its limit the car gets directly dequeued.
 */
public class ArrivingAtTheTestStation extends Event implements Runnable{
    private List<Car> carQueue = null;

    // Counts total number of persons in the cars (even if the queue is full).
    public static int personsInCar = 0;
    // Counts the total number of cars generated.
    public static int carGenerated = 0;
    // Counts the number of cars which were required due to a full queue.
    public static int carLeftLaneSinceItIsFull;

    public ArrivingAtTheTestStation(List<Car> carQueue) {
        super();
        this.carQueue = carQueue;
    }

    /**
     * Function which allows to generate cars. The cars can arrive in the interval of 30s-120s.
     * In case the queue size exceeds the number ex. 10. The car needs to leave the queue directly.
     * @throws InterruptedException
     */
    public void  arrivesAtTestSTation() throws InterruptedException {
        int max = 5;
        int min = 1;
        int range = max - min + 1;

        long time = System.currentTimeMillis();
        long timeTo = System.currentTimeMillis() + 7200;
        while(time <= timeTo){
            Thread.sleep(Times.calculateTimeDistributionForArrivingAtTheCarStation());
            int res = (int) ( Math.random()*range)+min;
            Car c = new Car(res);
            personsInCar += c.getNumberOfPassengers();
            carGenerated++;
            if(carQueue.size() >= Times.carQueueSize) {
                if(Times.enableDebugging)
                    System.out.println("0. Car queue is to hight and needs to leave: " + c.getIdentifier().getCarId() );
                carLeftLaneSinceItIsFull++;
            }else {
                c.setArrivesAtTestStation(true);
                c.setStartsWaiting(System.currentTimeMillis());
                c.setCurrentStation("Arrives Test Station");
                synchronized (carQueue){
                    carQueue.add(c);}
                // Collections.sort(carQueue);
                if(Times.enableDebugging)
                    System.out.println("1. Arrives at the Teststation: " + c.getIdentifier().getCarId() );
            }
            time = System.currentTimeMillis();
        }
    }

    @Override
    public void run() {
        try {
            arrivesAtTestSTation();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
