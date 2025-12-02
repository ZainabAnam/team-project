package game.interface_adapter.shop;

import game.entity.User;
import game.Constants;

/**
 * The State information representing the shop interface.
 */
public class ShopState {
    /**
     * Current user.
     */
    private User currentUser;
    /**
     * Last message.
     */
    private String lastMessage = "";
    /**
     * Last operation success flag.
     */
    private boolean lastOperationSuccess = false;
    /**
     * Last purchased item.
     */
    private String lastPurchasedItem = "";

    /**
     * Constructs ShopState from copy.
     * @param copy state to copy
     */
    public ShopState(final ShopState copy) {
        currentUser = copy.currentUser;
        lastMessage = copy.lastMessage;
        lastOperationSuccess = copy.lastOperationSuccess;
        lastPurchasedItem = copy.lastPurchasedItem;
    }

    /**
     * Constructs ShopState.
     */
    public ShopState() {
        this.currentUser = new User();
    }

    /**
     * Gets current user.
     * @return current user
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * Sets current user.
     * @param user user to set
     */
    public void setCurrentUser(final User user) {
        this.currentUser = user;
    }

    /**
     * Gets current coins.
     * @return coin count
     */
    public int getCurrentCoins() {
        return currentUser != null
                ? currentUser.getCoinCount()
                : Constants.INITIAL_COINS;
    }

    /**
     * Gets current click level.
     * @return click bonus time
     */
    public int getCurrentClickLevel() {
        return currentUser != null
                ? currentUser.getClickBonusTime()
                : Constants.INITIAL_CLICKBONUS_TIME;
    }

    /**
     * Gets current slots.
     * @return unlocked slots count
     */
    public int getCurrentSlots() {
        return currentUser != null
                ? currentUser.getUnlockedSlots()
                : Constants.INITIAL_SLOTS;
    }

    /**
     * Checks if can upgrade clicker.
     * @return true if can upgrade
     */
    public boolean canUpgradeClicker() {
        return currentUser != null ? currentUser.canUpgradeClicker() : false;
    }

    /**
     * Checks if can unlock slot.
     * @return true if can unlock
     */
    public boolean canUnlockSlot() {
        return currentUser != null ? currentUser.canUnlockSlot() : false;
    }

    /**
     * Gets last message.
     * @return last message
     */
    public String getLastMessage() {
        return lastMessage;
    }

    /**
     * Sets last message.
     * @param message message to set
     */
    public void setLastMessage(final String message) {
        this.lastMessage = message;
    }

    /**
     * Checks if last operation was successful.
     * @return true if successful
     */
    public boolean isLastOperationSuccess() {
        return lastOperationSuccess;
    }

    /**
     * Sets last operation success.
     * @param success success flag
     */
    public void setLastOperationSuccess(final boolean success) {
        this.lastOperationSuccess = success;
    }

    /**
     * Gets last purchased item.
     * @return item name
     */
    public String getLastPurchasedItem() {
        return lastPurchasedItem;
    }

    /**
     * Sets last purchased item.
     * @param item item name
     */
    public void setLastPurchasedItem(final String item) {
        this.lastPurchasedItem = item;
    }
}
