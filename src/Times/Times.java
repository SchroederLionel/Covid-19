package Times;




import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.math3.distribution.UniformRealDistribution;

import Car.Car;

public class Times {

    public static List<Car> timesList = new ArrayList<>();
    public static AtomicInteger carsCurrentlyInTheSystem = new AtomicInteger(0);
    public static long currentTimeMillis = System.currentTimeMillis();
    public static boolean seeHowManyCarsAreInTheSystem =true;
    public static int carQueueSize = 10;
    public static boolean enableDebugging = false;
    public static long carGeneratingTimer = 7200;

    public static long arrivingMax = 120;
    public static long arrivingMin = 30;

    public static long handInPapersMax = 120;
    public static long handInPapersMin = 60;

    public static long testForCovid = 240;

    public static void setCarQueueSize(int number) {
        carQueueSize = number;
    }
    public static void setTimeFaster(long multiplicator) {

        carGeneratingTimer = carGeneratingTimer/multiplicator;

        arrivingMax = arrivingMax/multiplicator;
        arrivingMin = arrivingMin/multiplicator;

        handInPapersMax = handInPapersMax/multiplicator;
        handInPapersMin = handInPapersMin/multiplicator;

        testForCovid =  testForCovid/multiplicator;

    }

    public static void changeDebugginMod() {
        enableDebugging = !enableDebugging;
    }



    public static long calculateTimeDistributionForArrivingAtTheCarStation() {
        UniformRealDistribution u = new UniformRealDistribution(handInPapersMin,handInPapersMax);
        return (long) u.sample();
    }

    public static long calculateTimeDistributionForHandingInTestNotentification() {
        UniformRealDistribution u = new UniformRealDistribution(arrivingMin,arrivingMax);
        return (long) u.sample();
    }

    /**
     * Function which allows to increment the cars in the system by 1.
     */
    public static void increment() {
        carsCurrentlyInTheSystem.set(carsCurrentlyInTheSystem.get()+1);
    }

    /**
     * Function which allows to decrease the cars in the system by 1.
     */
    public static void decrement() {
        carsCurrentlyInTheSystem.set(carsCurrentlyInTheSystem.get()-1);
    }

    /**
     * Function which allows to disable viewing how many cars are in the system.
     */
    public static void dontWatchCurrentCars() {
        seeHowManyCarsAreInTheSystem = false;
    }





}
