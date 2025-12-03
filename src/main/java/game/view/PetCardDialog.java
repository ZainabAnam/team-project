package game.view;

import game.interface_adapter.PetCard.IncreaseEnergy.IncreaseEnergyController;
import game.interface_adapter.PetCard.PetCardState;
import game.interface_adapter.PetCard.PetCardViewModel;
import game.interface_adapter.ViewManagerModel;
import game.interface_adapter.collections.CollectionsState;
import game.interface_adapter.PetCard.IncreaseAffection.IncreaseAffectionController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static game.Constants.ITEM_KIBBLE;
import static game.Constants.ITEM_CANNED_FOOD;
import static game.Constants.ITEM_HOME_COOKED;
import static game.Constants.ITEM_CHEW_TOY;
import static game.Constants.ITEM_TOSS_TOY;
import static game.Constants.ITEM_PLUSH_TOY;

public class PetCardDialog extends JDialog implements ActionListener, PropertyChangeListener {

    private final String viewName = "pet card";
    private final PetCardViewModel petCardViewModel;
    private final ViewManagerModel viewManagerModel;

    private final JLabel nameLabel = new JLabel();
    private final JLabel breedLabel = new JLabel();
    private final JLabel levelLabel = new JLabel();
    private final JLabel energyLabel = new JLabel();
    private final JLabel imageLabel = new JLabel();
    private final JLabel factLabel = new JLabel();

    private final JLabel clickingSpeedLabel = new JLabel();
    private final JLabel affectionLabel = new JLabel();
    private final JLabel sellingPriceLabel = new JLabel();

    private final EnergyBar energyBar = new EnergyBar();

    private IncreaseAffectionController increaseAffectionController;
    private IncreaseEnergyController increaseEnergyController;

    public PetCardDialog(Window owner, CollectionsState.PetCardState pet, PetCardViewModel petCardViewModel, ViewManagerModel viewManagerModel) {
        super(owner, "Pet Details", ModalityType.APPLICATION_MODAL);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        this.petCardViewModel = petCardViewModel;
        this.petCardViewModel.addPropertyChangeListener(this);
        this.viewManagerModel = viewManagerModel;

        // roughly “card sized”
        setPreferredSize(new Dimension(380, 520));

        buildUI(pet);
        fillFromPet(pet);
        pack();
        setLocationRelativeTo(owner);
    }

