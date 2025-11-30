package game.view;

import game.Constants;
import game.entity.Slot;
import game.interface_adapter.main_page.MainController;
import game.interface_adapter.main_page.MainState;
import game.interface_adapter.main_page.MainViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


/**
 * The View for the game's main screen.
 */
public class MainView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "Pet Clicker";
    private final MainViewModel mainViewModel;

    // Images
    private final Image backgroundImage = new ImageIcon(getClass().getResource("/images/MainBG.png")).getImage();;
    private final ImageIcon clickerImage = new ImageIcon(getClass().getResource("/images/Clicker.png"));
    private final ImageIcon clickerClickedImage = new ImageIcon(getClass().getResource("/images/ClickerClicked.png"));

    private final JButton clicker;
    private JLabel coinCountNumber = new JLabel(String.valueOf(Constants.INITIAL_COINS),  SwingConstants.CENTER);
    private JPanel coinCountPanel = getCoinCountPanel();

    private MainController mainController;

    public MainView(MainViewModel mainViewModel) {

        this.mainViewModel = mainViewModel;
        this.mainViewModel.addPropertyChangeListener(this);

        setPreferredSize(new Dimension(720, 540));
        setLayout(null);

        // Setting up/adding the main clicker.
        this.clicker = getClicker();
        clicker.setBounds(260, 50, 200, 200); // x, y, width, height
        add(clicker);

        // Setting up and adding the coin count panel
        clicker.setBounds(260, 50, 90, 50);
        add(coinCountPanel);

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

        clicker.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource() == clicker) {
                            final MainState state =  mainViewModel.getState();

                            mainController.execute();
                        }
                    }
                }
        );


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

    private JPanel getCoinCountPanel() {
        final JPanel coinCountPanel = new JPanel() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(90, 50);
            }
        };

        coinCountPanel.setVisible(true);

        final JLabel coinCountLabel = new JLabel("Coin Count");
        coinCountLabel.setFont(new Font("Arial", Font.BOLD, 16));
        coinCountPanel.add(coinCountLabel);
        coinCountNumber.setFont(new Font("Arial", Font.BOLD, 20));
        coinCountPanel.add(coinCountNumber);

        return coinCountPanel;
    }

    // Customizing other menu buttons
    private JButton getMenuButton(JButton menuButton) {
        menuButton.setSize(150, 50);
        menuButton.setBackground(Color.orange);
        menuButton.setBorderPainted(false);
        return menuButton;
    }

    public String getViewName() {
        return viewName;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    private void updateCoinCount() {
        final MainState state = mainViewModel.getState();
        coinCountNumber.setText(String.valueOf(state.getNewCoinCount()));
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
            final MainState state = (MainState) evt.getNewValue();
            updateCoinCount();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
