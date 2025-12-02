package game.data_access;

import game.entity.User;
import game.use_case.PetShop.BuyItem.BuyItemDataAccessInterface;
import game.use_case.PetShop.BuyLootBox.BuyLootBoxDataAccessInterface;
import game.use_case.PetShop.UpgradeClicker.UpgradeClickerDataAccessInterface;
import game.use_case.PetShop.UnlockSlot.UnlockSlotDataAccessInterface;

/**
 * Data Access Object for Shop operations.
 * Implements all shop-related data access interfaces.
 */
public class ShopDataAccessObject implements BuyItemDataAccessInterface,
                                           BuyLootBoxDataAccessInterface,
                                           UpgradeClickerDataAccessInterface,
                                           UnlockSlotDataAccessInterface {

    /**
     * Current user.
     */
    private User currentUser;

    /**
     * Initialize with a default user for demo purposes.
     */
    public ShopDataAccessObject() {
        this.currentUser = new User();
    }

    /**
     * Gets current user.
     * @return current user
     */
    @Override
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * Saves user.
     * @param user user to save
     */
    @Override
    public void saveUser(final User user) {
        this.currentUser = user;
        //TODO: save database here
    }

    /**
     * Updates the current user (for testing purposes).
     * @param user the new user
     */
    public void setCurrentUser(final User user) {
        this.currentUser = user;
    }
}
