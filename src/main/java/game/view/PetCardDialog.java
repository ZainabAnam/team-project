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
    private final JLabel affectionLabel = new JLabel();

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

        // top: level + name
        JPanel top = new JPanel(new BorderLayout());
        top.setOpaque(false);

        levelLabel.setFont(levelLabel.getFont().deriveFont(Font.BOLD, 12f));
        levelLabel.setHorizontalAlignment(SwingConstants.LEFT);
        top.add(levelLabel, BorderLayout.WEST);

        nameLabel.setFont(nameLabel.getFont().deriveFont(Font.BOLD, 22f));
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        top.add(nameLabel, BorderLayout.CENTER);

        root.add(top, BorderLayout.NORTH);

        // center: image + breed
        JPanel center = new JPanel();
        center.setOpaque(false);
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));

        imageLabel.setPreferredSize(new Dimension(220, 220));
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        center.add(imageLabel);
        center.add(Box.createVerticalStrut(8));

        breedLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        center.add(breedLabel);

        root.add(center, BorderLayout.CENTER);

        // bottom: energy bar + buttons
        JPanel bottom = new JPanel();
        bottom.setOpaque(false);
        bottom.setLayout(new BoxLayout(bottom, BoxLayout.Y_AXIS));

        JPanel energyRow = new JPanel(new BorderLayout(8, 0));
        energyRow.setOpaque(false);

        energyLabel.setText("Energy:");
        energyRow.add(energyLabel, BorderLayout.WEST);

        energyBar.setPreferredSize(new Dimension(240, 10));
        energyRow.add(energyBar, BorderLayout.CENTER);

        bottom.add(energyRow);
        bottom.add(Box.createVerticalStrut(12));

        JPanel buttons = new JPanel(new GridLayout(1, 3, 10, 0));
        buttons.setOpaque(false);

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


        buttons.add(new JButton("Sell"));

        bottom.add(buttons);

        root.add(bottom, BorderLayout.SOUTH);

    }

    private void fillFromPet(CollectionsState.PetCardState pet) {
        if (pet == null) {
            return;
        }

        String name  = pet.getName();
        String breed = pet.getBreed();

        if (name == null || name.isBlank()) {
            nameLabel.setText(breed);
            breedLabel.setText("");
        } else {
            nameLabel.setText(name);
            breedLabel.setText(breed);
        }

        levelLabel.setText("Lvl " + pet.getLevel());
        energyLabel.setText("Energy: " + pet.getEnergy() + "%");
        energyBar.setValue(pet.getEnergy());

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

    private void updateEnergy() {
        final PetCardState state = petCardViewModel.getState();
        energyLabel.setText("Energy: " + state.getNewEnergyLevel() + "%");
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
            updateEnergy();
            updateAffectionXPAndLevel();
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
