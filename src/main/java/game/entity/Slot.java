package game.entity;

import javax.swing.*;
import java.awt.*;

/*
 *  An entity representing a slot for pet autoclickers.
*/
public class Slot extends JButton {

    private final int slotID;
    private boolean unlocked;
    private Pet pet = null;
    private final ImageIcon unlockedImage = new ImageIcon(getClass().getResource("/images/MainPageIcons/Slot.png"));
    private final ImageIcon lockedImage = new ImageIcon(getClass().getResource("/images/MainPageIcons/SlotLocked.png"));
    private final ImageIcon unlockedClickedImage = new ImageIcon(getClass().getResource("/images/MainPageIcons/SlotClicked.png"));
    private final ImageIcon lockedClickedImage = new ImageIcon(getClass().getResource("/images/MainPageIcons/SlotLockedClicked.png"));
    private boolean petTired = false;

    public Slot(boolean unlocked, int id) {
        this.unlocked = unlocked;
        this.slotID = id;

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

    // The slot's id
    public int getID() {
        return slotID;
    }

    // Check if the slot is unlocked.
    public boolean isUnlocked() {
        return unlocked;
    }

    // Check if the slot has a pet in it: False if no pet, True if there is a pet.
    public boolean hasPet() {
        return pet != null;
    }

    // Check if the pet is tired.
    public boolean isPetTired() { return petTired; }

    // Get Pet info.
    public Pet getPet() { return pet; }

    // Add Pet to slot.
    public void addPet(Pet pet) {
        this.pet = pet;
        updateAppearance();
    }

    // Set Pet to a tired status.
    public void petTired(Pet pet) {
        if (pet.getEnergyLevel() == 0) {
            this.petTired = true;
        }
    }

    // Remove Pet from slot.
    public void removePet(Pet pet) {
        this.pet = null;
        this.petTired = false;
        updateAppearance();
    }

    // Update the button's appearance depending on whether a pet is slotted in.
    private void updateAppearance() {
        if (this.pet != null) {
            ImageIcon petPic = this.pet.getPetVisual();
            setIcon(petPic);
        } else {
            setIcon(unlockedImage);
        }
    }
}
