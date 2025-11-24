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

    private User currentUser;

    public ShopDataAccessObject() {
        // Initialize with a default user for demo purposes
        this.currentUser = new User();
        //this.currentUser.coinCount = 500; // test coins
    }

    @Override
    public User getCurrentUser() {
        return currentUser;
    }

    @Override
    public void saveUser(User user) {
        this.currentUser = user;
        //TODO: save database here
    }

    /**
     * Updates the current user (for testing purposes).
     * @param user the new user
     */
    public void setCurrentUser(User user) {
        this.currentUser = user;
    }
}
