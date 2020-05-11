package com.vytrack.utilities;

public class WaitStatic {

    public static void waitLine(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
