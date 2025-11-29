package game.use_case.PetShop.BuyItem;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import game.entity.User;
import game.use_case.PetShop.ShopMessageConstants;
import game.Constants;

/**
 * Test class for BuyItemInteractor.
 */
public class BuyItemInteractorTest {
    TestDataAccess dataAccess = new TestDataAccess();
    TestOutputBoundary outputBoundary = new TestOutputBoundary();
    BuyItemInteractor interactor = new BuyItemInteractor(outputBoundary, dataAccess);
    /**
     * Test successful item purchase.
     */
    @Test
    public void testBuyItemSuccess() {
        dataAccess.getUser().addCoins(100);

        interactor.execute(new BuyItemInputData("Kibble"));

        //make sure outputing the right data
        assertEquals(ShopMessageConstants.PURCHASE_SUCCESS, outputBoundary.lastOutputData.getMessage());
        assertEquals("Kibble", outputBoundary.lastOutputData.getItemName());
        assertTrue(outputBoundary.lastOutputData.isSuccess());

        //make sure the user is changing correctly
        assertEquals(100-Constants.PET_FOOD_BASIC_PRICE, dataAccess.getUser().getCoinCount()); 
        assertEquals(1, dataAccess.getUser().getItemsAmountList().get("Kibble"));
    }
    
    /**
     * Test item purchase with insufficient coins.
     */
    @Test
    public void testBuyItemInsufficientCoins() {
        dataAccess.getUser().addCoins(5);

        interactor.execute(new BuyItemInputData("Kibble"));
        
        //make sure outputing the right data
        assertEquals(ShopMessageConstants.INSUFFICIENT_COINS, outputBoundary.lastOutputData.getMessage());
        assertEquals("Kibble", outputBoundary.lastOutputData.getItemName());
        assertFalse(outputBoundary.lastOutputData.isSuccess());
        
        //make sure the user is changing correctly
        assertEquals(5, dataAccess.getUser().getCoinCount());
        assertEquals(0, dataAccess.getUser().getItemsAmountList().get("Kibble"));
    }
    
    /**
     * Test buying different item types.
     */
    @Test
    public void testBuyToyItemSuccess() {
        dataAccess.getUser().addCoins(200);

        interactor.execute(new BuyItemInputData("Chew Toy"));

        //make sure outputing the right data
        assertEquals(ShopMessageConstants.PURCHASE_SUCCESS, outputBoundary.lastOutputData.getMessage());
        assertEquals("Chew Toy", outputBoundary.lastOutputData.getItemName());
        assertTrue(outputBoundary.lastOutputData.isSuccess());

        //make sure the user is changing correctly
        assertEquals(200-Constants.PET_TOY_BASIC_PRICE, dataAccess.getUser().getCoinCount());
        assertEquals(1, dataAccess.getUser().getItemsAmountList().get("Chew Toy"));
    }
    
    private static class TestDataAccess implements BuyItemDataAccessInterface {
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
    
    private static class TestOutputBoundary implements BuyItemOutputBoundary {
        private BuyItemOutputData lastOutputData;
        
        @Override
        public void presentBuyingSuccess(BuyItemOutputData outputData) {
            this.lastOutputData = outputData;
        }
        
        @Override
        public void presentBuyingFailure(BuyItemOutputData outputData) {
            this.lastOutputData = outputData;
        }
    }
}
