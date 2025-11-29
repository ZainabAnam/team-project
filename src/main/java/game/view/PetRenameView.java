package game.view;

import game.interface_adapter.RenamePet.RenamePetController;
import game.interface_adapter.RenamePet.RenamePetViewModel;
import game.interface_adapter.RenamePet.RenamePetState;
import game.entity.Pet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PetRenameView extends JPanel implements PropertyChangeListener {
    private final RenamePetController renamePetController;
    private final RenamePetViewModel renamePetViewModel;

    private JTextField nameField;
    private JButton confirmButton;
    private JButton cancelButton;
    private JLabel messageLabel;
    private JLabel titleLabel;

    private int currentPetIndex;
    private String currentUsername;

    public PetRenameView(RenamePetController renamePetController,
                         RenamePetViewModel renamePetViewModel, JTextField nameField, JButton confirmButton, JButton cancelButton, JLabel messageLabel, JLabel titleLabel) {
        this.renamePetController = renamePetController;
        this.renamePetViewModel = renamePetViewModel;
        this.nameField = nameField;
        this.confirmButton = confirmButton;
        this.cancelButton = cancelButton;
        this.messageLabel = messageLabel;
        this.titleLabel = titleLabel;
        renamePetViewModel.addPropertyChangeListener(this);

        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // title panel
        titleLabel = new JLabel("Name Your Pet", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        // text input field
        JPanel inputPanel = new JPanel(new GridLayout(2, 1, 10, 5));
        JLabel nameLabel = new JLabel("Pet Name:");
        nameField = new JTextField(20);

        inputPanel.add(nameLabel);
        inputPanel.add(nameField);

        // message field
        messageLabel = new JLabel(" ", SwingConstants.CENTER);
        messageLabel.setForeground(Color.RED);

        // button field
        JPanel buttonPanel = new JPanel(new FlowLayout());
        confirmButton = new JButton("Confirm");
        cancelButton = new JButton("Cancel");

        confirmButton.addActionListener(new ConfirmListener());
        cancelButton.addActionListener(new CancelListener());

        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);

        // Pack Everything up
        add(titleLabel, BorderLayout.NORTH);
        add(inputPanel, BorderLayout.CENTER);
        add(messageLabel, BorderLayout.SOUTH);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // name a new pet from loot box
    public void prepareForNewPet(Pet pet, int petIndex, String username) {
        this.currentPetIndex = petIndex;
        this.currentUsername = username;
        titleLabel.setText("Name Your New " + pet.getPetBreed());
        nameField.setText("");
        nameField.requestFocus();
        messageLabel.setText(" ");
        messageLabel.setForeground(Color.BLACK);
    }

    // rename your pet in collection
    public void prepareForRename(Pet pet, int petIndex, String username) {
        this.currentPetIndex = petIndex;
        this.currentUsername = username;
        titleLabel.setText("Rename " + pet.getName());
        nameField.setText(pet.getName());
        nameField.selectAll();
        nameField.requestFocus();
        messageLabel.setText(" ");
        messageLabel.setForeground(Color.BLACK);
    }

    // clear all stas
    public void clear() {
        nameField.setText("");
        messageLabel.setText(" ");
        currentPetIndex = -1;
        currentUsername = null;
    }

    private class ConfirmListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String newName = nameField.getText().trim();

            if (newName.isEmpty()) {
                messageLabel.setText("Please enter a name for your pet");
                return;
            }

            if (newName.length() > 20) {
                messageLabel.setText("Name too long (max 20 characters)");
                return;
            }

            // use controller
            renamePetController.execute(currentPetIndex, newName, currentUsername);
        }
    }

    private class CancelListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            clear();
            // Back to previous View
            // viewManagerModel.setState("previous_view");
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            RenamePetState state = renamePetViewModel.getState();

            SwingUtilities.invokeLater(() -> {
                if (state.isSuccess()) {
                    messageLabel.setForeground(new Color(0, 128, 0)); // 绿色
                    messageLabel.setText("✓ " + state.getMessage());

                    // successful respond
                } else {
                    messageLabel.setForeground(Color.RED);
                    messageLabel.setText("✗ " + state.getMessage());
                }
            });
        }
    }
}
