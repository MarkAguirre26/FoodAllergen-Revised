package com.thesis.pdm.hallergen;

public class ModelAllowanceLeft {
    private String name;
    private String energy;
    private String protein;
    private String total_fat;
    private String carbohydtrate;
    private String essential_fatty;
    private String dietary_fiber;
    private String water;

    public ModelAllowanceLeft() {

    }

    public ModelAllowanceLeft(String name, String energy, String protein, String total_fat, String carbohydtrate, String essential_fatty, String dietary_fiber, String water) {
        this.name = name;
        this.energy = energy;
        this.protein = protein;
        this.total_fat = total_fat;
        this.carbohydtrate = carbohydtrate;
        this.essential_fatty = essential_fatty;
        this.dietary_fiber = dietary_fiber;
        this.water = water;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnergy() {
        return energy;
    }

    public void setEnergy(String energy) {
        this.energy = energy;
    }

    public String getProtein() {
        return protein;
    }

    public void setProtein(String protein) {
        this.protein = protein;
    }

    public String getTotal_fat() {
        return total_fat;
    }

    public void setTotal_fat(String total_fat) {
        this.total_fat = total_fat;
    }

    public String getCarbohydtrate() {
        return carbohydtrate;
    }

    public void setCarbohydtrate(String carbohydtrate) {
        this.carbohydtrate = carbohydtrate;
    }

    public String getEssential_fatty() {
        return essential_fatty;
    }

    public void setEssential_fatty(String essential_fatty) {
        this.essential_fatty = essential_fatty;
    }

    public String getDietary_fiber() {
        return dietary_fiber;
    }

    public void setDietary_fiber(String dietary_fiber) {
        this.dietary_fiber = dietary_fiber;
    }

    public String getWater() {
        return water;
    }

    public void setWater(String water) {
        this.water = water;
    }
}
