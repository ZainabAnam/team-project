package game.use_case.PetShop.BuyLootBox;

import game.entity.User;
import game.entity.Pet;
import game.entity.LootBox;
import game.use_case.PetShop.ShopMessageConstants;

/**
 * Interactor for buying loot box use case.
 * This class implements the BuyLootBoxInputBoundary interface and is responsible for buying loot box.
 * Special: navigate to pet naming interface upon successful purchase.
 */
public class BuyLootBoxInteractor implements BuyLootBoxInputBoundary {
    
    private final BuyLootBoxOutputBoundary outputBoundary;
    private final BuyLootBoxDataAccessInterface dataAccess;
    
    /**
     * Constructor for BuyLootBoxInteractor.
     * @param outputBoundary the presenter that will handle output
     * @param dataAccess the data access object for user data persistence
     */
    public BuyLootBoxInteractor(BuyLootBoxOutputBoundary outputBoundary, 
                               BuyLootBoxDataAccessInterface dataAccess) {
        this.outputBoundary = outputBoundary;
        this.dataAccess = dataAccess;
    }
    
    /**
     * Execute the buy loot box use case.
     * 
     * @param inputData (actually no data needed for this use case)
     */
    @Override
    public void execute(BuyLootBoxInputData inputData) {
        // Get current user
        User user = dataAccess.getCurrentUser();
        
        // Create loot box with default price
        LootBox lootBox = new LootBox();
        
        // Check if user has enough coins
        if (!user.coinCheck(lootBox.getPrice())) {
            BuyLootBoxOutputData outputData = new BuyLootBoxOutputData(
                false, 
                ShopMessageConstants.INSUFFICIENT_COINS, 
                null
            );
            outputBoundary.presentLootBoxFailure(outputData);
            return;
        }
        
        // buy loot box
        // This will: 1) consume coins, 2) generate random pet, 3) add pet to inventory 4) name the pet
        user.buyLootBox(lootBox);
        
        // get the newly generated pet (last pet in inventory)
        Pet newPet = user.getPetInventory().get(user.getPetInventory().size() - 1);
        
        // Save the updated user data
        dataAccess.saveUser(user);
        
        // Create success output data with the new pet
        BuyLootBoxOutputData outputData = new BuyLootBoxOutputData(
            true, 
            ShopMessageConstants.LOOTBOX_SUCCESS, 
            newPet
        );
        
        // This will trigger navigation to pet naming interface
        outputBoundary.presentLootBoxSuccess(outputData);
    }
}
