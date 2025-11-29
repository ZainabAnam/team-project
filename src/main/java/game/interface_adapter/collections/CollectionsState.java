package game.interface_adapter.collections;

import java.util.List;

public class CollectionsState {

    // Pets displayed in the Collections view (NOT entity objects)
    private List<PetCardState> pets;

    // Item counts (already view-friendly data)
    private int cannedFoodCount;
    private int kibbleCount;
    private int homeCookedCount;

    private int chewToyCount;
    private int tossToyCount;
    private int plushToyCount;

    private String errorMessage;

    // ----- Getters / Setters -----
    public List<PetCardState> getPets() {
        return pets;
    }

    public void setPets(List<PetCardState> pets) {
        this.pets = pets;
    }

    public int getCannedFoodCount() { return cannedFoodCount; }
    public void setCannedFoodCount(int v) { this.cannedFoodCount = v; }

    public int getKibbleCount() { return kibbleCount; }
    public void setKibbleCount(int v) { this.kibbleCount = v; }

    public int getHomeCookedCount() { return homeCookedCount; }
    public void setHomeCookedCount(int v) { this.homeCookedCount = v; }

    public int getChewToyCount() { return chewToyCount; }
    public void setChewToyCount(int v) { this.chewToyCount = v; }

    public int getTossToyCount() { return tossToyCount; }
    public void setTossToyCount(int v) { this.tossToyCount = v; }

    public int getPlushToyCount() { return plushToyCount; }
    public void setPlushToyCount(int v) { this.plushToyCount = v; }

    public String getErrorMessage() { return errorMessage; }
    public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage; }

    // Inner DTO for each pet card
    public static class PetCardState {
        private String name;        // "Buddy"
        private String breed;       // "Golden Retriever"
        private int level;          // 1,2,3...
        private int energy;         // 0–100
        private int affectionXp;    // xp points
        private int sellingPrice;   // coins
        private String fact;        // from API

        // getters/setters
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public String getBreed() { return breed; }
        public void setBreed(String breed) { this.breed = breed; }

        public int getLevel() { return level; }
        public void setLevel(int level) { this.level = level; }

        public int getEnergy() { return energy; }
        public void setEnergy(int energy) { this.energy = energy; }

        public int getAffectionXp() { return affectionXp; }
        public void setAffectionXp(int affectionXp) { this.affectionXp = affectionXp; }

        public int getSellingPrice() { return sellingPrice; }
        public void setSellingPrice(int sellingPrice) { this.sellingPrice = sellingPrice; }

        public String getFact() { return fact; }
        public void setFact(String fact) { this.fact = fact; }
    }
}
