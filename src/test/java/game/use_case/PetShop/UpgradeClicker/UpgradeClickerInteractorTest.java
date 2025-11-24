package game.use_case.PetShop.UpgradeClicker;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import game.Constants;
import game.entity.User;
import game.use_case.PetShop.ShopMessageConstants;

/**
 * Test class for UpgradeClickerInteractor.
 */
public class UpgradeClickerInteractorTest {
    TestDataAccess dataAccess = new TestDataAccess();
    TestOutputBoundary outputBoundary = new TestOutputBoundary();
    UpgradeClickerInteractor interactor = new UpgradeClickerInteractor(outputBoundary, dataAccess);
    
    /**
     * Test successful clicker upgrade.
     */
    @Test
    public void testUpgradeClickerSuccess() {
        dataAccess.getUser().coinCount = 100;
        int initialLevel = dataAccess.getUser().getClickBonusTime();

        int storeCurrentPrice = dataAccess.getUser().getCurrentUpgradePrice();
        interactor.execute(new UpgradeClickerInputData());
        
        //make sure outputing the right data
        assertEquals(ShopMessageConstants.UPGRADE_SUCCESS, outputBoundary.lastOutputData.getMessage());
        assertTrue(outputBoundary.lastOutputData.isSuccess());
        assertEquals(initialLevel, outputBoundary.lastOutputData.getBeforeLevel());
        assertEquals(initialLevel + 1, outputBoundary.lastOutputData.getAfterLevel());
        
        //make sure the user is changing correctly
        assertEquals(100-storeCurrentPrice, dataAccess.getUser().coinCount);
        assertEquals(initialLevel + 1, dataAccess.getUser().getClickBonusTime());
    }
    
    /**
     * Test clicker upgrade with insufficient coins.
     */
    @Test
    public void testUpgradeClickerInsufficientCoins() {
        dataAccess.getUser().coinCount = 30; // Less than upgrade price (50)
        int initialLevel = dataAccess.getUser().getClickBonusTime();
        
        interactor.execute(new UpgradeClickerInputData());
        
        //make sure outputing the right data
        assertEquals(ShopMessageConstants.INSUFFICIENT_COINS, outputBoundary.lastOutputData.getMessage());
        assertFalse(outputBoundary.lastOutputData.isSuccess());
        assertEquals(initialLevel, outputBoundary.lastOutputData.getBeforeLevel());
        assertEquals(initialLevel, outputBoundary.lastOutputData.getAfterLevel());
        
        //make sure the user is changing correctly
        assertEquals(30, dataAccess.getUser().coinCount);
        assertEquals(initialLevel, dataAccess.getUser().getClickBonusTime());
    }
    
    /**
     * Test clicker upgrade at max level.
     */
    @Test
    public void testUpgradeClickerMaxLevel() {
        dataAccess.getUser().coinCount = 10000;
        for (int i = 0; i < Constants.MAX_CLICK_BONUS_UPGRADES; i++) {
            dataAccess.getUser().upgradeClickBonus();
        }
        int maxLevel = dataAccess.getUser().getClickBonusTime();

        dataAccess.getUser().coinCount = 100;

        interactor.execute(new UpgradeClickerInputData());
        
        //make sure outputing the right data
        assertEquals(ShopMessageConstants.MAX_LEVEL_REACHED, outputBoundary.lastOutputData.getMessage());
        assertFalse(outputBoundary.lastOutputData.isSuccess());
        assertEquals(maxLevel, outputBoundary.lastOutputData.getBeforeLevel());
        assertEquals(maxLevel, outputBoundary.lastOutputData.getAfterLevel());
        
        //make sure the user is changing correctly
        assertEquals(100, dataAccess.getUser().coinCount);
        assertEquals(maxLevel, dataAccess.getUser().getClickBonusTime());
    }
    
    /**
     * Test multiple upgrades with increasing prices.
     */
    @Test
    public void testMultipleUpgrades() {
        dataAccess.getUser().coinCount = 500;

        int storeCurrentPrice = dataAccess.getUser().getCurrentUpgradePrice();
        
        // First upgrade 
        interactor.execute(new UpgradeClickerInputData());
        assertTrue(outputBoundary.lastOutputData.isSuccess());
        assertEquals(500-storeCurrentPrice, dataAccess.getUser().coinCount); 
        assertEquals(2, dataAccess.getUser().getClickBonusTime());

        int storeCurrentCoin = dataAccess.getUser().coinCount;
        storeCurrentPrice = dataAccess.getUser().getCurrentUpgradePrice();
        
        // Second upgrade 
        interactor.execute(new UpgradeClickerInputData());
        assertTrue(outputBoundary.lastOutputData.isSuccess());
        assertEquals(storeCurrentCoin-storeCurrentPrice, dataAccess.getUser().coinCount); 
        assertEquals(3, dataAccess.getUser().getClickBonusTime());
    }
    
    // Test helper classes
    
    private static class TestDataAccess implements UpgradeClickerDataAccessInterface {
        private User user = new User();
        
        @Override
        public User getCurrentUser() {
            return user;
        }
        
        @Override
        public void saveUser(User user) {
            this.user = user;
        }
        
        public User getUser() {
            return user;
        }
    }
    
    private static class TestOutputBoundary implements UpgradeClickerOutputBoundary {
        private UpgradeClickerOutputData lastOutputData;
        
        @Override
        public void presentUpgradeSuccess(UpgradeClickerOutputData outputData) {
            this.lastOutputData = outputData;
        }
        
        @Override
        public void presentUpgradeFailure(UpgradeClickerOutputData outputData) {
            this.lastOutputData = outputData;
        }
    }
}
