package model.braumaschine;

import model.getraenk.bier.Bier;
import model.getraenk.bockbier.BockBier;
import model.getraenk.limonade.Limonade;
import model.getraenk.radler.Radler;
import model.zutaten.pflanzen.Hopfen;
import model.zutaten.pflanzen.Malz;
import model.zutaten.pflanzen.Zitrone;
import model.zutaten.wasser.Wasser;

public class Braumaschine {
    private boolean werteGesetzt;
    private int werteGeladen;
    //Zutaten Referenzen
    private Hopfen hopfenRef = new Hopfen();
    private Malz malzRef = new Malz();
    private Zitrone zitroneRef = new Zitrone();
    private Wasser wasserRef = new Wasser();
    //Getränke Referenzen
    private Bier bierRef = new Bier();
    private BockBier bockRef = new BockBier();
    private Limonade limoRef = new Limonade();
    private Radler radlerRef = new Radler();

    //Zutaten Methoden
    public String bestand(String pZutat) {
        int bestand;
        String sBestand = "";
        switch (pZutat.toLowerCase()) {
            case "hopfen":
                bestand = hopfenRef.bestandAnzeigen();
                sBestand += bestand;
                return sBestand;
            case "malz":
                bestand = malzRef.bestandAnzeigen();
                sBestand += bestand;
                return sBestand;
            case "zitrone":
                bestand = zitroneRef.bestandAnzeigen();
                sBestand += bestand;
                return sBestand;
            case "wasser":
                bestand = wasserRef.bestandAnzeigen();
                sBestand += bestand;
                return sBestand;
            default:
                System.out.println("LOG: BRAUMASCHINE - ANZEIGE METHODE - DEFAULT");
        }
        return "LOG: BRAUMASCHINE - ANZEIGE METHODE - Out of Switch-Case";
    }
    public String ertrag(String pZutat) {
        int ertrag;
        String sErtrag = "+";
        switch (pZutat.toLowerCase()) {
            case "hopfen":
                ertrag = hopfenRef.getErtrag();
                return sErtrag + ertrag;
            case "malz":
                ertrag = malzRef.getErtrag();
                return sErtrag + ertrag;
            case "zitrone":
                ertrag = zitroneRef.getErtrag();
                return sErtrag + ertrag;
            case "wasser":
                ertrag = wasserRef.getErtrag();
                return sErtrag + ertrag;
            default:
                System.out.println("LOG: BRAUMASCHINE - ERTRAG METHODE - DEFAULT");
        }
        return "LOG: BRAUMASCHINE - ERTRAG METHODE - Out of Switch-Case";
    }
    public void bestelle(String pZutat, int pOft) {
        switch (pZutat.toLowerCase()) {
            case "hopfen":
                hopfenRef.lieferungErhalten(pOft);
                break;
            case "malz":
                malzRef.lieferungErhalten(pOft);
                break;
            case "zitrone":
                zitroneRef.lieferungErhalten(pOft);
                break;
            case "wasser":
                wasserRef.lieferungErhalten(pOft);
                break;
            default:
                System.out.println("LOG: BRAUMASCHINE - BESTAND METHODE - DEFAULT");
        }
    }
    public void abliefernZutaten(String pZutat, int pMenge){
        switch (pZutat.toLowerCase()) {
            case "hopfen":
                hopfenRef.bestandVerbrauchen(pMenge);
                break;
            case "malz":
                malzRef.bestandVerbrauchen(pMenge);
                break;
            case "zitrone":
                zitroneRef.bestandVerbrauchen(pMenge);
                break;
            case "wasser":
                wasserRef.bestandVerbrauchen(pMenge);
                break;
            default:
                System.out.println("LOG: BRAUMASCHINE - ABLIEFERN METHODE - DEFAULT");
        }
    }

