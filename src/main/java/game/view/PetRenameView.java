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
    private JLabel petIconLabel;

    private int currentPetIndex;
    private String currentUsername;

    // color setup
    private final Color PRIMARY_COLOR = new Color(255, 182, 193);
    private final Color SECONDARY_COLOR = new Color(147, 112, 219);
    private final Color ACCENT_COLOR = new Color(255, 105, 180);
    private final Color BACKGROUND_COLOR = new Color(255, 250, 250);

    public PetRenameView(RenamePetController renamePetController,
                         RenamePetViewModel renamePetViewModel) {
        this.renamePetController = renamePetController;
        this.renamePetViewModel = renamePetViewModel;
        renamePetViewModel.addPropertyChangeListener(this);

        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BorderLayout(15, 15));
        setBackground(BACKGROUND_COLOR);
        setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        // title field
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(BACKGROUND_COLOR);
        topPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));

        titleLabel = new JLabel("Name Your Pet 🐾", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        titleLabel.setForeground(SECONDARY_COLOR);
        topPanel.add(titleLabel, BorderLayout.CENTER);

        // central area field
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(BACKGROUND_COLOR);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        // pet pattern
        petIconLabel = new JLabel("🐕 🐈", SwingConstants.CENTER);
        petIconLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 48));
        petIconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        petIconLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        // input field label
        JLabel namePromptLabel = new JLabel("What should we call your new friend?");
        namePromptLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
        namePromptLabel.setForeground(new Color(75, 75, 75));
        namePromptLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // input field
        nameField = new JTextField(20);
        nameField.setFont(new Font("Arial", Font.PLAIN, 16));
        nameField.setMaximumSize(new Dimension(250, 35));
        nameField.setAlignmentX(Component.CENTER_ALIGNMENT);
        nameField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(PRIMARY_COLOR, 2, true),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        nameField.setBackground(new Color(255, 245, 245));

        // message label
        messageLabel = new JLabel(" ", SwingConstants.CENTER);
        messageLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        messageLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        // configuration
        centerPanel.add(petIconLabel);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(namePromptLabel);
        centerPanel.add(Box.createVerticalStrut(15));
        centerPanel.add(nameField);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(messageLabel);

        // bottom button field
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(BACKGROUND_COLOR);

        // confirm button
        confirmButton = new JButton("✨ Confirm ✨");
        styleButton(confirmButton, new Color(144, 238, 144));
        confirmButton.addActionListener(new ConfirmListener());

        // cancel button
        cancelButton = new JButton("Cancel");
        styleButton(cancelButton, new Color(255, 182, 193));
        cancelButton.addActionListener(new CancelListener());

        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);

        // all panel configuration
        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // button design
    private void styleButton(JButton button, Color bgColor) {
        button.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 14));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(bgColor.getRed(), bgColor.getGreen(), bgColor.getBlue(), 150), 2, true),
                BorderFactory.createEmptyBorder(8, 20, 8, 20)
        ));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        //cursor pattern
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor.darker());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });
    }

    // name your new pet
    public void prepareForNewPet(Pet pet, int petIndex, String username) {
        this.currentPetIndex = petIndex;
        this.currentUsername = username;

        // different pattern for your pet
        String emoji = pet.getPetType().equalsIgnoreCase("Dog") ? "🐕" : "🐈";
        petIconLabel.setText(emoji + " " + emoji + " " + emoji);

        titleLabel.setText("Name Your New " + pet.getPetBreed() + "! 🎉");
        nameField.setText("");
        nameField.requestFocus();
        messageLabel.setText(" ");
        messageLabel.setForeground(new Color(75, 75, 75));
    }

    // rename current pet
    public void prepareForRename(Pet pet, int petIndex, String username) {
        this.currentPetIndex = petIndex;
        this.currentUsername = username;

        String emoji = pet.getPetType().equalsIgnoreCase("Dog") ? "🐕" : "🐈";
        petIconLabel.setText(emoji + " " + emoji + " " + emoji);

        titleLabel.setText("Rename " + pet.getName() + " ✏️");
        nameField.setText(pet.getName());
        nameField.selectAll();
        nameField.requestFocus();
        messageLabel.setText(" ");
        messageLabel.setForeground(new Color(75, 75, 75));
    }

    // clear status
    public void clear() {
        nameField.setText("");
        messageLabel.setText(" ");
        currentPetIndex = -1;
        currentUsername = null;
    }

    public String getViewName() {
        return "rename_pet";
    }

    private class ConfirmListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String newName = nameField.getText().trim();

            if (newName.isEmpty()) {
                messageLabel.setForeground(ACCENT_COLOR);
                messageLabel.setText("💝 Please enter a name for your pet");
                return;
            }

            if (newName.length() > 20) {
                messageLabel.setForeground(ACCENT_COLOR);
                messageLabel.setText("📝 Name too long (max 20 characters)");
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
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            RenamePetState state = renamePetViewModel.getState();

            SwingUtilities.invokeLater(() -> {
                if (state.isSuccess()) {
                    messageLabel.setForeground(new Color(60, 179, 113));
                    messageLabel.setText("🎉 " + state.getMessage() + " 🎉");
                    Timer timer = new Timer(1500, ae -> {
                        clear();

                    });
                    timer.setRepeats(false);
                    timer.start();

                } else {
                    messageLabel.setForeground(ACCENT_COLOR);
                    messageLabel.setText("❌ " + state.getMessage());
                }
            });
        }
    }
}