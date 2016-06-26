package com.gmt.myschool.database.tables;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by user on 4/10/2016.
 */
@Table(name = "Passwords")
public class Passwords extends Model {

    @Column(name = "Name", unique = true)
    private String mName = null;

    @Column(name = "Password")
    private String mPassword = null;

    @Column(name = "DummyPassword")
    private String mDummyPassword = null;

    public Passwords() {
        super();
    }

    public Passwords(String name, String pwd) {
        super();
        this.mName = name;
        this.mPassword = pwd;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mAccountName) {
        this.mName = mAccountName;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    public String getDummyPassword() {
        return mDummyPassword;
    }

    public void setDummyPassword(String mDummyPassword) {
        this.mDummyPassword = mDummyPassword;
    }
}
