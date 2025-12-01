package game.interface_adapter.Sell_Pet;

public class SellPetState {
    private String petName = "";
    private int sellingPrice = 0;
    private int newCoinCount = 0;
    private String message = "";
    private boolean success = false;
    private String previousView = "";

    public SellPetState() {}

    public SellPetState(SellPetState copy) {
        this.petName = copy.petName;
        this.sellingPrice = copy.sellingPrice;
        this.newCoinCount = copy.newCoinCount;
        this.message = copy.message;
        this.success = copy.success;
        this.previousView = copy.previousView;
    }


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
    public String getPreviousView() {
        return previousView;
    }

    public void setPreviousView(String previousView) {
        this.previousView = previousView;
    }
}
