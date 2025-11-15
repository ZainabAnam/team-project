package game.entity;

import java.util.*;

public class LootBox extends Item{
    private String name;
    private String type;
    private int price;
    private int energyIncrease;
    private List<String>dogBreedList=new ArrayList<>();
    private List<String>catBreedList=new ArrayList<>();

    public LootBox(String name,int price) {
        super(name,price);
        this.type="LootBox";
    }

    public LootBox(int price){
        super("LootBox",price);
        this.type="LootBox";
    }

    public Pet getPet(){
        String type="LootBox";
        String breed="random";

        Random random = new Random();
        int typeIndex=random.nextInt(1);
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
