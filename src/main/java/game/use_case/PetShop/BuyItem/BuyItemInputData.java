package game.use_case.PetShop.BuyItem;

/**
 * Input data for buying an item use case.
 * Contains the item name that the user wants to buy.
 */
public class BuyItemInputData {
    private final String itemName;
    
    /**
     * Constructor for BuyItemInputData
     * @param itemName the name of the item to buy (must match item names in Constants.itemList)
     */
    public BuyItemInputData(String itemName) {
        this.itemName = itemName;
    }
    
    /**
     * Get the name of the item to buy
     * @return the item name
     */
    public String getItemName() {
        return itemName;
    }
}
