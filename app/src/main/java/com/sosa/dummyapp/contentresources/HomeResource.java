package com.sosa.dummyapp.contentresources;

import java.util.Arrays;

/**
 * Class HomeResource
 * This class is an encapsulating object meant to
 * obtain JSON data received from the SmartPlug System
 * API. It's member objects reflect the data displayed
 * in the Home view of the App
 * See the SmartPlugAPI github page to further understand
 * implementation format
 * SmartPlugAPI : https://github.com/DanielSosa521/SmartPlugAPI
 */

public class HomeResource {

    private final String month;
    private final String summary;
    private final String savings;
    private final Integer delta;
    private final String[] plugs;

    public String getMonth() {
        return month;
    }

    public String getSummary() {
        return summary;
    }

    public String getSavings() {
        return savings;
    }

    public Integer getDelta() {
        return delta;
    }

    public String[] getPlugs() {
        return plugs;
    }

    public HomeResource(){
        month = null;
        summary = null;
        savings = null;
        delta = null;
        plugs = null;
    }

    @Override
    public String toString() {
        return "HomeResource{" +
                "month='" + month + '\'' +
                ", summary='" + summary + '\'' +
                ", savings='" + savings + '\'' +
                ", delta=" + delta +
                ", plugs=" + Arrays.toString(plugs) +
                '}';
    }
}
