package game.use_case.Collections;

import game.entity.Pet;
import game.entity.PetFood;
import game.entity.PetToy;

import java.util.List;

public class CollectionsOutputData {

    private final List<Pet> pets;
    private final List<PetFood> petFoods;
    private final List<PetToy> petToys;

    public CollectionsOutputData(List<Pet> pets,
                                 List<PetFood> petFoods,
                                 List<PetToy> petToys) {
        this.pets = pets;
        this.petFoods = petFoods;
        this.petToys = petToys;
    }

    public List<Pet> getPets() {
        return pets;
    }
    public List<PetFood> getPetFoods() {
        return petFoods;
    }
    public List<PetToy> getPetToys() {
        return petToys;
    }
}
