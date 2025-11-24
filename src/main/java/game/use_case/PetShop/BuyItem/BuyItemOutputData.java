package game.use_case.PetShop.BuyItem;

/**
 * Output data for buying an item use case.
 * Contains the result of the buying operation including success status, item name and relevant messages.
 */
public class BuyItemOutputData {    
    private final boolean success;
    private final String message;
    private final String itemName;
    
    /**
     * Constructor for BuyItemOutputData
     * @param success whether the buying was successful
     * @param message success or error message to display
     * @param itemName name of the bought item
     */
    public BuyItemOutputData(boolean success, String message, 
                           String itemName) {
        this.success = success;
        this.message = message;
        this.itemName = itemName;
    }
    
    /**
     * Check if the buying was successful
     * @return true if purchase succeeded, false otherwise
     */
    public boolean isSuccess() {
        return success;
    }
    
    /**
     * Get the message to display to user
     * @return success or error message
     */
    public String getMessage() {
        return message;
    }
    
    /**
     * Get the name of the bought item
     * @return item name
     */
    public String getItemName() {
        return itemName;
    }
}
