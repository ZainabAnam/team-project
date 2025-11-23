package game.use_case.PetShop.BuyLootBox;

/**
 * Output Boundary interface for buying loot box use case.
 * This interface defines the function of the Presenter need to implement.
 */
public interface BuyLootBoxOutputBoundary {
    
    /**
     * Present the result of a successful loot box purchase.
     * This method is called by the Interactor when loot box is successfully purchased.
     * Should navigate to pet naming interface.
     * 
     * @param outputData contains loot box purchase success information and new pet
     */
    void presentLootBoxSuccess(BuyLootBoxOutputData outputData);
    
    /**
     * Present the result of a failed loot box purchase.
     * This method is called by the Interactor when loot box purchase fails
     * (insufficient coins).
     * 
     * @param outputData contains loot box purchase failure information
     */
    void presentLootBoxFailure(BuyLootBoxOutputData outputData);
}
