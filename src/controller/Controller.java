package controller;

import model.aufgaben.Aufgaben;
import model.braumaschine.Braumaschine;
import viewing.spielRahmen.MainRahmen;
import viewing.spielRahmen.StartRahmen;
import viewing.spielstaende.LadeRahmen;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Controller implements ActionListener {
    private boolean geladenVonOptionen;
    private boolean wirdGeladen;
    private Braumaschine pBraumaschine = new Braumaschine();
    private Aufgaben pAufgaben = new Aufgaben();

    public int anzeigeZutatenAufgabe(){
        return pAufgaben.aufgabenZutatenAbgegeben();
    }
    public int anzeigeDrinkAbgegeben(){
        return pAufgaben.aufgabenDrinkAbgegeben();
    }

    public boolean getWirdGeladen(){
        return wirdGeladen;
    }
    public void setWirdGeladen(){
        this.wirdGeladen = false;
    }

    public boolean getGeladenVonOptionen(){
        return geladenVonOptionen;
    }

    //allgemeine Implementierung
    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }

    //Action Events Start-Rahmens
    public void start() { new MainRahmen(this); this.geladenVonOptionen = true;}
    public void laden() { new LadeRahmen(this, null); }

    //Action Events LadeRahmen & SpeicherRahmen
    public void back() { new StartRahmen(); }

    public void newSave(String pName){
        if(!pName.equals("")){
            File speicherstandFile = new File("data/"+pName);
            boolean exists = speicherstandFile.exists();
            if(exists){
                System.out.println("LOG: EXISTIERT bereits");
            }else{
                try{
                    String zuSpeichern = pBraumaschine.getZuSpeichern();
                    zuSpeichern += pAufgaben.getZuSpeichern();
                    FileWriter F1 = new FileWriter("data/"+ pName);
                    F1.write(zuSpeichern);
                    F1.flush();
                    F1.close();
                } catch (IOException ex){
                    ex.printStackTrace();
                }
            }
        }else{
            System.out.println("LOG: FEHLER");
        }

    }

    public void ladeSave(String pName, MainRahmen mr) {
        if (mr != null) {
            mr.dispose();
        }
        if (pName != null) {
            pBraumaschine.setGesetzt();
            pAufgaben.setGesetzt();
            try {
                StringBuilder restDaten = new StringBuilder();
                Stream<String> dataStream = Files.lines(Paths.get("data/"+ pName), StandardCharsets.UTF_8);
                dataStream.forEach(s -> {
                    for (String value : s.split(" ")) {
                        if (!pBraumaschine.getGesetzt()) {
                            pBraumaschine.setzeWerte(value);
                            pBraumaschine.ladeWerte();
                        } else if (!pAufgaben.getGesetzt()) {
                            pAufgaben.setzeWerte(value);
                            pAufgaben.ladeWerte();
                        } else if (pAufgaben.getAufgabenListeAbgeschlossene() <= 1) {
                            pAufgaben.setzeAufgabenLevel(value);
                        } else {
                            restDaten.append(value).append(" ");
                        }
                    }
                });
                pAufgaben.ladeDatenAListe(restDaten.toString());
                this.wirdGeladen = true;
                start();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            laden();
        }
    }

    public void ladeAufgabenListen(){
        pAufgaben.ladeAktualisierteListe();
    }

    //Action Events Verbesserungen-Rahmens
    public int zutatenPunkte(){
        return pAufgaben.getZutatenPunkte();
    }
    public int drinkPunkte(){
        return pAufgaben.getDrinkPunkte();
    }
    public void upgrade(String pString){
        switch (pString.toLowerCase()){
            case "hopfen":
            case "malz":
            case "zitrone":
            case "wasser":
                if (pAufgaben.getZutatenPunkte() >= 1) {
                    pBraumaschine.ertragErhoehen(pString);
                    pAufgaben.setZutatenPunkte(1);
                    pAufgaben.iUP(pString);
                }
                break;
            case "bier":
            case "bockbier":
            case "radler":
            case "limonade":
                if(pAufgaben.getDrinkPunkte() >= 1) {
                    pBraumaschine.ertragErhoehen(pString);
                    pAufgaben.setDrinkPunkte(1);
                    pAufgaben.iUP(pString);
                    break;
                }
                break;
        }
    }
    public int getUpgradePunkte(String pString){
        return pAufgaben.getIUP(pString);
    }

    //Action Events des Main-Rahmens

    //ZUTATEN
    //HOPFEN
    public String hopfenBestand() {
        return pBraumaschine.bestand("Hopfen");
    }
    public String hopfenErtrag() {
        return pBraumaschine.ertrag("Hopfen");
    }
    public void hb() {
        pBraumaschine.bestelle("Hopfen", 1);
    }
    //MALZ
    public String malzBestand() {
        return pBraumaschine.bestand("Malz");
    }
    public String malzErtrag() {
        return pBraumaschine.ertrag("Malz");
    }
    public void mb() {
        pBraumaschine.bestelle("Malz", 1);
    }
    //ZITRONE
    public String zitroneBestand() {
        return pBraumaschine.bestand("Zitrone");
    }
    public String zitroneErtrag() {
        return pBraumaschine.ertrag("Zitrone");
    }
    public void zb() {
        pBraumaschine.bestelle("Zitrone", 1);
    }
    //WASSER
    public String wasserBestand() {
        return pBraumaschine.bestand("Wasser");
    }
    public String wasserErtrag() {
        return pBraumaschine.ertrag("Wasser");
    }
    public void wb() {
        pBraumaschine.bestelle("Wasser", 1);
    }

    //GETRÄNKE
    //BIER
    public String bierBestand() {
        return pBraumaschine.bestandDrink("Bier");
    }
    public void bh() {
        pBraumaschine.herstellen("Bier", 1);
    }
    //BOCKBIER
    public String bockBestand() {
        return pBraumaschine.bestandDrink("Bockbier");
    }
    public void bb() {
        pBraumaschine.herstellen("Bockbier", 1);
    }
    //RADLER
    public String radlerBestand() {
        return pBraumaschine.bestandDrink("Radler");
    }
    public void rh() {
        pBraumaschine.herstellen("Radler", 1);
    }
    //LIMONADE
    public String limoBestand() {
        return pBraumaschine.bestandDrink("Limonade");
    }
    public void lh() {
        pBraumaschine.herstellen("Limonade", 1);
    }

    //LIEFERUNGEN
    public List zeigeAufgabenZutaten(){
        return pAufgaben.zeigeZutatenListe();
    }
    public List zeigeAufgabenDrinks(){
        return pAufgaben.zeigeDrinkListe();
    }
    public List ladeAufgabenZutaten() { return pAufgaben.ladeZutatenListe();}
    public List ladeAufgabenDrink() { return pAufgaben.ladeDrinkListe();}

    //Methode zum ermitteln des Indexes für die Listen
    public void abschluss(){
        int zIndex = pAufgaben.getIndexZutaten();
        int dIndex = pAufgaben.getIndexDrinks();

        if(!(zIndex == -1) & dIndex == -1 || dIndex == 4) {
            switch (zIndex) {
                case 0:
                    if(Integer.parseInt(pBraumaschine.bestand("Hopfen")) >= pAufgaben.liefereZutaten(zIndex)){
                        pBraumaschine.abliefernZutaten("Hopfen",pAufgaben.liefereZutaten(zIndex));
                        pAufgaben.abschlussZutaten(zIndex);}
                    break;
                case 1:
                    if(Integer.parseInt(pBraumaschine.bestand("Malz")) >= pAufgaben.liefereZutaten(zIndex)){
                        pBraumaschine.abliefernZutaten("Malz",pAufgaben.liefereZutaten(zIndex));
                        pAufgaben.abschlussZutaten(zIndex);}
                    break;
                case 2:
                    if(Integer.parseInt(pBraumaschine.bestand("Zitrone")) >= pAufgaben.liefereZutaten(zIndex)){
                        pBraumaschine.abliefernZutaten("Zitrone",pAufgaben.liefereZutaten(zIndex));
                        pAufgaben.abschlussZutaten(zIndex);}
                    break;
                case 3:
                    if(Integer.parseInt(pBraumaschine.bestand("Wasser")) >= pAufgaben.liefereZutaten(zIndex)){
                        pBraumaschine.abliefernZutaten("Wasser",pAufgaben.liefereZutaten(zIndex));
                        pAufgaben.abschlussZutaten(zIndex);}
                    break;
                case 5:
                    if(pAufgaben.aufgabenZutatenErledigt()){
                       pAufgaben.neueZutatenAufgaben();
                    }
                    break;
            }
        }else if(!(dIndex == -1) & zIndex == -1 || zIndex == 4){
            switch (dIndex) {
                case 0:
                    if(Integer.parseInt(pBraumaschine.bestandDrink("Bier")) >= pAufgaben.liefereDrinks(dIndex)){
                        pBraumaschine.abliefernDrink("Bier",pAufgaben.liefereDrinks(dIndex));
                        pAufgaben.abschlussDrink(dIndex);}
                    break;
                case 1:
                    if(Integer.parseInt(pBraumaschine.bestandDrink("BockBier")) >= pAufgaben.liefereDrinks(dIndex)){
                        pBraumaschine.abliefernDrink("BockBier",pAufgaben.liefereDrinks(dIndex));
                        pAufgaben.abschlussDrink(dIndex);}
                    break;
                case 2:
                    if(Integer.parseInt(pBraumaschine.bestandDrink("Radler")) >= pAufgaben.liefereDrinks(dIndex)){
                        pBraumaschine.abliefernDrink("Radler",pAufgaben.liefereDrinks(dIndex));
                        pAufgaben.abschlussDrink(dIndex);}
                    break;
                case 3:
                    if(Integer.parseInt(pBraumaschine.bestandDrink("limonade")) >= pAufgaben.liefereDrinks(dIndex)){
                        pBraumaschine.abliefernDrink("limonade",pAufgaben.liefereDrinks(dIndex));
                        pAufgaben.abschlussDrink(dIndex);}
                    break;
                case 5:
                    if(pAufgaben.aufgabenDrinkErledigt()){
                        pAufgaben.neueDrinkAufgaben();
                    }
                    break;
            }
        }else if(!(zIndex == -1) & !(dIndex == -1)){
           switch (zIndex){
               case 0:
                   switch (dIndex){
                       case 0:
                           if(Integer.parseInt(pBraumaschine.bestand("Hopfen")) >= pAufgaben.liefereZutaten(zIndex)){
                              pBraumaschine.abliefernZutaten("Hopfen",pAufgaben.liefereZutaten(zIndex));
                           pAufgaben.abschlussZutaten(zIndex);}
                           if(Integer.parseInt(pBraumaschine.bestandDrink("Bier")) >= pAufgaben.liefereDrinks(dIndex)){
                              pBraumaschine.abliefernDrink("Bier",pAufgaben.liefereDrinks(dIndex));
                              pAufgaben.abschlussDrink(dIndex);}
                           break;
                       case 1:
                           if(Integer.parseInt(pBraumaschine.bestand("Hopfen")) >= pAufgaben.liefereZutaten(zIndex)){
                               pBraumaschine.abliefernZutaten("Hopfen",pAufgaben.liefereZutaten(zIndex));
                           pAufgaben.abschlussZutaten(zIndex);}
                           if(Integer.parseInt(pBraumaschine.bestandDrink("BockBier")) >= pAufgaben.liefereDrinks(dIndex)){
                               pBraumaschine.abliefernDrink("BockBier",pAufgaben.liefereDrinks(dIndex));
                               pAufgaben.abschlussDrink(dIndex);}
                           break;
                       case 2:
                           if(Integer.parseInt(pBraumaschine.bestand("Hopfen")) >= pAufgaben.liefereZutaten(zIndex)){
                               pBraumaschine.abliefernZutaten("Hopfen",pAufgaben.liefereZutaten(zIndex));
                           pAufgaben.abschlussZutaten(zIndex);}
                           if(Integer.parseInt(pBraumaschine.bestandDrink("Radler")) >= pAufgaben.liefereDrinks(dIndex)){
                               pBraumaschine.abliefernDrink("Radler",pAufgaben.liefereDrinks(dIndex));
                               pAufgaben.abschlussDrink(dIndex);}
                           break;
                       case 3:
                           if(Integer.parseInt(pBraumaschine.bestand("Hopfen")) >= pAufgaben.liefereZutaten(zIndex)){
                               pBraumaschine.abliefernZutaten("Hopfen",pAufgaben.liefereZutaten(zIndex));
                           pAufgaben.abschlussZutaten(zIndex);}
                           if( Integer.parseInt(pBraumaschine.bestandDrink("limonade")) >= pAufgaben.liefereDrinks(dIndex)){
                               pBraumaschine.abliefernDrink("limonade",pAufgaben.liefereDrinks(dIndex));
                               pAufgaben.abschlussDrink(dIndex);}
                           break;
                       case 5:
                           if(Integer.parseInt(pBraumaschine.bestand("Hopfen")) >= pAufgaben.liefereZutaten(zIndex)){
                               pBraumaschine.abliefernZutaten("Hopfen",pAufgaben.liefereZutaten(zIndex));
                           pAufgaben.abschlussZutaten(zIndex);}
                           if(pAufgaben.aufgabenDrinkErledigt()){
                               pAufgaben.neueDrinkAufgaben();
                           }
                           break;
                   }
                   break;
               case 1:
                   switch (dIndex){
                   case 0:
                       if(Integer.parseInt(pBraumaschine.bestand("Malz")) >= pAufgaben.liefereZutaten(zIndex)){
                           pBraumaschine.abliefernZutaten("Malz",pAufgaben.liefereZutaten(zIndex));
                       pAufgaben.abschlussZutaten(zIndex);}
                       if( Integer.parseInt(pBraumaschine.bestandDrink("Bier")) >= pAufgaben.liefereDrinks(dIndex)){
                           pBraumaschine.abliefernDrink("Bier",pAufgaben.liefereDrinks(dIndex));
                       pAufgaben.abschlussDrink(dIndex);}
                       break;
                   case 1:
                       if(Integer.parseInt(pBraumaschine.bestand("Malz")) >= pAufgaben.liefereZutaten(zIndex)){
                           pBraumaschine.abliefernZutaten("Malz",pAufgaben.liefereZutaten(zIndex));
                       pAufgaben.abschlussZutaten(zIndex);}
                        if(Integer.parseInt(pBraumaschine.bestandDrink("BockBier")) >= pAufgaben.liefereDrinks(dIndex)){
                           pBraumaschine.abliefernDrink("BockBier",pAufgaben.liefereDrinks(dIndex));
                       pAufgaben.abschlussDrink(dIndex);}
                       break;
                   case 2:
                       if(Integer.parseInt(pBraumaschine.bestand("Malz")) >= pAufgaben.liefereZutaten(zIndex)){
                           pBraumaschine.abliefernZutaten("Malz",pAufgaben.liefereZutaten(zIndex));
                       pAufgaben.abschlussZutaten(zIndex);}
                       if(Integer.parseInt(pBraumaschine.bestandDrink("Radler")) >= pAufgaben.liefereDrinks(dIndex)){
                           pBraumaschine.abliefernDrink("Radler",pAufgaben.liefereDrinks(dIndex));
                       pAufgaben.abschlussDrink(dIndex);}
                       break;
                   case 3:
                       if(Integer.parseInt(pBraumaschine.bestand("Malz")) >= pAufgaben.liefereZutaten(zIndex)){
                           pBraumaschine.abliefernZutaten("Malz",pAufgaben.liefereZutaten(zIndex));
                       pAufgaben.abschlussZutaten(zIndex);}
                       if(Integer.parseInt(pBraumaschine.bestandDrink("limonade")) >= pAufgaben.liefereDrinks(dIndex)){
                           pBraumaschine.abliefernDrink("limonade",pAufgaben.liefereDrinks(dIndex));
                       pAufgaben.abschlussDrink(dIndex);}
                       break;
                   case 5:
                       if(Integer.parseInt(pBraumaschine.bestand("Malz")) >= pAufgaben.liefereZutaten(zIndex)){
                           pBraumaschine.abliefernZutaten("Malz",pAufgaben.liefereZutaten(zIndex));
                       pAufgaben.abschlussZutaten(zIndex);}
                       if(pAufgaben.aufgabenDrinkErledigt()){
                           pAufgaben.neueDrinkAufgaben();
                       }
                       break;
                   }
                   break;
               case 2:
                   switch (dIndex){
                   case 0:
                       if(Integer.parseInt(pBraumaschine.bestand("Zitrone")) >= pAufgaben.liefereZutaten(zIndex)){
                           pBraumaschine.abliefernZutaten("Zitrone",pAufgaben.liefereZutaten(zIndex));
                       pAufgaben.abschlussZutaten(zIndex);}
                       if(Integer.parseInt(pBraumaschine.bestandDrink("Bier")) >= pAufgaben.liefereDrinks(dIndex)){
                           pBraumaschine.abliefernDrink("Bier",pAufgaben.liefereDrinks(dIndex));
                       pAufgaben.abschlussDrink(dIndex);}
                       break;
                   case 1:
                       if(Integer.parseInt(pBraumaschine.bestand("Zitrone")) >= pAufgaben.liefereZutaten(zIndex)){
                           pBraumaschine.abliefernZutaten("Zitrone",pAufgaben.liefereZutaten(zIndex));
                       pAufgaben.abschlussZutaten(zIndex);}
                       if(Integer.parseInt(pBraumaschine.bestandDrink("BockBier")) >= pAufgaben.liefereDrinks(dIndex)){
                           pBraumaschine.abliefernDrink("BockBier",pAufgaben.liefereDrinks(dIndex));
                       pAufgaben.abschlussDrink(dIndex);}
                       break;
                   case 2:
                       if(Integer.parseInt(pBraumaschine.bestand("Zitrone")) >= pAufgaben.liefereZutaten(zIndex)){
                           pBraumaschine.abliefernZutaten("Zitrone",pAufgaben.liefereZutaten(zIndex));
                       pAufgaben.abschlussZutaten(zIndex);}
                       if(Integer.parseInt(pBraumaschine.bestandDrink("Radler")) >= pAufgaben.liefereDrinks(dIndex)){
                           pBraumaschine.abliefernDrink("Radler",pAufgaben.liefereDrinks(dIndex));
                       pAufgaben.abschlussDrink(dIndex);}
                       break;
                   case 3:
                       if(Integer.parseInt(pBraumaschine.bestand("Zitrone")) >= pAufgaben.liefereZutaten(zIndex)){
                           pBraumaschine.abliefernZutaten("Zitrone",pAufgaben.liefereZutaten(zIndex));
                       pAufgaben.abschlussZutaten(zIndex);}
                       if( Integer.parseInt(pBraumaschine.bestandDrink("limonade")) >= pAufgaben.liefereDrinks(dIndex)){
                           pBraumaschine.abliefernDrink("limonade",pAufgaben.liefereDrinks(dIndex));
                       pAufgaben.abschlussDrink(dIndex);}
                       break;
                   case 5:
                       if(Integer.parseInt(pBraumaschine.bestand("Zitrone")) >= pAufgaben.liefereZutaten(zIndex)){
                           pBraumaschine.abliefernZutaten("Zitrone",pAufgaben.liefereZutaten(zIndex));
                       pAufgaben.abschlussZutaten(zIndex);}
                       if(pAufgaben.aufgabenDrinkErledigt()){
                           pAufgaben.neueDrinkAufgaben();
                       }
                       break;
                   }
                   break;
               case 3:
                   switch (dIndex){
                   case 0:
                       if(Integer.parseInt(pBraumaschine.bestand("Wasser")) >= pAufgaben.liefereZutaten(zIndex)){
                           pBraumaschine.abliefernZutaten("Wasser",pAufgaben.liefereZutaten(zIndex));
                       pAufgaben.abschlussZutaten(zIndex);}
                       if (Integer.parseInt(pBraumaschine.bestandDrink("Bier")) >= pAufgaben.liefereDrinks(dIndex)){
                           pBraumaschine.abliefernDrink("Bier",pAufgaben.liefereDrinks(dIndex));
                           pAufgaben.abschlussDrink(dIndex);}
                       break;
                   case 1:
                       if(Integer.parseInt(pBraumaschine.bestand("Wasser")) >= pAufgaben.liefereZutaten(zIndex)){
                           pBraumaschine.abliefernZutaten("Wasser",pAufgaben.liefereZutaten(zIndex));
                       pAufgaben.abschlussZutaten(zIndex);}
                       if(Integer.parseInt(pBraumaschine.bestandDrink("BockBier")) >= pAufgaben.liefereDrinks(dIndex)){
                           pBraumaschine.abliefernDrink("BockBier",pAufgaben.liefereDrinks(dIndex));
                           pAufgaben.abschlussDrink(dIndex);}
                       break;
                   case 2:
                       if(Integer.parseInt(pBraumaschine.bestand("Wasser")) >= pAufgaben.liefereZutaten(zIndex)){
                           pBraumaschine.abliefernZutaten("Wasser",pAufgaben.liefereZutaten(zIndex));
                       pAufgaben.abschlussZutaten(zIndex);}
                       if(Integer.parseInt(pBraumaschine.bestandDrink("Radler")) >= pAufgaben.liefereDrinks(dIndex)){
                           pBraumaschine.abliefernDrink("Radler",pAufgaben.liefereDrinks(dIndex));
                           pAufgaben.abschlussDrink(dIndex);}
                       break;
                   case 3:
                       if(Integer.parseInt(pBraumaschine.bestand("Wasser")) >= pAufgaben.liefereZutaten(zIndex)){
                           pBraumaschine.abliefernZutaten("Wasser",pAufgaben.liefereZutaten(zIndex));
                       pAufgaben.abschlussZutaten(zIndex);}
                       if(Integer.parseInt(pBraumaschine.bestandDrink("limonade")) >= pAufgaben.liefereDrinks(dIndex)){
                           pBraumaschine.abliefernDrink("limonade",pAufgaben.liefereDrinks(dIndex));
                           pAufgaben.abschlussDrink(dIndex);}
                       break;
                   case 5:
                       if(Integer.parseInt(pBraumaschine.bestand("Wasser")) >= pAufgaben.liefereZutaten(zIndex)){
                           pBraumaschine.abliefernZutaten("Wasser",pAufgaben.liefereZutaten(zIndex));
                       pAufgaben.abschlussZutaten(zIndex);}
                       if(pAufgaben.aufgabenDrinkErledigt()){
                           pAufgaben.neueDrinkAufgaben();
                       }
                       break;
                   }
                   break;
               case 5:
                   switch (dIndex){
                   case 0:
                       if(Integer.parseInt(pBraumaschine.bestandDrink("Bier")) >= pAufgaben.liefereDrinks(dIndex)){
                           pBraumaschine.abliefernDrink("Bier",pAufgaben.liefereDrinks(dIndex));
                       pAufgaben.abschlussDrink(dIndex);}
                       if(pAufgaben.aufgabenZutatenErledigt()){
                           pAufgaben.neueZutatenAufgaben();
                       }
                       break;
                   case 1:
                       if(Integer.parseInt(pBraumaschine.bestandDrink("BockBier")) >= pAufgaben.liefereDrinks(dIndex)){
                           pBraumaschine.abliefernDrink("BockBier",pAufgaben.liefereDrinks(dIndex));
                       pAufgaben.abschlussDrink(dIndex);}
                       if(pAufgaben.aufgabenZutatenErledigt()){
                           pAufgaben.neueZutatenAufgaben();
                       }
                       break;
                   case 2:
                       if(Integer.parseInt(pBraumaschine.bestandDrink("Radler")) >= pAufgaben.liefereDrinks(dIndex)){
                           pBraumaschine.abliefernDrink("Radler",pAufgaben.liefereDrinks(dIndex));
                       pAufgaben.abschlussDrink(dIndex);}
                       if(pAufgaben.aufgabenZutatenErledigt()){
                           pAufgaben.neueZutatenAufgaben();
                       }
                       break;
                   case 3:
                       if(Integer.parseInt(pBraumaschine.bestandDrink("limonade")) >= pAufgaben.liefereDrinks(dIndex)){
                           pBraumaschine.abliefernDrink("limonade",pAufgaben.liefereDrinks(dIndex));
                       pAufgaben.abschlussDrink(dIndex);}
                       if(pAufgaben.aufgabenZutatenErledigt()){
                           pAufgaben.neueZutatenAufgaben();
                       }
                       break;
                   case 5:
                       if(pAufgaben.aufgabenZutatenErledigt()){
                           pAufgaben.neueZutatenAufgaben();
                       }
                       if(pAufgaben.aufgabenDrinkErledigt()){
                           pAufgaben.neueDrinkAufgaben();
                       }
                       break;
               }
           }
        }
    }
}
