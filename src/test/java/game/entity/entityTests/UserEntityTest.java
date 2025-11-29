package game.entity.entityTests;

import game.entity.LootBox;
import game.entity.Pet;
import game.entity.User;
import org.testng.annotations.Test;
import game.Constants;

import javax.swing.*;

import java.util.Optional;

import static org.junit.Assert.*;

public class UserEntityTest {
    @Test
    public void testUserEntity() {
        User user  = new User();
        assertEquals(Constants.INITIAL_COINS, user.getCoinCount());
        assertEquals(Constants.INITIAL_CLICKBONUS, user.getClickBonus());
        assertEquals(Constants.INITIAL_CLICKBONUS_TIME, user.getClickBonusTime());
        assertEquals(Constants.INITIAL_SLOTS, user.getUnlockedSlots());
        assertEquals(Constants.itemList.toArray().length, user.getItemsList().size());
        assertEquals(Constants.itemList.toArray().length, user.getItemsAmountList().size());
    }

    @Test
    public void testCoinCheck() {
        User user  = new User();
        assertTrue(user.coinCheck(Constants.INITIAL_COINS - 1));
        assertFalse(user.coinCheck(Constants.INITIAL_COINS + 1));
    }

    @Test
    public void testBuyLootBox() {
        User user  = new User();
        user.buyLootBox(new LootBox());
        assertEquals(Constants.INITIAL_COINS - Constants.LOOT_BOX_PRICE, user.getCoinCount());
    }

    @Test
    public void testAddToPetInventory() {
        User user  = new User();
        ImageIcon imageIcon = new ImageIcon("/images/Pet Images/Cat Images/Main Coon Icon.png");
        Pet pet = new Pet("Cat", "Maine Coon", "Elite", 7, 6, 6,
                imageIcon);
        user.addToPetInventory(pet);

        assertFalse(user.getPetInventory().isEmpty());
        assertTrue(user.getPetInventory().contains(pet));
    }

    @Test
    public void testGetCoinCount() {
        User user  = new User();
        assertEquals(Constants.INITIAL_COINS, user.getCoinCount());
    }

    @Test
    public void testSubtractCoins() {
        User user  = new User();
        user.subtractCoins(Constants.INITIAL_COINS);
        assertEquals(0, user.getCoinCount());
    }

    @Test
    public void testAddCoins() {
        User user  = new User();
        user.addCoins(Constants.INITIAL_COINS);
        assertEquals(2 * Constants.INITIAL_COINS, user.getCoinCount());
    }

    @Test
    public void testUnlockPetSlot() {
        User user  = new User();
        user.unlockPetSlot();
        assertEquals(Constants.INITIAL_SLOTS + 1, user.getUnlockedSlots());
    }

    @Test
    public void testCanUnlockSlot() {
        User user  = new User();
        user.unlockPetSlot();
        assertTrue(user.canUnlockSlot());
        user.unlockPetSlot();
        assertFalse(user.canUnlockSlot());
    }

    @Test
    public void testGetCurrentUnlockSlotPrice() {
        User user  = new User();
        int price1 = user.getCurrentUnlockSlotPrice();
        assertEquals(Constants.UNLOCK_SLOT_BASE_PRICE, price1);
        user.unlockPetSlot();
        int price2 = user.getCurrentUnlockSlotPrice();
        assertEquals(2 * Constants.UNLOCK_SLOT_BASE_PRICE, price2);
    }

    @Test
    public void testGetUnlockedSlots() {
        User user  = new User();
        assertEquals(Constants.INITIAL_SLOTS, user.getUnlockedSlots());
        user.unlockPetSlot();
        assertEquals(Constants.INITIAL_SLOTS + 1, user.getUnlockedSlots());
    }

    @Test
    public void testGetClickBonusTime() {
        User user  = new User();
        user.upgradeClickBonus();
        assertEquals(Constants.INITIAL_CLICKBONUS_TIME + 1, user.getClickBonusTime());
    }

    @Test
    public void testGetCurrentUpgradePrice() {
        User user  = new User();
        assertEquals(Constants.INITIAL_CLICKBONUS_TIME * Constants.UPGRADE_CLICKER_BASE_PRICE,
                user.getCurrentUpgradePrice());
        user.upgradeClickBonus();
        assertEquals((Constants.INITIAL_CLICKBONUS_TIME + 1) * Constants.UPGRADE_CLICKER_BASE_PRICE,
                user.getCurrentUpgradePrice());
    }

    @Test
    public void testGetClickBonus() {
        User user = new User();
        assertEquals(Constants.INITIAL_CLICKBONUS, user.getClickBonus());
        user.upgradeClickBonus();
        assertEquals(Constants.CLICKBONUS_INCREASE_BASE,
                user.getClickBonus());
    }

    @Test
    public void testCanUpgradeClicker() {
        User user  = new User();
        assertTrue(user.canUpgradeClicker());
        user.upgradeClickBonus();
        user.upgradeClickBonus();
        user.upgradeClickBonus();
        user.upgradeClickBonus();
        assertFalse(user.canUpgradeClicker());
    }

    @Test
    public void testItemsAmountList() {
        User user  = new User();
        ImageIcon imageIcon = new ImageIcon("/images/Pet Images/Cat Images/Main Coon Icon.png");
        Pet pet = new Pet("Cat", "Maine Coon", "Elite", 7, 6, 6,
                imageIcon);
        user.buyPetItem(Constants.ITEM_KIBBLE);
        assertEquals(Constants.INITIAL_COINS - Constants.PET_FOOD_BASIC_PRICE, user.getCoinCount());
        int i = user.getItemsAmountList().get(Constants.ITEM_KIBBLE);
        assertEquals(1, i);
        assertTrue(user.itemCheck(Constants.ITEM_KIBBLE));
        user.usePetItem(pet, Constants.ITEM_KIBBLE);
        int j = user.getItemsAmountList().get(Constants.ITEM_KIBBLE);
        assertEquals(0, j);
    }
}
