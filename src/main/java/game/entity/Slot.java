package game.entity;

import javax.swing.*;
import java.awt.*;

/*
 *  An entity representing a slot for pet autoclickers.
*/
public class Slot extends JButton {

    private boolean unlocked;
    private final ImageIcon unlockedImage;
    private final ImageIcon lockedImage;
    private final ImageIcon unlockedClickedImage;
    private final ImageIcon lockedClickedImage;
    private Pet pet;

    public Slot(boolean unlocked) {
        this.unlocked = unlocked;
        this.unlockedImage = new ImageIcon(getClass().getResource("/images/Slot.png"));
        this.lockedImage = new ImageIcon(getClass().getResource("/images/SlotLocked.png"));
        this.unlockedClickedImage = new ImageIcon(getClass().getResource("/images/SlotClicked.png"));
        this.lockedClickedImage = new ImageIcon(getClass().getResource("/images/SlotLockedClicked.png"));

        if (unlocked) {
            setIcon(unlockedImage);
            setPressedIcon(unlockedClickedImage);
        } else {
            setIcon(lockedImage);
            setPressedIcon(lockedClickedImage);
        }
        ImageIcon icon = (ImageIcon) getIcon();
        setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));

        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setOpaque(false);
    }

    // Unlocks a slot and updates the button appearance.
    public void setUnlocked() {
        unlocked = true;
        setIcon(unlockedImage);
        ImageIcon icon = (ImageIcon) getIcon();
        setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
    }

    // Check if the slot is unlocked.
    public boolean isUnlocked() {
        return unlocked;
    }

    // Add Pet to slot.
    public void addPet(Pet pet) {
        this.pet = pet;
    }

    // Remove Pet from slot.
    public void removePet(Pet pet) {
        this.pet = null;
    }

    // Update the button's appearance depending on whether a pet is slotted in.
    private void updateAppearance() {

    }

}
