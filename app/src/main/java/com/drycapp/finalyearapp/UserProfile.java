package com.drycapp.finalyearapp;

public class UserProfile {
    public String user_phone;
    public String user_email;
    public String user_name;

    public UserProfile() {
    }

    public UserProfile(String user_phone, String user_email, String user_name) {
        this.user_phone = user_phone;
        this.user_email = user_email;
        this.user_name = user_name;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
}