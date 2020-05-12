import Car.Car;
import Events.ArrivingAtTheTestStation;

import Events.LeavingTheTestStation;
import Events.Testing;
import Events.TestingTest;
import Times.Times;
import Thread.SystemSpy;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class Simulation {


    public static void main(String[] args) throws InterruptedException {


        List<Car> carQueue = Collections.synchronizedList(new ArrayList<Car>());
        AtomicBoolean isTestingStationEmpty = new AtomicBoolean(false);
        // Times.setTimeSlower(10);

        Times.carQueueSize = 12;
        // Times.changeDebugginMod();
        Thread t1 = new Thread(new ArrivingAtTheTestStation(carQueue),"Thread T-1");
        t1.start();

        while(carQueue.size()<2){}
        Thread t2 = new Thread(new Testing(carQueue,isTestingStationEmpty), "Thread T-2");
        t2.start();

        Thread t3 = new Thread(new SystemSpy(carQueue),"Thread Spy");
        // t3.start();
        t2.join();
        t1.join();
        System.out.println("Persons in a car: "+ArrivingAtTheTestStation.personsInCar);
        System.out.println("Cars generated: "+ArrivingAtTheTestStation.carGenerated);
        System.out.println("Cars left due to the queue: "+ArrivingAtTheTestStation.carLeftLaneSinceItIsFull);
        System.out.println("Cars left after getting tested: "+ LeavingTheTestStation.carLeft);
        System.out.println("Persons in car which were tested: "+ LeavingTheTestStation.personsInCar);
        long time = 0;

        for(int i = 0; i < Times.timesList.size(); i++) {
            time += Times.timesList.get(i).getStopsWaiting() - Times.timesList.get(i).getStartsWaiting();
            System.out.println("Car with id: "+Times.timesList.get(i).getIdentifier().getCarId() +" was waiting "+(time)+" s");
        }


        System.out.println("-------------------------------------------------------------------");

        System.out.println("Average waiting time per car: "+ (time/Times.timesList.size()) + " s");


    }
}
