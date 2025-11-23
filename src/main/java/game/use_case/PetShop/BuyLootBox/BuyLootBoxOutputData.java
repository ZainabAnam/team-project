package game.use_case.PetShop.BuyLootBox;

import game.entity.Pet;

/**
 * Output data for buying loot box use case.
 * Contains the result of the loot box purchase and the new pet obtained.
 */
public class BuyLootBoxOutputData {
    private final boolean success;
    private final String message;
    private final Pet lootboxPet; // The pet from loot box (null if failed)
    
    /**
     * Constructor for BuyLootBoxOutputData
     * @param success whether the loot box purchase was successful
     * @param message success or error message indicator
     * @param lootBoxPet the new pet obtained from loot box (null if failed)
     */
    public BuyLootBoxOutputData(boolean success, String message, Pet lootBoxPet) {
        this.success = success;
        this.message = message;
        this.lootboxPet = lootBoxPet;
    }
    
    /**
     * Check if the loot box purchase was successful
     * @return true if purchase succeeded, false otherwise
     */
    public boolean isSuccess() {
        return success;
    }
    
    /**
     * Get the message indicator to display to user
     * @return success or error message indicator
     */
    public String getMessage() {
        return message;
    }
    
    /**
     * Get the new pet obtained from loot box
     * @return the new Pet object, or null if purchase failed
     */
    public Pet getLootboxPet() {
        return lootboxPet;
    }
}
