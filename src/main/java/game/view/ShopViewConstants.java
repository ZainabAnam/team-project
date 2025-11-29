package game.view;

import game.Constants;

/**
 * Constants for Shop View text
 */
public final class ShopViewConstants {
    
    // Shop titles
    public static final String SHOP_TITLE = "Pet Shop";
    public static final String LOOT_BOX_TITLE = "Loot Box";
    public static final String PET_FOOD_TITLE = "Pet Food";
    public static final String PET_TOY_TITLE = "Pet Toys";
    public static final String UPGRADE_CLICKER_TITLE = "Upgrade Clicker";
    public static final String UNLOCK_SLOT_TITLE = "Unlock Pet Slot";
    
    // Item descriptions
    public static final String LOOT_BOX_DESC = "Get a random pet!";
    public static final String KIBBLE_DESC = "+"+Constants.PET_ENERGY_BASIC_INCREASE+" Energy";
    public static final String CANNED_FOOD_DESC = "+"+Constants.PET_ENERGY_MEDIUM_INCREASE+" Energy";
    public static final String HOME_COOKED_DESC = "+"+Constants.PET_ENERGY_PREMIUM_INCREASE+" Energy";
    public static final String CHEW_TOY_DESC = "+"+Constants.PET_TOY_BASIC_AFFECTION_INCREASE+" Affection XP";
    public static final String TOSS_TOY_DESC = "+"+Constants.PET_TOY_MEDIUM_AFFECTION_INCREASE+" Affection XP";
    public static final String PLUSH_TOY_DESC = "+"+Constants.PET_TOY_PREMIUM_AFFECTION_INCREASE+" Affection XP";
    public static final String UPGRADE_CLICKER_DESC = "Increase click bonus";
    public static final String UNLOCK_SLOT_DESC = "Add pet slot";
    
    // Button text
    public static final String COIN_SUFFIX = " Coins";
    public static final String MAX_BUTTON_TEXT = "MAX";
    public static final String CLOSE_BUTTON_TEXT = "Close";
    
    // Dialog messages
    public static final String INSUFFICIENT_COINS_TITLE = "Not Enough Coins";
    public static final String INSUFFICIENT_COINS_MESSAGE = "Oops! You do not have enough coins!";
    public static final String EARN_MORE_BUTTON = "Click and earn more coins!";
    public static final String UPGRADE_SUCCESS_NICE = "Nice!";
    public static final String UNLOCK_SUCCESS_WOW = "Wow!";
    public static final String ARROW_SYMBOL = " -> ";
    
    private ShopViewConstants() {}
}
