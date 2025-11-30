package game.view;

import game.interface_adapter.collections.CollectionsViewModel;
import game.interface_adapter.collections.CollectionsState;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The View for a Pet Card.
 */
public class PetCardView extends JPanel implements PropertyChangeListener {

    public static final String VIEW_NAME = "pet card";

    private final CollectionsViewModel viewModel;

    private JLabel nameLabel;
    private JLabel breedLabel;
    private JLabel levelLabel;
    private JLabel energyLabel;

    private JLabel imageHolder;
    private EnergyBar energyBar;

    public PetCardView(CollectionsViewModel viewModel) {
        this.viewModel = viewModel;
        this.viewModel.addPropertyChangeListener(this);

        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(20, 20, 20, 20));
        setOpaque(false);

        buildUI();
    }

    private void buildUI() {
        // Title
        JLabel title = new JLabel("Pet Details");
        title.setFont(title.getFont().deriveFont(Font.BOLD, 26f));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        add(title, BorderLayout.NORTH);

        // Card container
        JPanel card = new JPanel(new BorderLayout(0, 16));
        card.setBackground(Color.WHITE);
        card.setBorder(new EmptyBorder(20, 24, 20, 24));
        add(card, BorderLayout.CENTER);

        // Top: level + name
        JPanel top = new JPanel(new BorderLayout());
        top.setOpaque(false);

        levelLabel = new JLabel("", SwingConstants.LEFT);
        levelLabel.setFont(levelLabel.getFont().deriveFont(Font.BOLD, 12f));
        top.add(levelLabel, BorderLayout.WEST);

        nameLabel = new JLabel("", SwingConstants.CENTER);
        nameLabel.setFont(nameLabel.getFont().deriveFont(Font.BOLD, 22f));
        top.add(nameLabel, BorderLayout.CENTER);

        card.add(top, BorderLayout.NORTH);

        // Center: image + breed
        JPanel center = new JPanel();
        center.setOpaque(false);
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));

        imageHolder = new JLabel();
        imageHolder.setPreferredSize(new Dimension(220, 220));
        imageHolder.setHorizontalAlignment(SwingConstants.CENTER);
        imageHolder.setAlignmentX(Component.CENTER_ALIGNMENT);

        center.add(imageHolder);
        center.add(Box.createVerticalStrut(10));

        breedLabel = new JLabel("", SwingConstants.CENTER);
        breedLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        center.add(breedLabel);

        card.add(center, BorderLayout.CENTER);

        // Bottom: energy bar + buttons
        JPanel bottom = new JPanel();
        bottom.setOpaque(false);
        bottom.setLayout(new BoxLayout(bottom, BoxLayout.Y_AXIS));

        // Energy row
        JPanel energyRow = new JPanel(new BorderLayout(8, 0));
        energyRow.setOpaque(false);

        energyLabel = new JLabel("Energy: ");
        energyRow.add(energyLabel, BorderLayout.WEST);

        energyBar = new EnergyBar();
        energyBar.setPreferredSize(new Dimension(260, 10));
        energyRow.add(energyBar, BorderLayout.CENTER);

        bottom.add(energyRow);
        bottom.add(Box.createVerticalStrut(12));

        // Buttons row: Feed | Play | Sell
        JPanel buttonRow = new JPanel(new GridLayout(1, 3, 16, 0));
        buttonRow.setOpaque(false);
        buttonRow.setBorder(new EmptyBorder(0, 40, 0, 40));

        JButton feedButton = new JButton("Feed");
        JButton playButton = new JButton("Play");
        JButton sellButton = new JButton("Sell"); // no price text, just action

        buttonRow.add(feedButton);
        buttonRow.add(playButton);
        buttonRow.add(sellButton);

        bottom.add(buttonRow);

        card.add(bottom, BorderLayout.SOUTH);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (!"state".equals(evt.getPropertyName())) {
            return;
        }

        CollectionsState state = (CollectionsState) evt.getNewValue();
        CollectionsState.PetCardState selected = state.getSelectedPet();
        if (selected == null) {
            return;
        }

        // Safely pull fields from selected pet
        String name  = selected.getName();
        String breed = selected.getBreed();

        // If no name yet, show breed as main label
        if (name == null || name.isBlank()) {
            nameLabel.setText(breed);
            breedLabel.setText("");
        } else {
            nameLabel.setText(name);
            breedLabel.setText(breed);
        }

        levelLabel.setText("Lvl " + selected.getLevel());
        energyLabel.setText("Energy: " + selected.getEnergy() + "%");
        energyBar.setValue(selected.getEnergy());

        // Image
        Icon icon = selected.getPetVisual();
        if (icon instanceof ImageIcon) {
            Image img = ((ImageIcon) icon).getImage();
            Image scaled = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            imageHolder.setIcon(new ImageIcon(scaled));
        } else {
            imageHolder.setIcon(icon);
        }
    }

    public void showPet(String name, String breed, int level, int energy, Icon icon) {
        if (name == null || name.isBlank()) {
            nameLabel.setText(breed);
            breedLabel.setText("");
        } else {
            nameLabel.setText(name);
            breedLabel.setText(breed);
        }

        levelLabel.setText("Lvl " + level);
        energyLabel.setText("Energy: " + energy + "%");
        energyBar.setValue(energy);

        if (icon instanceof ImageIcon) {
            Image img = ((ImageIcon) icon).getImage();
            Image scaled = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            imageHolder.setIcon(new ImageIcon(scaled));
        } else {
            imageHolder.setIcon(icon);
        }
    }

    @Override
    public String getName() {
        return VIEW_NAME;
    }

    // Simple energy bar: light green fill based on percentage
    private static class EnergyBar extends JComponent {
        private int value = 0; // 0–100

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
            g2.setColor(new Color(144, 238, 144)); // light green
            g2.fillRect(0, 0, filled, h);

            g2.dispose();
        }
    }
}
