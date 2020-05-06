package viewing.spielRahmen.einstellungenRahmen;

import controller.Controller;

import javax.swing.*;
import java.awt.*;

public class VerbesserungenRahmen extends JFrame {
    public VerbesserungenRahmen(Controller pController) {
        //deklaration aller Label und Buttons
        JLabel bestellungsPunkte = new JLabel("Bestellung PKT: " + pController.zutatenPunkte(), SwingConstants.CENTER); JLabel brauPunkte = new JLabel("Brau PKT: " + pController.drinkPunkte(), SwingConstants.CENTER);
        JButton hopfenUp = new JButton("Hopfen + " + pController.getUpgradePunkte("Hopfen")); JButton bierUp = new JButton("Bier + " + pController.getUpgradePunkte("Bier"));
        JButton malzUp = new JButton("Malz + " + pController.getUpgradePunkte("Malz")); JButton bockUp = new JButton("BockBier + " + pController.getUpgradePunkte("BockBier"));
        JButton zitronenUp = new JButton("Zitrone + " + pController.getUpgradePunkte("Zitrone")); JButton radlerUp = new JButton("Radler + " + pController.getUpgradePunkte("Radler"));
        JButton wasserUp = new JButton("Wasser + " + pController.getUpgradePunkte("Wasser")); JButton limonUp = new JButton("Limonade + " + pController.getUpgradePunkte("Limonade"));
        JLabel infoLabel1 = new JLabel("Jede Stufe gibt dir +1 ", SwingConstants.RIGHT); JLabel infoLabel2 = new JLabel("Verbesserungs Punkt.");
        JLabel fakeLabel1 = new JLabel(); JButton annehmenButton = new JButton("Annehmen");

        //Action Listener Sammlung
        hopfenUp.addActionListener(e ->{pController.upgrade("Hopfen"); bestellungsPunkte.setText("Bestellung PKT: " + pController.zutatenPunkte()); hopfenUp.setText("Hopfen + " + pController.getUpgradePunkte("Hopfen"));});
        malzUp.addActionListener(e ->{pController.upgrade("Malz"); bestellungsPunkte.setText("Bestellung PKT: " + pController.zutatenPunkte()); malzUp.setText("Malz + " + pController.getUpgradePunkte("Malz"));});
        zitronenUp.addActionListener(e ->{pController.upgrade("Zitrone"); bestellungsPunkte.setText("Bestellung PKT: " + pController.zutatenPunkte()); zitronenUp.setText("Zitrone + " + pController.getUpgradePunkte("Zitrone"));});
        wasserUp.addActionListener(e ->{pController.upgrade("Wasser"); bestellungsPunkte.setText("Bestellung PKT: " + pController.zutatenPunkte()); wasserUp.setText("Wasser + " + pController.getUpgradePunkte("Wasser"));});
        bierUp.addActionListener(e ->{pController.upgrade("Bier"); brauPunkte.setText("Brau PKT: " + pController.drinkPunkte()); bierUp.setText("Bier + " + pController.getUpgradePunkte("Bier"));});
        bockUp.addActionListener(e ->{pController.upgrade("BockBier"); brauPunkte.setText("Brau PKT: " + pController.drinkPunkte()); bockUp.setText("Bockbier + " + pController.getUpgradePunkte("BockBier"));});
        radlerUp.addActionListener(e ->{pController.upgrade("Radler"); brauPunkte.setText("Brau PKT: " + pController.drinkPunkte()); radlerUp.setText("Radler + " + pController.getUpgradePunkte("Radler"));});
        limonUp.addActionListener(e ->{pController.upgrade("Limonade"); brauPunkte.setText("Brau PKT: " + pController.drinkPunkte()); limonUp.setText("Limonade + " + pController.getUpgradePunkte("Limonade"));});

        annehmenButton.addActionListener (e ->  dispose());
        //Rahmen allgemein Einstellungen
        this.setLayout(new GridLayout(7, 2,2,2));
        this.add(bestellungsPunkte); this.add(brauPunkte);
        this.add(hopfenUp); this.add(bierUp);
        this.add(malzUp); this.add(bockUp);
        this.add(zitronenUp); this.add(radlerUp);
        this.add(wasserUp); this.add(limonUp);
        this.add(infoLabel1); this.add(infoLabel2);
        this.add(fakeLabel1); this.add(annehmenButton);

        this.setLocation(600, 300);
        this.setTitle("Verbesserungen");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(300, 300);
        this.setResizable(false);
        this.setVisible(true);
    }
}
