package com.example.ace201m.teammayo.dbhelper;

public class LearnReq {

    private String contPhone;
    private String learnId;
    private String title;
    private String city;
    private String body;

    public LearnReq(String contPhone, String learnId, String title, String city, String bod){
        setContPhone(contPhone);
        setCity(city);
        setLearnId(learnId);
        setTitle(title);
        this.body = bod;
    }


    public String getContPhone() {
        return contPhone;
    }

    public void setContPhone(String contPhone) {
        this.contPhone = contPhone;
    }

    public String getLearnId() {
        return learnId;
    }

    public void setLearnId(String learnId) {
        this.learnId = learnId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(String city) {
        this.body = body;
    }


}
