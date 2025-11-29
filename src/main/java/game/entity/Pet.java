package game.entity;

import game.Constants;

import javax.swing.*;

public class Pet {
    /**
     * petType == 'Dog' || petType == 'Cat'
     * petBreed is a valid breed of Dog (if applicable) or Cat (if applicable)
     * visualURL is a valid URL
     * affectionLevel >= 0
     * energyLevel >= 0
     * clickingSpeed >= 0
     * sellingPrice >= 0
     */

    final String petType;
    final String petBreed;
    final ImageIcon petVisual;
    int affectionXP;
    int affectionLevel;
    int energyLevel;
    int clickingSpeed;
    int sellingPrice;
    boolean isDeployed;

    private String name;



    public Pet(String petType, String petBreed, ImageIcon petVisual) {
        this.petType = petType;
        this.petBreed = petBreed;
        this.petVisual = petVisual;
        // call to visual database to get appropriate url
        this.affectionXP = Constants.INITIAL_AFFECTION_XP;
        this.affectionLevel = Constants.INITIAL_AFFECTION_LEVEL;
        this.energyLevel = Constants.INITIAL_ENERGY_LEVEL;
        // call to info database to get clicking speed
        this.sellingPrice = Constants.SELLING_BASE_PRICE;
        this.isDeployed = false;
    }

    public void upgradeClickSpeed() {
        this.clickingSpeed += 5;
    }

    public void increaseAffectionXP(int amount) {
        this.affectionXP += amount;
    }

    public void increaseEnergyLevel(int amount) {
        this.energyLevel += amount;
    }

    public void depleteEnergy() {
        this.energyLevel--;
    }

    public void deployPet() {
        this.isDeployed = true;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public ImageIcon getPetVisual() {
        return this.petVisual;
    }

    public String getBreed() { return this.petBreed; }

    public int getLevel() { return this.affectionLevel; }

    public int getAffectionXP() { return this.affectionLevel; }

    public int getSellingPrice() { return this.sellingPrice; }

    public int getEnergyLevel() { return energyLevel; }

    public boolean getIsDeployed() {
        return isDeployed;
    }
}
