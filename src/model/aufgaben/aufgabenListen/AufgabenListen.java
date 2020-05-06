package model.aufgaben.aufgabenListen;

import java.awt.*;

public class AufgabenListen {
    private int aufgabenLVLBestellung = 1;
    private int aufgabenLVLGebraut = 1;
    private int zutatenAbgeliefert;
    private int drinkAbgeliefert;
    private int werteGeladen;

    private List zutatenListe = new List();
    private List drinkList = new List();

    private int[] gebrauchteZutatenMenge = {0, 0, 0, 0};
    private int[] gebrauchteDrinkMenge = {0, 0, 0, 0};

    private boolean[] abgeschlossenenZutaten = {false, false, false, false};
    private boolean[] abgeschlossenenDrink = {false, false, false, false};

    public void bestellungErhoehen() {
        for (int i = 0; i < abgeschlossenenZutaten.length; i++) {
            abgeschlossenenZutaten[i] = false;
        }
        this.aufgabenLVLBestellung++;
    }

    public void gebrautErhoehen() {
        for (int i = 0; i < abgeschlossenenDrink.length; i++) {
            abgeschlossenenDrink[i] = false;
        }
        this.aufgabenLVLGebraut++;
    }

    private int gebrauchteZutaten() {
        double zwischenSumme = (((Math.random() + 1) * 10) + aufgabenLVLBestellung);
        double ergebnis = zwischenSumme + (zwischenSumme * 0.3);
        return (int) ergebnis;
    }

    private int gebrauchteDrinks() {
        double zwischenSumme = (((Math.random() + 1) * 2) + aufgabenLVLGebraut);
        double ergebnis = zwischenSumme + (zwischenSumme * 0.3 + 0.5);
        return (int) ergebnis;
    }

    public List getZutatenListe() {
        return zutatenListe;
    }

    public List getDrinkListe() {
        return drinkList;
    }

    public void bearbeiteZutatenListe(int index) {
        if (index <= 3 && !(abgeschlossenenZutaten[index])) {
            zutatenListe.replaceItem("Aufgabe Abgeschlossen!", index);
            abgeschlossenenZutaten[index] = true;
            zutatenAbgeliefert++;
        }
        if (zutatenAbgeliefert == 4) {
            zutatenListe.replaceItem("Neue Aufgaben anfordern!", 5);
            zutatenAbgeliefert = 0;
        }
    }

    public void bearbeiteDrinkListe(int index) {
        if (index <= 3 && !(abgeschlossenenDrink[index])) {
            drinkList.replaceItem("Aufgabe Abgeschlossen!", index);
            abgeschlossenenDrink[index] = true;
            drinkAbgeliefert++;
        }
        if (drinkAbgeliefert == 4) {
            drinkList.replaceItem("Neue Aufgaben anfordern", 5);
            drinkAbgeliefert = 0;
        }
    }

    public void fuelleZutatenListe() {
        zutatenListe.removeAll();
        for (int i = 0; i < gebrauchteZutatenMenge.length; i++) {
            gebrauchteZutatenMenge[i] += gebrauchteZutaten();
        }
        zutatenListe.add("Liefere " + gebrauchteZutatenMenge[0] + " kg Hopfen aus!");
        zutatenListe.add("Liefere " + gebrauchteZutatenMenge[1] + " kg Malz aus!");
        zutatenListe.add("Liefere " + gebrauchteZutatenMenge[2] + " kg Zitronen aus!");
        zutatenListe.add("Liefere " + gebrauchteZutatenMenge[3] + " Liter Wasser aus!");
        zutatenListe.add("");
        zutatenListe.add("Noch nicht alle Aufgaben erledigt.");
    }

    public void fuelleDrinkListe() {
        drinkList.removeAll();
        for (int i = 0; i < gebrauchteDrinkMenge.length; i++) {
            gebrauchteDrinkMenge[i] += gebrauchteDrinks();
        }
        drinkList.add("Liefere " + gebrauchteDrinkMenge[0] + " Kisten Bier aus!");
        drinkList.add("Liefere " + gebrauchteDrinkMenge[1] + " Kisten Bock-Bier aus!");
        drinkList.add("Liefere " + gebrauchteDrinkMenge[2] + " Kisten Radler aus!");
        drinkList.add("Liefere " + gebrauchteDrinkMenge[3] + " Kisten Limonade aus!");
        drinkList.add("");
        drinkList.add("Noch nicht alle Aufgaben erledigt.");
    }

    public void ladeZutatenListe(){
        zutatenListe.removeAll();
        zutatenListe.add("Liefere " + gebrauchteZutatenMenge[0] + " kg Hopfen aus!");
        zutatenListe.add("Liefere " + gebrauchteZutatenMenge[1] + " kg Malz aus!");
        zutatenListe.add("Liefere " + gebrauchteZutatenMenge[2] + " kg Zitronen aus!");
        zutatenListe.add("Liefere " + gebrauchteZutatenMenge[3] + " Liter Wasser aus!");
        zutatenListe.add("");
        zutatenListe.add("Noch nicht alle Aufgaben erledigt.");
    }

