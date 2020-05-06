package model.getraenk.bier;

import model.getraenk.Getraenk;

import java.util.HashMap;

public class Bier extends Getraenk {
    private int aufLager;
    private int abfuellungen = 1;
    private HashMap<String, Integer> zutaten;

    public Bier() {
        super("Bier");
        zutaten = new HashMap<>();
        this.zutaten.put("Hopfen", 5);
        this.zutaten.put("Malz", 5);
        this.zutaten.put("Wasser", 10);
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
