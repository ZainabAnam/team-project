package game.view;

import game.entity.Slot;

import javax.swing.*;
import java.awt.*;


/**
 * The View for the game's main screen.
 */
public class MainView extends JPanel{
    private final String viewName = "Pet Clicker";
    // private final MainViewModel MainViewModel;

    // Images
    private final Image backgroundImage;
    private final ImageIcon clickerImage;
    private final ImageIcon clickerClickedImage;
    private final ImageIcon slotImage;
    private final ImageIcon lockedSlotImage;

    // Buttons
    private final JButton clicker;
    private final Slot slot1;
    private final Slot slot2;
    private final Slot slot3;
    private final Slot slot4;

    // private MainController mainController = null;

    public MainView() {
        // this.MainViewModel = mainViewModel;

        // Storing images in variables
        this.backgroundImage = new ImageIcon(getClass().getResource("/images/MainBG.png")).getImage();
        this.clickerImage = new ImageIcon(getClass().getResource("/images/Clicker.png"));
        this.clickerClickedImage = new ImageIcon(getClass().getResource("/images/ClickerClicked.png"));
        this.slotImage = new ImageIcon(getClass().getResource("/images/Slot.png"));
        this.lockedSlotImage = new ImageIcon(getClass().getResource("/images/SlotLocked.png"));
        // Slots
        this.slot1 = new Slot(true);  // unlocked at start
        this.slot2 = new Slot(false);
        this.slot3 = new Slot(false);
        this.slot4 = new Slot(false);

        setPreferredSize(new Dimension(720, 540));

        clicker = getClicker();
        clicker.setBounds(260, 200, 200, 200); // x, y, width, height
        add(clicker);

        final JPanel slotsPanel = new JPanel();
        slotsPanel.add(slot1);
        slotsPanel.add(slot2);
        slotsPanel.add(slot3);
        slotsPanel.add(slot4);
        slotsPanel.setLayout(new GridLayout(1, 4, 40, 0));

        add(slotsPanel);
    }

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
