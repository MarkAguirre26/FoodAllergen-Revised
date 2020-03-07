package com.thesis.pdm.hallergen;



public class ModelIntake {

    private String id;
    private String accountID;
    private String famId;
    private String energy;
    private String protein;
    private String total_fat;
    private String carbohydtrate;
    private String essentialFattyAcid;
    private String dietaryFiber;
    private String water;


    public String getCarbohydtrate() {
        return StringManager.putSafe(carbohydtrate);
    }

    public void setCarbohydtrate(String carbohydtrate) {
        this.carbohydtrate = carbohydtrate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountID() {
        return StringManager.putSafe(accountID);
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getFamId() {
        return famId;
    }

    public void setFamId(String famId) {
        this.famId = famId;
    }

    public String getEnergy() {

        return StringManager.putSafe(energy);
    }

    public void setEnergy(String energy) {
        this.energy = energy;
    }

    public String getProtein() {
        return StringManager.putSafe(protein);
    }

    public void setProtein(String protein) {
        this.protein = protein;
    }

    public String getTotal_fat() {
        return StringManager.putSafe(total_fat);
    }

    public void setTotal_fat(String total_fat) {
        this.total_fat = total_fat;
    }

    public String getEssentialFattyAcid() {
        return StringManager.putSafe(essentialFattyAcid);
    }

    public void setEssentialFattyAcid(String essentialFattyAcid) {
        this.essentialFattyAcid = essentialFattyAcid;
    }

    public String getDietaryFiber() {
        return StringManager.putSafe(dietaryFiber);
    }

    public void setDietaryFiber(String dietaryFiber) {
        this.dietaryFiber = dietaryFiber;
    }

    public String getWater() {
        return StringManager.putSafe(water);
    }

    public void setWater(String water) {
        this.water = water;
    }
}
