package game.view;

import game.entity.Slot;
import game.interface_adapter.ViewManagerModel;
import game.interface_adapter.collections.CollectionsViewModel;

import javax.swing.*;
import java.awt.*;

/**
 * The View for the game's main screen.
 */
public class MainView extends JPanel {

    private final String viewName = "main";
    private final ViewManagerModel viewManagerModel;

    // Images
    private final Image backgroundImage =
            new ImageIcon(getClass().getResource("/images/MainBG.png")).getImage();
    private final ImageIcon clickerImage =
            new ImageIcon(getClass().getResource("/images/Clicker.png"));
    private final ImageIcon clickerClickedImage =
            new ImageIcon(getClass().getResource("/images/ClickerClicked.png"));

    public MainView(ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;

        setPreferredSize(new Dimension(720, 540));
        setLayout(null);

        // Setting up/adding the main clicker.
        JButton clicker = getClicker();
        clicker.setBounds(260, 50, 200, 200);
        add(clicker);

        // Instantiating Slots.
        Slot slot1 = new Slot(true);
        Slot slot2 = new Slot(false);
        Slot slot3 = new Slot(false);
        Slot slot4 = new Slot(false);

        final JPanel slotsPanel = new JPanel();
        slotsPanel.setOpaque(false);
        slotsPanel.add(slot1);
        slotsPanel.add(slot2);
        slotsPanel.add(slot3);
        slotsPanel.add(slot4);
        slotsPanel.setLayout(new GridLayout(1, 4, 40, 0));

        slotsPanel.setBounds(110, 280, 520, 150);
        add(slotsPanel);

        // Menu Buttons
        JButton shop = getMenuButton(new JButton("Shop"));
        JButton collections = getMenuButton(new JButton("Collection"));
        JButton save = getMenuButton(new JButton("Save"));

        // When user clicks "Collection", tell ViewManagerModel to switch to collections view
        collections.addActionListener(e -> {
            viewManagerModel.setState(CollectionsViewModel.VIEW_NAME);
            viewManagerModel.firePropertyChange();
        });

        final JPanel menuButtons = new JPanel();
        menuButtons.setOpaque(false);
        menuButtons.add(shop);
        menuButtons.add(collections);
        menuButtons.add(save);
        menuButtons.setLayout(new GridLayout(1, 3, 80, 0));

        menuButtons.setBounds(55, 480, 610, 50);
        add(menuButtons);
    }

    public String getViewName() {
        return viewName;
    }

    private JButton getClicker() {
        final JButton clicker = new JButton();
        clicker.setIcon(clickerImage);
        clicker.setBorderPainted(false);
        clicker.setContentAreaFilled(false);
        clicker.setFocusPainted(false);
        clicker.setPressedIcon(clickerClickedImage);
        return clicker;
    }

    private JButton getMenuButton(JButton menuButton) {
        menuButton.setSize(150, 50);
        menuButton.setBackground(Color.orange);
        menuButton.setBorderPainted(false);
        return menuButton;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
