package game.view;

import game.Constants;
import game.entity.Slot;
import game.interface_adapter.GameSave.SaveController;
import game.interface_adapter.GameSave.SaveState;
import game.interface_adapter.GameSave.SaveViewModel;
import game.interface_adapter.SlotAddPet.SelectPet.SelectPetController;
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
    private final String viewName = "main";
    private final MainViewModel mainViewModel;
    // private final ViewManagerModel viewManagerModel;

    private final SaveViewModel saveViewModel;
    private SaveController saveController;

    // Images
    private final Image backgroundImage =
            new ImageIcon(getClass().getResource("/images/MainBG.png")).getImage();
    private final ImageIcon clickerImage =
            new ImageIcon(getClass().getResource("/images/Clicker.png"));
    private final ImageIcon clickerClickedImage =
            new ImageIcon(getClass().getResource("/images/ClickerClicked.png"));

    private final JButton clicker;
    private JLabel coinCountNumber = new JLabel(String.valueOf(Constants.INITIAL_COINS),  SwingConstants.CENTER);
    private JPanel coinCountPanel = getCoinCountPanel();

    //private MainController mainController;
    private SelectPetController selectPetController;

    public MainView(MainViewModel mainViewModel, SaveViewModel saveViewModel, SaveController  saveController) {

        this.mainViewModel = mainViewModel;

        this.saveViewModel = saveViewModel;
        this.saveController = saveController;
        this.saveViewModel.addPropertyChangeListener(this);

        //this.selectPetController = selectPetController;
        //this.mainViewModel.addPropertyChangeListener(this);
        //this.viewManagerModel = viewManagerModel;

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
        Slot slot1 = new Slot(true, 1);
        Slot slot2 = new Slot(false, 2);
        Slot slot3 = new Slot(false, 3);
        Slot slot4 = new Slot(false, 4);

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
            //viewManagerModel.setState(CollectionsViewModel.VIEW_NAME);
            //viewManagerModel.firePropertyChange();
        });

        final JPanel menuButtons = new JPanel();
        menuButtons.setOpaque(false);
        menuButtons.add(shop);
        menuButtons.add(collections);
        menuButtons.add(save);
        menuButtons.setLayout(new GridLayout(1, 3, 80, 0));

        menuButtons.setBounds(55, 480, 610, 50);
        add(menuButtons);

        slot1.addActionListener(e -> {
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource().equals(slot1)) {
                        selectPetController.execute(slot1);
                    }
                }
            };
        });
        slot2.addActionListener(e -> {
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource().equals(slot2)) {
                        selectPetController.execute(slot2);
                    }
                }
            };
        });
        slot3.addActionListener(e -> {
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource().equals(slot3)) {
                        selectPetController.execute(slot3);
                    }
                }
            };
        });
        slot4.addActionListener(e -> {
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource().equals(slot4)) {
                        selectPetController.execute(slot4);
                    }
                }
            };
        });

        save.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(save)) {
                            saveController.execute();
                        }
                    }
                }
        );

    }

//        clicker.addActionListener(
//                new ActionListener() {
//        public void actionPerformed(ActionEvent evt) {
//            if (evt.getSource() == clicker) {
//                final MainState state =  mainViewModel.getState();
//
//                mainController.execute();
//            }
//        }
//    }
//        );


    private JButton getClicker() {
        final JButton clicker = new JButton();
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

//    public void setMainController(MainController mainController) {
//        this.mainController = mainController;
//    }

//    private void updateCoinCount() {
//        final MainState state = mainViewModel.getState();
//        coinCountNumber.setText(String.valueOf(state.getNewCoinCount()));
//    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        System.out.println(evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        //final SelectPetState selectPetState = selectViewModel.getState();
        final SaveState state = saveViewModel.getState();
        if (state.isSaveSuccessful()){
            JOptionPane.showMessageDialog(
                    this,
                    "Game saved successfully!",
                    "Save Complete",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
        if (state.getErrorMessage() != null) {
            JOptionPane.showMessageDialog(
                    this,
                    "Failed to save: " + state.getErrorMessage(),
                    "Save Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}