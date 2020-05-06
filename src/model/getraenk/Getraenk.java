package model.getraenk;

public abstract class Getraenk {
    private String name;

    public Getraenk(String pName) {
        this.name = pName;
    }

    protected abstract int setAufLager(int pLager);
    protected abstract int getZutaten(String pZutat);
    protected abstract void brauen();
    protected abstract int bestandAnzeigen();
    protected abstract void liefern(int pLiefern);
    protected abstract void setAbfuellungen(int p);
    protected abstract int getAbfuellungen();

    @Override
    public String toString() {
        return this.name + " ";
    }
}
