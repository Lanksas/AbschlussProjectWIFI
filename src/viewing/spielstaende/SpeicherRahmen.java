package viewing.spielstaende;

import controller.Controller;
import viewing.spielRahmen.MainRahmen;
import viewing.spielRahmen.einstellungenRahmen.OptionenRahmen;

import javax.swing.*;
import java.awt.*;

public class SpeicherRahmen extends JFrame {
    public SpeicherRahmen(Controller pController, MainRahmen mr) {
        JLabel infoLabel = new JLabel("Bitte Namen eingeben:");
        JTextField nameField = new JTextField();
        JButton saveButton = new JButton("Speichern");
        JButton abbrechenButton = new JButton("Abbrechen");
        Panel buttonPanel = new Panel();
        buttonPanel.setLayout(new GridLayout(1,2));
        buttonPanel.add(saveButton);
        buttonPanel.add(abbrechenButton);

        abbrechenButton.addActionListener(e -> {dispose(); new OptionenRahmen(pController, mr); });
        saveButton.addActionListener(e -> { pController.newSave(nameField.getText()); dispose(); });

        this.setTitle("Speichern");
        this.setLayout(new GridLayout(3, 1));
        this.add(infoLabel);
        this.add(nameField);
        this.add(buttonPanel);
        this.setSize(250, 200);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
