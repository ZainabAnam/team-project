package game.entity;

import game.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * User for the game.
 */
public class User {
    /**
     * Coin count.
     */
    private int coinCount;
    /**
     * Click bonus.
     */
    private int clickBonus;
    /**
     * Helper for counting the times of upgrades.
     */
    private int clickBonusTime;
    /**
     * Unlocked slots.
     */
    private int unlockedSlots;

    /**
     * List of pets.
     */
    private List<Pet> petInventory = new ArrayList<>();
    /**
     * List of item names and their amounts.
     */
    private HashMap<String, Integer> itemsAmountList = new HashMap<>();
    /**
     * List of item names and their objects (easy to find the item by name).
     */
    private HashMap<String, Item> itemsList = new HashMap<>();

    /**
     * Constructs a new User.
     */
    public User() {
        this.coinCount = Constants.INITIAL_COINS;
        this.clickBonus = Constants.INITIAL_CLICKBONUS;
        this.clickBonusTime = Constants.INITIAL_CLICKBONUS_TIME;
        this.unlockedSlots = Constants.INITIAL_SLOTS;
        this.addShopList(Constants.itemList);
    }

    /**
     * Helper method for adding the shop list to the user.
     * @param itemList list of items
     */
    public void addShopList(final List<Item> itemList) {
        for (Item item : itemList) {
            itemsAmountList.put(item.getName(), 0);
            itemsList.put(item.getName(), item);
        }
    }

    /**
     * Helper method for getting an item by name.
     * @param itemName name of the item
     * @return item object
     */
    public Item getItemByName(final String itemName) {
        return itemsList.get(itemName);
    }

    /**
     * Check if the user has enough coins.
     * @param price price to check
     * @return true if user has enough coins
     */
    public boolean coinCheck(final int price) {
        return this.coinCount >= price;
    }

    /**
     * Pre: helper method for buying.
     * @param price price to pay
     */
    private void buy(final int price) {
        this.coinCount -= price;
    }

    /**
     * Pre: coinCheck is true.
     * @param lootBox loot box to buy
     */
    public void buyLootBox(final LootBox lootBox) {
        this.buy(lootBox.getPrice());
        this.addToPetInventory(lootBox.getPet());
    }

    /**
     * Adding a pet to the pet inventory.
     * @param pet pet to add
     */
    public void addToPetInventory(final Pet pet) {
        if (pet != null) {
            petInventory.add(pet);
        }
    }

    /**
     * Pre: coinCheck is true.
     * @param itemName name of the item to buy
     */
    public void buyPetItem(final String itemName) {
        Item petItemByName = getItemByName(itemName);
        this.buy(petItemByName.getPrice());
        this.addToItemList(petItemByName.getName());
    }

    /**
     * Helper method that helps to add an item to the item list
     * with the item name.
     * @param itemName name of the item
     */
    public void addToItemList(final String itemName) {
        this.itemsAmountList.put(itemName,
                itemsAmountList.get(itemName) + 1);
    }

    /**
     * Checking if the user have enough items.
     * @param itemName name of the item
     * @return true if user has the item
     */
    public boolean itemCheck(final String itemName) {
        return this.itemsAmountList.get(itemName) > 0;
    }

    /**
     * Pre: itemCheck is true.
     * @param pet pet to use item on
     * @param itemName name of the item
     */
    public void usePetItem(final Pet pet, final String itemName) {
        //find the type of the item and use it
        if (Objects.equals(getItemByName(itemName).getType(), "PetFood")) {
            PetFood petFood = (PetFood) getItemByName(itemName);
            pet.increaseEnergyLevel(petFood.getEnergyIncrease());
        } else if (Objects.equals(getItemByName(itemName).getType(),
                "PetToy")) {
            PetToy petToy = (PetToy) getItemByName(itemName);
            pet.increaseAffectionXP(petToy.getAffectionIncrease());
        }
        this.itemsAmountList.put(itemName,
                itemsAmountList.get(itemName) - 1);
    }

    /**
     * Gets pet inventory.
     * @return list of pets
     */
    public List<Pet> getPetInventory() {
        return new ArrayList<>(this.petInventory);
    }

    /**
     * Gets items amount list.
     * @return map of item names to amounts
     */
    public HashMap<String, Integer> getItemsAmountList() {
        return new HashMap<>(this.itemsAmountList);
    }

