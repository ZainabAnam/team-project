package game.interface_adapter.collections;

import javax.swing.*;
import java.util.List;

public class CollectionsState {

    // Pets displayed in the Collections view (NOT entity objects)
    private List<PetCardState> pets;
    private int selectedPetIndex = -1;

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

    public int getSelectedPetIndex() { return selectedPetIndex; }
    public void setSelectedPetIndex(int i) { this.selectedPetIndex = i; }

    public PetCardState getSelectedPet() {
        if (pets == null || selectedPetIndex < 0 || selectedPetIndex >= pets.size()) {
            return null;
        }
        return pets.get(selectedPetIndex);
    }


    public String getErrorMessage() { return errorMessage; }
    public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage; }

    // Inner DTO for each pet card
    public static class PetCardState {
        private String name;
        private ImageIcon petVisual;
        private String breed;
        private int level;
        private int energy;
        private int affectionXp;
        private int sellingPrice;
        private String fact;
        private String type;
        private int clickingSpeed;

        // getters/setters
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public ImageIcon getPetVisual() { return petVisual; }
        public void setPetVisual(ImageIcon petVisual) { this.petVisual = petVisual; }

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

        public int getClickingSpeed() { return this.clickingSpeed; }
        public void setClickingSpeed(int clickingSpeed) { this.clickingSpeed = clickingSpeed; }

        public String getType() { return this.type; }
        public void setType(String type) { this.type = type; }
    }
}
