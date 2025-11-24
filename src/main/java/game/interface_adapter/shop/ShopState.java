package game.interface_adapter.shop;

import game.entity.User;
import game.Constants;

/**
 * The State information representing the shop interface.
 */
public class ShopState {
    private User currentUser;
    private String lastMessage = "";
    private boolean lastOperationSuccess = false;
    private String lastPurchasedItem = "";

    public ShopState(ShopState copy) {
        currentUser = copy.currentUser;
        lastMessage = copy.lastMessage;
        lastOperationSuccess = copy.lastOperationSuccess;
        lastPurchasedItem = copy.lastPurchasedItem;
    }

    public ShopState() {
        this.currentUser = new User();
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public int getCurrentCoins() {
        return currentUser != null ? currentUser.coinCount : Constants.INITIAL_COINS;
    }

    public int getCurrentClickLevel() {
        return currentUser != null ? currentUser.getClickBonusTime() : Constants.INITIAL_CLICKBONUS_TIME;
    }

    public int getCurrentSlots() {
        return currentUser != null ? currentUser.getUnlockedSlots() : Constants.INITIAL_SLOTS;
    }

    public boolean canUpgradeClicker() {
        return currentUser != null ? currentUser.canUpgradeClicker() : false;
    }

    public boolean canUnlockSlot() {
        return currentUser != null ? currentUser.canUnlockSlot() : false;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public boolean isLastOperationSuccess() {
        return lastOperationSuccess;
    }

    public void setLastOperationSuccess(boolean lastOperationSuccess) {
        this.lastOperationSuccess = lastOperationSuccess;
    }

    public String getLastPurchasedItem() {
        return lastPurchasedItem;
    }

    public void setLastPurchasedItem(String lastPurchasedItem) {
        this.lastPurchasedItem = lastPurchasedItem;
    }
}
