package game.entity;

import game.Constants;
import java.util.*;

/**
 * Loot box for random new pets
 */
public class LootBox extends Item{
    private List<String>dogBreedList=new ArrayList<>();
    private List<String>catBreedList=new ArrayList<>();

    public LootBox(){
        super("LootBox", Constants.LOOT_BOX_PRICE,"LootBox");
        initializeBreedLists();
    }
    
    /**
     * will be implemented in the future with api
     */
    private void initializeBreedLists() {
        this.catBreedList=new ArrayList<>(Arrays.asList("catA","catB","catC"));
        this.dogBreedList=new ArrayList<>(Arrays.asList("dogA","dogB","dogC"));
    }

    public Pet getPet(){
        String type;
        String breed;

        Random random = new Random();
        int typeIndex=random.nextInt(2);
        if (typeIndex==0){
            type="Cat";
            int randomIndex = random.nextInt(this.catBreedList.size());
            breed = this.catBreedList.get(randomIndex);
        }else{
            type="Dog";
            int randomIndex = random.nextInt(this.dogBreedList.size());
            breed = this.dogBreedList.get(randomIndex);
        }

        Pet randomPet=new Pet(type,breed);
        return randomPet;
    }
}
