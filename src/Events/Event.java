package Events;

import Car.Car;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Event {

    static int queueSize = 10;
    private static int id = 0;

    public Event(List<Car> carQueue){
        this.id = id++;

    }

    public Event() {

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
