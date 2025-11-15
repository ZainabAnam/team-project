package game.entity;

public class PetFood extends Item{
    private String name;
    private String type;
    private int price;
    private int energyIncrease;
    public PetFood(String name, int price,int energyIncrease) {
        super(name,price);
        this.energyIncrease=energyIncrease;
        this.type="PetFood";
    }

    public int getEnergyIncrease() {
        return energyIncrease;
    }
}
