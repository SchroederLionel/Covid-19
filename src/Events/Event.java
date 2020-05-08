package Events;

import Car.Car;

import java.util.*;

public class Event {

    private static int queueSize = 10;
    List<Car> carQueue = Collections.synchronizedList(new ArrayList<Car>());
    private static int id = 0;

    public Event(){
        this.id = id++;
    }

    public void processEvent() throws InterruptedException {
        System.out.println("Processing Event");
        int  index = 0;
        while(true){
            if(carQueue.size() >  0){
                carQueue.get(index).setArrivesAtTestStation(true);
                Car c = carQueue.get(0);
                carQueue.remove(carQueue.get(0));
                ArrivingAtTheTestStation testStation = new ArrivingAtTheTestStation(c);
                testStation.arrivesAtTestSTation();
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
        if(carQueue.size() > 0) {
           Car car = carQueue.remove(carQueue.size()-1);
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
