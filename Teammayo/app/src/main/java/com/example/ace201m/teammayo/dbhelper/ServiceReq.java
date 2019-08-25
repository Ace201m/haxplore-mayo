package com.example.ace201m.teammayo.dbhelper;

public class ServiceReq {

    private String contPhone;
    private String serviceId;
    private String title;
    private String city;
    private String body;

    public ServiceReq(String contPhone, String serviceId, String title, String city, String bod){
        setContPhone(contPhone);
        setCity(city);
        setServiceId(serviceId);
        setTitle(title);
        this.body = bod;
    }

    private void setServiceId(String serviceId) {
        this.serviceId=serviceId;
    }


    public String getContPhone() {
        return contPhone;
    }

    public void setContPhone(String contPhone) {
        this.contPhone = contPhone;
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
