package game.use_case.PetShop.BuyItem;

/**
 * Output Boundary interface for buying an item use case.
 * This interface defines the function of the Presenter need to implement.
 */
public interface BuyItemOutputBoundary {
    
    /**
     * Present the result of a successful item buying.
     * This method is called by the Interactor when an item is successfully bought.
     * 
     * @param outputData contains buying success information
     */
    void presentBuyingSuccess(BuyItemOutputData outputData);
    
    /**
     * Present the result of a failed item buying.
     * This method is called by the Interactor when an item purchase fails
     * (insufficient coins).
     * 
     * @param outputData contains buying failure information
     */
    void presentBuyingFailure(BuyItemOutputData outputData);
}
