package com.example.ace201m.teammayo.dbhelper;

public class LearnReq {

    private String contPhone;
    private int learnId;
    private String title;
    private String city;
    private String body;

    public LearnReq(String contPhone, int learnId, String title, int need, String city){
        setContPhone(contPhone);
        setCity(city);
        setLearnId(learnId);
        setTitle(title);
        setBody(body);
    }


    public String getContPhone() {
        return contPhone;
    }

    public void setContPhone(String contPhone) {
        this.contPhone = contPhone;
    }

    public int getLearnId() {
        return learnId;
    }

    public void setLearnId(int learnId) {
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
        return body;
    }

    public void setBody(String city) {
        this.body = body;
    }


}
