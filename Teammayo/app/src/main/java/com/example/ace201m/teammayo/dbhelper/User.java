package com.example.ace201m.teammayo.dbhelper;

public class User {

    private String phoneNo;
    private String address;
    private String name;
    private String skill;
    private String city;
    private String state;

//    public User(String phoneNo, String address1, String name, String skill, String city, String state){
//        this.address = address1;
//        this.name = name;
//        this.skill = skill;
//        this.city = city;
//        this.state = state;
//        setPhoneNo(phoneNo);
//    }

    public User(String phoneNo){
        setPhoneNo(phoneNo);
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
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
}