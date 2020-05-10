package Events;

import Car.Car;


import java.util.Collections;
import java.util.List;

public class ArrivingAtTheTestStation extends Event implements Runnable{
    private List<Car> carQueue = null;

    public ArrivingAtTheTestStation(List<Car> carQueue) {
        super();
        this.carQueue = carQueue;
    }

    public void  arrivesAtTestSTation() throws InterruptedException {
        int max = 5;
        int min = 1;
        int range = max - min + 1;
        for(int i = 0; i < 3 ; i++){
            int res = (int) ( Math.random()*range)+min;
            Car c = new Car(res);
            if(carQueue.size() > 10) {
                System.out.println("0. Car queue is to hight and needs to leave: " + c.getIdentifier().getCarId() );
            }else {
                c.setArrivesAtTestStation(true);
                this.carQueue.add(c);
                Collections.sort(carQueue);
                System.out.println("1. Arrives at the Teststation: " + c.getIdentifier().getCarId() );
            }
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
