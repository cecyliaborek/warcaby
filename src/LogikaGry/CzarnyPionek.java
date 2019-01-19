package LogikaGry;

import Wyjatki.ZabronionyRuchException;

public class CzarnyPionek extends Pionek {
    public CzarnyPionek(Plansza plansza, Pole pole) throws ZabronionyRuchException {
        super(plansza, pole);
        this.rodzajFigury = RodzajFigury.CZARNY_PIONEK;
    }

    @Override
    public String toString(){
        return "p";
    }
}
