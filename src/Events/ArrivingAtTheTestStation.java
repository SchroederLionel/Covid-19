package Events;

import Car.Car;
import Times.Times;


import java.text.SimpleDateFormat;
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
        Times.calculateTimeDistributionForArrivingAtTheCarStation();
        long time = System.currentTimeMillis();
        long timeTo = System.currentTimeMillis() + 7200;
        System.out.println(timeTo);
        while(time <= timeTo){
            Thread.sleep(Times.calculateTimeDistributionForArrivingAtTheCarStation());
            int res = (int) ( Math.random()*range)+min;
            Car c = new Car(res);
            if(carQueue.size() > 10) {
                System.out.println("0. Car queue is to hight and needs to leave: " + c.getIdentifier().getCarId() );
            }else {
                c.setArrivesAtTestStation(true);
                carQueue.add(c);
                Collections.sort(carQueue);
                System.out.println("1. Arrives at the Teststation: " + c.getIdentifier().getCarId() );
            }

            time = System.currentTimeMillis();
            System.out.println(time + " : " + (timeTo-time));
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
