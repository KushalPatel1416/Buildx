package com.example.buildx.Models;

public class Material_Model {

    private String steel,wood,bricks,aggregate,cement,sand;

    public Material_Model() {
    }

    public Material_Model(String steel, String wood, String bricks, String aggregate, String cement, String sand) {
        this.steel = steel;
        this.wood = wood;
        this.bricks = bricks;
        this.aggregate = aggregate;
        this.cement = cement;
        this.sand = sand;
    }

    public String getSteel() {
        return steel;
    }

    public void setSteel(String steel) {
        this.steel = steel;
    }

    public String getWood() {
        return wood;
    }

    public void setWood(String wood) {
        this.wood = wood;
    }

    public String getBricks() {
        return bricks;
    }

    public void setBricks(String bricks) {
        this.bricks = bricks;
    }

    public String getAggregate() {
        return aggregate;
    }

    public void setAggregate(String aggregate) {
        this.aggregate = aggregate;
    }

    public String getCement() {
        return cement;
    }

    public void setCement(String cement) {
        this.cement = cement;
    }

    public String getSand() {
        return sand;
    }

    public void setSand(String sand) {
        this.sand = sand;
    }
}
