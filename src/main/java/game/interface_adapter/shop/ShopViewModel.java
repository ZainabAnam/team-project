package game.interface_adapter.shop;

import game.interface_adapter.ViewModel;
import game.entity.User;

/**
 * The View Model for the Shop View.
 */
public class ShopViewModel extends ViewModel<ShopState> {

    public static final String TITLE_LABEL = "Pet Shop";

    public ShopViewModel() {
        super("shop");
        setState(new ShopState());
    }

    /**
     * Updates the current user in the state and fires property change.
     * @param user the current user
     */
    public void updateUser(User user) {
        ShopState currentState = getState();
        currentState.setCurrentUser(user);
        setState(currentState);
        firePropertyChange();
    }

    /**
     * Gets the current coins from the state.
     * @return current coins
     */
    public int getCurrentCoins() {
        return getState().getCurrentCoins();
    }

    /**
     * Gets the current click level from the state.
     * @return current click level
     */
    public int getCurrentClickLevel() {
        return getState().getCurrentClickLevel();
    }

    /**
     * Gets the current slots from the state.
     * @return current slots
     */
    public int getCurrentSlots() {
        return getState().getCurrentSlots();
    }

    /**
     * Checks if clicker can be upgraded.
     * @return true if can upgrade, false otherwise
     */
    public boolean canUpgradeClicker() {
        return getState().canUpgradeClicker();
    }

    /**
     * Checks if slot can be unlocked.
     * @return true if can unlock, false otherwise
     */
    public boolean canUnlockSlot() {
        return getState().canUnlockSlot();
    }

    /**
     * Gets the last operation message.
     * @return last message
     */
    public String getLastMessage() {
        return getState().getLastMessage();
    }

    /**
     * Checks if last operation was successful.
     * @return true if successful, false otherwise
     */
    public boolean isLastOperationSuccess() {
        return getState().isLastOperationSuccess();
    }

    /**
     * Gets the last purchased item.
     * @return last purchased item name
     */
    public String getLastPurchasedItem() {
        return getState().getLastPurchasedItem();
    }

    /**
     * Clears the last operation message and status.
     * Used after displaying dialogs to reset state.
     */
    public void clearLastOperation() {
        ShopState currentState = getState();
        currentState.setLastMessage("");
        currentState.setLastOperationSuccess(false);
        currentState.setLastPurchasedItem("");
        setState(currentState);
        firePropertyChange();
    }
}
