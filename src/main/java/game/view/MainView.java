package game.view;

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
    private final JButton slot_1;
    private final JButton slot_2;
    private final JButton slot_3;
    private final JButton slot_4;

    // Panels
    private final JPanel coinCount;

    // private MainController mainController = null;

    public MainView() {
        // this.MainViewModel = mainViewModel;
        this.backgroundImage = new ImageIcon(getClass().getResource("/images/MainBG.png")).getImage();
        this.clickerImage = new ImageIcon(getClass().getResource("/images/Clicker.png"));
        this.clickerClickedImage = new ImageIcon(getClass().getResource("/images/ClickerClicked.png"));
        this.slotImage = new ImageIcon(getClass().getResource("/images/Slot.png"));
        this.lockedSlotImage = new ImageIcon(getClass().getResource("/images/SlotLocked.png"));

        setPreferredSize(new Dimension(720, 540));

        clicker = getClicker();
        clicker.setBounds(260, 400, 200, 200); // x, y, width, height
        add(clicker);

        coinCount = getCoinCountPanel();
        coinCount.setBounds(600, 400, 90, 50);
//        add(coinCount);
//        NOTE: I haven't been able to align the panel onscreen without offsetting
//        the clicker.  Will fix when I can.

        final JPanel slotsPanel = new JPanel();
        slot_1 = new JButton("Slot 1");
        slot_2 = new JButton("Slot 2");
        slot_3 = new JButton("Slot 3");
        slot_4 = new JButton("Slot 4");
        slotsPanel.add(slot_1);
        slotsPanel.add(slot_2);
        slotsPanel.add(slot_3);
        slotsPanel.add(slot_4);
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

    private JPanel getCoinCountPanel() {
        final JPanel coinCountPanel = new JPanel() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(90, 50);
            }
        };

        coinCountPanel.setVisible(true);
        coinCountPanel.setLayout(new BoxLayout(coinCountPanel, BoxLayout.Y_AXIS));

        final JLabel coinCountLabel = new JLabel("Coin Count");
        coinCountLabel.setFont(new Font("Arial", Font.BOLD, 16));
        coinCountPanel.add(coinCountLabel);

        JLabel coinCountNumber = new JLabel("0",  SwingConstants.CENTER);
        coinCountNumber.setFont(new Font("Arial", Font.BOLD, 20));
        coinCountPanel.add(coinCountNumber);

        return coinCountPanel;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
