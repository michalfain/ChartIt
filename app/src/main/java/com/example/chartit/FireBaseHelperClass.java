package com.example.chartit;

import java.util.HashMap;

public class FireBaseHelperClass {
    String fullName, email;
    HashMap<String, Chart> userCharts;

    public FireBaseHelperClass() {
    }

    public FireBaseHelperClass(String fullName, String email, HashMap userCharts) {
        this.fullName = fullName;
        this.email = email;
        this.userCharts = userCharts;
    }

    public HashMap<String, Chart> getUserCharts() {
        return userCharts;
    }

    public void setUserCharts(HashMap<String, Chart> userCharts) {
        this.userCharts = userCharts;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
