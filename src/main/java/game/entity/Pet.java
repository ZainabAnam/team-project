package game.entity;

import game.Constants;

import game.Constants;

import java.util.HashMap;
import java.util.Map;

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

    private static class BreedData {
        final int baseEnergy;
        final int baseClick;
        final int baseRecovery;
        final String tier;

        public BreedData(int baseEnergy, int baseClick, int baseRecovery) {
            this(baseEnergy, baseClick, baseRecovery, "common");
        }

        public BreedData(int baseEnergy, int baseClick, int baseRecovery, String tier) {
            this.baseEnergy = baseEnergy;
            this.baseClick = baseClick;
            this.baseRecovery = baseRecovery;
            this.tier = tier;
        }
    }
    private static Map<String, BreedData> createBreedData() {
        Map<String, BreedData> data = new HashMap<>();

        data.put("labrador retriever", new BreedData(5, 4, 5, "common"));
        data.put("golden retriever", new BreedData(5, 3, 5, "common"));
        data.put("french bulldog", new BreedData(2, 3, 2, "common"));
        data.put("poodle", new BreedData(4, 5, 5, "common"));
        data.put("beagle", new BreedData(4, 4, 4, "common"));
        data.put("shih tzu", new BreedData(3, 2, 4, "common"));
        data.put("boxer", new BreedData(5, 5, 3, "common"));
        data.put("pomeranian", new BreedData(2, 6, 4, "common"));
        data.put("siberian husky", new BreedData(8, 7, 5, "elite"));
        data.put("german shepherd", new BreedData(7, 6, 6, "elite"));

        data.put("siamese", new BreedData(5, 6, 4, "common"));
        data.put("british shorthair", new BreedData(5, 4, 5, "common"));
        data.put("persian", new BreedData(3, 2, 3, "common"));
        data.put("russian blue", new BreedData(4, 5, 6, "common"));
        data.put("ragdoll", new BreedData(4, 3, 5, "common"));
        data.put("american shorthair", new BreedData(5, 4, 5, "common"));
        data.put("sphynx", new BreedData(4, 6, 4, "common"));
        data.put("scottish fold", new BreedData(4, 3, 4, "common"));
        data.put("maine coon", new BreedData(7, 6, 6, "elite"));
        data.put("bengal", new BreedData(8, 7, 5, "elite"));

        return data;
    }

    private static final Map<String, BreedData> BREED_DATA = createBreedData();

    public Pet(String petType, String petBreed, String name) {
        this.petType = petType;
        this.petBreed = petBreed;
        this.name = name;
        // call to visual database to get appropriate url
        this.affectionXP = Constants.INITIAL_AFFECTION_XP;
        this.affectionLevel = calculateAffectionLevel();
        BreedData data = BREED_DATA.getOrDefault(
                petBreed.toLowerCase(),
                new BreedData(5, 3, 5)
        );
        this.energyLevel = data.baseEnergy * 20;
        this.clickingSpeed = data.baseClick;
        this.sellingPrice = calculateSellingPrice(data.tier);
        this.deployStatus = false;
    }

    private int calculateAffectionLevel() {
        return Math.min(this.affectionXP / 10 + 1, Constants.MAX_AFFECTION_LEVEL);
    }

    private int calculateClickingSpeed() {
        return 1;
    }

    private int calculateSellingPrice(String tier) {
        int basePrice = Constants.SELLING_BASE_PRICE;
        if ("elite".equalsIgnoreCase(tier)) {
            basePrice *= 2;
        }
        return basePrice * this.affectionLevel;
    }

    public void upgradeClickSpeed() {
        this.clickingSpeed += 5;
    }

    public void increaseAffectionXP(int amount) {
        int oldLevel = this.affectionLevel;
        this.affectionXP = Math.min(this.affectionXP + amount, Constants.MAX_AFFECTION_XP);
        this.affectionLevel = calculateAffectionLevel();

        if (this.affectionLevel != oldLevel) {
            BreedData data = BREED_DATA.getOrDefault(petBreed.toLowerCase(), new BreedData(5, 3, 5));
            this.sellingPrice = calculateSellingPrice(data.tier);
        }
    }

    public void increaseEnergyLevel(int amount) {
        this.energyLevel = Math.min(this.energyLevel + amount, Constants.MAX_ENERGY_LEVEL);
    }

    public void depleteEnergy() {
        this.energyLevel = Math.max(this.energyLevel - 1, 0);
    }

    public void depleteEnergy(int amount) {
        this.energyLevel = Math.max(this.energyLevel - amount, 0);
    }

    public boolean isTired(){
        return this.energyLevel == 0;
    }

    public boolean canDeploy(){
        return this.affectionLevel >= 2;
    }

    public void deployPet() {
        this.deployStatus = true;
    }

    public void retrievePet() {
        this.deployStatus = false;
    }

    public int getMaxEnergy() {
        BreedData data = BREED_DATA.getOrDefault(petBreed.toLowerCase(), new BreedData(5, 3, 5));
        return data.baseEnergy * 20;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() { return name; }
    public String getPetType() { return petType; }
    public String getPetBreed() { return petBreed; }
    public int getAffectionXP() { return affectionXP; }
    public int getAffectionLevel() { return affectionLevel; }
    public int getEnergyLevel() { return energyLevel; }
    public int getClickingSpeed() { return clickingSpeed; }
    public int getSellingPrice() { return sellingPrice; }
    public boolean isDeployed() { return deployStatus; }

    @Override
    public String toString() {
        return String.format("Pet{name='%s', type='%s', breed='%s', level=%d, energy=%d}",
                name, petType, petBreed, affectionLevel, energyLevel);
    }
}
