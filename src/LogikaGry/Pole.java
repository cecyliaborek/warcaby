package LogikaGry;

import Wyjatki.ZabronionyRuchException;

public class Pole {
    private Figura figura = null;
    private PozycjaNaPlanszy pozycja;

    public Pole(PozycjaNaPlanszy pozycja){
        this.pozycja = pozycja;
    }

    public boolean czyPuste(){
        return figura == null;
    }
    public void ustawPuste(){
        this.figura = null;
    }
    public void przesunFigureNaPole(Figura figura) throws ZabronionyRuchException {
        if(this.czyPuste()){
            this.figura = figura;
        }
        else{
            throw new ZabronionyRuchException();
        }
    }

    public Figura getFigura() {
        return figura;
    }

    public PozycjaNaPlanszy getPozycja() {
        return pozycja;
    }

    @Override
    public String toString(){
        if(figura==null)return " ";
        return figura.toString();
    }
}
