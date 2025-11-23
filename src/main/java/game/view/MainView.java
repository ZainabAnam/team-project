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

    // Buttons
    private final JButton clicker;
    private final Slot slot1;
    private final Slot slot2;
    private final Slot slot3;
    private final Slot slot4;

    public MainView() {

        setPreferredSize(new Dimension(720, 540));
        setLayout(null);

        // Instantiating Slots
        this.slot1 = new Slot(true);  // unlocked at start
        this.slot2 = new Slot(false);
        this.slot3 = new Slot(false);
        this.slot4 = new Slot(false);

        clicker = getClicker();

        clicker.setBounds(260, 50, 200, 200); // x, y, width, height
        add(clicker);

        final JPanel slotsPanel = new JPanel();
        slotsPanel.setOpaque(false);
        slotsPanel.add(slot1);
        slotsPanel.add(slot2);
        slotsPanel.add(slot3);
        slotsPanel.add(slot4);
        slotsPanel.setLayout(new GridLayout(1, 4, 40, 0));

        slotsPanel.setBounds(110, 280, 520, 150);
        add(slotsPanel);
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
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
