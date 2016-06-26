package com.gmt.myschool.database;

/**
 * Created by user on 6/8/2016.
 */
public class MarksCard {

    private String rollNumber;

    private String testNumber;

    private String year;

    private String subject;

    private String min;

    private String max;

    private String obtained;

    private String percentage;

    public MarksCard(String roll, String testNumber, String year, String subject, String min, String max, String obtained, String percentage) {
        this.rollNumber = roll;
        this.testNumber = testNumber;
        this.year = year;
        this.subject = subject;
        this.min = min;
        this.max = max;
        this.obtained = obtained;
        this.percentage = percentage;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getTestNumber() {
        return testNumber;
    }

    public void setTestNumber(String testNumber) {
        this.testNumber = testNumber;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getObtained() {
        return obtained;
    }

    public void setObtained(String obtained) {
        this.obtained = obtained;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }
}
