package LogikaGry;

import Wyjatki.ZabronionyRuchException;

public class CzarnaKrolowa extends Krolowa {
    public CzarnaKrolowa(Plansza plansza, Pole pole) throws ZabronionyRuchException {
        super(plansza, pole);
        this.rodzajFigury = RodzajFigury.CZARNA_KROLOWA;
    }

    @Override
    public String toString(){
        return "q";
    }
}