    private void buildUI(CollectionsState.PetCardState pet) {
        JPanel root = new JPanel(new BorderLayout());
        root.setBorder(new EmptyBorder(16, 16, 16, 16));
        root.setBackground(Color.WHITE);
        setContentPane(root);

        // Row 1: level (left) + CPM (right)
        JPanel headerRow = new JPanel(new BorderLayout());
        headerRow.setOpaque(false);

        levelLabel.setFont(levelLabel.getFont().deriveFont(Font.BOLD, 12f));
        headerRow.add(levelLabel, BorderLayout.WEST);

        clickingSpeedLabel.setFont(clickingSpeedLabel.getFont().deriveFont(11f));
        clickingSpeedLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        headerRow.add(clickingSpeedLabel, BorderLayout.EAST);

        root.add(headerRow);
        root.add(Box.createVerticalStrut(4));

        // Row 1.5: name (centered)
        JPanel nameRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        nameRow.setOpaque(false);
        nameLabel.setFont(nameLabel.getFont().deriveFont(Font.BOLD, 22f));
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nameRow.add(nameLabel);

        root.add(nameRow);
        root.add(Box.createVerticalStrut(8));

        // Row 2: pet image
        imageLabel.setPreferredSize(new Dimension(220, 220));
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel imagePanel = new JPanel(new GridBagLayout());
        imagePanel.setOpaque(false);
        imagePanel.add(imageLabel);

        root.add(imagePanel);
        root.add(Box.createVerticalStrut(8));

        // Row 3: Type – Breed line
        breedLabel.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel breedRow = new JPanel();
        breedRow.setOpaque(false);
        breedRow.add(breedLabel);

        root.add(breedRow);
        root.add(Box.createVerticalStrut(12));

        // Row 4: buttons (Feed / Play / Sell)
        JPanel buttonsRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 16, 0));
        buttonsRow.setOpaque(false);

        buttonsRow.add(new JButton("Feed"));
        buttonsRow.add(new JButton("Play"));
        buttonsRow.add(new JButton("Sell"));

        root.add(buttonsRow);
        root.add(Box.createVerticalStrut(16));

        // Row 5: Energy label + bar
        JPanel energyRow = new JPanel(new BorderLayout(8, 0));
        energyRow.setOpaque(false);

        energyLabel.setText("Energy:");
        energyRow.add(energyLabel, BorderLayout.WEST);

        energyBar.setPreferredSize(new Dimension(240, 10));
        energyRow.add(energyBar, BorderLayout.CENTER);

        root.add(energyRow);
        root.add(Box.createVerticalStrut(8));

        // Row 5.5: Affection XP
        JPanel affectionRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        affectionRow.setOpaque(false);
        affectionLabel.setFont(affectionLabel.getFont().deriveFont(12f));
        affectionRow.add(affectionLabel);

        root.add(affectionRow);
        root.add(Box.createVerticalStrut(4));

        // Row 5.6: Selling price
        JPanel sellingRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        sellingRow.setOpaque(false);
        sellingPriceLabel.setFont(sellingPriceLabel.getFont().deriveFont(12f));
        sellingRow.add(sellingPriceLabel);

        root.add(sellingRow);
        root.add(Box.createVerticalStrut(16));

        // Row 6: “Did you know?” line, then fact below – both left aligned
        JPanel factRow = new JPanel(new BorderLayout());
        factRow.setOpaque(false);

        JPanel factColumn = new JPanel();
        factColumn.setOpaque(false);
        factColumn.setLayout(new BoxLayout(factColumn, BoxLayout.Y_AXIS));

        JButton feedButton = new JButton("Feed");
        buttons.add(feedButton);

        JPopupMenu foodPopup = new JPopupMenu();
        JMenuItem kibbleItem = new JMenuItem(ITEM_KIBBLE);
        foodPopup.add(kibbleItem);
        JMenuItem cannedFoodItem = new JMenuItem(ITEM_CANNED_FOOD);
        foodPopup.add(cannedFoodItem);
        JMenuItem homeCookedItem = new JMenuItem(ITEM_HOME_COOKED);
        foodPopup.add(homeCookedItem);

        feedButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(feedButton)) {
                            foodPopup.show(feedButton, 0, feedButton.getHeight());
                        }
                    }
                });
        ActionListener feedListener;
        feedListener = evt -> {
            String food = evt.getActionCommand();
            increaseEnergyController.execute(pet, food);
        };

        kibbleItem.addActionListener(feedListener);
        cannedFoodItem.addActionListener(feedListener);
        homeCookedItem.addActionListener(feedListener);



        JButton playButton = new JButton("Play");
        buttons.add(playButton);

        JPopupMenu toyPopup = new JPopupMenu();
        JMenuItem chewToyItem = new JMenuItem(ITEM_CHEW_TOY);
        toyPopup.add(chewToyItem);
        JMenuItem tossToyItem = new JMenuItem(ITEM_TOSS_TOY);
        toyPopup.add(tossToyItem);
        JMenuItem plushToyItem = new JMenuItem(ITEM_PLUSH_TOY);
        toyPopup.add(plushToyItem);

        playButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(playButton)) {
                            toyPopup.show(playButton, 0, playButton.getHeight());
                        }
                    }
                });

        ActionListener playListener;
        playListener = evt -> {
            String toy = evt.getActionCommand();
            increaseAffectionController.execute(pet, toy);
        };

        chewToyItem.addActionListener(playListener);
        tossToyItem.addActionListener(playListener);
        plushToyItem.addActionListener(playListener);


        buttons.add(new JButton("Sell"));
        JLabel didYouKnowLabel = new JLabel("Did you know?");
        didYouKnowLabel.setFont(didYouKnowLabel.getFont().deriveFont(Font.BOLD, 12f));
        didYouKnowLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        factLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        factLabel.setHorizontalAlignment(SwingConstants.LEFT);
        factLabel.setFont(factLabel.getFont().deriveFont(12f));
        factLabel.setBorder(new EmptyBorder(4, 0, 0, 0));
        factLabel.setOpaque(false);

        root.add(bottom, BorderLayout.SOUTH);

        factColumn.add(didYouKnowLabel);
        factColumn.add(factLabel);

        factColumn.setAlignmentX(Component.LEFT_ALIGNMENT);
        factRow.add(factColumn, BorderLayout.CENTER);

        factRow.setAlignmentX(Component.LEFT_ALIGNMENT);

        root.add(factRow);
    }

    private void fillFromPet(CollectionsState.PetCardState pet) {
        if (pet == null) {
            return;
        }

        String name  = pet.getName();
        String breed = pet.getBreed();
        String type  = pet.getType();

        String fact = pet.getFact();
        if (fact == null || fact.isBlank()) {
            factLabel.setText("No fact available.");
        } else {
            factLabel.setText(
                    "<html><div style='text-align: left; width: 320px;'>"
                            + fact
                            + "</div></html>"
            );
        }

        if (name == null || name.isBlank()) {
            if (breed != null && !breed.isBlank()) {
                nameLabel.setText(breed);
            } else if (type != null && !type.isBlank()) {
                nameLabel.setText(type);
            } else {
                nameLabel.setText("Unknown");
            }
        } else {
            nameLabel.setText(name);
        }

        String typeText  = (type  == null || type.isBlank())  ? "" : type;
        String breedText = (breed == null || breed.isBlank()) ? "" : breed;

        if (!typeText.isEmpty() && !breedText.isEmpty()) {
            breedLabel.setText(typeText + " - " + breedText);
        } else if (!typeText.isEmpty()) {
            breedLabel.setText(typeText);
        } else {
            breedLabel.setText(breedText);
        }

        levelLabel.setText("Lvl " + pet.getLevel());
        energyLabel.setText("Energy: " + pet.getEnergy() + "%");
        energyBar.setValue(pet.getEnergy());

        affectionLabel.setText("Affection XP: " + pet.getAffectionXp() + "/30");

        int coinsPerMinute = pet.getClickingSpeed();
        clickingSpeedLabel.setText("CPM: " + coinsPerMinute + " coins/min");

        sellingPriceLabel.setText("Selling price: " + pet.getSellingPrice() + " coins");

        Icon icon = pet.getPetVisual();
        if (icon instanceof ImageIcon) {
            Image img = ((ImageIcon) icon).getImage();
            Image scaled = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(scaled));
        } else {
            imageLabel.setIcon(icon);
        }
    }

    public static void showForPet(Component parent, CollectionsState.PetCardState pet) {
        Window owner = SwingUtilities.getWindowAncestor(parent);
        PetCardDialog dialog = new PetCardDialog(owner, pet, new PetCardViewModel(), new ViewManagerModel());
        dialog.setVisible(true);
    }

    public String getViewName() {
        return viewName;
    }

    public void setAffectionController(IncreaseAffectionController increaseAffectionController) {
        this.increaseAffectionController = increaseAffectionController;
    }

    public void setEnergyController(IncreaseEnergyController increaseEnergyController) {
        this.increaseEnergyController = increaseEnergyController;
    }

    private void updateEnergyLevel() {
        final PetCardState state = petCardViewModel.getState();
        energyLabel.setText("Energy: " + state.getNewEnergyLevel() + "%");
    }

    private void updateEnergyBar() {
        final PetCardState state = petCardViewModel.getState();
        energyBar.setValue(state.getNewEnergyLevel());
    }

    private void updateAffectionXP() {
        final PetCardState state = petCardViewModel.getState();
        affectionLabel.setText("Affection XP: " + state.getNewAffectionXP() + "/30");

    }

    private void updateAffectionLevel() {
        final PetCardState state = petCardViewModel.getState();
        levelLabel.setText("Lvl " + state.getNewPetLevel());
    }

    private void updateClickingSpeed() {
        final PetCardState state = petCardViewModel.getState();
        clickingSpeedLabel.setText("CPM: " + state.getNewClickingSpeed() + " coins/min");
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        System.out.println(evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (!evt.getPropertyName().equals("state")) {
            return;
        }
        else {
            final PetCardState state = (PetCardState) evt.getNewValue();
            updateEnergyLevel();
            updateEnergyBar();
            updateAffectionXP();
            updateAffectionLevel();
            updateClickingSpeed();
        }
    }


    private static class EnergyBar extends JComponent {
        private int value = 0;

        void setValue(int v) {
            value = Math.max(0, Math.min(100, v));
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();

            int w = getWidth();
            int h = getHeight();

            g2.setColor(new Color(230, 230, 230));
            g2.fillRect(0, 0, w, h);

            int filled = (int) (w * (value / 100.0));
            g2.setColor(new Color(144, 238, 144));
            g2.fillRect(0, 0, filled, h);

            g2.dispose();
        }
    }
}
