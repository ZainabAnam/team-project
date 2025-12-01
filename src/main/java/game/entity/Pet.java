package game.entity;
import game.Constants;

import javax.swing.*;

public class Pet {
    /**
     * petType == 'Dog' || petType == 'Cat'
     * petBreed is a valid breed of Dog (if applicable) or Cat (if applicable)
     * tier is 'Common' || 'Elite'
     * baseEnergy >= 0
     * baseClick >= 0
     * baseRecovery >= 0
     * visualURL is a valid URL
     * affectionLevel >= 0
     * energyLevel >= 0
     * clickingSpeed >= 0
     * sellingPrice >= 0
     */

    final String petType;
    final String petBreed;

    final String tier;
    final int baseEnergy;
    final int baseClick;
    final int baseRecovery;
    //  final String visualURL;
    ImageIcon petIcon;
    int affectionXP;
    int affectionLevel;
    int energyLevel;
    int clickingSpeed;
    int sellingPrice;
    boolean isDeployed;

    private String name;

    public Pet(String petType, String petBreed, String tier, int baseEnergy, int baseClick, int baseRecovery, ImageIcon petIcon) {
        this.petType = petType;
        this.petBreed = petBreed;
        this.tier = tier;
        this.baseEnergy = baseEnergy;
        this.baseClick = baseClick;
        this.baseRecovery = baseRecovery;
        // call to visual database to get appropriate url
        this.petIcon = petIcon;
        this.affectionXP = Constants.INITIAL_AFFECTION_XP;
        this.affectionLevel = Constants.INITIAL_AFFECTION_LEVEL;
        this.energyLevel = baseEnergy;
        this.clickingSpeed = baseClick;
        this.sellingPrice = Constants.SELLING_BASE_PRICE;
        this.isDeployed = false;
    }

    public void upgradeClickSpeed() {
        this.clickingSpeed += 5;
    }

    public void increaseAffectionXP(int amount) {
        this.affectionXP += amount;
    }

    public void  increaseAffectionLevel() {
        this.affectionLevel++;
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

    public void setIsDeployed(boolean isDeployed) {this.isDeployed = isDeployed;}

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public String getPetType() {
        return this.petType;
    }

    public String getPetBreed() {
        return this.petBreed;
    }

    public String getTier() {
        return this.tier;
    }

    public int getBaseEnergy() {
        return this.baseEnergy;
    }

    public int getBaseClick() {
        return this.baseClick;
    }

    public int getBaseRecovery() {
        return this.baseRecovery;
    }

    public ImageIcon getPetIcon() {
        return petIcon;
    }

    public int getEnergyLevel() {return this.energyLevel;}

    public ImageIcon getPetVisual() {
        if (petIcon != null) {
            return petIcon;
        }

        try {
            String imagePath = "/images/Pet Images/" +
                    petType + " Images/" +
                    petBreed + " Icon.png";

            java.net.URL imageUrl = getClass().getResource(imagePath);

            if (imageUrl != null) {
                petIcon = new ImageIcon(imageUrl);
            } else {
                System.err.println("Image not found: " + imagePath);
            }
        } catch (Exception e) {
            System.err.println("Error loading image for " + petBreed + ": " + e.getMessage());
        }

        return petIcon;
    }

    public boolean isDeployed() {return this.isDeployed;}

    public int getAffectionXP() {
        return this.affectionXP;
    }

    public int getAffectionLevel() {
        return this.affectionLevel;
    }

    public String getBreed() { return this.petBreed; }

    public int getLevel() { return this.affectionLevel; }

//    public int getAffectionXP() { return this.affectionLevel; }

    public int getSellingPrice() { return this.sellingPrice; }

//    public int getEnergyLevel() { return energyLevel; }

    public int getClickingSpeed() {
        return this.clickingSpeed;
    }
}