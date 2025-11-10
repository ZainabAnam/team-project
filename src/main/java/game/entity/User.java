package game.entity;

import java.util.ArrayList;

public class User {
    /*
    PetInventory: List<Pet>
    itemsList: List<Item>
    + coinCount: int
    clickBonus (Click multiplier; how many coins you get per click):int
    clickBonusTime:int
    petSlot:int
    */
    ArrayList<Pet> PetInventory = new ArrayList<Pet>();
    ArrayList<Item> ItemInventory = new ArrayList<Item>();
    int coinCount = 0;
    int clickBonus = 0;
    int slotsUnlocked = 0;

}
