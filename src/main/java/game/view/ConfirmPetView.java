package game.view;
import game.entity.Pet;
import game.entity.Slot;
import game.interface_adapter.SlotAddPet.ConfirmPet.*;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;

/**
 * The View for Selecting a Pet to add to a slot.
 */

public class ConfirmPetView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "Pet Select View";
    private final ConfirmPetViewModel confirmPetViewModel;

    private final JComboBox<Pet> petComboBox = new JComboBox<>();
    private final JLabel petErrorField = new JLabel();

    private Map<String, ConfirmPetViewModel> petInfoMap;

    private ConfirmPetController confirmPetController = null;

    public ConfirmPetView(ConfirmPetViewModel confirmPetViewModel) {
        this.confirmPetViewModel = confirmPetViewModel;
        this.confirmPetViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Select Pet to Deploy:");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Pet Stat Labels
        final JPanel petInfo = new JPanel();
        JLabel petName =  new JLabel("Name:");
        petInfo.add(petName);
        JLabel petBreed = new JLabel("Breed:");
        petInfo.add(petBreed);
        JLabel petEnergyLevel = new JLabel("Energy Level:");
        petInfo.add(petEnergyLevel);
        JLabel petAffection = new JLabel("Affection:");
        petInfo.add(petAffection);
        JLabel petClick = new JLabel("Clicking Stat:");
        petInfo.add(petClick);
        petInfo.setAlignmentY(10);

        // Screen Labels
        final JPanel textLabels = new JPanel();
        JLabel comboBoxLabel = new JLabel("Your Pets:");
        textLabels.add(comboBoxLabel);

        // The Confirm/Cancel buttons
        final JPanel buttons = new JPanel();
        final JButton confirm = new JButton("Confirm");
        buttons.add(confirm);
        final JButton cancel = new JButton("Cancel");
        buttons.add(cancel);

        add(petInfo);
        add(textLabels);
        add(buttons);

        confirm.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(confirm)) {
                            final ConfirmPetState currentState = confirmPetViewModel.getState();

                            ConfirmPetController.execute(
                                    //currentState.getPet();
                            );
                        }

                    }
                }
        );

       }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
