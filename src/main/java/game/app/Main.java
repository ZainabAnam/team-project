//package game.app;
//
//import game.interface_adapter.ViewManagerModel;
//import game.view.MainView;
//
//import javax.swing.*;
//
//public class Main {
//    public static void main(String[] args) {
//        JFrame frame = new JFrame("Pet Clicker");
//        frame.setContentPane(new MainView(new ViewManagerModel()));
//        frame.pack();
//        frame.setLocationRelativeTo(null);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setVisible(true);
//    }
//}

package game.app;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        AppBuilder builder = new AppBuilder()
                .addMainView()
                .addShopView()
                .addShopUseCases()
                .addRenamePetView()
                .addSellPetView()
                .addCollectionsView();
        JFrame app = builder.build();

        app.setSize(1000, 800);
        app.setLocationRelativeTo(null);
        app.setResizable(false);
        app.setVisible(true);
    }
}