package game.use_case.PetShop.UnlockSlot;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import game.Constants;
import game.entity.User;
import game.use_case.PetShop.ShopMessageConstants;

/**
 * Test class for UnlockSlotInteractor.
 */
public class UnlockSlotInteractorTest {
    TestDataAccess dataAccess = new TestDataAccess();
    TestOutputBoundary outputBoundary = new TestOutputBoundary();
    UnlockSlotInteractor interactor = new UnlockSlotInteractor(outputBoundary, dataAccess);
    
    /**
     * Test successful slot unlock.
     */
    @Test
    public void testUnlockSlotSuccess() {
        dataAccess.getUser().addCoins(150);
        int initialSlots = dataAccess.getUser().getUnlockedSlots();

        int storeCurrentPrice = dataAccess.getUser().getCurrentUnlockSlotPrice();
        interactor.execute(new UnlockSlotInputData());
        
        //make sure outputing the right data
        assertEquals(ShopMessageConstants.UNLOCK_SUCCESS, outputBoundary.lastOutputData.getMessage());
        assertTrue(outputBoundary.lastOutputData.isSuccess());
        assertEquals(initialSlots, outputBoundary.lastOutputData.getBeforeSlots());
        assertEquals(initialSlots + 1, outputBoundary.lastOutputData.getAfterSlots());
        
        //make sure the user is changing correctly
        assertEquals(150-storeCurrentPrice, dataAccess.getUser().getCoinCount()); 
        assertEquals(initialSlots + 1, dataAccess.getUser().getUnlockedSlots());
    }
    
    /**
     * Test slot unlock with insufficient coins.
     */
    @Test
    public void testUnlockSlotInsufficientCoins() {
        dataAccess.getUser().addCoins(50); 
        int initialSlots = dataAccess.getUser().getUnlockedSlots();
        
        interactor.execute(new UnlockSlotInputData());
        
        //make sure outputing the right data
        assertEquals(ShopMessageConstants.INSUFFICIENT_COINS, outputBoundary.lastOutputData.getMessage());
        assertFalse(outputBoundary.lastOutputData.isSuccess());
        assertEquals(initialSlots, outputBoundary.lastOutputData.getBeforeSlots());
        assertEquals(initialSlots, outputBoundary.lastOutputData.getAfterSlots());
        
        //make sure the user is changing correctly
        assertEquals(50, dataAccess.getUser().getCoinCount());
        assertEquals(initialSlots, dataAccess.getUser().getUnlockedSlots());
    }
    
    /**
     * Test slot unlock at max slots.
     */
    @Test
    public void testUnlockSlotMaxSlots() {
        dataAccess.getUser().addCoins(10000);
        for (int i = 0; i < Constants.MAX_PET_SLOTS; i++) {
            dataAccess.getUser().unlockPetSlot();
        }
        int maxSlots = dataAccess.getUser().getUnlockedSlots();

        dataAccess.getUser().addCoins(100);

        interactor.execute(new UnlockSlotInputData());
        
        //make sure outputing the right data
        assertEquals(ShopMessageConstants.MAX_SLOTS_REACHED, outputBoundary.lastOutputData.getMessage());
        assertFalse(outputBoundary.lastOutputData.isSuccess());
        assertEquals(maxSlots, outputBoundary.lastOutputData.getBeforeSlots());
        assertEquals(maxSlots, outputBoundary.lastOutputData.getAfterSlots());
        
        //make sure the user is changing correctly
        assertEquals(100, dataAccess.getUser().getCoinCount());
        assertEquals(maxSlots, dataAccess.getUser().getUnlockedSlots());
    }
    
    /**
     * Test multiple unlocks with increasing prices.
     */
    @Test
    public void testMultipleUnlocks() {
        dataAccess.getUser().addCoins(1000);
        
        int storeCurrentPrice = dataAccess.getUser().getCurrentUnlockSlotPrice();

        interactor.execute(new UnlockSlotInputData());
        assertTrue(outputBoundary.lastOutputData.isSuccess());
        assertEquals(1000-storeCurrentPrice, dataAccess.getUser().getCoinCount());
        assertEquals(Constants.INITIAL_SLOTS+1, dataAccess.getUser().getUnlockedSlots());

        int storeCurrentCoin = dataAccess.getUser().getCoinCount();
        storeCurrentPrice = dataAccess.getUser().getCurrentUnlockSlotPrice();
  
        interactor.execute(new UnlockSlotInputData());
        assertTrue(outputBoundary.lastOutputData.isSuccess());
        assertEquals(storeCurrentCoin-storeCurrentPrice, dataAccess.getUser().getCoinCount()); 
        assertEquals(4, dataAccess.getUser().getUnlockedSlots());

        storeCurrentCoin = dataAccess.getUser().getCoinCount();

        interactor.execute(new UnlockSlotInputData());
        assertTrue(!outputBoundary.lastOutputData.isSuccess());
        assertEquals(storeCurrentCoin, dataAccess.getUser().getCoinCount()); 
        assertEquals(4, dataAccess.getUser().getUnlockedSlots());
    }
    
    // Test helper classes
    
    private static class TestDataAccess implements UnlockSlotDataAccessInterface {
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
    
    private static class TestOutputBoundary implements UnlockSlotOutputBoundary {
        private UnlockSlotOutputData lastOutputData;
        
        @Override
        public void presentUnlockSuccess(UnlockSlotOutputData outputData) {
            this.lastOutputData = outputData;
        }
        
        @Override
        public void presentUnlockFailure(UnlockSlotOutputData outputData) {
            this.lastOutputData = outputData;
        }
    }
}
