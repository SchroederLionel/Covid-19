package Events;

import Car.Car;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Event {

    static int queueSize = 10;
    List<Car> carQueue;
    private static int id = 0;

    public Event(List<Car> carQueue){
        this.id = id++;
        this.carQueue = carQueue;
    }

    public Event() {

    }

    public void processEvent() throws InterruptedException {
        int  index = 0;
        while(true){
            if(carQueue.size() >  0){
                carQueue.get(index).setArrivesAtTestStation(true);
                // ArrivingAtTheTestStation testStation = new ArrivingAtTheTestStation(c);
                Thread t1 = new Thread(new ArrivingAtTheTestStation(carQueue),"Thread  T-2");
                t1.start();
            }
        }
    }

    public boolean addToQueue(Car car) {
        if(carQueue.size() > queueSize) {
            return false;
        }else if (car.getHasTestNotification() == false) {
            return false;
        }else {
            carQueue.add(car);
            Collections.sort(carQueue);
            return true;
        }
    }

    /**
     * Removing car from the waiting list
     * @return Car
     */
    public Car removeFromQueue() {
        if(carQueue != null &&carQueue.size() > 0) {
           Car car = carQueue.remove(0);
           Collections.sort(carQueue);
           if(car.getHasTestNotification())
               return  car;
        }
        return null;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static int getQueueSize() {
        return queueSize;
    }

    public static void setQueueSize(int queueSize) {
        Event.queueSize = queueSize;
    }


}
