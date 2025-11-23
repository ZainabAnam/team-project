package game.use_case.PetShop.UpgradeClicker;

/**
 * Output data for upgrading clicker use case.
 * Contains the result of the upgrade operation and upgrade level information.
 */
public class UpgradeClickerOutputData {
    private final boolean success;
    private final String message;
    private final int beforeLevel;
    private final int afterLevel;
    
    /**
     * Constructor for UpgradeClickerOutputData
     * @param success whether the upgrade was successful
     * @param message success or error message to display
     * @param beforeLevel the click bonus level before upgrade
     * @param afterLevel the click bonus level after upgrade
     */
    public UpgradeClickerOutputData(boolean success, String message, int beforeLevel, int afterLevel) {
        this.success = success;
        this.message = message;
        this.beforeLevel = beforeLevel;
        this.afterLevel = afterLevel;
    }
    
    /**
     * Check if the upgrade was successful
     * @return true if upgrade succeeded, false otherwise
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
     * Get the click bonus level before upgrade
     * @return before level
     */
    public int getBeforeLevel() {
        return beforeLevel;
    }
    
    /**
     * Get the click bonus level after upgrade
     * @return after level
     */
    public int getAfterLevel() {
        return afterLevel;
    }
}