    public void ladeDrinkListe(){
        drinkList.removeAll();
        drinkList.add("Liefere " + gebrauchteDrinkMenge[0] + " Kisten Bier aus!");
        drinkList.add("Liefere " + gebrauchteDrinkMenge[1] + " Kisten Bock-Bier aus!");
        drinkList.add("Liefere " + gebrauchteDrinkMenge[2] + " Kisten Radler aus!");
        drinkList.add("Liefere " + gebrauchteDrinkMenge[3] + " Kisten Limonade aus!");
        drinkList.add("");
        drinkList.add("Noch nicht alle Aufgaben erledigt.");
    }

    public int gebrauchteMengeZutaten(int index) {
        if (!abgeschlossenenZutaten[index]) {
            return gebrauchteZutatenMenge[index];
        }
        return 0;
    }

    public int gebrauchteMengeDrinks(int index) {
        if (!abgeschlossenenDrink[index]) {
            return gebrauchteDrinkMenge[index];
        }
        return 0;
    }

    public boolean[] getAbgeschlossenenZutaten() {
        return abgeschlossenenZutaten;
    }

    public boolean[] getAbgeschlossenenDrink() {
        return abgeschlossenenDrink;
    }

    public int getAufgabenLVLBestellung() {
        return aufgabenLVLBestellung;
    }

    public int getAufgabenLVLGebraut() {
        return aufgabenLVLGebraut;
    }

    public String getZuSpeichern() {
        StringBuilder listeZuSpeichern = new StringBuilder();
        listeZuSpeichern.append(this.aufgabenLVLBestellung).append(" ");
        listeZuSpeichern.append(this.aufgabenLVLGebraut).append(" ");
        for (boolean tf : abgeschlossenenZutaten) {
            listeZuSpeichern.append(tf).append(" ");
        }
        for (boolean tf : abgeschlossenenDrink) {
            listeZuSpeichern.append(tf).append(" ");
        }
        for (int n : gebrauchteZutatenMenge) {
            listeZuSpeichern.append(n).append(" ");
        }
        for (int n : gebrauchteDrinkMenge) {
            listeZuSpeichern.append(n).append(" ");
        }
        return listeZuSpeichern.toString();
    }

    public int getWerteGeladen(){
        return this.werteGeladen;
    }

    public void setWerteGeladen(){
        this.werteGeladen = 0;
    }

    public void setzeAufgabenLevel(String pWerte) {
        switch (werteGeladen) {
            case 0:
                this.aufgabenLVLBestellung = Integer.parseInt(pWerte);
                werteGeladen++;
                break;
            case 1:
                this.aufgabenLVLGebraut = Integer.parseInt(pWerte);
                werteGeladen++;
                break;
        }
    }

    public void ladeDaten(String pDaten){
        String[] wert = pDaten.split(" ");
        int wertIndex = 0;
        for(int index = 0; index < abgeschlossenenZutaten.length; index++){
            abgeschlossenenZutaten[index] = Boolean.parseBoolean(wert[index]);
            wertIndex = index + 1;
        }
        for(int index = 0; index < abgeschlossenenDrink.length; index++){
            abgeschlossenenDrink[index] = Boolean.parseBoolean(wert[wertIndex]);
            wertIndex++;
        }
        for(int index = 0; index < gebrauchteZutatenMenge.length; index++){
            gebrauchteZutatenMenge[index] = Integer.parseInt(wert[wertIndex]);
            wertIndex++;
        }
        for(int index = 0; index < gebrauchteDrinkMenge.length; index++){
            gebrauchteDrinkMenge[index] = Integer.parseInt(wert[wertIndex]);
            wertIndex++;
        }
    }

    public void ladeListe() {
        for (int i = 0; i < abgeschlossenenZutaten.length; i++) {
            if (abgeschlossenenZutaten[i]) {
                zutatenListe.replaceItem("Aufgabe Abgeschlossen!", i);
                zutatenAbgeliefert++;
            }
            if (zutatenAbgeliefert == 4) {
                zutatenListe.replaceItem("Neue Aufgaben anfordern!", 5);
                zutatenAbgeliefert = 0;
            }
        }
        for (int i = 0; i < abgeschlossenenDrink.length; i++) {
            if (abgeschlossenenDrink[i]) {
                drinkList.replaceItem("Aufgabe Abgeschlossen!", i);
                drinkAbgeliefert++;
            }
            if (drinkAbgeliefert == 4) {
                drinkList.replaceItem("Neue Aufgaben anfordern", 5);
                drinkAbgeliefert = 0;
            }
        }
    }
}
