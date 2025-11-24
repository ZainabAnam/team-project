package game.entity;

public class PetToy extends Item{
    private int affectionIncrease;
    public PetToy(String name,int price,int affectionIncrease) {
        super(name,price,"PetToy");
        this.affectionIncrease=affectionIncrease;
    }

    public int getAffectionIncrease() {
        return affectionIncrease;
    }
}
