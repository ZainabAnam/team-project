package game.use_case.PetShop.UnlockSlot;

/**
 * Output Boundary interface for unlocking slot use case.
 * This interface defines the function of the Presenter need to implement.
 */
public interface UnlockSlotOutputBoundary {
    
    /**
     * Present the result of a successful slot unlock.
     * This method is called by the Interactor when slot is successfully unlocked.
     * 
     * @param outputData contains unlock success information
     */
    void presentUnlockSuccess(UnlockSlotOutputData outputData);
    
    /**
     * Present the result of a failed slot unlock.
     * This method is called by the Interactor when slot unlock fails
     * (insufficient coins or max slots reached).
     * 
     * @param outputData contains unlock failure information
     */
    void presentUnlockFailure(UnlockSlotOutputData outputData);
}
