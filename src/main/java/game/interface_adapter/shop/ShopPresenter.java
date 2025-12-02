package game.interface_adapter.shop;

import game.interface_adapter.ViewManagerModel;
import game.data_access.ShopDataAccessObject;
import game.use_case.PetShop.BuyItem.BuyItemOutputBoundary;
import game.use_case.PetShop.BuyItem.BuyItemOutputData;
import game.use_case.PetShop.BuyLootBox.BuyLootBoxOutputBoundary;
import game.use_case.PetShop.BuyLootBox.BuyLootBoxOutputData;
import game.use_case.PetShop.UpgradeClicker.UpgradeClickerOutputBoundary;
import game.use_case.PetShop.UpgradeClicker.UpgradeClickerOutputData;
import game.use_case.PetShop.UnlockSlot.UnlockSlotOutputBoundary;
import game.use_case.PetShop.UnlockSlot.UnlockSlotOutputData;

/**
 * The Presenter for the Shop Use Cases.
 * Implements OutputBoundary interfaces and updates the ShopViewModel.
 */
public class ShopPresenter implements BuyItemOutputBoundary,
                                     BuyLootBoxOutputBoundary,
                                     UpgradeClickerOutputBoundary,
                                     UnlockSlotOutputBoundary {

    /**
     * Not changing page as it is a pop-up window.
     */
    private final ViewManagerModel viewManagerModel;
    /**
     * Shop view model.
     */
    private final ShopViewModel shopViewModel;
    /**
     * Data access object.
     */
    private final ShopDataAccessObject dataAccess;

    /**
     * Constructs ShopPresenter.
     * @param vmModel view manager model
     * @param svm shop view model
     * @param dao data access object
     */
    public ShopPresenter(final ViewManagerModel vmModel,
                        final ShopViewModel svm,
                        final ShopDataAccessObject dao) {
        this.viewManagerModel = vmModel;
        this.shopViewModel = svm;
        this.dataAccess = dao;
    }

    /**
     * Presents buying success.
     * @param outputData output data
     */
    @Override
    public void presentBuyingSuccess(final BuyItemOutputData outputData) {
        ShopState currentState = shopViewModel.getState();

        // Refresh
        currentState.setCurrentUser(dataAccess.getCurrentUser());

        currentState.setLastOperationSuccess(true);
        currentState.setLastMessage(outputData.getMessage());
        currentState.setLastPurchasedItem(outputData.getItemName());

        shopViewModel.setState(currentState);
        shopViewModel.firePropertyChange();
    }

    /**
     * Presents buying failure.
     * @param outputData output data
     */
    @Override
    public void presentBuyingFailure(final BuyItemOutputData outputData) {
        ShopState currentState = shopViewModel.getState();

        currentState.setCurrentUser(dataAccess.getCurrentUser());

        currentState.setLastOperationSuccess(false);
        currentState.setLastMessage(outputData.getMessage());
        currentState.setLastPurchasedItem("");

        shopViewModel.setState(currentState);
        shopViewModel.firePropertyChange();
    }

    /**
     * Presents loot box success.
     * @param outputData output data
     */
    @Override
    public void presentLootBoxSuccess(final BuyLootBoxOutputData outputData) {
        ShopState currentState = shopViewModel.getState();

        currentState.setCurrentUser(dataAccess.getCurrentUser());

        currentState.setLastOperationSuccess(true);
        currentState.setLastMessage(outputData.getMessage());
        currentState.setLastPurchasedItem("Loot Box");

        shopViewModel.setState(currentState);
        shopViewModel.firePropertyChange();

        viewManagerModel.setState("rename_pet");
        viewManagerModel.firePropertyChange();
    }

    /**
     * Presents loot box failure.
     * @param outputData output data
     */
    @Override
    public void presentLootBoxFailure(final BuyLootBoxOutputData outputData) {
        ShopState currentState = shopViewModel.getState();

        // Refresh
        currentState.setCurrentUser(dataAccess.getCurrentUser());

        // Update state with loot box failure
        currentState.setLastOperationSuccess(false);
        currentState.setLastMessage(outputData.getMessage());
        currentState.setLastPurchasedItem("");

        // Update the ViewModel and fire property change
        shopViewModel.setState(currentState);
        shopViewModel.firePropertyChange();
    }

    /**
     * Presents upgrade success.
     * @param outputData output data
     */
    @Override
    public void presentUpgradeSuccess(
            final UpgradeClickerOutputData outputData) {
        ShopState currentState = shopViewModel.getState();

        currentState.setCurrentUser(dataAccess.getCurrentUser());

        currentState.setLastOperationSuccess(true);
        currentState.setLastMessage(outputData.getMessage());
        currentState.setLastPurchasedItem("Upgrade Clicker: Level "
                + String.valueOf(outputData.getBeforeLevel())
                + " -> Level "
                + String.valueOf(outputData.getAfterLevel()));

        shopViewModel.setState(currentState);
        shopViewModel.firePropertyChange();
    }

    /**
     * Presents upgrade failure.
     * @param outputData output data
     */
    @Override
    public void presentUpgradeFailure(
            final UpgradeClickerOutputData outputData) {
        ShopState currentState = shopViewModel.getState();

        currentState.setCurrentUser(dataAccess.getCurrentUser());

        currentState.setLastOperationSuccess(false);
        currentState.setLastMessage(outputData.getMessage());
        currentState.setLastPurchasedItem("");

        shopViewModel.setState(currentState);
        shopViewModel.firePropertyChange();
    }

    /**
     * Presents unlock success.
     * @param outputData output data
     */
    @Override
    public void presentUnlockSuccess(final UnlockSlotOutputData outputData) {
        ShopState currentState = shopViewModel.getState();

        currentState.setCurrentUser(dataAccess.getCurrentUser());

        currentState.setLastOperationSuccess(true);
        currentState.setLastMessage(outputData.getMessage());
        currentState.setLastPurchasedItem("Unlock Slot: "
                + String.valueOf(outputData.getBeforeSlots())
                + " slots -> "
                + String.valueOf(outputData.getAfterSlots())
                + " slots");

        shopViewModel.setState(currentState);
        shopViewModel.firePropertyChange();
    }

    /**
     * Presents unlock failure.
     * @param outputData output data
     */
    @Override
    public void presentUnlockFailure(final UnlockSlotOutputData outputData) {
        ShopState currentState = shopViewModel.getState();

        currentState.setCurrentUser(dataAccess.getCurrentUser());

        currentState.setLastOperationSuccess(false);
        currentState.setLastMessage(outputData.getMessage());
        currentState.setLastPurchasedItem("");

        shopViewModel.setState(currentState);
        shopViewModel.firePropertyChange();
    }
}
