package game.use_case.PetShop.BuyLootBox;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import game.Constants;
import game.entity.User;
import game.entity.Pet;
import game.use_case.PetShop.ShopMessageConstants;

/**
 * Test class for BuyLootBoxInteractor.
 */
public class BuyLootBoxInteractorTest {
    TestDataAccess dataAccess = new TestDataAccess();
    TestOutputBoundary outputBoundary = new TestOutputBoundary();
    BuyLootBoxInteractor interactor = new BuyLootBoxInteractor(outputBoundary, dataAccess);
    
    /**
     * Test successful loot box purchase.
     */
    @Test
    public void testBuyLootBoxSuccess() {
        dataAccess.getUser().coinCount = 100;
        int initialPetCount = dataAccess.getUser().getPetInventory().size();

        interactor.execute(new BuyLootBoxInputData());
        
        //make sure outputing the right data
        assertEquals(ShopMessageConstants.LOOTBOX_SUCCESS, outputBoundary.lastOutputData.getMessage());
        assertTrue(outputBoundary.lastOutputData.isSuccess());
        assertNotNull(outputBoundary.lastOutputData.getLootboxPet());
        
        //make sure the user is changing correctly
        assertEquals(100-Constants.LOOT_BOX_PRICE, dataAccess.getUser().coinCount); 
        assertEquals(initialPetCount + 1, dataAccess.getUser().getPetInventory().size());
        
        // Check the new pet is the same as returned in output
        Pet newPet = dataAccess.getUser().getPetInventory().get(dataAccess.getUser().getPetInventory().size() - 1);
        assertEquals(newPet, outputBoundary.lastOutputData.getLootboxPet());
    }
    
    /**
     * Test loot box purchase with insufficient coins.
     */
    @Test
    public void testBuyLootBoxInsufficientCoins() {
        dataAccess.getUser().coinCount = 30; 
        int initialPetCount = dataAccess.getUser().getPetInventory().size();

        interactor.execute(new BuyLootBoxInputData());
        
        //make sure outputing the right data
        assertEquals(ShopMessageConstants.INSUFFICIENT_COINS, outputBoundary.lastOutputData.getMessage());
        assertFalse(outputBoundary.lastOutputData.isSuccess());
        assertNull(outputBoundary.lastOutputData.getLootboxPet());
        
        //make sure the user is changing correctly
        assertEquals(30, dataAccess.getUser().coinCount);
        assertEquals(initialPetCount, dataAccess.getUser().getPetInventory().size());
    }
    
    /**
     * Test multiple loot box purchases.
     */
    @Test
    public void testMultipleLootBoxPurchases() {
        dataAccess.getUser().coinCount = 150;
        int initialPetCount = dataAccess.getUser().getPetInventory().size();
        
        // First purchase
        interactor.execute(new BuyLootBoxInputData());
        assertTrue(outputBoundary.lastOutputData.isSuccess());
        assertEquals(150-Constants.LOOT_BOX_PRICE, dataAccess.getUser().coinCount); 
        
        // Second purchase
        interactor.execute(new BuyLootBoxInputData());
        assertTrue(outputBoundary.lastOutputData.isSuccess());
        assertEquals(150-Constants.LOOT_BOX_PRICE*2, dataAccess.getUser().coinCount);
        
        // Check final state
        assertEquals(initialPetCount + 2, dataAccess.getUser().getPetInventory().size());
    }
    
    // Test helper classes
    
    private static class TestDataAccess implements BuyLootBoxDataAccessInterface {
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
    
    private static class TestOutputBoundary implements BuyLootBoxOutputBoundary {
        private BuyLootBoxOutputData lastOutputData;
        
        @Override
        public void presentLootBoxSuccess(BuyLootBoxOutputData outputData) {
            this.lastOutputData = outputData;
        }
        
        @Override
        public void presentLootBoxFailure(BuyLootBoxOutputData outputData) {
            this.lastOutputData = outputData;
        }
    }
}
