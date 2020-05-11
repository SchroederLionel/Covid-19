package Thread;

import Car.Car;
import Times.Times;

import java.util.List;

/**
 * The SystemSpy allows to check what is going on inside the system.
 */
public class SystemSpy implements Runnable {

    List<Car> carQueue;

    public SystemSpy(List<Car> carQueue) {
        this.carQueue = carQueue;
    }

    @Override
    public void run() {
        String format = "|%1$-10s|%2$-10s|%3$-20s|\n";
        long timeTo = System.currentTimeMillis() + Times.carGeneratingTimer;

        long time = System.currentTimeMillis();


        while(time <= timeTo+5000){
            synchronized (carQueue){
                if(!(carQueue.size() ==0))System.out.format(format, " Car ID", " Timestamp", " Station");
            }

            for (int i = 0; i < carQueue.size(); i++) {
                synchronized (carQueue){
                    System.out.format(format, carQueue.get(i).getIdentifier().getCarId(),time - Long.valueOf(carQueue.get(i).timeStamp), carQueue.get(i).getCurrentStation());}
            }
            if(!(carQueue.size() ==0)) System.out.println("--------------------------------------------");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            time = System.currentTimeMillis();
        }


    }
}
