package com.vytrack.utilities.DeadUtilities;

public class StaticWaits {

    public static void waitLine(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
