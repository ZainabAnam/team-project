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
    private String name;
    final String petType;
    final String petBreed;
    final String petVisualPath;
    final String tier;
    int baseEnergy;
    int baseClick;
    int baseRecovery;
    int affectionXP;
    int affectionLevel;
    int energyLevel;
    int clickingSpeed;
    int sellingPrice;
    boolean isDeployed;



    public Pet(String name, String petType, String petBreed, String tier, int baseEnergy, int baseClick,
               int baseRec, String petVisualPath) {
        this.name = name;
        this.petType = petType;
        this.petBreed = petBreed;
        this.tier = tier;
        this.baseEnergy = baseEnergy;
        this.baseClick = baseClick;
        this.baseRecovery = baseRecovery;
        this.petVisualPath = petVisualPath;
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

    public String getPetVisualPath() {
        return this.petVisualPath;
    }

    public int getEnergyLevel() {
        return energyLevel;
    }

    public boolean getIsDeployed() {
        return isDeployed;
    }

    public String getPetBreed() {
        return petBreed;
    }
    public int getClickingSpeed() {
        return clickingSpeed;
    }
    public int getAffectionXP() {
        return affectionXP;
    }
    public int getAffectionLevel() {
        return affectionLevel;
    }
    public String getTier(){
        return tier;
    }
    public String getPetType() {
        return petType;
    }
    public int getBaseEnergy() {
        return baseEnergy;
    }
    public int getBaseClick() {
        return baseClick;
    }
    public int getBaseRecovery() {
        return baseRecovery;
    }


}
