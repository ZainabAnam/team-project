package game.use_case.PetShop.BuyLootBox;

/**
 * Input Boundary interface for buying loot box use case.
 * This interface defines the function of the Use Case Interactor need to implement.
 */
public interface BuyLootBoxInputBoundary {
    
    /**
     * Execute the buy loot box use case.
     * This method should be called by the Controller to initiate the loot box purchase process.
     * 
     * @param inputData (actually no data needed for this use case)
     */
    void execute(BuyLootBoxInputData inputData);
}
