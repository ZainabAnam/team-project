package game.view;

import game.Constants;
import game.interface_adapter.shop.ShopState;
import game.interface_adapter.shop.ShopViewModel;
import game.use_case.PetShop.ShopController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The View for the Shop interface.
 */
public class ShopView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "shop";
    private final ShopViewModel shopViewModel;

    private final JLabel coinsLabel = new JLabel();
    
    // Item buttons
    private final JButton lootBoxButton;
    private final JButton[] petFoodButtons;
    private final JButton[] petToyButtons;
    private final JButton upgradeClickerButton;
    private final JButton unlockSlotButton;
    private final JButton closeButton;
    
    // Panels need updates
    private JPanel upgradeClickerPanel;
    private JPanel unlockSlotPanel;
    
    private ShopController shopController;

    public ShopView(ShopViewModel shopViewModel) {
        this.shopViewModel = shopViewModel;
        this.shopViewModel.addPropertyChangeListener(this);

        // Initialize buttons
        this.lootBoxButton = new JButton();
        this.petFoodButtons = new JButton[3];
        this.petToyButtons = new JButton[3];
        this.upgradeClickerButton = new JButton();
        this.unlockSlotButton = new JButton();
        this.closeButton = new JButton(ShopViewConstants.CLOSE_BUTTON_TEXT);

        for (int i = 0; i < 3; i++) {
            this.petFoodButtons[i] = new JButton();
        }

        for (int i = 0; i < 3; i++) {
            this.petToyButtons[i] = new JButton();
        }

        initializeLayout();
        updateButtonsFromState();
    }

    private void initializeLayout() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(900, 700));
        
        // Use yellow background
        this.setBackground(new Color(255, 235, 59)); 
        setupContent(this);
    }

    private void setupContent(Container container) {
        // Title and coins panel
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);
        topPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 10, 20));
        
        // Title 
        JLabel titleLabel = new JLabel("\uD83D\uDC3E " + ShopViewConstants.SHOP_TITLE);
        titleLabel.setFont(new Font("Dialog", Font.BOLD, 28));
        titleLabel.setForeground(Color.BLACK);
        
        // Coins
        coinsLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        coinsLabel.setForeground(Color.BLACK);
        
        topPanel.add(titleLabel, BorderLayout.WEST);
        topPanel.add(coinsLabel, BorderLayout.EAST);

        // Main panels with 3x3 grid
        JPanel contentPanel = new JPanel(new GridLayout(3, 3, 10, 10));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        contentPanel.setOpaque(false);

        // Row 1: Loot Box, Upgrade Clicker, Unlock Slot
        contentPanel.add(createItemPanel(
            ShopImageConstants.LOOTBOX_CLOSED_IMG,
            ShopViewConstants.LOOT_BOX_TITLE,
            ShopViewConstants.LOOT_BOX_DESC,
            lootBoxButton,
            Constants.LOOT_BOX_PRICE
        ));

        upgradeClickerPanel = createItemPanel(
            ShopImageConstants.UPGRADE_CLICKER_IMG,
            ShopViewConstants.UPGRADE_CLICKER_TITLE,
            ShopViewConstants.UPGRADE_CLICKER_DESC + " (Level: 1)",//set as default level
            upgradeClickerButton,
            Constants.UPGRADE_CLICKER_BASE_PRICE
        );
        contentPanel.add(upgradeClickerPanel);

        unlockSlotPanel = createItemPanel(
            ShopImageConstants.UNLOCK_SLOT_IMG,
            ShopViewConstants.UNLOCK_SLOT_TITLE,
            ShopViewConstants.UNLOCK_SLOT_DESC + " (Slots: 2)",//set as default slots
            unlockSlotButton,
            Constants.UNLOCK_SLOT_BASE_PRICE
        );
        contentPanel.add(unlockSlotPanel);

        // Row 2: Pet Food (3 types)
        String[] foodNames = {Constants.ITEM_KIBBLE, Constants.ITEM_CANNED_FOOD, Constants.ITEM_HOME_COOKED};
        String[] foodDescs = {
            ShopViewConstants.KIBBLE_DESC,
            ShopViewConstants.CANNED_FOOD_DESC,
            ShopViewConstants.HOME_COOKED_DESC
        };
        String[] foodImages = {
            ShopImageConstants.KIBBLE_IMG,
            ShopImageConstants.CANNED_FOOD_IMG,
            ShopImageConstants.HOME_COOKED_IMG
        };
        int[] foodPrices = {
            Constants.PET_FOOD_BASIC_PRICE,
            Constants.PET_FOOD_MEDIUM_PRICE,
            Constants.PET_FOOD_PREMIUM_PRICE
        };
        
        for (int i = 0; i < 3; i++) {
            contentPanel.add(createItemPanel(
                foodImages[i],
                foodNames[i],
                foodDescs[i],
                petFoodButtons[i],
                foodPrices[i]
            ));
        }

        // Row 3: Pet Toys (3 types)
        String[] toyNames = {Constants.ITEM_CHEW_TOY, Constants.ITEM_TOSS_TOY, Constants.ITEM_PLUSH_TOY};
        String[] toyDescs = {
            ShopViewConstants.CHEW_TOY_DESC,
            ShopViewConstants.TOSS_TOY_DESC,
            ShopViewConstants.PLUSH_TOY_DESC
        };
        String[] toyImages = {
            ShopImageConstants.CHEW_TOY_IMG,
            ShopImageConstants.TOSS_TOY_IMG,
            ShopImageConstants.PLUSH_TOY_IMG
        };
        int[] toyPrices = {
            Constants.PET_TOY_BASIC_PRICE,
            Constants.PET_TOY_MEDIUM_PRICE,
            Constants.PET_TOY_PREMIUM_PRICE
        };
        
        for (int i = 0; i < 3; i++) {
            contentPanel.add(createItemPanel(
                toyImages[i],
                toyNames[i],
                toyDescs[i],
                petToyButtons[i],
                toyPrices[i]
            ));
        }

        // Bottom panel close button
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setOpaque(false);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 15, 20));
        closeButton.setPreferredSize(new Dimension(120, 40));
        closeButton.setFont(new Font("Arial", Font.BOLD, 14));
        closeButton.setBackground(new Color(244, 67, 54)); // Red close button
        closeButton.setForeground(Color.WHITE);
        closeButton.setBorder(BorderFactory.createRaisedBevelBorder());
        bottomPanel.add(closeButton);

        container.add(topPanel, BorderLayout.NORTH);
        container.add(contentPanel, BorderLayout.CENTER);
        container.add(bottomPanel, BorderLayout.SOUTH);
    }

    //panel for each item on shop page
    private JPanel createItemPanel(String imagePath, String title, String description, JButton button, int price) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(224, 224, 224), 1),
            BorderFactory.createEmptyBorder(8, 8, 8, 8)
        ));
        panel.setPreferredSize(new Dimension(280, 200));

        // Image (left side)
        JLabel imageLabel = new JLabel();
        imageLabel.setPreferredSize(new Dimension(120, 120));
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setVerticalAlignment(JLabel.CENTER);
        
        ImageIcon icon = new ImageIcon(imagePath);
        Image scaledImage = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(scaledImage));

        // Info panel (right side)
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setOpaque(false);
        infoPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 5));

        // Title
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Description
        JLabel descLabel = new JLabel("<html>" + description + "</html>");//wrap with html tools
        descLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        descLabel.setForeground(new Color(100, 100, 100));
        descLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // buy button
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.setPreferredSize(new Dimension(120, 35));
        button.setMaximumSize(new Dimension(120, 35));
        button.setBackground(new Color(76, 175, 80)); //green button
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createRaisedBevelBorder());
        button.setText(String.valueOf(price) + ShopViewConstants.COIN_SUFFIX);

        // Add components to info panel
        infoPanel.add(titleLabel);
        infoPanel.add(Box.createVerticalStrut(5));
        infoPanel.add(descLabel);
        infoPanel.add(Box.createVerticalGlue());
        infoPanel.add(button);

        // Add to main panel
        panel.add(imageLabel, BorderLayout.WEST);
        panel.add(infoPanel, BorderLayout.CENTER);

        return panel;
    }

    private void updateButtonsFromState() {
        final ShopState currentState = shopViewModel.getState();
        
        // Update coins
        coinsLabel.setText("Coin: " + String.valueOf(currentState.getCurrentCoins()));
        
        updateUpgradekPanel(currentState);
        updateUnlockSlotPanel(currentState);
        
        updateButtonState(lootBoxButton, Constants.LOOT_BOX_PRICE, true, currentState.getCurrentCoins());
        
        updateButtonState(petFoodButtons[0], Constants.PET_FOOD_BASIC_PRICE, true, currentState.getCurrentCoins());
        updateButtonState(petFoodButtons[1], Constants.PET_FOOD_MEDIUM_PRICE, true, currentState.getCurrentCoins());
        updateButtonState(petFoodButtons[2], Constants.PET_FOOD_PREMIUM_PRICE, true, currentState.getCurrentCoins());
        
        updateButtonState(petToyButtons[0], Constants.PET_TOY_BASIC_PRICE, true, currentState.getCurrentCoins());
        updateButtonState(petToyButtons[1], Constants.PET_TOY_MEDIUM_PRICE, true, currentState.getCurrentCoins());
        updateButtonState(petToyButtons[2], Constants.PET_TOY_PREMIUM_PRICE, true, currentState.getCurrentCoins());
        
        // upgrade clicker button and see if it is max
        if (currentState.canUpgradeClicker()) {
            int upgradePrice = currentState.getCurrentClickLevel() * Constants.UPGRADE_CLICKER_BASE_PRICE;
            updateButtonState(upgradeClickerButton, upgradePrice, true, currentState.getCurrentCoins());
        } else {
            upgradeClickerButton.setText(ShopViewConstants.MAX_BUTTON_TEXT);
            upgradeClickerButton.setEnabled(false);
            upgradeClickerButton.setBackground(Color.GRAY);
        }
        
        // unlock slot button and see if it is max
        if (currentState.canUnlockSlot()) {
            int unlockPrice = (currentState.getCurrentSlots() - Constants.INITIAL_SLOTS + 1) * Constants.UNLOCK_SLOT_BASE_PRICE;
            updateButtonState(unlockSlotButton, unlockPrice, true, currentState.getCurrentCoins());
        } else {
            unlockSlotButton.setText(ShopViewConstants.MAX_BUTTON_TEXT);
            unlockSlotButton.setEnabled(false);
            unlockSlotButton.setBackground(Color.GRAY);
        }
    }
    
    private void updateButtonState(JButton button, int price, boolean available, int currentCoins) {
        button.setText(String.valueOf(price) + ShopViewConstants.COIN_SUFFIX);
        
        // change color by if there is enough coins
        if (available && currentCoins >= price) {
            button.setEnabled(true);
            button.setBackground(new Color(76, 175, 80)); // Green, have enough coins to buy
            button.setForeground(Color.WHITE);
        } else if (available) {
            button.setEnabled(true);
            button.setBackground(new Color(255, 152, 0)); // Orange, not enough coins
            button.setForeground(Color.WHITE);
        } else {
            button.setEnabled(false);
            button.setBackground(Color.GRAY); // Gray - default
            button.setForeground(Color.WHITE);
        }
    }
    
    private void updateUpgradekPanel(ShopState currentState) {
        // Update upgrade clicker panel description
        if (upgradeClickerPanel != null) {
            updatePanelDescription(upgradeClickerPanel, 
                ShopViewConstants.UPGRADE_CLICKER_DESC + " (Level: " + currentState.getCurrentClickLevel() + ")");
        }
    }
        
    private void updateUnlockSlotPanel(ShopState currentState) {
        // Update unlock slot panel description
        if (unlockSlotPanel != null) {
            updatePanelDescription(unlockSlotPanel, 
                ShopViewConstants.UNLOCK_SLOT_DESC + " (Slots: " + currentState.getCurrentSlots() + ")");
        }
    }
    
    private void updatePanelDescription(JPanel panel, String newDescription) {
        // helper method for updating the description
        Component[] components = panel.getComponents();
        for (Component comp : components) {
            if (comp instanceof JPanel) {
                JPanel infoPanel = (JPanel) comp;
                Component[] infoComponents = infoPanel.getComponents();
                for (Component infoComp : infoComponents) {
                    if (infoComp instanceof JLabel) {
                        JLabel label = (JLabel) infoComp;
                        String text = label.getText();
                        // Check if this is the description label (wrap with html) (increase or add for upgrade and unlock)
                        if (text.startsWith("<html>") && (text.contains("Increase") || text.contains("Add"))) {
                            label.setText("<html>" + newDescription + "</html>");
                            return;
                        }
                    }
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final ShopState state = (ShopState) evt.getNewValue();
            updateButtonsFromState();
            
            // show success/failure messages
            if (!state.getLastMessage().isEmpty()) {
                if (state.isLastOperationSuccess()) {
                    showSuccessDialog(state.getLastMessage(), state.getLastPurchasedItem());
                } else {
                    showFailureDialog(state.getLastMessage());
                }
                
                // Clear the message after showing dialog
                SwingUtilities.invokeLater(() -> shopViewModel.clearLastOperation());
            }
        }
    }

    private void showSuccessDialog(String message, String item) {
        if (item.contains("Upgrade") || item.contains("Unlock")) {
            showUpgradeSuccessDialog(message, item);
        } 
        else {
            showPurchaseSuccessDialog(message, item);
        }
    }
    
    // item is the "last purchased item" that can be used as the text
    private void showUpgradeSuccessDialog(String message, String item) {
        //pop up window
        JDialog dialog = new JDialog((JFrame) SwingUtilities.getWindowAncestor(this), "", true);
        dialog.setLayout(new BorderLayout());
        
        JLabel arrowLabel = new JLabel(item);
        arrowLabel.setHorizontalAlignment(JLabel.CENTER);
        arrowLabel.setFont(new Font("Arial", Font.BOLD, 14));
        arrowLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        JButton okButton = new JButton();
        
        if (item.contains("Upgrade")) {
            okButton.setText(ShopViewConstants.UPGRADE_SUCCESS_NICE);
        } else {
            okButton.setText(ShopViewConstants.UNLOCK_SUCCESS_WOW);
        }
        okButton.addActionListener(e -> dialog.dispose());
        okButton.setPreferredSize(new Dimension(80, 30));
        
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(okButton);
        
        dialog.add(arrowLabel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setSize(300, 150);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }
    
    private void showPurchaseSuccessDialog(String message, String item) {
        // Loot Box - open animation
        if (Constants.ITEM_LOOT_BOX.equals(item)) {
            showLootBoxOpeningDialog();
            return;
        }
        
        // transparent image + item
        showItemPurchaseOverlay(item);
    }
    
    private void showLootBoxOpeningDialog() {
        //shining background+loot box
        JDialog lootDialog = new JDialog((JFrame) SwingUtilities.getWindowAncestor(this), true);
        lootDialog.setUndecorated(true);
        lootDialog.setBackground(new Color(0, 0, 0, 0)); // Transparent background

        JLabel shiningBackgroundLabel = new JLabel();
        shiningBackgroundLabel.setHorizontalAlignment(JLabel.CENTER);
        shiningBackgroundLabel.setVerticalAlignment(JLabel.CENTER);
        
        ImageIcon shiningIcon = new ImageIcon(ShopImageConstants.SHINING_BACKGROUND_IMG);
        Image scaledShining = shiningIcon.getImage().getScaledInstance(800, 800, Image.SCALE_SMOOTH);
        shiningBackgroundLabel.setIcon(new ImageIcon(scaledShining));
        
        JLabel lootBoxLabel = new JLabel();
        lootBoxLabel.setHorizontalAlignment(JLabel.CENTER);
        lootBoxLabel.setVerticalAlignment(JLabel.CENTER);
        lootBoxLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        lootBoxLabel.setAlignmentY(Component.CENTER_ALIGNMENT);
        
        // closed loot box 
        ImageIcon closedIcon = new ImageIcon(ShopImageConstants.LOOTBOX_CLOSED_IMG);
        Image scaledClosed = closedIcon.getImage().getScaledInstance(600, 600, Image.SCALE_SMOOTH);
        lootBoxLabel.setIcon(new ImageIcon(scaledClosed));
    
        shiningBackgroundLabel.setLayout(new OverlayLayout(shiningBackgroundLabel));
        shiningBackgroundLabel.add(lootBoxLabel);
        
        // click to open lootbox
        shiningBackgroundLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            private boolean opened = false;
            
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (!opened) {
                    // open loot box
                    ImageIcon openIcon = new ImageIcon(ShopImageConstants.LOOTBOX_OPEN_IMG);
                    Image scaledOpen = openIcon.getImage().getScaledInstance(600, 600, Image.SCALE_SMOOTH);
                    lootBoxLabel.setIcon(new ImageIcon(scaledOpen));
                    opened = true;
                } else {
                    // Close dialog and navigate to pet naming 
                    // TODO: navigate to pet naming
                    lootDialog.dispose();
                }
            }
        });
        
        lootDialog.add(shiningBackgroundLabel);
        lootDialog.setSize(800, 800); // Match background image size
        lootDialog.setLocationRelativeTo(this);
        lootDialog.setVisible(true);
    }
    
    private void showItemPurchaseOverlay(String item) {
        // shining background for item purchase
        JDialog itemDialog = new JDialog((JFrame) SwingUtilities.getWindowAncestor(this), true);
        itemDialog.setUndecorated(true);
        itemDialog.setBackground(new Color(0, 0, 0, 0)); //transparent background
        
        JLabel shiningBackgroundLabel = new JLabel();
        shiningBackgroundLabel.setHorizontalAlignment(JLabel.CENTER);
        shiningBackgroundLabel.setVerticalAlignment(JLabel.CENTER);
        
        ImageIcon shiningIcon = new ImageIcon(ShopImageConstants.SHINING_BACKGROUND_IMG);
        Image scaledShining = shiningIcon.getImage().getScaledInstance(800, 800, Image.SCALE_SMOOTH);
        shiningBackgroundLabel.setIcon(new ImageIcon(scaledShining));
        
        JLabel itemLabel = new JLabel();
        itemLabel.setHorizontalAlignment(JLabel.CENTER);
        itemLabel.setVerticalAlignment(JLabel.CENTER);
        itemLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        itemLabel.setAlignmentY(Component.CENTER_ALIGNMENT);

        String itemImagePath = getItemImagePath(item);
        ImageIcon itemIcon = new ImageIcon(itemImagePath);
        Image scaledItem = itemIcon.getImage().getScaledInstance(600, 600, Image.SCALE_SMOOTH);
        itemLabel.setIcon(new ImageIcon(scaledItem));
        
        shiningBackgroundLabel.setLayout(new OverlayLayout(shiningBackgroundLabel));
        shiningBackgroundLabel.add(itemLabel);
        
        // Click to close
        shiningBackgroundLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                itemDialog.dispose();
            }
        });
        
        itemDialog.add(shiningBackgroundLabel);
        itemDialog.setSize(800, 800); // match background image size
        itemDialog.setLocationRelativeTo(this);
        itemDialog.setVisible(true);
    }
    
    private String getItemImagePath(String itemName) {
        // get the path with the item name
        if (Constants.ITEM_LOOT_BOX.equals(itemName)) {
            return ShopImageConstants.LOOTBOX_CLOSED_IMG;
        } else if (Constants.ITEM_KIBBLE.equals(itemName)) {
            return ShopImageConstants.KIBBLE_IMG;
        } else if (Constants.ITEM_CANNED_FOOD.equals(itemName)) {
            return ShopImageConstants.CANNED_FOOD_IMG;
        } else if (Constants.ITEM_HOME_COOKED.equals(itemName)) {
            return ShopImageConstants.HOME_COOKED_IMG;
        } else if (Constants.ITEM_CHEW_TOY.equals(itemName)) {
            return ShopImageConstants.CHEW_TOY_IMG;
        } else if (Constants.ITEM_TOSS_TOY.equals(itemName)) {
            return ShopImageConstants.TOSS_TOY_IMG;
        } else if (Constants.ITEM_PLUSH_TOY.equals(itemName)) {
            return ShopImageConstants.PLUSH_TOY_IMG;
        } else {
            return ShopImageConstants.LOOTBOX_CLOSED_IMG; // Default fallback
        }
    }

    private void showFailureDialog(String message) {
        // Create dialog with "Click and earn more coins!" button
        JDialog dialog = new JDialog((JFrame) SwingUtilities.getWindowAncestor(this), 
                                   ShopViewConstants.INSUFFICIENT_COINS_TITLE, true);
        dialog.setLayout(new BorderLayout());
        
        JLabel messageLabel = new JLabel(ShopViewConstants.INSUFFICIENT_COINS_MESSAGE);
        messageLabel.setHorizontalAlignment(JLabel.CENTER);
        messageLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
        
        JButton earnMoreButton = new JButton(ShopViewConstants.EARN_MORE_BUTTON);
        earnMoreButton.addActionListener(e -> dialog.dispose());
        
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(earnMoreButton);
        
        dialog.add(messageLabel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setSize(300, 150);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    public String getViewName() {
        return viewName;
    }

    public void setShopController(ShopController shopController) {
        this.shopController = shopController;
        
        // Set up action listeners
        lootBoxButton.addActionListener(e -> shopController.buyLootBox());
        
        petFoodButtons[0].addActionListener(e -> shopController.buyBasicPetFood());
        petFoodButtons[1].addActionListener(e -> shopController.buyMediumPetFood());
        petFoodButtons[2].addActionListener(e -> shopController.buyPremiumPetFood());
        
        petToyButtons[0].addActionListener(e -> shopController.buyBasicPetToy());
        petToyButtons[1].addActionListener(e -> shopController.buyMediumPetToy());
        petToyButtons[2].addActionListener(e -> shopController.buyPremiumPetToy());
        
        upgradeClickerButton.addActionListener(e -> shopController.upgradeClicker());
        unlockSlotButton.addActionListener(e -> shopController.unlockSlot());
        
        closeButton.addActionListener(e -> {
            // Close the window
            Window window = SwingUtilities.getWindowAncestor(this);
            if (window != null) {
                window.dispose();
            }
        });
    }
    
}
    
 