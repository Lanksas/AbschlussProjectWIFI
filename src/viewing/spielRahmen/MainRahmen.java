package viewing.spielRahmen;

import controller.Controller;
import viewing.spielRahmen.einstellungenRahmen.OptionenRahmen;
import viewing.spielRahmen.einstellungenRahmen.VerbesserungenRahmen;

import javax.swing.*;
import java.awt.*;

public class MainRahmen extends JFrame {
    private JLabel hopfenMengeLabel,
            malzMengeLabel,
            zitroneMengeLabel,
            wasserMengeLabel,
            mengeBierLabel,
            mengeBockLabel,
            mengeRadlerLabel,
            mengeLimoLabel,
            anzahlAbgeschlossenBestellungen,
            anzahlAbgeschlossenBrauungen;

    private void aktualisierung(Controller cr){
        hopfenMengeLabel.setText(cr.hopfenBestand());
        malzMengeLabel.setText(cr.malzBestand());
        zitroneMengeLabel.setText(cr.zitroneBestand());
        wasserMengeLabel.setText(cr.wasserBestand());
        mengeBierLabel.setText(cr.bierBestand());
        mengeBockLabel.setText(cr.bockBestand());
        mengeRadlerLabel.setText(cr.radlerBestand());
        mengeLimoLabel.setText(cr.limoBestand());
        anzahlAbgeschlossenBestellungen.setText(String.valueOf(cr.anzeigeZutatenAufgabe()));
        anzahlAbgeschlossenBrauungen.setText(String.valueOf(cr.anzeigeDrinkAbgegeben()));
    }

