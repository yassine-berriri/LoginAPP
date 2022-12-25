package com.example.loginapp.models;

import java.io.Serializable;

public class UserModel implements Serializable {

    private int id;
    private  String lastName;
    private  String firstName;
    private String email;
    private  String dateOfBirth;
    private  String address;
    private String password;
    private  String gender;
    private  String postalCode;
    private  String town;
    private  String country;
    private  boolean isAdmin;


    public  UserModel clone()
    {
        UserModel monClone = new UserModel();
        monClone.id = this.id;
        monClone.lastName = this.lastName;
        monClone.firstName = this.firstName;
        monClone.email = this.email;
        monClone.dateOfBirth = this.dateOfBirth;
        monClone.address = this.address;
        monClone.password  =this.password;
        monClone.gender = this.gender;
        monClone.postalCode = this.postalCode;
        monClone.town = this.town;
        monClone.country = this.country;
        monClone.isAdmin = this.isAdmin;
        return  monClone;
    }

    public UserModel() {}

    public UserModel(int id,String lastName, String firstName, String email,String dateOfBirth, String address,String password,String gender
    ,String postalCode,String town,String country,boolean isAdmin)
    {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.dateOfBirth=dateOfBirth;
        this.address=address;
        this.password=password;
        this.gender=gender;
        this.postalCode = postalCode;
        this.town = town;
        this.country = country;
        this.isAdmin = isAdmin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public void set(UserModel updatedUserModel) {
        this.id = updatedUserModel.getId();
        this.lastName = updatedUserModel.lastName;
        this.firstName = updatedUserModel.firstName;
        this.email = updatedUserModel.email;
        this.dateOfBirth = updatedUserModel.dateOfBirth;
        this.address = updatedUserModel.address;
        this.password = updatedUserModel.password;
        this.gender = updatedUserModel.gender;
        this.postalCode = updatedUserModel.postalCode;
        this.town = updatedUserModel.town;
        this.country = updatedUserModel.country;
        this.isAdmin = updatedUserModel.isAdmin;
    }
}
