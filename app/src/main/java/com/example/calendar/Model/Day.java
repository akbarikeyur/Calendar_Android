package com.example.calendar.Model;

public class Day {
    private String strDate, strDay, strDesc;

    public Day(String strDate, String strDay, String strDesc, String strType) {
        this.strDate = strDate;
        this.strDay = strDay;
        this.strDesc = strDesc;
    }

    public String getStrDate() {
        return strDate;
    }

    public String getStrDay() {
        return strDay;
    }

    public String getStrDesc() {
        return strDesc;
    }

    public void setStrDate(String strDate) {
        this.strDate = strDate;
    }

    public void setStrDay(String strDay) {
        this.strDay = strDay;
    }

    public void setStrDesc(String strDesc) {
        this.strDesc = strDesc;
    }

    public Day() {

    }
}
