package game.entity;

public class PetFood extends Item{
    private int energyIncrease;
    public PetFood(String name, int price,int energyIncrease) {
        super(name,price,"PetFood");
        this.energyIncrease=energyIncrease;
    }

    public int getEnergyIncrease() {
        return energyIncrease;
    }
}
