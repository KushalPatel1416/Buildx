package com.example.buildx.Models;

public class SellerList_Model {
    String name,shop,contact,address;

    public SellerList_Model() {
    }

    public SellerList_Model(String name, String shop, String contact,String address) {
        this.name = name;
        this.shop = shop;
        this.contact = contact;
        this.address = address;
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
}
