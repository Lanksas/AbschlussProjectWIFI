package viewing.spielRahmen;

import controller.Controller;
import viewing.hintergrund.Hintergrund;

import javax.swing.*;
import java.awt.*;

public class StartRahmen extends JFrame {
    public StartRahmen() {
        //Layout befüllen
        JLabel fakeLabel1 = new JLabel(); JLabel titelLabel = new JLabel(""); JLabel fakeLabel2 = new JLabel();
        JLabel fakeLabel3 = new JLabel(); JLabel fakeLabel4 = new JLabel(); JLabel fakeLabel5 = new JLabel();
        JLabel fakeLabel6 = new JLabel(); JButton startButton = new JButton("Start"); JLabel fakeLabel7 = new JLabel();
        JLabel fakeLabel8 = new JLabel(); JLabel fakeLabel9 = new JLabel();  JLabel fakeLabel10 = new JLabel();
        JLabel fakeLabel11 = new JLabel(); JButton ladenButton = new JButton("Laden"); JLabel fakeLabel12 = new JLabel();

        //Controller Events
        Controller cr = new Controller();
        startButton.addActionListener(e -> {cr.start(); this.dispose();});
        ladenButton.addActionListener(e -> {cr.laden(); this.dispose();});

        //Hintergrund Image
        Image image = Toolkit.getDefaultToolkit().getImage("images/Start-Frame.jpg");
        Hintergrund mainPanel = new Hintergrund(new BorderLayout());
        mainPanel.setImage(image, true);

        JPanel centerPanel = new JPanel(new GridLayout(6, 3));
        centerPanel.setOpaque(false);
        centerPanel.add(fakeLabel1); centerPanel.add(titelLabel); centerPanel.add(fakeLabel2);
        centerPanel.add(fakeLabel3); centerPanel.add(fakeLabel4); centerPanel.add(fakeLabel5);
        centerPanel.add(fakeLabel6); centerPanel.add(startButton); centerPanel.add(fakeLabel7);
        centerPanel.add(fakeLabel8); centerPanel.add(fakeLabel9); centerPanel.add(fakeLabel10);
        centerPanel.add(fakeLabel11); centerPanel.add(ladenButton); centerPanel.add(fakeLabel12);

        mainPanel.add(centerPanel);

        //Rahmen allgemein Einstellungen
        this.add(mainPanel);

        this.setLocation(600, 300);
        this.setTitle("");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(size.width * 20 / 100,size.height * 30 / 100);
        this.setResizable(false);
        this.setVisible(true);
    }
}