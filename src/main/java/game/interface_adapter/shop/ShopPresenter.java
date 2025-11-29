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
public class ShopPresenter implements BuyItemOutputBoundary, BuyLootBoxOutputBoundary, 
                                     UpgradeClickerOutputBoundary, UnlockSlotOutputBoundary {

    private final ViewManagerModel viewManagerModel;//not changing page as it is a pop-up window
    private final ShopViewModel shopViewModel;
    private final ShopDataAccessObject dataAccess;

    public ShopPresenter(ViewManagerModel viewManagerModel, ShopViewModel shopViewModel, ShopDataAccessObject dataAccess) {
        this.viewManagerModel = viewManagerModel;
        this.shopViewModel = shopViewModel;
        this.dataAccess = dataAccess;
    }

    @Override
    public void presentBuyingSuccess(BuyItemOutputData outputData) {
        ShopState currentState = shopViewModel.getState();
        
        // Refresh
        currentState.setCurrentUser(dataAccess.getCurrentUser());
        
        currentState.setLastOperationSuccess(true);
        currentState.setLastMessage(outputData.getMessage());
        currentState.setLastPurchasedItem(outputData.getItemName());
        
        shopViewModel.setState(currentState);
        shopViewModel.firePropertyChange();
    }

    @Override
    public void presentBuyingFailure(BuyItemOutputData outputData) {
        ShopState currentState = shopViewModel.getState();
        
        currentState.setCurrentUser(dataAccess.getCurrentUser());
        
        currentState.setLastOperationSuccess(false);
        currentState.setLastMessage(outputData.getMessage());
        currentState.setLastPurchasedItem("");
        
        shopViewModel.setState(currentState);
        shopViewModel.firePropertyChange();
    }

    @Override
    public void presentLootBoxSuccess(BuyLootBoxOutputData outputData) {
        ShopState currentState = shopViewModel.getState();
        
        currentState.setCurrentUser(dataAccess.getCurrentUser());
        
        currentState.setLastOperationSuccess(true);
        currentState.setLastMessage(outputData.getMessage());
        currentState.setLastPurchasedItem("Loot Box");
        
        shopViewModel.setState(currentState);
        shopViewModel.firePropertyChange();
        
        // TODO: Navigate to pet naming view
        // viewManagerModel.setState("pet_naming");
        // viewManagerModel.firePropertyChange();
    }

    @Override
    public void presentLootBoxFailure(BuyLootBoxOutputData outputData) {
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

    @Override
    public void presentUpgradeSuccess(UpgradeClickerOutputData outputData) {
        ShopState currentState = shopViewModel.getState();
        
        currentState.setCurrentUser(dataAccess.getCurrentUser());
        
        currentState.setLastOperationSuccess(true);
        currentState.setLastMessage(outputData.getMessage());
        currentState.setLastPurchasedItem("Upgrade Clicker: Level " + 
            String.valueOf(outputData.getBeforeLevel()) + " -> Level " + 
            String.valueOf(outputData.getAfterLevel()));
        
        shopViewModel.setState(currentState);
        shopViewModel.firePropertyChange();
    }

    @Override
    public void presentUpgradeFailure(UpgradeClickerOutputData outputData) {
        ShopState currentState = shopViewModel.getState();
        
        currentState.setCurrentUser(dataAccess.getCurrentUser());
        
        currentState.setLastOperationSuccess(false);
        currentState.setLastMessage(outputData.getMessage());
        currentState.setLastPurchasedItem("");
        
        shopViewModel.setState(currentState);
        shopViewModel.firePropertyChange();
    }

    @Override
    public void presentUnlockSuccess(UnlockSlotOutputData outputData) {
        ShopState currentState = shopViewModel.getState();
        
        currentState.setCurrentUser(dataAccess.getCurrentUser());
        
        currentState.setLastOperationSuccess(true);
        currentState.setLastMessage(outputData.getMessage());
        currentState.setLastPurchasedItem("Unlock Slot: " + 
            String.valueOf(outputData.getBeforeSlots()) + " slots -> " + 
            String.valueOf(outputData.getAfterSlots()) + " slots");
        
        shopViewModel.setState(currentState);
        shopViewModel.firePropertyChange();
    }

    @Override
    public void presentUnlockFailure(UnlockSlotOutputData outputData) {
        ShopState currentState = shopViewModel.getState();
        
        currentState.setCurrentUser(dataAccess.getCurrentUser());
        
        currentState.setLastOperationSuccess(false);
        currentState.setLastMessage(outputData.getMessage());
        currentState.setLastPurchasedItem("");
        
        shopViewModel.setState(currentState);
        shopViewModel.firePropertyChange();
    }
}
