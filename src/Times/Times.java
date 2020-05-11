package Times;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.distribution.UniformRealDistribution;

import java.util.Random;

public class Times {
    public static boolean enableDebugging = true;
    public static long carGeneratingTimer = 7200;

    public static long arrivingMax = 120;
    public static long arrivingMin = 30;

    public static long handInPapersMax = 120;
    public static long handInPapersMin = 60;

    public static long testForCovid = 240;


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





}
