package com.gmt.myschool.database.tables;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by user on 6/5/2016.
 */
@Table(name = "Student")
public class Student extends Model {

    @Column(name = "student_id", unique = true, index = true)
    public String student_id;
    @Column(name = "name", index = true)
    public String name;
    @Column(name = "standard", index = true)
    public String standard;
    @Column(name = "section", index = true)
    public String section;
    @Column(name = "roll_number")
    public String roll_number;
    @Column(name = "teacher_id", index = true)
    public String teacher_id;
    @Column(name = "created_on", index = true)
    public String created_on;
    @Column(name = "last_updated_on", index = true)
    public String last_updated_on;

    public Student() {
        // Default constructor required for calls to DataSnapshot.getValue(Parent.class)
    }

    public Student(String student_id, String name, String standard, String section, String roll_number, String teacher_id, String created_on, String last_updated_on) {
        this.student_id = student_id;
        this.name = name;
        this.standard = standard;
        this.section = section;
        this.roll_number = roll_number;
        this.teacher_id = teacher_id;
        this.created_on = created_on;
        this.last_updated_on = last_updated_on;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getRoll_number() {
        return roll_number;
    }

    public void setRoll_number(String roll_number) {
        this.roll_number = roll_number;
    }

    public String getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(String teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getCreated_on() {
        return created_on;
    }

    public void setCreated_on(String created_on) {
        this.created_on = created_on;
    }

    public String getLast_updated_on() {
        return last_updated_on;
    }

    public void setLast_updated_on(String last_updated_on) {
        this.last_updated_on = last_updated_on;
    }
}
