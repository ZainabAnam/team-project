package game.entity;

import java.util.*;

public class User {
        public int coinCount;
        private int clickBonus;
        private int clickBonusTime;
        private List<Pet> PetInventory= new ArrayList<>();
        private List<Item> itemsList = new ArrayList<>();
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

        public boolean coinCheck(int price) {
            if (this.coinCount >= price) {
                return true;
            }
            return false;
        }

        private void buy(int price){
            this.coinCount -= price;
        }

        public void buyLootBox(LootBox lootBox){
            this.buy(lootBox.getPrice());

        }

        public void buyPetFood(PetFood petFood){
            this.buy(petFood.getPrice());
            this.addToItemList(petFood);
        }

        public void buyPetToy(PetToy petToy){
            this.buy(petToy.getPrice());
            this.addToItemList(petToy);
        }

        //pre: the clickBonusTime should be less than 5
        public void upgradeClickBonus(){
            this.clickBonusTime+=1;
            this.clickBonus+=INITIAL_CLICKBONUSINCREASE;
        }

        public int getClickBonusTime(){
            return this.clickBonusTime;
        }

        //pre: the unlockPetSlot should be less than 5
        public void unlockPetSlot(){
            this.unlockedSlots+=1;
        }

        public void addToPetInventory(Pet pet) {
            this.PetInventory.add(pet);
        }
        public void addToItemList(Item item) {
            this.itemsList.add(item);
        }



}
