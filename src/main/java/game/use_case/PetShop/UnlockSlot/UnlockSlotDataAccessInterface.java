package game.use_case.PetShop.UnlockSlot;

import game.entity.User;

/**
 * Data Access Interface for unlocking slot use case.
 * This interface defines the function of the Data Access Object need to implement.
 */
public interface UnlockSlotDataAccessInterface {
    
    /**
     * Get the current user data.
     * @return the current User entity
     */
    User getCurrentUser();
    
    /**
     * Save the updated user data after unlocking slot.
     * @param user the updated User entity to save
     */
    void saveUser(User user);
}
