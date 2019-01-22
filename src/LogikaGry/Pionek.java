package LogikaGry;

import Wyjatki.BrakFiguryDoZbiciaException;
import Wyjatki.ZabronionyRuchException;

public class Pionek extends Figura {

    public Pionek(Plansza plansza, Pole pole, RodzajFigury rodzajFigury) {
        super(plansza, pole, rodzajFigury);
    }

    @Override
    public boolean czyMozliwyRuchBezZbicia(Pole pole){
        PozycjaNaPlanszy pozycja = this.pole.getPozycja();
        if(pozycja.roznicaMiedzyRzedami(pole.getPozycja()) == rodzajFigury.getKolor().getWartosc() && Math.abs(pozycja.roznicaMiedzyKolumnami(pole.getPozycja())) == 1){
            return true;
        }
        return false;
    }

    public Pole poleZbijane(Pole poleNa) throws BrakFiguryDoZbiciaException {
        PozycjaNaPlanszy pozycja = this.pole.getPozycja();
        for(int i = -1; i <= 1; i+=2){
            for(int j = -1; j <=1; j+=2){
                if(pozycja.roznicaMiedzyRzedami(poleNa.getPozycja()) == 2 * i && pozycja.roznicaMiedzyKolumnami(poleNa.getPozycja())==2*j){
                    Pole poleZbijane = this.plansza.getPole(new PozycjaNaPlanszy(pozycja.getRzad() + i, pozycja.getKolumna() + j));
                    if(poleZbijane.getFigura() == null) throw new BrakFiguryDoZbiciaException();
                    if(poleZbijane.getFigura().getKolor() == this.getKolor().opposite()) return poleZbijane;
                }
            }
        }
        return null;
    }
}
