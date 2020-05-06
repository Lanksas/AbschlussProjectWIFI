package model.zutaten.wasser;

import model.zutaten.Zutaten;

public class Wasser extends Zutaten {
    private int bestand;
    private int ertrag = 10;

    public Wasser() {
        super("Wasser");
    }

    @Override
    public void ladeBestand(int pWert) {
         this.bestand = pWert;
    }
    @Override
    public void ladeErtrag(int pWert){
        this.ertrag = pWert;
    }
    @Override
    public int bestandAnzeigen() {
        return this.bestand;
    }

    @Override
    public void bestandVerbrauchen(int pVerbrauch) {
        this.bestand -= pVerbrauch;
    }
    @Override
    public boolean bestandUeberpruefen(int pVerbrauch) {
        return this.bestand >= pVerbrauch;
    }
    @Override
    public void lieferungErhalten(int pMenge) {
        this.bestand += (this.ertrag * pMenge);
    }

    @Override
    public int getErtrag() {
        return ertrag;
    }

    @Override
    public void setErtrag(int pErtrag) {
        this.ertrag += pErtrag;
    }

    @Override
    public String toString() {
        return super.toString() + this.bestand;
    }
}
