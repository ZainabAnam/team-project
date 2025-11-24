package game.interface_adapter.collections;

import game.entity.Pet;
import game.entity.PetFood;
import game.entity.PetToy;

import java.util.List;

/**
 * State object for the Collections view.
 * Holds the data that the view needs to display.
 */
public class CollectionsState {

    private List<Pet> pets;
    private List<PetFood> petFoods;
    private List<PetToy> petToys;

    // Optional: for errors from the use case
    private String errorMessage;

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    public List<PetFood> getPetFoods() {
        return petFoods;
    }

    public void setPetFoods(List<PetFood> petFoods) {
        this.petFoods = petFoods;
    }

    public List<PetToy> getPetToys() {
        return petToys;
    }

    public void setPetToys(List<PetToy> petToys) {
        this.petToys = petToys;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
