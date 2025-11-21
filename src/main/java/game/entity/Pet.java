package game.entity;
import game.Constants;

import game.Constants;

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
//  final String visualURL;
    int affectionXP;
    int affectionLevel;
    int energyLevel;
    int clickingSpeed;
    int sellingPrice;
    boolean deployStatus;

    private String name;



    public Pet(String petType, String petBreed) {
        this.petType = petType;
        this.petBreed = petBreed;
        // call to visual database to get appropriate url
        this.affectionXP = Constants.INITIAL_AFFECTION_XP;
        this.affectionLevel = Constants.INITIAL_AFFECTION_LEVEL;
        this.energyLevel = Constants.INITIAL_ENERGY_LEVEL;
        // call to info database to get clicking speed
        this.sellingPrice = Constants.SELLING_BASE_PRICE;
        this.deployStatus = false;
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
        this.deployStatus = true;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
