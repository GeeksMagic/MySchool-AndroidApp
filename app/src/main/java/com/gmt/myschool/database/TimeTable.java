package com.gmt.myschool.database;

/**
 * Created by user on 6/8/2016.
 */
public class TimeTable {

    private int period;

    private String subject;

    private String teacher;

    private String time;

    public TimeTable(int period, String subject, String teacher, String time) {
        this.period = period;
        this.subject = subject;
        this.teacher = teacher;
        this.time = time;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
