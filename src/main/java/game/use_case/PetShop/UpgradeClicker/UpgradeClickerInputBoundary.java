package game.use_case.PetShop.UpgradeClicker;

/**
 * Input Boundary interface for upgrading clicker use case.
 * This interface defines the function of the Use Case Interactor need to implement.
 */
public interface UpgradeClickerInputBoundary {
    
    /**
     * Execute the upgrade clicker use case.
     * This method should be called by the Controller to initiate the upgrade process.
     * 
     * @param inputData (actually no data needed for this use case)
     */
    void execute(UpgradeClickerInputData inputData);
}
