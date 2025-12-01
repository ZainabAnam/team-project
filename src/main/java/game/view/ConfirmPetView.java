package game.view;
import game.entity.Pet;
import game.entity.Slot;
import game.interface_adapter.SlotAddPet.ConfirmPet.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.*;
import java.util.List;

/**
 * The View for Selecting a Pet to add to a slot.
 */

public class ConfirmPetView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "Pet Select View";

    private final JComboBox<Pet> petComboBox = new JComboBox<>();
    private final JLabel petName = new JLabel("-");
    private final JLabel petBreed = new JLabel("-");
    private final JLabel petEnergyLevel = new JLabel("-");
    private final JLabel petAffection = new JLabel("-");
    private final JLabel petClick = new JLabel("-");
    private final JLabel petErrorField = new JLabel();

    private Map<String, ConfirmPetViewModel> petInfoMap;

    private ConfirmPetController confirmPetController = null;

    public ConfirmPetView(ConfirmPetViewModel confirmPetViewModel, Slot slot) {


        final JLabel title = new JLabel("Select Pet to Deploy:");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Pet Stat Labels
        JPanel petInfo = new JPanel(new GridLayout(5, 2));
        JLabel name =  new JLabel("Name:");
        petInfo.add(name);
        JLabel breed = new JLabel("Breed:");
        petInfo.add(breed);
        JLabel energy = new JLabel("Energy Level:");
        petInfo.add(energy);
        JLabel affection = new JLabel("Affection:");
        petInfo.add(affection);
        JLabel click = new JLabel("Clicking Stat:");
        petInfo.add(click);
        petInfo.setAlignmentY(10);

        JPanel petStat = new JPanel(new GridLayout(5, 2));


        // Combo box
        JPanel comboPanel = new JPanel();
        comboPanel.add(new JLabel("Your Pets:"));
        comboPanel.add(petComboBox);

        petComboBox.addActionListener(e -> updateDisplayedPetInfo());

        // The Confirm/Cancel buttons
        JPanel buttons = new JPanel();
        JButton confirm = new JButton("Confirm");
        JButton cancel = new JButton("Cancel");
        buttons.add(confirm);
        buttons.add(cancel);

        add(petInfo);
        add(comboPanel);
        add(buttons);


        confirm.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        Pet selectedPet = (Pet) petComboBox.getSelectedItem();
                        if (selectedPet != null && confirmPetController != null) {
                            //ConfirmPetController.execute(slot, selectedPet);
                        }
                    }
                }
            );

        // Cancel Action
        cancel.addActionListener(evt -> {
            // Up to you — for now simply hide window or fire cancel event
            System.out.println("User cancelled pet selection.");
        });
        add(petErrorField);
        add(buttons);
    }
    private void updateComboBox(List<Pet> pets) {
        petComboBox.removeAllItems();
        for (Pet p : pets) {
            petComboBox.addItem(p);
        }
    }

    //Update display when a pet is selected
    private void updateDisplayedPetInfo() {
        Pet p = (Pet) petComboBox.getSelectedItem();
        if (p == null) {
            petName.setText("-");
            petBreed.setText("-");
            petEnergyLevel.setText("-");
            petAffection.setText("-");
            petClick.setText("-");
            return;
        }

        petName.setText(p.getName());
        petBreed.setText(p.getPetBreed());
        petEnergyLevel.setText(String.valueOf(p.getEnergyLevel()));
        petAffection.setText(String.valueOf(p.getAffectionLevel()));
        petClick.setText(String.valueOf(p.getClickingSpeed()));
    }

    public void setConfirmPetController(ConfirmPetController controller) {
        this.confirmPetController = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {}

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        updateComboBox(state.getPetList());
        petErrorField.setText(state.getErrorMessage());

        // Update displayed info when list changes
        updateDisplayedPetInfo();
    }
}
