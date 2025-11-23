package game.use_case.PetShop.BuyLootBox;

import game.entity.User;

/**
 * Data Access Interface for buying loot box use case.
 * This interface defines the function of the Data Access Object need to implement.
 */
public interface BuyLootBoxDataAccessInterface {
    
    /**
     * Get the current user data.
     * @return the current User entity
     */
    User getCurrentUser();
    
    /**
     * Save the updated user data after buying loot box.
     * @param user the updated User entity to save
     */
    void saveUser(User user);
}