    // 2 ways of implementing this.
    // way 1: what im doing now: use 2 hashmap, one showing the identity
    // of the items, the other one shows the number of each item
    // way 2: add another variable called "amount", and add the number
    // of amount to the itemList
    // for way2, we only need one list to include everything.
    /**
     * Gets items list.
     * @return map of item names to item objects
     */
    public HashMap<String, Item> getItemsList() {
        return new HashMap<>(this.itemsList);
    }

    /**
     * Validation for upgrading the click bonus.
     * @return true if can upgrade
     */
    public boolean canUpgradeClicker() {
        return this.clickBonusTime < Constants.MAX_CLICK_BONUS_UPGRADES;
    }

    /**
     * Pre: canUpgradeClicker is true.
     */
    public void upgradeClickBonus() {
        this.buy(this.getCurrentUpgradePrice());
        //click bonus will be: 1,2,4,6,8,10...
        this.clickBonus = Constants.CLICKBONUS_INCREASE_BASE
                * this.clickBonusTime;
        this.clickBonusTime += 1;
    }

    /**
     * Gets click bonus.
     * @return click bonus value
     */
    public int getClickBonus() {
        return this.clickBonus;
    }

    /**
     * Gets click bonus time.
     * @return click bonus time
     */
    public int getClickBonusTime() {
        return this.clickBonusTime;
    }

    /**
     * Pre: the unlockPetSlot should be less than 5.
     * @return current upgrade price
     */
    public int getCurrentUpgradePrice() {
        return this.getClickBonusTime()
                * Constants.UPGRADE_CLICKER_BASE_PRICE;
    }

    /**
     * Validation for unlocking a pet slot.
     * @return true if can unlock slot
     */
    public boolean canUnlockSlot() {
        return this.unlockedSlots < Constants.MAX_PET_SLOTS;
    }

    /**
     * Pre: canUnlockSlot is true.
     */
    public void unlockPetSlot() {
        this.buy(this.getCurrentUnlockSlotPrice());
        this.unlockedSlots += 1;
    }

    /**
     * Helper method for unlockPetSlot.
     * @return current unlock slot price
     */
    public int getCurrentUnlockSlotPrice() {
        // Price increases with each unlock: 100, 200, 300 for slots 3, 4, 5
        return (this.unlockedSlots - Constants.INITIAL_SLOTS + 1)
                * Constants.UNLOCK_SLOT_BASE_PRICE;
    }

    /**
     * Gets unlocked slots.
     * @return number of unlocked slots
     */
    public int getUnlockedSlots() {
        return this.unlockedSlots;
    }

    /**
     * Sells a pet.
     * @param pet pet to sell
     * @return true if sold successfully
     */
    public boolean sellPet(final Pet pet) {
        if (pet == null || !petInventory.contains(pet)) {
            return false; // No pet in your Inventory
        }

        if (pet.isDeployed()) {
            return false; //Cannot sell a Deployed pet
        }

        // Pet removed and user earned coins
        petInventory.remove(pet);
        this.coinCount += pet.getSellingPrice();
        return true;
    }

    /**
     * Sell your pet based on the index.
     * @param petIndex index of the pet
     * @return selling price, or -1 if failed
     */
    public int sellPetByIndex(final int petIndex) {
        if (petIndex < 0 || petIndex >= petInventory.size()) {
            return -1; // Invalid index
        }

        Pet pet = petInventory.get(petIndex);
        if (pet.isDeployed()) {
            return -1; // Pet is deployed
        }

        int sellingPrice = pet.getSellingPrice();
        petInventory.remove(petIndex);
        this.coinCount += sellingPrice;
        return sellingPrice;
    }

    /**
     * Sell your pet based on their name.
     * @param petName name of the pet
     * @return selling price, or -1 if failed
     */
    public int sellPetByName(final String petName) {
        for (int i = 0; i < petInventory.size(); i++) {
            Pet pet = petInventory.get(i);
            if (pet.getName().equals(petName) && !pet.isDeployed()) {
                int sellingPrice = pet.getSellingPrice();
                petInventory.remove(i);
                this.coinCount += sellingPrice;
                return sellingPrice;
            }
        }
        return -1; // Pet not found or it's deployed
    }

    /**
     * Sets coins.
     * @param coins coin amount
     */
    public void setCoins(final int coins) {
        this.coinCount = coins;
    }

    /**
     * Adds coins.
     * @param coins coins to add
     */
    public void addCoins(final int coins) {
        this.coinCount += coins;
    }

    /**
     * Subtracts coins.
     * @param coins coins to subtract
     */
    public void subtractCoins(final int coins) {
        this.coinCount -= coins;
    }

    /**
     * Gets coin count.
     * @return coin count
     */
    public int getCoinCount() {
        return this.coinCount;
    }
}