    //Getränke Methoden
    public String bestandDrink(String pDrink){
        int bestand;
        String sBestand = "";
        switch (pDrink.toLowerCase()) {
            case "bier":
                bestand = bierRef.bestandAnzeigen();
                sBestand += bestand;
                return sBestand;
            case "bockbier":
                bestand = bockRef.bestandAnzeigen();
                sBestand += bestand;
                return sBestand;
            case "radler":
                bestand = radlerRef.bestandAnzeigen();
                sBestand += bestand;
                return sBestand;
            case "limonade":
                bestand = limoRef.bestandAnzeigen();
                sBestand += bestand;
                return sBestand;
            default:
                System.out.println("LOG: BRAUMASCHINE - BESTANDDRINK METHODE - DEFAULT");
        }
        return "LOG: BRAUMASCHINE - BESTANDDRINK METHODE - Out of Switch-Case";
    }
    public void herstellen(String pGetraenk, int pOft) {
        switch (pGetraenk.toLowerCase()) {
            case "bier":
                for (int a = 0; a < pOft; a++) {
                    if (hopfenRef.bestandUeberpruefen(bierRef.getZutaten("Hopfen"))
                            && malzRef.bestandUeberpruefen(bierRef.getZutaten("Malz"))
                            && wasserRef.bestandUeberpruefen(bierRef.getZutaten("Wasser"))) {
                        hopfenRef.bestandVerbrauchen(bierRef.getZutaten("Hopfen"));
                        malzRef.bestandVerbrauchen(bierRef.getZutaten("Malz"));
                        wasserRef.bestandVerbrauchen(bierRef.getZutaten("Wasser"));
                        bierRef.brauen();
                    }
                }
                break;
            case "bockbier":
                for (int a = 0; a < pOft; a++) {
                    if (hopfenRef.bestandUeberpruefen(bockRef.getZutaten("Hopfen"))
                            && malzRef.bestandUeberpruefen(bockRef.getZutaten("Malz"))
                            && wasserRef.bestandUeberpruefen(bockRef.getZutaten("Wasser"))) {
                        hopfenRef.bestandVerbrauchen(bockRef.getZutaten("Hopfen"));
                        malzRef.bestandVerbrauchen(bockRef.getZutaten("Malz"));
                        wasserRef.bestandVerbrauchen(bockRef.getZutaten("Wasser"));
                        bockRef.brauen();
                    }
                }
                break;
            case "radler":
                for (int a = 0; a < pOft; a++) {
                    if (zitroneRef.bestandUeberpruefen(radlerRef.getZutaten("Zitrone"))
                            && malzRef.bestandUeberpruefen(radlerRef.getZutaten("Malz"))
                            && wasserRef.bestandUeberpruefen(radlerRef.getZutaten("Wasser"))) {
                        zitroneRef.bestandVerbrauchen(radlerRef.getZutaten("Zitrone"));
                        malzRef.bestandVerbrauchen(radlerRef.getZutaten("Malz"));
                        wasserRef.bestandVerbrauchen(radlerRef.getZutaten("Wasser"));
                        radlerRef.brauen();
                    }
                }
                break;
            case "limonade":
                for (int a = 0; a < pOft; a++) {
                    if (zitroneRef.bestandUeberpruefen(limoRef.getZutaten("Zitrone"))
                            && wasserRef.bestandUeberpruefen(limoRef.getZutaten("Wasser"))) {
                        zitroneRef.bestandVerbrauchen(limoRef.getZutaten("Zitrone"));
                        wasserRef.bestandVerbrauchen(limoRef.getZutaten("Wasser"));
                        limoRef.brauen();
                    }
                }
                break;
            default:
                System.out.println("LOG: BRAUMASCHINE - BRAU METHODE - Out of Switch-Case");
        }
    }
    public void abliefernDrink(String pDrink, int pMenge){
        switch (pDrink.toLowerCase()) {
            case "bier":
                bierRef.liefern(pMenge);
                break;
            case "bockbier":
                bockRef.liefern(pMenge);
                break;
            case "radler":
                radlerRef.liefern(pMenge);
                break;
            case "limonade":
                limoRef.liefern(pMenge);
                break;
            default:
                System.out.println("LOG: BRAUMASCHINE - ABLIEFERN METHODE - DEFAULT");
        }
    }

