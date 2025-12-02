package game.entity;

public class PetFood extends Item {
    /**
     * Energy increase value.
     */
    private int energyIncrease;

    /**
     * Constructs PetFood.
     * @param name item name
     * @param price item price
     * @param energyInc energy increase amount
     */
    public PetFood(final String name, final int price,
                   final int energyInc) {
        super(name, price, "PetFood");
        this.energyIncrease = energyInc;
    }

    /**
     * Gets energy increase.
     * @return energy increase value
     */
    public int getEnergyIncrease() {
        return energyIncrease;
    }
}
