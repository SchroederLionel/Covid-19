import Car.Car;
import Events.ArrivingAtTheTestStation;

import Events.Testing;
import Events.TestingTest;
import Times.Times;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;


public class Simulation {


    public static void main(String[] args) throws InterruptedException {

        List<Car> carQueue = Collections.synchronizedList(new ArrayList<Car>());
        AtomicBoolean isTestingStationEmpty = new AtomicBoolean(false);


        Thread t1 = new Thread(new ArrivingAtTheTestStation(carQueue),"Thread T-1");
        t1.start();

        Thread.sleep(300);
        Thread t2 = new Thread(new Testing(carQueue,isTestingStationEmpty), "Thread T-2");
        t2.start();

        //Thread.sleep(3000);
        //Thread t3 = new Thread(new TestingTest(carQueue,isTestingStationEmpty));
       // t3.start();
/*
        Thread t4 = new Thread(LeavingTheTestStation(),"Thread T-3");
        t4.start();*/





       // System.out.println("Finished");
    }
}
