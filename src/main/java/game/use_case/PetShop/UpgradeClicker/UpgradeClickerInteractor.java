package game.use_case.PetShop.UpgradeClicker;

import game.entity.User;
import game.use_case.PetShop.ShopMessageConstants;

/**
 * Interactor for upgrading clicker use case.
 * This class implements the UpgradeClickerInputBoundary interface and is responsible for upgrading clicker.
 */
public class UpgradeClickerInteractor implements UpgradeClickerInputBoundary {
    
    private final UpgradeClickerOutputBoundary outputBoundary;
    private final UpgradeClickerDataAccessInterface dataAccess;
    
    /**
     * Constructor for UpgradeClickerInteractor.
     * @param outputBoundary the presenter that will handle output
     * @param dataAccess the data access object for user data persistence
     */
    public UpgradeClickerInteractor(UpgradeClickerOutputBoundary outputBoundary, 
                                  UpgradeClickerDataAccessInterface dataAccess) {
        this.outputBoundary = outputBoundary;
        this.dataAccess = dataAccess;
    }
    
    /**
     * Execute the upgrade clicker use case.
     * 
     * @param inputData contains the upgrade request data
     */
    @Override
    public void execute(UpgradeClickerInputData inputData) {
        // Get current user
        User user = dataAccess.getCurrentUser();
        
         // Check if user can upgrade (not at max level)
         if (!user.canUpgradeClicker()) {
             UpgradeClickerOutputData outputData = new UpgradeClickerOutputData(
                 false, 
                 ShopMessageConstants.MAX_LEVEL_REACHED, 
                 user.getClickBonusTime(), 
                 user.getClickBonusTime()
             );
             outputBoundary.presentUpgradeFailure(outputData);
             return;
         }
         
         // Get upgrade price
         int upgradePrice = user.getCurrentUpgradePrice();
         
         // Check if user has enough coins
         if (!user.coinCheck(upgradePrice)) {
             UpgradeClickerOutputData outputData = new UpgradeClickerOutputData(
                 false, 
                 ShopMessageConstants.INSUFFICIENT_COINS, 
                 user.getClickBonusTime(), 
                 user.getClickBonusTime()
             );
             outputBoundary.presentUpgradeFailure(outputData);
             return;
         }
         
         // store the before level for output
         int beforeLevel = user.getClickBonusTime();
         
         // upgrade
         user.upgradeClickBonus();
         
         // Save the updated user data
         dataAccess.saveUser(user);
         
         // Create success output data
         UpgradeClickerOutputData outputData = new UpgradeClickerOutputData(
             true, 
             ShopMessageConstants.UPGRADE_SUCCESS, 
             beforeLevel, 
             user.getClickBonusTime()
         );
        
        outputBoundary.presentUpgradeSuccess(outputData);
    }
}
