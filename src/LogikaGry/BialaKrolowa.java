package LogikaGry;

import Wyjatki.ZabronionyRuchException;

public class BialaKrolowa extends Krolowa{
    public BialaKrolowa(Plansza plansza, Pole pole) throws ZabronionyRuchException {
        super(plansza, pole);
        this.rodzajFigury = RodzajFigury.BIALA_KROLOWA;
    }
    @Override
    public String toString(){
        return "Q";
    }
}
