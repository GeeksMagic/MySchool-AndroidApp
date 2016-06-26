package com.gmt.myschool.database.tables;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by user on 6/5/2016.
 */
@Table(name = "Teacher")
public class Teacher extends Model {

    @Column(name = "teacher_id", unique = true, index = true)
    public String teacher_id;
    @Column(name = "name", index = true)
    public String name;
    @Column(name = "address", index = true)
    public String address;
    @Column(name = "phone", index = true)
    public String phone;
    @Column(name = "created_on", index = true)
    public String created_on;
    @Column(name = "last_updated_on", index = true)
    public String last_updated_on;

    public Teacher() {
        // Default constructor required for calls to DataSnapshot.getValue(Parent.class)
    }

    public Teacher(String teacher_id, String name, String address, String phone, String created_on, String last_updated_on) {
        this.teacher_id = teacher_id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.created_on = created_on;
        this.last_updated_on = last_updated_on;
    }

    public String getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(String teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
