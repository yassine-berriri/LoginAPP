package com.example.loginapp.webservices.entities;

import java.io.Serializable;

public class UserWeb implements Serializable {
    private String email;
    private String password;
    private int id;
    private String lastname;
    private String firstname;
    private String sub;
    private String customer;
    private String vlsService;
    private String vldService;
    private String exp;
    public UserWeb(){}

    public UserWeb(String email, String password, int id){
        this.email = email;
        this.password = password;
        this.id = id;
    }

    public UserWeb(String sub,String customer,String firstname,String lastname,String vlsService
    ,String vldService, String exp
    ){
        this.sub = sub;
        this.customer = customer;
        this.firstname = firstname;
        this.lastname = lastname;
        this.vlsService = vlsService;
        this.vldService = vldService;
        this.exp = exp;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getVlsService() {
        return vlsService;
    }

    public void setVlsService(String vlsService) {
        this.vlsService = vlsService;
    }

    public String getVldService() {
        return vldService;
    }

    public void setVldService(String vldService) {
        this.vldService = vldService;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }
}
