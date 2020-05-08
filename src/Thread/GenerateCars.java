package Thread;

import Car.Car;
import Events.Event;

public class GenerateCars implements Runnable {
    Event e;
    public GenerateCars(Event e)  {
        this.e = e;
    }

    public void run() {
        int max = 5;
        int min = 1;
        int range = max - min + 1;
        for(int i = 0; i < 10 ; i++){
            int res = (int) ( Math.random()*range)+min;
            Car c = new Car(res);
            this.e.addToQueue(c);
            System.out.println("Car added to queue with id : "+ c.getIdentifier().getCarId());
            try {
                System.out.println("Thread is sleeping");
                Thread.sleep(100);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        }
    }
}
