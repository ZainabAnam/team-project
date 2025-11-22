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

        dogBreedList.add("Labrador Retriever");
        dogBreedList.add("Golden Retriever");
        dogBreedList.add("French Bulldog");
        dogBreedList.add("Poodle");
        dogBreedList.add("Beagle");
        dogBreedList.add("Shih Tzu");
        dogBreedList.add("Boxer");
        dogBreedList.add("Pomeranian");
        dogBreedList.add("Siberian Husky");
        dogBreedList.add("German Shepherd");

        catBreedList.add("Siamese");
        catBreedList.add("British Shorthair");
        catBreedList.add("Persian");
        catBreedList.add("Russian Blue");
        catBreedList.add("Ragdoll");
        catBreedList.add("American Shorthair");
        catBreedList.add("Sphynx");
        catBreedList.add("Scottish Fold");
        catBreedList.add("Maine Coon");
        catBreedList.add("Bengal");
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

        String randomName = generateRandomName();
        Pet randomPet = new Pet(type, breed, randomName);
        return randomPet;
    }

    private String generateRandomName(){
        Random random = new Random();
        String[] names= {"Buddy", "Max", "Charlie", "Lucy", "Bailey", "Daisy", "Molly", "Jack"};
        return names[random.nextInt(names.length)];
    }
}
