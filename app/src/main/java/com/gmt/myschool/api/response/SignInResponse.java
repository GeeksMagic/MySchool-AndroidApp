package com.gmt.myschool.api.response;

import com.gmt.myschool.enums.Gender;
import com.gmt.myschool.enums.UserTypes;
import com.google.gson.annotations.Expose;

import java.sql.Date;

/**
 * Created by user on 6/26/2016.
 */
public class SignInResponse extends MessageResponse {

    @Expose
    private User user;

    public SignInResponse() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public class User {

        @Expose
        private Long id;

        @Expose
        private String firstName;

        @Expose
        private String lastName;

        @Expose
        private String username;

        @Expose
        private String password;

        @Expose
        private String email;

        @Expose
        private String phone;

        @Expose
        private String address;

        @Expose
        private String city;

        @Expose
        private String state;

        @Expose
        private Long pincode;

        @Expose
        private String dob;

        @Expose
        private Gender gender;

        @Expose
        private UserTypes userType;

        @Expose
        private Long school_id;

        @Expose
        private Long image_id;

        @Expose
        private String createdOn;

        @Expose
        private String updatedOn;

        @Expose
        private Boolean isDeleted;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
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

        public String getCreatedOn() {
            return createdOn;
        }

        public void setCreatedOn(String createdOn) {
            this.createdOn = createdOn;
        }

        public String getUpdatedOn() {
            return updatedOn;
        }

        public void setUpdatedOn(String updatedOn) {
            this.updatedOn = updatedOn;
        }

        public Boolean getIsDeleted() {
            return isDeleted;
        }

        public void setIsDeleted(Boolean isDeleted) {
            this.isDeleted = isDeleted;
        }
    }
}
