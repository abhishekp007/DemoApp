package com.example.demoapp.model;

public class RegistrationModel {

    private int regId;
    private String name;
    private Integer mobile;
    private String email;
    private String password;

    public RegistrationModel(int regId, String name,int mobile,String email, String password) {
        this.regId = regId;
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.password = password;
    }

    public int getRegId() {
        return regId;
    }

    public void setRegId(int regId) {
        this.regId = regId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMobile() {
        return mobile;
    }

    public void setMobile(Integer mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
