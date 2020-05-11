import Car.Car;
import Events.ArrivingAtTheTestStation;

import Events.LeavingTheTestStation;
import Events.Testing;
import Events.TestingTest;
import Times.Times;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class Simulation {


    public static void main(String[] args) throws InterruptedException {

        List<Car> carQueue = Collections.synchronizedList(new ArrayList<Car>());
        AtomicBoolean isTestingStationEmpty = new AtomicBoolean(false);

        // Change integer value to set queue size;
        Times.setCarQueueSize(10);
        // Comment to see whats going on in the system.
        Times.changeDebugginMod();


        Thread t1 = new Thread(new ArrivingAtTheTestStation(carQueue),"Thread T-1");
        t1.start();

        Thread.sleep(300);
        Thread t2 = new Thread(new Testing(carQueue,isTestingStationEmpty), "Thread T-2");
        t2.start();
        t2.join();

        System.out.println("Persons in a car: "+ArrivingAtTheTestStation.personsInCar);
        System.out.println("Cars generated: "+ArrivingAtTheTestStation.carGenerated);
        System.out.println("Cars left due to the queue: "+ArrivingAtTheTestStation.carLeftLaneSinceItIsFull);
        System.out.println("Cars left after getting tested: "+ LeavingTheTestStation.carLeft);
        System.out.println("Persons in car which were tested: "+ LeavingTheTestStation.personsInCar);



        //Thread.sleep(3000);
        //Thread t3 = new Thread(new TestingTest(carQueue,isTestingStationEmpty));
       // t3.start();
/*
        Thread t4 = new Thread(LeavingTheTestStation(),"Thread T-3");
        t4.start();*/





       // System.out.println("Finished");
    }
}
