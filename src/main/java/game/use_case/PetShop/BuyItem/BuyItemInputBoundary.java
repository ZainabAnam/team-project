package game.use_case.PetShop.BuyItem;

/**
 * Input Boundary interface for buying an item use case.
 * This interface defines the function of the Use Case Interactor need to implement.
 */
public interface BuyItemInputBoundary {
    
    /**
     * Execute the buy item use case.
     * This method should be called by the Controller to initiate the buying process.
     * 
     * @param inputData contains the item name to purchase
     */
    void execute(BuyItemInputData inputData);
}
