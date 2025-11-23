package game.use_case.PetShop.UpgradeClicker;

import game.entity.User;

/**
 * Data Access Interface for upgrading clicker use case.
 * This interface defines the function of the Data Access Object need to implement.
 */
public interface UpgradeClickerDataAccessInterface {
    
    /**
     * Get the current user data.
     * @return the current User entity
     */
    User getCurrentUser();
    
    /**
     * Save the updated user data after upgrading clicker.
     * @param user the updated User entity to save
     */
    void saveUser(User user);
}
