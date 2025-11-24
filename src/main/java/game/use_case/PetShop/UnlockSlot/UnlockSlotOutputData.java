package game.use_case.PetShop.UnlockSlot;

/**
 * Output data for unlocking slot use case.
 * Contains the result of the unlock operation and slot number information.
 */
public class UnlockSlotOutputData {
    private final boolean success;
    private final String message;
    private final int beforeSlots;
    private final int afterSlots;
    
    /**
     * Constructor for UnlockSlotOutputData
     * @param success whether the unlock was successful
     * @param message success or error message to display
     * @param beforeSlots the number of slots before unlock
     * @param afterSlots the number of slots after unlock
     */
    public UnlockSlotOutputData(boolean success, String message, int beforeSlots, int afterSlots) {
        this.success = success;
        this.message = message;
        this.beforeSlots = beforeSlots;
        this.afterSlots = afterSlots;
    }
    
    /**
     * Check if the unlock was successful
     * @return true if unlock succeeded, false otherwise
     */
    public boolean isSuccess() {
        return success;
    }
    
    /**
     * Get the message to display to user
     * @return success or error message
     */
    public String getMessage() {
        return message;
    }
    
    /**
     * Get the number of slots before unlock
     * @return before slots
     */
    public int getBeforeSlots() {
        return beforeSlots;
    }
    
    /**
     * Get the number of slots after unlock
     * @return after slots
     */
    public int getAfterSlots() {
        return afterSlots;
    }
}
