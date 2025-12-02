package game.use_case.PetShop.BuyItem;

import game.entity.User;
import game.use_case.PetShop.ShopMessageConstants;

/**
 * Interactor for buying an item use case.
 * This class implements the BuyItemInputBoundary interface and is
 * responsible for buying an item.
 */
public class BuyItemInteractor implements BuyItemInputBoundary {

    /**
     * Output boundary.
     */
    private final BuyItemOutputBoundary outputBoundary;
    /**
     * Data access interface.
     */
    private final BuyItemDataAccessInterface dataAccess;

    /**
     * Constructor for BuyItemInteractor.
     * @param output the presenter that will handle output
     * @param dataAccessObj the data access object for user data persistence
     */
    public BuyItemInteractor(final BuyItemOutputBoundary output,
                             final BuyItemDataAccessInterface dataAccessObj) {
        this.outputBoundary = output;
        this.dataAccess = dataAccessObj;
    }

    /**
     * Execute the buy item use case.
     *
     * @param inputData contains the item name to buy
     */
    @Override
    public void execute(final BuyItemInputData inputData) {
        String itemName = inputData.getItemName();
        // Get current user
        User user = dataAccess.getCurrentUser();

        // Check if user has enough coins
        if (!user.coinCheck(user.getItemByName(itemName).getPrice())) {
            BuyItemOutputData outputData = new BuyItemOutputData(
                    false,
                    ShopMessageConstants.INSUFFICIENT_COINS,
                    itemName
            );
            outputBoundary.presentBuyingFailure(outputData);
            return;
        }

        // buy item
        user.buyPetItem(itemName);

        // Save the updated user data
        dataAccess.saveUser(user);

        // Create success output data
        BuyItemOutputData outputData = new BuyItemOutputData(
                true,
                ShopMessageConstants.PURCHASE_SUCCESS,
                itemName
        );

        outputBoundary.presentBuyingSuccess(outputData);
    }
}
