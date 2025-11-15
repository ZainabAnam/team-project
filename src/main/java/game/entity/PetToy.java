package game.entity;

public class PetToy extends Item{
    private String name;
    private String type;
    private int price;
    private int affectionIncrease;
    public PetToy(String name,int price,int affectionIncrease) {
        super(name,price);
        this.affectionIncrease=affectionIncrease;
        this.type="PetToy";
    }

    public int getAffectionIncrease() {
        return affectionIncrease;
    }
}
