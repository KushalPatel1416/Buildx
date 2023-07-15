package com.example.buildx.Models;

public class Engineer_Model {
    String name,contact,experience;

    public Engineer_Model() {
    }

    public Engineer_Model(String name, String contact, String experience) {
        this.name = name;
        this.contact = contact;
        this.experience = experience;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }
}