    //Ertrag Methode
    public void ertragErhoehen(String pString){
        switch(pString.toLowerCase()){
            case "hopfen": hopfenRef.setErtrag(1); break;
            case "malz": malzRef.setErtrag(1); break;
            case "zitrone": zitroneRef.setErtrag(1); break;
            case "wasser": wasserRef.setErtrag(1); break;
            case "bier": bierRef.setAbfuellungen(1); break;
            case "bockbier": bockRef.setAbfuellungen(1); break;
            case "radler": radlerRef.setAbfuellungen(1); break;
            case "limonade": limoRef.setAbfuellungen(1); break;
        }
    }

    //Speicher Methode
    public String getZuSpeichern(){
        String gespicherteWerte = "";
        gespicherteWerte += this.hopfenRef.bestandAnzeigen() + " ";
        gespicherteWerte += this.hopfenRef.getErtrag() + " ";
        gespicherteWerte += this.malzRef.bestandAnzeigen() + " ";
        gespicherteWerte += this.malzRef.getErtrag() + " ";
        gespicherteWerte += this.zitroneRef.bestandAnzeigen() + " ";
        gespicherteWerte += this.zitroneRef.getErtrag() + " ";
        gespicherteWerte += this.wasserRef.bestandAnzeigen() + " ";
        gespicherteWerte += this.wasserRef.getErtrag() + " ";
        gespicherteWerte += this.bierRef.bestandAnzeigen() + " ";
        gespicherteWerte += this.bierRef.getAbfuellungen() + " ";
        gespicherteWerte += this.bockRef.bestandAnzeigen() + " ";
        gespicherteWerte += this.bockRef.getAbfuellungen() + " ";
        gespicherteWerte += this.limoRef.bestandAnzeigen() + " ";
        gespicherteWerte += this.limoRef.getAbfuellungen() + " ";
        gespicherteWerte += this.radlerRef.bestandAnzeigen() + " ";
        gespicherteWerte += this.radlerRef.getAbfuellungen() + " ";
        return gespicherteWerte;
    }

    public void setzeWerte(String pWerte){
        switch(werteGeladen){
            case 0:
                this.hopfenRef.ladeBestand(Integer.parseInt(pWerte)); break;
            case 1:
                this.hopfenRef.ladeErtrag(Integer.parseInt(pWerte)); break;
            case 2:
                this.malzRef.ladeBestand(Integer.parseInt(pWerte)); break;
            case 3:
                this.malzRef.ladeErtrag(Integer.parseInt(pWerte)); break;
            case 4:
                this.zitroneRef.ladeBestand(Integer.parseInt(pWerte)); break;
            case 5:
                this.zitroneRef.ladeErtrag(Integer.parseInt(pWerte)); break;
            case 6:
                this.wasserRef.ladeBestand(Integer.parseInt(pWerte)); break;
            case 7:
                this.wasserRef.ladeErtrag(Integer.parseInt(pWerte)); break;
            case 8:
                this.bierRef.setAufLager(Integer.parseInt(pWerte)); break;
            case 9:
                this.bierRef.setAbfuellungen(Integer.parseInt(pWerte)); break;
            case 10:
                this.bockRef.setAufLager(Integer.parseInt(pWerte)); break;
            case 11:
                this.bockRef.setAbfuellungen(Integer.parseInt(pWerte)); break;
            case 12:
                this.radlerRef.setAufLager(Integer.parseInt(pWerte)); break;
            case 13:
                this.radlerRef.setAbfuellungen(Integer.parseInt(pWerte)); break;
            case 14:
                this.limoRef.setAufLager(Integer.parseInt(pWerte)); break;
            case 15:
                this.limoRef.setAbfuellungen(Integer.parseInt(pWerte)); break;
            default:
                System.out.println("LOG// FEHLER IN BRAUMASCHINE - setzeWerte");
        }
    }

    public void ladeWerte(){
        if (this.werteGeladen == 15){
            this.werteGesetzt = true;
        }else{
            this.werteGeladen++;
        }
    }
    public boolean getGesetzt(){
        return this.werteGesetzt;
    }

    public void setGesetzt(){
        this.werteGeladen = 0;
        this.werteGesetzt = false;
    }
}
