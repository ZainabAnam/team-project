package game.view;

import game.interface_adapter.GameStart.GameStartController;
import game.interface_adapter.GameStart.GameStartState;
import game.interface_adapter.GameStart.GameStartViewModel;
import game.interface_adapter.main_page.MainViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The view for the game start screen.
 */
public class GameStartView extends JFrame implements ActionListener, PropertyChangeListener {
    private final String viewName = "start screen";

    private final GameStartViewModel loadViewModel;
    private GameStartController gameStartController;
    private final JButton newGameButton;
    private final JButton loadButton;

    public GameStartView(GameStartViewModel loadViewModel, GameStartController gameStartController) {
        this.loadViewModel = loadViewModel;
        this.loadViewModel.addPropertyChangeListener(this);
        this.gameStartController = gameStartController;

        setTitle("Pet Clicker");
        setSize(360, 270);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        final JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));

        final JLabel title = new JLabel("Pet Clicker");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        newGameButton = new JButton("New Game");
        newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        loadButton = new JButton("Load Game");
        loadButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        main.add(Box.createVerticalStrut(30));
        main.add(title);
        main.add(Box.createVerticalStrut(40));
        main.add(newGameButton);
        main.add(Box.createVerticalStrut(20));
        main.add(loadButton);
        main.add(Box.createVerticalGlue());

        add(main);

        newGameButton.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    if (evt.getSource().equals(newGameButton)) {
                        gameStartController.newGameExecute();
                    }
                }
            }
        );

        loadButton.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    if (evt.getSource().equals(loadButton)) {
                        gameStartController.loadGameExecute();
                    }
                }
            }
        );
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Click" + e.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final GameStartState state = loadViewModel.getState();
        if (state.isLoadSuccessful()) {
            MainViewModel mainViewModel = new MainViewModel();
            MainView mainView = new MainView(mainViewModel);
            mainView.setVisible(true);
            this.setVisible(false);
        }
        if (state.getErrorMessage() != null) {
            JOptionPane.showMessageDialog(this, state.getErrorMessage(),
                    "Load Error", JOptionPane.ERROR_MESSAGE);
            }
    }

    public void setGameStartController (GameStartController gameStartController) { this.gameStartController = gameStartController; }
}
