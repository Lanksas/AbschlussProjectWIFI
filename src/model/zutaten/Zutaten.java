package model.zutaten;

public abstract class Zutaten {
    private String name;

    public Zutaten(String pName){
        this.name = pName;
    }

    protected abstract void ladeBestand(int pWert);
    protected abstract void ladeErtrag(int pWert);
    protected abstract int bestandAnzeigen();
    protected abstract void bestandVerbrauchen(int pVerbrauch);
    protected abstract boolean bestandUeberpruefen(int pVerbrauch);
    protected abstract void lieferungErhalten(int pMenge);
    protected abstract int getErtrag();
    protected abstract void setErtrag(int pErtrag);

    @Override
    public String toString() {
        return this.name + " Menge: ";
    }
}
