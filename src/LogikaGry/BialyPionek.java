package LogikaGry;

import Wyjatki.ZabronionyRuchException;

public class BialyPionek extends Pionek{
    public BialyPionek(Plansza plansza, Pole pole) throws ZabronionyRuchException {
        super(plansza, pole);
        this.rodzajFigury = RodzajFigury.BIALY_PIONEK;
    }
    @Override
    public String toString(){
        return "P";
    }
}
