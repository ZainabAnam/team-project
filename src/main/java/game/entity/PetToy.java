package game.entity;

public class PetToy extends Item {
    /**
     * Affection increase value.
     */
    private int affectionIncrease;

    /**
     * Constructs PetToy.
     * @param name item name
     * @param price item price
     * @param affectionInc affection increase amount
     */
    public PetToy(final String name, final int price,
                 final int affectionInc) {
        super(name, price, "PetToy");
        this.affectionIncrease = affectionInc;
    }

    /**
     * Gets affection increase.
     * @return affection increase value
     */
    public int getAffectionIncrease() {
        return affectionIncrease;
    }
}
