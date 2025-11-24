package game.use_case.PetShop.UpgradeClicker;

/**
 * Output Boundary interface for upgrading clicker use case.
 * This interface defines the function of the Presenter need to implement.
 */
public interface UpgradeClickerOutputBoundary {
    
    /**
     * Present the result of a successful clicker upgrade.
     * This method is called by the Interactor when clicker is successfully upgraded.
     * 
     * @param outputData contains upgrade success information
     */
    void presentUpgradeSuccess(UpgradeClickerOutputData outputData);
    
    /**
     * Present the result of a failed clicker upgrade.
     * This method is called by the Interactor when clicker upgrade fails
     * (insufficient coins or max level reached).
     * 
     * @param outputData contains upgrade failure information
     */
    void presentUpgradeFailure(UpgradeClickerOutputData outputData);
}
