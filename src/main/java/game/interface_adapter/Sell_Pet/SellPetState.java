package game.interface_adapter.Sell_Pet;

public class SellPetState {
    private String petName = "";
    private int sellingPrice = 0;
    private int newCoinCount = 0;
    private String message = "";
    private boolean success = false;

    public SellPetState() {}

    // Getters and Setters
    public String getPetName() { return petName; }
    public void setPetName(String petName) { this.petName = petName; }

    public int getSellingPrice() { return sellingPrice; }
    public void setSellingPrice(int sellingPrice) { this.sellingPrice = sellingPrice; }

    public int getNewCoinCount() { return newCoinCount; }
    public void setNewCoinCount(int newCoinCount) { this.newCoinCount = newCoinCount; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }
}
