package game;

import java.util.*;
import game.entity.Item;
import game.entity.PetFood;
import game.entity.PetToy;

/**
 * Constants used in this program.
 */
public final class Constants {

    //Pet initial constants
    public static final int INITIAL_LEVEL = 1;
    public static final int INITIAL_AFFECTION_XP=0;
    public static final int INITIAL_AFFECTION_LEVEL=1;
    public static final int INITIAL_ENERGY_LEVEL=100;

    public static final int MAX_ENERGY_LEVEL = 100;
    public static final int MAX_AFFECTION_LEVEL = 3;
    public static final int MAX_AFFECTION_XP = 30;

    public static final int SELLING_BASE_PRICE = 25;


    //User initial constants
    public static final int INITIAL_COINS = 100;
    public static final int INITIAL_CLICKBONUS = 1;
    public static final int INITIAL_SLOTS=2;
    public static final int INITIAL_CLICKBONUS_TIME=1;//count the times of upgrades
    public static final int CLICKBONUS_INCREASE_BASE=2;//the base for calulate for the number increase of upgrades

    //Shop price constants
    public static final int LOOT_BOX_PRICE = 50;
    
    // Pet Food prices 
    public static final int PET_FOOD_BASIC_PRICE = 10;    // +10 energy
    public static final int PET_FOOD_MEDIUM_PRICE = 40;   // 50 energy  
    public static final int PET_FOOD_PREMIUM_PRICE = 60; // +100 energy

    // Pet Energy increase constants
    public static final int PET_ENERGY_BASIC_INCREASE = 10;
    public static final int PET_ENERGY_MEDIUM_INCREASE = 30;
    public static final int PET_ENERGY_PREMIUM_INCREASE = 50;
    
    // Pet Toy prices 
    public static final int PET_TOY_BASIC_PRICE =30;     // +1 affection XP
    public static final int PET_TOY_MEDIUM_PRICE = 50;   // +2 affection XP
    public static final int PET_TOY_PREMIUM_PRICE = 80;  // +3 affection XP

    // Pet Toy affection increase constants
    public static final int PET_TOY_BASIC_AFFECTION_INCREASE = 1;
    public static final int PET_TOY_MEDIUM_AFFECTION_INCREASE = 2;
    public static final int PET_TOY_PREMIUM_AFFECTION_INCREASE = 3;
    
    // Item name constants
    public static final String ITEM_KIBBLE = "Kibble";
    public static final String ITEM_CANNED_FOOD = "Canned Food";
    public static final String ITEM_HOME_COOKED = "Home-Cooked";
    public static final String ITEM_CHEW_TOY = "Chew Toy";
    public static final String ITEM_TOSS_TOY = "Toss Toy";
    public static final String ITEM_PLUSH_TOY = "Plush Toy";
    public static final String ITEM_LOOT_BOX = "Loot Box";
    
    //Shop item constants
    public static final List<Item> itemList=new ArrayList<Item>(
        Arrays.asList(new PetFood(ITEM_KIBBLE,PET_FOOD_BASIC_PRICE,PET_ENERGY_BASIC_INCREASE),
                        new PetFood(ITEM_CANNED_FOOD,PET_FOOD_MEDIUM_PRICE,PET_ENERGY_MEDIUM_INCREASE),
                        new PetFood(ITEM_HOME_COOKED,PET_FOOD_PREMIUM_PRICE,PET_ENERGY_PREMIUM_INCREASE),
                        new PetToy(ITEM_CHEW_TOY,PET_TOY_BASIC_PRICE,PET_TOY_BASIC_AFFECTION_INCREASE),
                        new PetToy(ITEM_TOSS_TOY,PET_TOY_MEDIUM_PRICE,PET_TOY_MEDIUM_AFFECTION_INCREASE),
                        new PetToy(ITEM_PLUSH_TOY,PET_TOY_PREMIUM_PRICE,PET_TOY_PREMIUM_AFFECTION_INCREASE)));
    
    // Upgrade limits and pricing
    public static final int MAX_CLICK_BONUS_UPGRADES = 5;
    public static final int UPGRADE_CLICKER_BASE_PRICE = 50; 
    
    // Slot limits and pricing  
    public static final int MAX_PET_SLOTS = 4;
    public static final int UNLOCK_SLOT_BASE_PRICE = 100; 

    private Constants() {}
}
