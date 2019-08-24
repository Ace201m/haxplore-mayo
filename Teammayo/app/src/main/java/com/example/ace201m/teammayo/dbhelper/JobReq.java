package com.example.ace201m.teammayo.dbhelper;

public class JobReq {

    private String contPhone;
    private int jobId;
    private String title;
    private int need;
    private String city;
    private String state;
    private String address;
    private int status;
    private String skill;

    public JobReq(String contPhone, int jobId, String title, int need, String city, String address, int status, String state, String skill){
        this.state = state;
        setContPhone(contPhone);
        setAddress(address);
        setCity(city);
        setJobId(jobId);
        setNeed(need);
        setStatus(status);
        setTitle(title);
        setState(state);
        setSkill(skill);
    }


    public String getContPhone() {
        return contPhone;
    }

    public void setContPhone(String contPhone) {
        this.contPhone = contPhone;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNeed() {
        return need;
    }

    public void setNeed(int need) {
        this.need = need;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }
}
