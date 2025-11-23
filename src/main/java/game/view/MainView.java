package game.view;

import game.entity.Slot;

import javax.swing.*;
import java.awt.*;


/**
 * The View for the game's main screen.
 */
public class MainView extends JPanel{
    private final String viewName = "Pet Clicker";

    // Images
    private final Image backgroundImage = new ImageIcon(getClass().getResource("/images/MainBG.png")).getImage();;
    private final ImageIcon clickerImage = new ImageIcon(getClass().getResource("/images/Clicker.png"));
    private final ImageIcon clickerClickedImage = new ImageIcon(getClass().getResource("/images/ClickerClicked.png"));

    public MainView() {

        setPreferredSize(new Dimension(720, 540));
        setLayout(null);

        // Setting up/adding the main clicker.
        JButton clicker = getClicker();
        clicker.setBounds(260, 50, 200, 200); // x, y, width, height
        add(clicker);

        // Instantiating Slots.
        Slot slot1 = new Slot(true);  // unlocked at start
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
        JButton shop = new JButton("Shop");
        shop = getMenuButton(shop);
        JButton collections = new JButton("Collections");
        collections = getMenuButton(collections);
        JButton save =  new JButton("Save");
        save = getMenuButton(save);

        final JPanel menuButtons = new JPanel();
        menuButtons.setOpaque(false);
        menuButtons.add(shop);
        menuButtons.add(collections);
        menuButtons.add(save);
        menuButtons.setLayout(new GridLayout(1, 3, 80, 0));

        menuButtons.setBounds(55, 480, 610, 50);
        add(menuButtons);


    }

    // Making a clicker JButton with custom graphic.
    private JButton getClicker() {
        final JButton clicker;
        clicker = new JButton();
        clicker.setIcon(clickerImage);
        clicker.setBorderPainted(false);
        clicker.setContentAreaFilled(false);
        clicker.setFocusPainted(false);
        clicker.setPressedIcon(clickerClickedImage);
        return clicker;
    }

    // Customizing other menu buttons
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
