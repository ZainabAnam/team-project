package game.entity;

public abstract class Item {
    /**
     * Item name.
     */
    private String name;
    /**
     * Item type.
     */
    private String type;
    /**
     * Item price.
     */
    private int price;

    /**
     * Constructs Item.
     * @param itemName item name
     * @param itemPrice item price
     * @param itemType item type
     */
    public Item(final String itemName, final int itemPrice,
               final String itemType) {
        this.name = itemName;
        this.price = itemPrice;
        this.type = itemType;
    }

    /**
     * Gets name.
     * @return item name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets type.
     * @return item type
     */
    public String getType() {
        return this.type;
    }

    /**
     * Gets price.
     * @return item price
     */
    public int getPrice() {
        return this.price;
    }
}
