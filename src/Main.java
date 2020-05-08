import Events.Event;
import Thread.GenerateCars;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Event event = new Event();
        Thread t1 = new Thread(new GenerateCars(event),"Thread  T-1");
        t1.start();

        event.processEvent();
        System.out.println("Finished");
    }
}
