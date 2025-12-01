package game.app;

import game.data_access.UserDataAccessObjectInterface;
import game.entity.User;
import game.interface_adapter.GameStart.GameStartController;
import game.interface_adapter.GameStart.GameStartPresenter;
import game.interface_adapter.GameStart.GameStartViewModel;
import game.use_case.GameStart.GameStartInteractor;
import game.view.GameStartView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
//        JFrame frame = new JFrame("Pet Clicker");
//        frame.setContentPane(new MainView());
//        frame.pack();
//        frame.setLocationRelativeTo(null);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setVisible(true);
        UserDataAccessObjectInterface userDAO = new UserDataAccessObjectInterface() {
            @Override
            public User newUser() {
                System.out.println("TEST: newUser called");
                return new User();
            }

            @Override
            public User loadUser() {
                System.out.println("TEST: loadUser called");
                return new User();
            }

            @Override
            public void saveUser(User user) {
                System.out.println("TEST: saveUser called");
            }
        };
        SwingUtilities.invokeLater(() -> {
            GameStartViewModel load = new GameStartViewModel();
            GameStartPresenter presenter = new GameStartPresenter(load);
            GameStartInteractor interactor = new GameStartInteractor(userDAO, presenter);
            GameStartController controller = new GameStartController(interactor);
            GameStartView view = new GameStartView(load, controller);
            view.setVisible(true);
        });
    }
}

