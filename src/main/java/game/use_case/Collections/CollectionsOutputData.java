package game.use_case.Collections;

import javax.swing.*;
import java.util.List;

public class CollectionsOutputData {

    // ---------- DTO for one pet ----------
    public static class PetInfo {
        private final String name;
        private final ImageIcon petVisual;
        private final String breed;
        private final int level;
        private final int energy;
        private final int affectionXp;
        private final int sellingPrice;
        private final String fact;
        private final int clickingSpeed;
        private final String type;

        public PetInfo(String name,
                       ImageIcon petVisual,
                       String breed,
                       int level,
                       int energy,
                       int affectionXp,
                       int sellingPrice,
                       int clickingSpeed,
                       String type,
                       String fact) {
            this.name = name;
            this.petVisual = petVisual;
            this.breed = breed;
            this.level = level;
            this.energy = energy;
            this.affectionXp = affectionXp;
            this.sellingPrice = sellingPrice;
            this.fact = fact;
            this.clickingSpeed = clickingSpeed;
            this.type = type;

        }

        public String getName() { return name; }
        public ImageIcon getPetVisual() { return petVisual; }
        public String getBreed() { return breed; }
        public int getLevel() { return level; }
        public int getEnergy() { return energy; }
        public int getAffectionXp() { return affectionXp; }
        public int getSellingPrice() { return sellingPrice; }
        public String getFact() { return fact; }
        public int getClickingSpeed() { return clickingSpeed; }
        public String getType() { return type; }
    }

    // ---------- Fields for the whole screen ----------
    private final List<PetInfo> pets;

    private final int cannedFoodCount;
    private final int kibbleCount;
    private final int homeCookedCount;

    private final int chewToyCount;
    private final int tossToyCount;
    private final int plushToyCount;

    public CollectionsOutputData(List<PetInfo> pets,
                                 int cannedFoodCount,
                                 int kibbleCount,
                                 int homeCookedCount,
                                 int chewToyCount,
                                 int tossToyCount,
                                 int plushToyCount) {
        this.pets = pets;
        this.cannedFoodCount = cannedFoodCount;
        this.kibbleCount = kibbleCount;
        this.homeCookedCount = homeCookedCount;
        this.chewToyCount = chewToyCount;
        this.tossToyCount = tossToyCount;
        this.plushToyCount = plushToyCount;
    }

    public List<PetInfo> getPets() { return pets; }
    public int getCannedFoodCount() { return cannedFoodCount; }
    public int getKibbleCount() { return kibbleCount; }
    public int getHomeCookedCount() { return homeCookedCount; }
    public int getChewToyCount() { return chewToyCount; }
    public int getTossToyCount() { return tossToyCount; }
    public int getPlushToyCount() { return plushToyCount; }
}
