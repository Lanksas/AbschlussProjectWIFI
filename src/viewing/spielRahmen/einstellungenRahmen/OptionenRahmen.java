package viewing.spielRahmen.einstellungenRahmen;

import controller.Controller;
import viewing.spielRahmen.MainRahmen;
import viewing.spielstaende.LadeRahmen;
import viewing.spielstaende.SpeicherRahmen;

import javax.swing.*;
import java.awt.*;

public class OptionenRahmen extends JFrame {
    public OptionenRahmen(Controller pController, MainRahmen mr) {
        JButton ladenButton = new JButton("Laden"); JButton saveButton = new JButton("Speichern"); JButton annehmenButton = new JButton("Fertig");
        ladenButton.setFont(new Font("",Font.PLAIN ,20));
        saveButton.setFont(new Font("",Font.PLAIN ,20));
        annehmenButton.setFont(new Font("",Font.PLAIN ,20));

        saveButton.addActionListener(e-> {new SpeicherRahmen(pController, mr); this.dispose();});
        ladenButton.addActionListener(e -> {new LadeRahmen(pController, mr); this.dispose();});
        annehmenButton.addActionListener(e ->  this.dispose());

        //Rahmen allgemein Einstellungen
        this.setLayout(new GridLayout(1, 3,3,3));
        this.add(ladenButton); this.add(saveButton); this.add(annehmenButton);

        this.setLocation(600, 300);
        this.setTitle("Optionen");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(size.width * 20 / 100,size.height * 8 / 100);
        this.setResizable(false);
        this.setVisible(true);
    }
}
