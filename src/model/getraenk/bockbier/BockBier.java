package model.getraenk.bockbier;

import model.getraenk.Getraenk;

import java.util.HashMap;

public class BockBier extends Getraenk {
    private int aufLager;
    private int abfuellungen = 1;
    private HashMap<String, Integer> zutaten;


    public BockBier() {
        super("BockBier");
        zutaten = new HashMap<>();
        this.zutaten.put("Hopfen", 2);
        this.zutaten.put("Malz", 2);
        this.zutaten.put("Wasser", 5);
    }

    @Override
    public int setAufLager(int pLager) {
        return this.aufLager = pLager;
    }

    @Override
    public int getZutaten(String pZutat) {
        return zutaten.get(pZutat);
    }

    @Override
    public void brauen() {
        this.aufLager += abfuellungen;
    }

    @Override
    public int bestandAnzeigen() {
        return this.aufLager;
    }

    @Override
    public void liefern(int pLiefern) {
        this.aufLager -= pLiefern;
    }

    @Override
    public void setAbfuellungen(int pAbfuellungen) {
        this.abfuellungen += pAbfuellungen;
    }

    @Override
    public int getAbfuellungen() {
        return this.abfuellungen;
    }

    @Override
    public String toString() {
        return super.toString() + this.aufLager;
    }
}
