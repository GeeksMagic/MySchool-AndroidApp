package com.gmt.myschool.database.tables;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.gmt.myschool.enums.Gender;
import com.gmt.myschool.enums.UserTypes;
import com.google.gson.annotations.Expose;

import java.sql.Date;

/**
 * Created by user on 6/23/2016.
 */
@Table(name = "users")
public class Users extends Model {

    public Users() {
    }

    @Expose
    @Column(name = "id", unique = true)
    private Long id;

    @Expose
    @Column(name = "first_name")
    private String firstName;

    @Expose
    @Column(name = "last_name")
    private String lastName;

    @Expose
    @Column(name = "username")
    private String username;

    @Expose
    @Column(name = "password")
    private String password;

    @Expose
    @Column(name = "email")
    private String email;

    @Expose
    @Column(name = "phone")
    private String phone;

    @Expose
    @Column(name = "address")
    private String address;

    @Expose
    @Column(name = "city")
    private String city;

    @Expose
    @Column(name = "state")
    private String state;

    @Expose
    @Column(name = "pincode")
    private Long pincode;

    @Expose
    @Column(name = "dob")
    private String dob;

    @Expose
    @Column(name = "gender")
    private Gender gender;

    @Expose
    @Column(name = "user_type")
    private UserTypes userType;

    @Expose
    @Column(name = "school_id")
    private Long school_id;

    @Expose
    @Column(name = "image_id")
    private Long image_id;

    @Expose
    @Column(name = "created_on")
    private Date createdOn;

    @Expose
    @Column(name = "updated_on")
    private Date updatedOn;

    @Expose
    @Column(name = "is_deleted")
    private Boolean isDeleted;

    public Long getID() {
        return id;
    }

    public void setID(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getPincode() {
        return pincode;
    }

    public void setPincode(Long pincode) {
        this.pincode = pincode;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public UserTypes getUserType() {
        return userType;
    }

    public void setUserType(UserTypes userType) {
        this.userType = userType;
    }

    public Long getSchool_id() {
        return school_id;
    }

    public void setSchool_id(Long school_id) {
        this.school_id = school_id;
    }

    public Long getImage_id() {
        return image_id;
    }

    public void setImage_id(Long image_id) {
        this.image_id = image_id;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}
