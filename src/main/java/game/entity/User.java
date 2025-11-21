package game.entity;

import game.Constants;

import java.util.*;

    /*
    User for the game
    */

public class User {
        private int coinCount;
        private int clickBonus;
        private int clickBonusTime;//helper for counting the times of upgrades
        private int unlockedSlots;

        private List<Pet> petInventory= new ArrayList<>();//list of pets
        private HashMap<String,Integer> itemsAmountList = new HashMap<>();//list of item names and their amounts
        private HashMap<String,Item> itemsList= new HashMap<>();//list of item names and their objects（easy to find the item by name）

        public User() {
            this.coinCount = Constants.INITIAL_COINS;
            this.clickBonus = Constants.INITIAL_CLICKBONUS;
            this.clickBonusTime = Constants.INITIAL_CLICKBONUS_TIME;
            this.unlockedSlots = Constants.INITIAL_SLOTS;
            this.addShopList(Constants.itemList);
        }

        //helper method for adding the shop list to the user
        public void addShopList(List<Item> itemList){
            for (Item item : itemList) {
                itemsAmountList.put(item.getName(), 0);
                itemsList.put(item.getName(), item);
            }
        }

        //helper method for getting an item by name
        public Item getItemByName(String itemName) {
            return itemsList.get(itemName);
        }

        //check if the user has enough coins
        public boolean coinCheck(int price) {
            return this.coinCount >= price;
        }

        //pre: helper method for buying
        private void buy(int price){
            this.coinCount -= price;
        }

        //pre: coinCheck is true
        public void buyLootBox(LootBox lootBox){
            this.buy(lootBox.getPrice());
            // this.addToPetInventory(lootBox.getPet());
        }

        //adding a pet to the pet inventory
        public void addToPetInventory(Pet pet) {
            if (pet != null) {
                petInventory.add(pet);
            }
        }

        //pre: coinCheck is true
        public void buyPetItem(String itemName){
            Item petItemByName = getItemByName(itemName);
            this.buy(petItemByName.getPrice());
            this.addToItemList(petItemByName.getName());
        }

        //helper method that helps to add an item to the item list with the item name
        public void addToItemList(String itemName) {
            this.itemsAmountList.put(itemName, itemsAmountList.get(itemName) + 1);
        }

        //checking if the user have enough items 
        public boolean itemCheck(String itemName){
            return this.itemsAmountList.get(itemName) > 0;
        }

        //pre: itemCheck is true
        public void usePetItem(Pet pet,String itemName){
            //find the type of the item and use it
            if (Objects.equals(getItemByName(itemName).getType(), "PetFood")){
                PetFood petFood= (PetFood) getItemByName(itemName);
                pet.increaseEnergyLevel(petFood.getEnergyIncrease());
            }else if (Objects.equals(getItemByName(itemName).getType(), "PetToy")){
                PetToy petToy= (PetToy) getItemByName(itemName);
                pet.increaseAffectionXP(petToy.getAffectionIncrease());
            }
            this.itemsAmountList.put(itemName, itemsAmountList.get(itemName) - 1);
        }
        
        // other getter methods
        public List<Pet> getPetInventory() {
            return new ArrayList<>(this.petInventory); 
        }
        
        public HashMap<String, Integer> getItemsAmountList() {
            return new HashMap<>(this.itemsAmountList); 
        }
        
        public HashMap<String, Item> getItemsList() {
            return new HashMap<>(this.itemsList); 
        }

        // Validation for upgrading the click bonus
        public boolean canUpgradeClicker() {
            return this.clickBonusTime < Constants.MAX_CLICK_BONUS_UPGRADES;
        }

        //pre: canUpgradeClicker is true
        public void upgradeClickBonus(){
            this.buy(this.getCurrentUpgradePrice());
            //click bonus will be: 1,2,4,6,8,10...
            this.clickBonus=Constants.CLICKBONUS_INCREASE_BASE*this.clickBonusTime;
            this.clickBonusTime+=1;
        }
                
        public int getClickBonus() {
            return this.clickBonus;
        }

        public int getClickBonusTime(){
            return this.clickBonusTime;
        }

        public int getCurrentUpgradePrice(){
            return this.getClickBonusTime()*Constants.UPGRADE_CLICKER_BASE_PRICE;
        }

        // Validation for unlocking a pet slot
        public boolean canUnlockSlot() {
            return this.unlockedSlots < Constants.MAX_PET_SLOTS;
        }
        //pre: canUnlockSlot is true
        public void unlockPetSlot(){
            this.buy(this.getCurrentUnlockSlotPrice());
            this.unlockedSlots+=1;
        }
        
        //helper method for unlockPetSlot
        public int getCurrentUnlockSlotPrice(){
            return (this.unlockedSlots - Constants.INITIAL_SLOTS + 1) * Constants.UNLOCK_SLOT_BASE_PRICE;
        }

        public int getUnlockedSlots() {
            return this.unlockedSlots;
        }

        public void addCoins(int coins) {
            this.coinCount += coins;
        }

        public void subtractCoins(int coins) {
            this.coinCount -= coins;
        }

        public int getCoinCount(){
            return this.coinCount;
        }



}
