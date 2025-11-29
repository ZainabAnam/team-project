package game.view;

import game.interface_adapter.Sell_Pet.SellPetController;
import game.interface_adapter.Sell_Pet.SellPetViewModel;
import game.interface_adapter.Sell_Pet.SellPetState;
import game.entity.Pet;
import game.interface_adapter.ViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SellPetView extends JPanel implements PropertyChangeListener {
    private final SellPetController sellPetController;
    private final SellPetViewModel sellPetViewModel;
    private final ViewManagerModel viewManagerModel;

    private JLabel messageLabel;
    private JLabel titleLabel;
    private JLabel petInfoLabel;
    private JButton confirmButton;
    private JButton cancelButton;

    private int currentPetIndex;
    private String currentUsername;
    private Pet currentPet;

    // color setup
    private final Color PRIMARY_COLOR = new Color(255, 182, 193);
    private final Color SECONDARY_COLOR = new Color(147, 112, 219);
    private final Color ACCENT_COLOR = new Color(255, 105, 180);
    private final Color BACKGROUND_COLOR = new Color(255, 250, 250);
    private final Color SUCCESS_COLOR = new Color(144, 238, 144);

    public SellPetView(SellPetController sellPetController,
                       SellPetViewModel sellPetViewModel, ViewManagerModel viewManagerModel) {
        this.sellPetController = sellPetController;
        this.sellPetViewModel = sellPetViewModel;
        this.viewManagerModel = viewManagerModel;
        sellPetViewModel.addPropertyChangeListener(this);

        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BorderLayout(15, 15));
        setBackground(BACKGROUND_COLOR);
        setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        // title panel
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(BACKGROUND_COLOR);

        titleLabel = new JLabel("💸 Sell Pet 💸", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        titleLabel.setForeground(SECONDARY_COLOR);
        topPanel.add(titleLabel, BorderLayout.CENTER);

        // middle panel
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(BACKGROUND_COLOR);
        centerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // pet icon
        JLabel emojiLabel = new JLabel("🐕 💰 🐈", SwingConstants.CENTER);
        emojiLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 48));
        emojiLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // pet info
        petInfoLabel = new JLabel("", SwingConstants.CENTER);
        petInfoLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
        petInfoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // messgae label
        messageLabel = new JLabel(" ", SwingConstants.CENTER);
        messageLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        messageLabel.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));

        centerPanel.add(emojiLabel);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(petInfoLabel);
        centerPanel.add(Box.createVerticalStrut(15));
        centerPanel.add(messageLabel);

        // bottom button setup
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(BACKGROUND_COLOR);

        confirmButton = new JButton("💰 Confirm Sale 💰");
        styleButton(confirmButton, SUCCESS_COLOR);
        confirmButton.addActionListener(new ConfirmListener());

        cancelButton = new JButton("💝 Keep Pet 💝");
        styleButton(cancelButton, PRIMARY_COLOR);
        cancelButton.addActionListener(new CancelListener());

        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);

        // configuration step
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
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor.darker());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });
    }

    // sell pet prep
    public void prepareForSale(Pet pet, int petIndex, String username, String previousView) {
        this.currentPet = pet;
        this.currentPetIndex = petIndex;
        this.currentUsername = username;

        // source visuals setup
        SellPetState state = sellPetViewModel.getState();
        state.setPreviousView(previousView);
        sellPetViewModel.setState(state);

        String petEmoji = pet.getPetType().equalsIgnoreCase("Dog") ? "🐕" : "🐈";
        petInfoLabel.setText(
                "<html><div style='text-align: center;'>" +
                        "Sell <b><font color='" + colorToHex(SECONDARY_COLOR) + "'>" + pet.getName() + "</font></b> " + petEmoji + "<br>" +
                        "Price: <font color='#2E8B57'><b>💰 " + pet.getSellingPrice() + " coins 💰</b></font><br>" +
                        "<small>" + pet.getPetBreed() + " | Level " + pet.getAffectionLevel() + "</small>" +
                        "</div></html>"
        );

        messageLabel.setText("Are you sure you want to sell your pet?");
        messageLabel.setForeground(new Color(75, 75, 75));
    }

    private class ConfirmListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // 使用控制器执行出售
            sellPetController.execute(currentPetIndex, currentUsername);
        }
    }

    private class CancelListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // back to previous page
            SellPetState state = sellPetViewModel.getState();
            String previousView = state.getPreviousView();
            System.out.println("🎯 SellPetView: Cancel clicked, returning to: " + previousView);
            if (previousView != null && !previousView.isEmpty()) {
                viewManagerModel.setState(previousView);
                viewManagerModel.firePropertyChange();
            } else {
                // back to collection view
                viewManagerModel.setState("collection");
                viewManagerModel.firePropertyChange();
            }

            clear();
        }
    }

    // clear state
    public void clear() {
        currentPetIndex = -1;
        currentUsername = null;
        currentPet = null;
        messageLabel.setText(" ");
    }

    public String getViewName() {
        return "sell_pet";
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            SellPetState state = sellPetViewModel.getState();

            SwingUtilities.invokeLater(() -> {
                if (state.isSuccess()) {
                    messageLabel.setForeground(new Color(60, 179, 113)); // 绿色
                    messageLabel.setText("🎉 " + state.getMessage() + " 🎉");

                    // prevent repetitive operation
                    confirmButton.setEnabled(false);
                    cancelButton.setEnabled(false);

                } else {
                    messageLabel.setForeground(ACCENT_COLOR);
                    messageLabel.setText("❌ " + state.getMessage());
                }
            });
        }
    }

    private String colorToHex(Color color) {
        return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
    }
}