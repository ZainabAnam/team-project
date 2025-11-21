package game.use_case.PetShop;

import game.use_case.PetShop.BuyItem.BuyItemInputBoundary;
import game.use_case.PetShop.BuyItem.BuyItemInputData;
import game.use_case.PetShop.BuyLootBox.BuyLootBoxInputBoundary;
import game.use_case.PetShop.BuyLootBox.BuyLootBoxInputData;
import game.use_case.PetShop.UpgradeClicker.UpgradeClickerInputBoundary;
import game.use_case.PetShop.UpgradeClicker.UpgradeClickerInputData;
import game.use_case.PetShop.UnlockSlot.UnlockSlotInputBoundary;
import game.use_case.PetShop.UnlockSlot.UnlockSlotInputData;

import game.Constants;

/**
 * Controller for shop operations.
 * This class handles all shop-related user interactions and converts them to use case calls.
 */
public class ShopController {
    
    private final BuyItemInputBoundary buyItemUseCase;
    private final BuyLootBoxInputBoundary buyLootBoxUseCase;
    private final UpgradeClickerInputBoundary upgradeClickerUseCase;
    private final UnlockSlotInputBoundary unlockSlotUseCase;
    
    /**
     * Constructor for ShopController.
     * 
     * @param buyItemUseCase the buy item use case
     * @param buyLootBoxUseCase the buy loot box use case
     * @param upgradeClickerUseCase the upgrade clicker use case
     * @param unlockSlotUseCase the unlock slot use case
     */
    public ShopController(BuyItemInputBoundary buyItemUseCase,
                         BuyLootBoxInputBoundary buyLootBoxUseCase,
                         UpgradeClickerInputBoundary upgradeClickerUseCase,
                         UnlockSlotInputBoundary unlockSlotUseCase) {
        this.buyItemUseCase = buyItemUseCase;
        this.buyLootBoxUseCase = buyLootBoxUseCase;
        this.upgradeClickerUseCase = upgradeClickerUseCase;
        this.unlockSlotUseCase = unlockSlotUseCase;
    }
    
    /**
     * Handle buying an item (pet food or pet toy).
     * Make the item name to input data and call the appropriate use case.
     * 
     * @param itemName the name of the item to buy (must match Constants.itemList)
     */
    public void buyItem(String itemName) {
        BuyItemInputData inputData = new BuyItemInputData(itemName);
        buyItemUseCase.execute(inputData);
    }
    
    /**
     * Handle buying a loot box.
     * Creates InputData and calls the loot box Use Case.
     * This will trigger navigation to pet naming interface if successful.
     */
    public void buyLootBox() {
        BuyLootBoxInputData inputData = new BuyLootBoxInputData();
        buyLootBoxUseCase.execute(inputData);
    }
    
    /**
     * Handle upgrading the clicker.
     * Creates InputData and calls the upgrade clicker Use Case.
     */
    public void upgradeClicker() {
        UpgradeClickerInputData inputData = new UpgradeClickerInputData();
        upgradeClickerUseCase.execute(inputData);
    }
    
    /**
     * Handle unlocking a pet slot.
     * Creates InputData and calls the unlock slot Use Case.
     */
    public void unlockSlot() {
        UnlockSlotInputData inputData = new UnlockSlotInputData();
        unlockSlotUseCase.execute(inputData);
    }

    //buy item methods

    /**
     * Buy basic pet food.
     */
    public void buyBasicPetFood() {
        buyItem(Constants.ITEM_KIBBLE);
    }
    
    /**
     * Buy medium pet food.
     */
    public void buyMediumPetFood() {
        buyItem(Constants.ITEM_CANNED_FOOD);
    }
    
    /**
     * Buy premium pet food.
     */
    public void buyPremiumPetFood() {
        buyItem(Constants.ITEM_HOME_COOKED);
    }
    
    /**
     * Buy basic pet toy .
     */
    public void buyBasicPetToy() {
        buyItem(Constants.ITEM_CHEW_TOY);
    }
    
    /**
     * Buy medium pet toy .
     */
    public void buyMediumPetToy() {
        buyItem(Constants.ITEM_TOSS_TOY);
    }
    
    /**
     * Buy premium pet toy.
     */
    public void buyPremiumPetToy() {
        buyItem(Constants.ITEM_PLUSH_TOY);
    }
}