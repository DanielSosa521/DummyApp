package com.sosa.dummyapp.contentresources;

import java.util.Arrays;

/**
 * Class DashboardResource
 * This class is an encapsulating object for
 * JSON data received from the SmartPlug System API
 * Member objects reflect the Series of DataPoints
 * used in the Month, Day, and Plugs tabs of the
 * viewpager.
 * See SmartPlugAPI github page to learn more
 * SmartPlugAPI : https://github.com/DanielSosa521/SmartPlugAPI
 */

public class DashboardResource {

    private final Integer[] points;

    public Integer[] getPoints(){
        return points;
    }


    DashboardResource(){
        points = null;
    }

    @Override
    public String toString() {
        return "DashboardResource{" +
                "points=" + Arrays.toString(points) +
                '}';
    }
}
