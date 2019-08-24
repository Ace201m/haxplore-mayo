package com.example.ace201m.contractor.dbhelper;

public class User {

    private String phoneNo;
    private String pin;

    public User(String phoneNo, String pin){
        setPhoneNo(phoneNo);
        setPin(pin);
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
}