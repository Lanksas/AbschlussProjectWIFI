package model.aufgaben;

import model.aufgaben.aufgabenListen.AufgabenListen;

import java.awt.*;

public class Aufgaben {
    private AufgabenListen aListen = new AufgabenListen();
    private boolean werteGesetzt;
    private int werteGeladen;
    private int zutatenPunkte;
    private int drinkPunkte;
    private int hopfenUpgrade, malzUpgrade, zitroneUpgrade, wasserUpgrade, bierUpgrade, bockUpgrade, radlerUpgrade, limonadeUpgrade;

    public List zeigeZutatenListe() {
        aListen.fuelleZutatenListe();
        return aListen.getZutatenListe();
    }

    public List zeigeDrinkListe() {
        aListen.fuelleDrinkListe();
        return aListen.getDrinkListe();
    }

    public List ladeZutatenListe(){
        aListen.ladeZutatenListe();
        return aListen.getZutatenListe();
    }

    public List ladeDrinkListe(){
        aListen.ladeDrinkListe();
        return aListen.getDrinkListe();
    }

    public int getIndexZutaten() {
        return aListen.getZutatenListe().getSelectedIndex();
    }

    public int getIndexDrinks() {
        return aListen.getDrinkListe().getSelectedIndex();
    }

    public int liefereZutaten(int index) {
        return aListen.gebrauchteMengeZutaten(index);
    }

    public int liefereDrinks(int index) {
        return aListen.gebrauchteMengeDrinks(index);
    }

    public void abschlussZutaten(int index) {
        aListen.bearbeiteZutatenListe(index);
    }

    public void abschlussDrink(int index) {
        aListen.bearbeiteDrinkListe(index);
    }

    public boolean aufgabenZutatenErledigt() {
        boolean erledigt = false;
        for (boolean b : aListen.getAbgeschlossenenZutaten()) {
            if (b) {
                erledigt = true;
            } else {
                return false;
            }
        }
        return erledigt;
    }

    public boolean aufgabenDrinkErledigt() {
        boolean erledigt = false;
        for (boolean b : aListen.getAbgeschlossenenDrink()) {
            if (b) {
                erledigt = true;
            } else {
                return false;
            }
        }
        return erledigt;
    }

    public void neueZutatenAufgaben() {
        aListen.bestellungErhoehen();
        aListen.fuelleZutatenListe();
        zutatenPunkte += zutatenPunkte + aListen.getAufgabenLVLBestellung();
    }

    public void neueDrinkAufgaben() {
        aListen.gebrautErhoehen();
        aListen.fuelleDrinkListe();
        drinkPunkte += drinkPunkte + aListen.getAufgabenLVLGebraut();
    }

    public int getZutatenPunkte() {
        return zutatenPunkte;
    }

    public int getDrinkPunkte() {
        return drinkPunkte;
    }

    public void setZutatenPunkte(int zutatenPunkte) {
        this.zutatenPunkte -= zutatenPunkte;
    }
    public void setDrinkPunkte(int drinkPunkte) {
        this.drinkPunkte -= drinkPunkte;
    }

    public void iUP(String pString) {
        switch (pString.toLowerCase()) {
            case "hopfen":
                hopfenUpgrade++;
                break;
            case "malz":
                malzUpgrade++;
                break;
            case "zitrone":
                zitroneUpgrade++;
                break;
            case "wasser":
                wasserUpgrade++;
                break;
            case "bier":
                bierUpgrade++;
                break;
            case "bockbier":
                bockUpgrade++;
                break;
            case "radler":
                radlerUpgrade++;
                break;
            case "limonade":
                limonadeUpgrade++;
                break;
        }
    }
    public int getIUP(String pString) {
        switch (pString.toLowerCase()) {
            case "hopfen":
                return hopfenUpgrade;
            case "malz":
                return malzUpgrade;
            case "zitrone":
                return zitroneUpgrade;
            case "wasser":
                return wasserUpgrade;
            case "bier":
                return bierUpgrade;
            case "bockbier":
                return bockUpgrade;
            case "radler":
                return radlerUpgrade;
            case "limonade":
                return limonadeUpgrade;
            default:
                return -1;
        }
    }

    public int aufgabenZutatenAbgegeben(){
       return aListen.getAufgabenLVLBestellung();
    }
    public int aufgabenDrinkAbgegeben(){
        return aListen.getAufgabenLVLGebraut();
    }

    public String getZuSpeichern(){
        String zuSpeichern = "";
        zuSpeichern += this.zutatenPunkte + " ";
        zuSpeichern += this.drinkPunkte + " ";
        zuSpeichern += this.hopfenUpgrade + " ";
        zuSpeichern += this.malzUpgrade + " ";
        zuSpeichern += this.zitroneUpgrade + " ";
        zuSpeichern += this.wasserUpgrade + " ";
        zuSpeichern += this.bierUpgrade + " ";
        zuSpeichern += this.bockUpgrade + " ";
        zuSpeichern += this.radlerUpgrade + " ";
        zuSpeichern += this.limonadeUpgrade + " ";
        zuSpeichern += aListen.getZuSpeichern();
        return zuSpeichern;
    }

    public void setzeWerte(String pWerte) {
        switch (werteGeladen) {
            case 0:
                this.zutatenPunkte = Integer.parseInt(pWerte);
                break;
            case 1:
                this.drinkPunkte = Integer.parseInt(pWerte);
                break;
            case 2:
                this.hopfenUpgrade = Integer.parseInt(pWerte);
                break;
            case 3:
                this.malzUpgrade = Integer.parseInt(pWerte);
                break;
            case 4:
                this.zitroneUpgrade = Integer.parseInt(pWerte);
                break;
            case 5:
                this.wasserUpgrade = Integer.parseInt(pWerte);
                break;
            case 6:
                this.bierUpgrade = Integer.parseInt(pWerte);
                break;
            case 7:
                this.bockUpgrade = Integer.parseInt(pWerte);
                break;
            case 8:
                this.radlerUpgrade = Integer.parseInt(pWerte);
                break;
            case 9:
                this.limonadeUpgrade = Integer.parseInt(pWerte); break;
            default:
                System.out.println("LOG// FEHLER IN AUFGABEN - setzeWerte");
        }
    }

    public void setzeAufgabenLevel(String pWerte){
        aListen.setzeAufgabenLevel(pWerte);
    }

    public int getAufgabenListeAbgeschlossene(){
        return aListen.getWerteGeladen();
    }

    public void ladeWerte(){
        if (this.werteGeladen == 9){
            this.werteGesetzt = true;
        }else{
            this.werteGeladen++;
        }
    }
    public void ladeDatenAListe(String pDaten){
        aListen.ladeDaten(pDaten);
    }
    public void ladeAktualisierteListe(){
        aListen.ladeListe();
    }
    public boolean getGesetzt(){
        return this.werteGesetzt;
    }

    public void setGesetzt(){
        this.werteGeladen = 0;
        this.werteGesetzt = false;
        aListen.setWerteGeladen();
    }
}
