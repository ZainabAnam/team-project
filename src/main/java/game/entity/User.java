package game.entity;

import java.util.*;

    /*
    PetInventory: List<Pet>
    itemsList: List<Item>
    + coinCount: int
    clickBonus (Click multiplier; how many coins you get per click):int
    clickBonusTime:int
    petSlot:int
    */

public class User {
        public int coinCount;
        private int clickBonus;
        private int clickBonusTime;
        private List<Pet> petInventory= new ArrayList<>();
        private HashMap<String,Integer> itemsAmountList = new HashMap<>();
        private HashMap<String,Item> itemsList= new HashMap<>();
        private int unlockedSlots;
        final private int INITIAL_COINS = 100;
        final private int INITIAL_CLICKBONUS = 1;
        final private int INITIAL_CLICKBONUSINCREASE = 1;

        public User() {
            this.coinCount = INITIAL_COINS;
            this.clickBonus = INITIAL_CLICKBONUS;
            this.clickBonusTime = 1;
            this.unlockedSlots = 2;
        }

        public void addShopList(List<Item> itemList){
            for (int i=0;i<itemList.size();i++){
                itemsAmountList.put(itemList.get(i).getName(),0);
                itemsList.put(itemList.get(i).getName(),itemList.get(i));
            }
        }

        public boolean coinCheck(int price) {
            if (this.coinCount >= price) {
                return true;
            }
            return false;
        }

        private void buy(int price){
            this.coinCount -= price;
        }

        //pre: buying need a coinCheck
        public void buyLootBox(LootBox lootBox){
            this.buy(lootBox.getPrice());
            this.addToPetInventory(lootBox.getPet());
        }

        public void buyPetItem(Item petItem){
            this.buy(petItem.getPrice());
            this.addToItemList(petItem);
        }

        //pre: the clickBonusTime should be less than 5
        public void upgradeClickBonus(){
            this.buy(this.getCurrentUpgradePrice());
            this.clickBonusTime+=1;
            this.clickBonus+=INITIAL_CLICKBONUSINCREASE;
        }

        public int getCurrentUpgradePrice(){
            return this.getClickBonusTime()*50;
        }

        public int getClickBonusTime(){
            return this.clickBonusTime;
        }

        //pre: the unlockPetSlot should be less than 5
        public void unlockPetSlot(){
            this.unlockedSlots+=1;
        }

        public void addToPetInventory(Pet pet) {
            if (pet != null) {
                petInventory.add(pet);
            }
        }
        public void addToItemList(Item item) {
            this.itemsAmountList.put(item.getName(), itemsAmountList.get(item.getName()) + 1);
        }

        public void addToItemList(String itemName) {
            this.itemsAmountList.put(itemName, itemsAmountList.get(itemName) + 1);
        }

        public boolean itemCheck(String itemName){
            if (this.itemsAmountList.get(itemName)<=0){
                return false;
            }else{
                return true;
            }
        }

        //pre: itemCheck is true
        public void usePetItem(Pet pet,String itemName){
            this.itemsAmountList.put(itemName, itemsAmountList.get(itemName) - 1);
            if (itemsList.get(itemName).getType()=="PetFood"){
                PetFood petFood= (PetFood) itemsList.get(itemName);
                pet.increaseEnergyLevel(petFood.getEnergyIncrease());
            }else if (itemsList.get(itemName).getType()=="PetToy"){
                PetToy petToy= (PetToy) itemsList.get(itemName);
                pet.increaseAffectionXP(petToy.getAffectionIncrease());
            }
        }



}
