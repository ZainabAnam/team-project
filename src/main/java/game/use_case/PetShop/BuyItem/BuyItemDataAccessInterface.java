package game.use_case.PetShop.BuyItem;

import game.entity.User;

/**
 * Data Access Interface for buying an item use case.
 * This interface defines the function of the Data Access Object need to implement.
 */
public interface BuyItemDataAccessInterface {
    
    /**
     * Get the current user data.
     * @return the current User entity
     */
    User getCurrentUser();
    
    /**
     * Save the updated user data after buying an item.
     * @param user the updated User entity to save
     */
    void saveUser(User user);
}
