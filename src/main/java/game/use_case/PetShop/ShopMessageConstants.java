package game.use_case.PetShop;

/**
 * Constants for shop-related messages.
 * messages for displaying appropriate messages in the View.
 */
public final class ShopMessageConstants {
    
    // Common error messages
    public static final String INSUFFICIENT_COINS = "INSUFFICIENT_COINS";
    public static final String MAX_LEVEL_REACHED = "MAX_LEVEL_REACHED";
    public static final String MAX_SLOTS_REACHED = "MAX_SLOTS_REACHED";
    
    // Success messages
    public static final String PURCHASE_SUCCESS = "PURCHASE_SUCCESS";
    public static final String UPGRADE_SUCCESS = "UPGRADE_SUCCESS";
    public static final String UNLOCK_SUCCESS = "UNLOCK_SUCCESS";
    public static final String LOOTBOX_SUCCESS = "LOOTBOX_SUCCESS";
    
    private ShopMessageConstants() {
    }
}
