package Thread;

import Car.Car;
import Events.Event;

import java.util.Collections;
import java.util.List;

public class GenerateCars implements Runnable {

    List<Car> carQueue;
    public GenerateCars(List<Car> carQueue)  {
        this.carQueue = carQueue;
    }

    public void run()  {
        int max = 5;
        int min = 1;
        int range = max - min + 1;
        for(int i = 0; i < 3 ; i++){
            int res = (int) ( Math.random()*range)+min;
            Car c = new Car(res);
            this.carQueue.add(c);
            Collections.sort(carQueue);
        }
    }
}
