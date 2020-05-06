package viewing.spielstaende;

import controller.Controller;
import viewing.spielRahmen.MainRahmen;
import viewing.spielRahmen.einstellungenRahmen.OptionenRahmen;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class LadeRahmen extends JFrame {
    public LadeRahmen(Controller cr, MainRahmen mr) {

        JPanel northPanel = new JPanel(new GridLayout(1, 1));
        JPanel southPanel = new JPanel(new GridLayout(2, 1));

        List saveStateList = new List();
        try {
           File f = new File("data/");
           String[] paths = f.list();
            assert paths != null;
            for(String path:paths){
                saveStateList.add(path);
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        JButton ladenButton = new JButton("Laden");
        ladenButton.setFont(new Font("",Font.PLAIN,35));
        JButton backButton = new JButton("Zurück");
        backButton.setFont(new Font("",Font.PLAIN,35));

        northPanel.add(saveStateList);
        saveStateList.setMultipleMode(false);
      //  southPanel.add(new JLabel()); southPanel.add(new JLabel());
        southPanel.add(ladenButton); southPanel.add(backButton);

        ladenButton.addActionListener(e -> {
            if(saveStateList.getSelectedItem() != null) {
                cr.ladeSave(saveStateList.getSelectedItem(), mr);
                LadeRahmen.this.dispose();
            }
        });

        if(cr.getGeladenVonOptionen()) {
            backButton.addActionListener(e -> {this.dispose(); new OptionenRahmen(cr, mr); });
        }else {
            backButton.addActionListener(e -> {
                cr.back();
                this.dispose();
            });
        }
        this.setLayout(new GridLayout(2,1));
        this.add(northPanel);
        this.add(southPanel);

        this.setLocation(600, 300);
        this.setTitle("Laden");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(250, 250);
        this.setResizable(false);
        this.setVisible(true);
    }
}