    public MainRahmen(Controller cr) {
        // OptionenRahmen optionenRef;
        // VerbesserungenRahmen verbesserungsRef;

        //JPanel
        JPanel northPanel1 = new JPanel();
        JPanel centerPanel1 = new JPanel();
        JPanel southPanel1 = new JPanel();
        JPanel southPanel2 = new JPanel();

        //North Panel 1 - Zutaten
        northPanel1.setLayout(new GridLayout(4,4,0,3));

        hopfenMengeLabel = new JLabel(String.valueOf(cr.hopfenBestand()), SwingConstants.CENTER); JLabel hopfenNamenLabel = new JLabel("Hopfen", SwingConstants.CENTER),  hopfenErhoehung = new JLabel("Grundertrag: " + cr.hopfenErtrag()); JButton hopfenKaufen = new JButton("Hopfen bestellen");
        malzMengeLabel = new JLabel(String.valueOf(cr.malzBestand()), SwingConstants.CENTER); JLabel malzNamenLabel = new JLabel("Malz", SwingConstants.CENTER),  malzErhoehung = new JLabel("Grundertrag: " + cr.malzErtrag()); JButton malzKaufen = new JButton("Malz bestellen");
        zitroneMengeLabel = new JLabel(String.valueOf(cr.zitroneBestand()), SwingConstants.CENTER); JLabel zitronenNamenLabel = new JLabel("Zitrone", SwingConstants.CENTER),  zitroneErhoehung = new JLabel("Grundertrag: " + cr.zitroneErtrag()); JButton zitronenKaufen = new JButton("Zitronen bestellen");
        wasserMengeLabel = new JLabel(String.valueOf(cr.wasserBestand()), SwingConstants.CENTER); JLabel wasserNamenLabel = new JLabel("Wasser", SwingConstants.CENTER),  wasserErhoehung = new JLabel("Grundertrag: " + cr.wasserErtrag()); JButton wasserNachfuellen = new JButton("Wasser bestellen");

        northPanel1.add(hopfenMengeLabel); northPanel1.add(hopfenNamenLabel); northPanel1.add(hopfenErhoehung); northPanel1.add(hopfenKaufen);
        northPanel1.add(malzMengeLabel); northPanel1.add(malzNamenLabel); northPanel1.add(malzErhoehung); northPanel1.add(malzKaufen);
        northPanel1.add(zitroneMengeLabel); northPanel1.add(zitronenNamenLabel); northPanel1.add(zitroneErhoehung); northPanel1.add(zitronenKaufen);
        northPanel1.add(wasserMengeLabel); northPanel1.add(wasserNamenLabel); northPanel1.add(wasserErhoehung); northPanel1.add(wasserNachfuellen);

        //South Panel 1 - Getränke Liste
        southPanel1.setLayout(new GridLayout(2,8,0,25));

        //JLabel abgeschlossenBestellen = new JLabel("Bestellungen ", SwingConstants.RIGHT), erledigtLabel = new JLabel("erledigt: "), anzahlAbgeschlossenBestellungen = new JLabel(), fakeLabel2 = new JLabel();
        JLabel abgeschlossenBestellen = new JLabel("Stufe ", SwingConstants.RIGHT), erledigtLabel = new JLabel("Zutaten: "); anzahlAbgeschlossenBestellungen = new JLabel(String.valueOf(cr.anzeigeZutatenAufgabe()), SwingConstants.LEFT); JLabel fakeLabel2 = new JLabel();
        JLabel abgeschlossenBrauen = new JLabel("Stufe ", SwingConstants.RIGHT), ausgeliefertLabel = new JLabel("Getränke:"); anzahlAbgeschlossenBrauungen = new JLabel(String.valueOf(cr.anzeigeDrinkAbgegeben()), SwingConstants.LEFT); JLabel fakeLabel4 = new JLabel();
        JLabel bierLabel = new JLabel("Bier: ", SwingConstants.CENTER); mengeBierLabel = new JLabel(cr.bierBestand());
        JLabel bockLabel = new JLabel("Bockbier: ", SwingConstants.CENTER); mengeBockLabel = new JLabel(cr.bockBestand());
        JLabel radlerLabel = new JLabel("Radler: ", SwingConstants.CENTER); mengeRadlerLabel = new JLabel(cr.radlerBestand());
        JLabel limoLabel = new JLabel("Limonade: ", SwingConstants.CENTER); mengeLimoLabel = new JLabel(cr.limoBestand());

        southPanel1.add(abgeschlossenBestellen); southPanel1.add(erledigtLabel); southPanel1.add(anzahlAbgeschlossenBestellungen); southPanel1.add(fakeLabel2);
        southPanel1.add(abgeschlossenBrauen); southPanel1.add(ausgeliefertLabel); southPanel1.add(anzahlAbgeschlossenBrauungen); southPanel1.add(fakeLabel4);
        southPanel1.add(bierLabel); southPanel1.add(mengeBierLabel); southPanel1.add(bockLabel); southPanel1.add(mengeBockLabel);
        southPanel1.add(radlerLabel); southPanel1.add(mengeRadlerLabel); southPanel1.add(limoLabel); southPanel1.add(mengeLimoLabel);

        //South Panel 2 - Brauen und Optionen
        southPanel2.setLayout(new GridLayout(2,4,2,3));

        JButton bierBrauenButton = new JButton("Bier brauen"); JButton bockbierBrauenButton = new JButton("Bockbier brauen"); JButton radlerBrauenButton = new JButton("Radler brauen"); JButton limonadeBrauenButton = new JButton("Mische Limonade");
        JButton abgabeButton = new JButton("Abschließen"); JButton upgradeButton = new JButton("Verbesserungen"); JButton optionenButton = new JButton("Optionen"); JButton exitButton = new JButton("Exit");

        southPanel2.add(bierBrauenButton); southPanel2.add(bockbierBrauenButton); southPanel2.add(radlerBrauenButton); southPanel2.add(limonadeBrauenButton);
        southPanel2.add(abgabeButton); southPanel2.add(upgradeButton); southPanel2.add(optionenButton); southPanel2.add(exitButton);

        //Sammlung Action-Events
        //KAUFEN BUTTONS
        hopfenKaufen.addActionListener(e ->{ cr.hb(); hopfenMengeLabel.setText(cr.hopfenBestand());});
        malzKaufen.addActionListener(e ->{ cr.mb(); malzMengeLabel.setText(cr.malzBestand());});
        zitronenKaufen.addActionListener(e ->{ cr.zb(); zitroneMengeLabel.setText(cr.zitroneBestand());});
        wasserNachfuellen.addActionListener(e ->{ cr.wb(); wasserMengeLabel.setText(cr.wasserBestand());});

        //BRAUEN BUTTONS
        bierBrauenButton.addActionListener(e ->{ cr.bh(); mengeBierLabel.setText(cr.bierBestand()); aktualisierung(cr);});
        bockbierBrauenButton.addActionListener(e ->{ cr.bb(); mengeBockLabel.setText(cr.bockBestand()); aktualisierung(cr);});
        radlerBrauenButton.addActionListener(e ->{ cr.rh(); mengeRadlerLabel.setText(cr.radlerBestand()); aktualisierung(cr);});
        limonadeBrauenButton.addActionListener(e ->{ cr.lh(); mengeLimoLabel.setText(cr.limoBestand()); aktualisierung(cr);});

        //ABSCHLUSS BUTTON
        abgabeButton.addActionListener(e ->{cr.abschluss(); aktualisierung(cr);});
        optionenButton.addActionListener(e -> new OptionenRahmen(cr, this));
        upgradeButton.addActionListener(e -> new VerbesserungenRahmen(cr));
        exitButton.addActionListener(cr);

        //Center Panel 1 - Aufträge
        centerPanel1.setLayout(new GridLayout(1,2,3,0));

        if(!cr.getWirdGeladen()) {
            centerPanel1.add(cr.zeigeAufgabenZutaten());
            centerPanel1.add(cr.zeigeAufgabenDrinks());
        }else{
            centerPanel1.add(cr.ladeAufgabenZutaten());
            centerPanel1.add(cr.ladeAufgabenDrink());
            cr.ladeAufgabenListen();
            this.aktualisierung(cr);
            cr.setWirdGeladen();
        }

        //Rahmen allgemein Einstellungen
        this.setLayout(new GridLayout(4, 1));
        this.add(northPanel1);
        this.add(centerPanel1);
        this.add(southPanel1);
        this.add(southPanel2);

        this.setLocation(600, 300);
        this.setTitle("Falkenstein in Osttirol");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(700, 500);
        this.setResizable(false);
        this.setVisible(true);
    }
}
