package com.gmt.myschool.database.tables;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by user on 6/5/2016.
 */
@Table(name = "Parent")
public class Parent extends Model{

    @Column(name = "roll_number", unique = true, index = true)
    public String roll_number;
    @Column(name = "password")
    public String password;
    @Column(name = "student_name", index = true)
    public String student_name;
    @Column(name = "clas")
    public String clas;
    @Column(name = "image_id")
    public String image_id;
    @Column(name = "parent_name")
    public String parent_name;
    @Column(name = "parent_phone")
    public String parent_phone;
    @Column(name = "parent_address")
    public String parent_address;
    @Column(name = "created_on", index = true)
    public String created_on;
    @Column(name = "last_updated_on", index = true)
    public String last_updated_on;

    public Parent() {
        // Default constructor required for calls to DataSnapshot.getValue(Parent.class)
    }

    public String getRoll_number() {
        return roll_number;
    }

    public void setRoll_number(String roll_number) {
        this.roll_number = roll_number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getClas() {
        return clas;
    }

    public void setClas(String clas) {
        this.clas = clas;
    }

    public String getImage_id() {
        return image_id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }

    public String getParent_name() {
        return parent_name;
    }

    public void setParent_name(String parent_name) {
        this.parent_name = parent_name;
    }

    public String getParent_phone() {
        return parent_phone;
    }

    public void setParent_phone(String parent_phone) {
        this.parent_phone = parent_phone;
    }

    public String getParent_address() {
        return parent_address;
    }

    public void setParent_address(String parent_address) {
        this.parent_address = parent_address;
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
