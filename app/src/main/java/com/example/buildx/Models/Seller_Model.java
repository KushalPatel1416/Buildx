package com.example.buildx.Models;

public class Seller_Model {

    private String name,email,DOB,shop,contact,address,password,role;

    public Seller_Model() {
    }

    public Seller_Model(String name, String email, String DOB, String shop, String contact, String address, String password,String role) {
        this.name = name;
        this.email = email;
        this.DOB = DOB;
        this.shop = shop;
        this.contact = contact;
        this.address = address;
        this.password = password;
        this.role = role;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
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
}
