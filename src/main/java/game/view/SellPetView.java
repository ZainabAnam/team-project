package game.view;

import game.entity.Pet;
import javax.swing.*;
import java.awt.*;

public class SellPetView extends JDialog {
    private boolean confirmed = false;

    public SellPetView(Frame parent, Pet pet) {
        super(parent, "Confirm Sale", true);
        initializeUI(pet);
        pack();
        setLocationRelativeTo(parent);
    }

    private void initializeUI(Pet pet) {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // pet info
        JLabel message = new JLabel(
                "<html><center>Sell <b>" + pet.getName() + "</b>?<br>" +
                        "You will receive <font color='green'><b>" + pet.getSellingPrice() + "</b></font> coins.</center></html>",
                SwingConstants.CENTER
        );

        // pet details
        JLabel details = new JLabel(
                "<html><small>" + pet.getPetBreed() + " " + pet.getPetType() +
                        " | Level: " + pet.getAffectionLevel() + "</small></html>",
                SwingConstants.CENTER
        );
        details.setForeground(Color.GRAY);

        // sell button
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton confirmButton = new JButton("Sell for " + pet.getSellingPrice() + " coins");
        JButton cancelButton = new JButton("Keep Pet");

        confirmButton.addActionListener(e -> {
            confirmed = true;
            dispose();
        });

        cancelButton.addActionListener(e -> dispose());

        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);

        // configuration
        mainPanel.add(message, BorderLayout.NORTH);
        mainPanel.add(details, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    // use static method
    public static boolean showDialog(Frame parent, Pet pet) {
        SellPetView dialog = new SellPetView(parent, pet);
        dialog.setVisible(true);
        return dialog.isConfirmed();
    }
}
