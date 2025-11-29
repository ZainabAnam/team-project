package game.view;

import game.interface_adapter.collections.CollectionsController;
import game.interface_adapter.collections.CollectionsViewModel;
import game.interface_adapter.collections.CollectionsState;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;
import java.lang.reflect.Array;
import java.util.List;

public class CollectionsView extends JPanel implements PropertyChangeListener {

    private final CollectionsViewModel viewModel;
    private CollectionsController controller;

    private Image backgroundImage;

    private final JPanel petsRowPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 60, 10));

    private final java.util.List<ItemCard> petCards = new java.util.ArrayList<>();

    private ItemCard foodCannedCard;
    private ItemCard foodKibbleCard;
    private ItemCard foodHomeCookedCard;

    private ItemCard toyChewCard;
    private ItemCard toyTossCard;
    private ItemCard toyPlushCard;

    private static final String[] petList = {
            "Labrador Retriever",
            "Golden Retriever",
            "French Bulldog",
            "Poodle",
            "Boxer",
            "Shih Tzu",
            "Beagle",
            "Pomeranian",
            "Siberian Husky",
            "German Shepherd",
            "Siamese",
            "British Shorthair",
            "Persian",
            "Russian Blue",
            "Ragdoll",
            "American Shorthair",
            "Sphynx",
            "Scottish Fold",
            "Maine Coon",
            "Bengal"
    };

    public CollectionsView(CollectionsViewModel viewModel,
                           CollectionsController controller) {
        this.viewModel = viewModel;
        this.controller = controller;

        this.viewModel.addPropertyChangeListener(this);

        setPreferredSize(new Dimension(1080, 1400));
        setLayout(null);

        setLayout(new BorderLayout());
        setBackground(new Color(230, 230, 230));
        setBorder(new EmptyBorder(20, 25, 20, 25));

        // ====== MAIN GRID (3 sections vertically) ======
        JPanel sections = new JPanel();
        sections.setOpaque(false);
        sections.setBorder(new EmptyBorder(20, 60, 40, 60));
        sections.setLayout(new BoxLayout(sections, BoxLayout.Y_AXIS));
        add(sections, BorderLayout.CENTER);

        // ----- Pets section -----
        petsRowPanel.setOpaque(false);
        petsRowPanel.setMinimumSize(new Dimension(0, 300));
        sections.add(makeSectionWithRow("Pets", petsRowPanel));
        sections.add(Box.createVerticalStrut(40));

        // ----- Pet Food section -----
        foodCannedCard = new ItemCard("Canned Food", "+10 energy", "x0");
        foodKibbleCard = new ItemCard("Kibble", "+30 energy", "x0");
        foodHomeCookedCard = new ItemCard("Home-Cooked", "+50 energy", "x0");

        foodCannedCard.setImageIcon(new ImageIcon(getClass().getResource("/game/img/shop/CannedFood.PNG")));
        foodKibbleCard.setImageIcon(new ImageIcon(getClass().getResource("/game/img/shop/kibble.PNG")));
        foodHomeCookedCard.setImageIcon(new ImageIcon(getClass().getResource("/game/img/shop/HomeCooked.PNG")));

        sections.add(makeSection("Pet Food",
                foodCannedCard, foodKibbleCard, foodHomeCookedCard));
        sections.add(Box.createVerticalStrut(60));

        // ----- Pet Toys section -----
        toyChewCard  = new ItemCard("Chew Toy", "+1 XP", "x0");
        toyTossCard  = new ItemCard("Toss Toy", "+2 XP", "x0");
        toyPlushCard = new ItemCard("Plush Toy", "+3 XP", "x0");

        toyChewCard.setImageIcon(new ImageIcon(getClass().getResource("/game/img/shop/chewToy.PNG")));
        toyTossCard.setImageIcon(new ImageIcon(getClass().getResource("/game/img/shop/tossToy.PNG")));
        toyPlushCard.setImageIcon(new ImageIcon(getClass().getResource("/game/img/shop/plushToy.PNG")));

        sections.add(makeSection("Pet Toys",
                toyChewCard, toyTossCard, toyPlushCard));
        sections.add(Box.createVerticalStrut(40));

        try {
            backgroundImage = new ImageIcon(
                    getClass().getResource("/images/MainBG.png")
            ).getImage();
        } catch (Exception e) {
            System.out.println("Background image not found");
        }

        // Optional: top title / nav bar
        JLabel title = new JLabel("Collection");
        title.setFont(title.getFont().deriveFont(Font.BOLD, 26f));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBorder(new EmptyBorder(0, 0, 20, 0));
        add(title, BorderLayout.NORTH);

        // TODO: Add Shop and Main button at SOUTH, wired to ViewManagerModel
    }

    // AppBuilder calls this after construction
    public void setCollectionsController(CollectionsController controller) {
        this.controller = controller;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    @Override
    public String getName() {
        // used by ViewManagerModel / AppBuilder
        return CollectionsViewModel.VIEW_NAME;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (!"state".equals(evt.getPropertyName())) {
            return;
        }
        CollectionsState state = (CollectionsState) evt.getNewValue();
        updateFromState(state);
    }

    public void load() {
        controller.loadCollections();
    }

    private void updateFromState(CollectionsState state) {
        java.util.List<CollectionsState.PetCardState> pets = state.getPets();
        if (pets != null) {
            for (CollectionsState.PetCardState p : pets) {
                ItemCard card = new ItemCard(
                        p.getName(),
                        p.getBreed(),
                        "Lvl " + p.getLevel()
                );
                card.setProgress(p.getEnergy());
                // card.setImageIcon(...); // TODO: Add image icon

                petCards.add(card);
                petsRowPanel.add(card);
            }
        }

        foodCannedCard.setQuantity("x" + state.getCannedFoodCount());
        foodKibbleCard.setQuantity("x" + state.getKibbleCount());
        foodHomeCookedCard.setQuantity("x" + state.getHomeCookedCount());

        toyChewCard.setQuantity("x" + state.getChewToyCount());
        toyTossCard.setQuantity("x" + state.getTossToyCount());
        toyPlushCard.setQuantity("x" + state.getPlushToyCount());

        revalidate();
        repaint();
    }

    private JPanel makeSectionWithRow(String titleText, JPanel rowPanel) {
        JPanel section = new JPanel(new BorderLayout());
        section.setOpaque(false);

        JPanel titleWrapper = new JPanel(new BorderLayout());
        titleWrapper.setOpaque(true);
        titleWrapper.setBackground(new Color(255, 255, 255, 200));
        JLabel label = new JLabel(titleText);
        label.setFont(label.getFont().deriveFont(Font.BOLD, 24f));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBorder(new EmptyBorder(4, 0, 8, 0));
        titleWrapper.add(label, BorderLayout.CENTER);

        section.add(titleWrapper, BorderLayout.NORTH);
        section.add(rowPanel, BorderLayout.CENTER);
        return section;
    }

    private JPanel makeSection(String titleText,
                               ItemCard card1, ItemCard card2, ItemCard card3) {
        JPanel section = new JPanel(new BorderLayout());
        section.setOpaque(false);

        JPanel titleWrapper = new JPanel(new BorderLayout());
        titleWrapper.setOpaque(true);
        titleWrapper.setBackground(new Color(255, 255, 255, 200));
        JLabel label = new JLabel(titleText);
        label.setFont(label.getFont().deriveFont(Font.BOLD, 24f));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBorder(new EmptyBorder(4, 0, 8, 0));
        titleWrapper.add(label, BorderLayout.CENTER);

        JPanel row = new JPanel(new GridLayout(1, 3, 40, 0));
        row.setOpaque(false);
        row.setPreferredSize(new Dimension(220, 200));

        row.add(card1);
        row.add(card2);
        row.add(card3);

        section.add(titleWrapper, BorderLayout.NORTH);
        section.add(row, BorderLayout.CENTER);
        return section;
    }

    // ===== ItemCard UI (circle + name + subtitle + optional quantity) =====
    private static class ItemCard extends JPanel {

        private final CirclePlaceholder circle;
        private final JLabel nameLabel;
        private final JLabel subtitleLabel;
        private final JLabel quantityLabel;
        private final JLabel levelLabel;
        private final JProgressBar progressBar;
        private final boolean isPet;

        ItemCard(String name, String subtitle, String quantity) {

            setLayout(new BorderLayout());
            setOpaque(false);
            setPreferredSize(new Dimension(220, 280));   // consistent card size
            setBorder(new EmptyBorder(10, 10, 10, 10));

            circle = new CirclePlaceholder();
            JPanel circleWrapper = new JPanel(new GridBagLayout());
            circleWrapper.setOpaque(false);
            circleWrapper.add(circle);

            quantityLabel = new JLabel(quantity);
            quantityLabel.setFont(quantityLabel.getFont().deriveFont(Font.BOLD, 12f));
            quantityLabel.setOpaque(true);
            quantityLabel.setBackground(new Color(0, 0, 0, 80));
            quantityLabel.setForeground(Color.WHITE);
            quantityLabel.setBorder(new EmptyBorder(2, 6, 2, 6));

            JPanel topRow = new JPanel(new BorderLayout());
            topRow.setOpaque(false);
            topRow.add(circleWrapper, BorderLayout.CENTER);
            topRow.add(quantityLabel, BorderLayout.NORTH);
            add(topRow, BorderLayout.CENTER);

            // is this card a pet?
            isPet = Arrays.asList(petList).contains(name);

            // bottom text
            nameLabel = new JLabel(name);
            subtitleLabel = new JLabel(subtitle);
            levelLabel = new JLabel();
            levelLabel.setFont(levelLabel.getFont().deriveFont(Font.PLAIN, 12f));

            if (isPet) {
                nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
                subtitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
                levelLabel.setHorizontalAlignment(SwingConstants.CENTER);

                // pets don’t use the quantity badge
                quantityLabel.setVisible(false);
            } else {
                nameLabel.setHorizontalAlignment(SwingConstants.LEFT);
                subtitleLabel.setHorizontalAlignment(SwingConstants.LEFT);
                levelLabel.setHorizontalAlignment(SwingConstants.LEFT);
            }

            nameLabel.setFont(nameLabel.getFont().deriveFont(Font.BOLD, 14f));
            subtitleLabel.setFont(subtitleLabel.getFont().deriveFont(Font.PLAIN, 12f));
            nameLabel.setForeground(new Color(40, 40, 40));
            subtitleLabel.setForeground(new Color(70, 70, 70));

            progressBar = new JProgressBar(0, 100);
            progressBar.setPreferredSize(new Dimension(80, 6));
            progressBar.setValue(0);

            JPanel bottom = new JPanel();
            bottom.setOpaque(false);
            bottom.setLayout(new BoxLayout(bottom, BoxLayout.Y_AXIS));
            bottom.add(Box.createVerticalStrut(6));
            bottom.add(nameLabel);
            bottom.add(subtitleLabel);
            bottom.add(levelLabel);
            bottom.add(Box.createVerticalStrut(6));

            JPanel barWrapper = new JPanel();
            barWrapper.setOpaque(false);

            if (isPet) {
                progressBar.setAlignmentX(Component.CENTER_ALIGNMENT);
                barWrapper.setAlignmentX(Component.CENTER_ALIGNMENT);
                barWrapper.add(progressBar);
            } else {
                barWrapper.setAlignmentX(Component.LEFT_ALIGNMENT);
                // no bar for non-pets
            }

            bottom.add(barWrapper);
            add(bottom, BorderLayout.SOUTH);
        }

        void setLevelText(String text) {
            if (isPet) {
                levelLabel.setText(text);
            }
        }

        void setQuantity(String quantity) {
            quantityLabel.setText(quantity);
        }

        void setProgress(int percent) {
            if (isPet) {
                progressBar.setValue(percent);
            }
        }

        void setImageIcon(Icon icon) {
            circle.setIcon(icon);
        }
    }

    // Simple circle placeholder (will show image later)
    private static class CirclePlaceholder extends JLabel {

        CirclePlaceholder() {
            setPreferredSize(new Dimension(120, 120));
            setHorizontalAlignment(SwingConstants.CENTER);
            setVerticalAlignment(SwingConstants.CENTER);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            int diameter = Math.min(getWidth(), getHeight()) - 4;
            int x = (getWidth() - diameter) / 2;
            int y = (getHeight() - diameter) / 2;

            g2.setColor(Color.WHITE);
            g2.fillOval(x, y, diameter, diameter);
            g2.setColor(Color.GRAY);
            g2.setStroke(new BasicStroke(2f));
            g2.drawOval(x, y, diameter, diameter);

            g2.dispose();
        }
    }
}
