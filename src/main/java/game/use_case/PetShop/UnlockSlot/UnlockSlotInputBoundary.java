package game.use_case.PetShop.UnlockSlot;

/**
 * Input Boundary interface for unlocking slot use case.
 * This interface defines the function of the Use Case Interactor need to implement.
 */
public interface UnlockSlotInputBoundary {
    
    /**
     * Execute the unlock slot use case.
     * This method should be called by the Controller to initiate the unlock process.
     * 
     * @param inputData (actually no data needed for this use case)
     */
    void execute(UnlockSlotInputData inputData);
}
