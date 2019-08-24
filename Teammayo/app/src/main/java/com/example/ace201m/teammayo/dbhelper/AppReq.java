package com.example.ace201m.teammayo.dbhelper;

public class AppReq {
    private String appId;
    private String empId;
    private String jobId;
    private String status;

    public AppReq(String appId,String empId, String jobId, String status){
        setAppId(appId);
        setEmpId(empId);
        setJobId(jobId);
        setStatus(status);
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
