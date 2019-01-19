package LogikaGry;

import Wyjatki.BrakFiguryDoZbiciaException;
import Wyjatki.ZabronionyRuchException;

public abstract class Krolowa extends Figura {
    public Krolowa(Plansza plansza, Pole pole) throws ZabronionyRuchException {
        super(plansza, pole);
    }

    @Override
    public boolean czyMozliweZbicie(Pole pole) {
        try{
            if(poleZbijane(pole) != null){
                PozycjaNaPlanszy obecnaPozycja = this.pole.pozycja;
                if(Math.abs(obecnaPozycja.roznicaMiedzyKolumnami(pole.getPozycja())) == Math.abs(obecnaPozycja.roznicaMiedzyRzedami(pole.getPozycja()))){
                    int kierunekRzedy = obecnaPozycja.roznicaMiedzyRzedami(pole.getPozycja())/Math.abs(obecnaPozycja.roznicaMiedzyRzedami(pole.getPozycja()));
                    int kierunekKolumny = obecnaPozycja.roznicaMiedzyKolumnami(pole.getPozycja())/Math.abs(obecnaPozycja.roznicaMiedzyKolumnami(pole.getPozycja()));
                    int roznicaMiedzyPolamiABS = Math.abs(obecnaPozycja.roznicaMiedzyKolumnami(pole.getPozycja()));
                    for(int i = 1; i < roznicaMiedzyPolamiABS -1 ; i++){
                        int r = obecnaPozycja.getRzad() + kierunekRzedy*i;
                        int k = obecnaPozycja.getKolumna() + kierunekKolumny*i;
                        if(!plansza.getPole(new PozycjaNaPlanszy(r, k)).czyPuste()) return false;
                    }
                    return true;
                }
                return false;
            }
            return false;
        }catch (ZabronionyRuchException e){
            return false;
        }
    }


    public Pole poleZbijane(Pole poleNa) throws ZabronionyRuchException{
        PozycjaNaPlanszy obecnaPozycja = this.pole.getPozycja();
        int roznicaMiedzyRzedami = obecnaPozycja.roznicaMiedzyRzedami(poleNa.getPozycja());
        int roznicaMiedzyKolumnami = obecnaPozycja.roznicaMiedzyKolumnami(poleNa.getPozycja());

        int kierunekRzedy = roznicaMiedzyRzedami/Math.abs(roznicaMiedzyRzedami);
        int kierunekKolumny = roznicaMiedzyKolumnami/Math.abs(roznicaMiedzyKolumnami);
        Pole poleZbijane = plansza.getPole(poleNa.getPozycja().dodaj(-kierunekRzedy,-kierunekKolumny));
        if(poleZbijane.czyPuste()) throw new BrakFiguryDoZbiciaException();
        if(poleZbijane.getFigura().getKolor().opposite() == this.getKolor()) return poleZbijane;
        else throw new ZabronionyRuchException();
    }

    @Override
    public boolean czyMozliwyRuchBezZbicia(Pole pole){
        PozycjaNaPlanszy obecnaPozycja = this.pole.pozycja;
        if(Math.abs(obecnaPozycja.roznicaMiedzyKolumnami(pole.getPozycja())) == Math.abs(obecnaPozycja.roznicaMiedzyRzedami(pole.getPozycja()))){
            int kierunekRzedy = obecnaPozycja.roznicaMiedzyRzedami(pole.getPozycja())/Math.abs(obecnaPozycja.roznicaMiedzyRzedami(pole.getPozycja()));
            int kierunekKolumny = obecnaPozycja.roznicaMiedzyKolumnami(pole.getPozycja())/Math.abs(obecnaPozycja.roznicaMiedzyKolumnami(pole.getPozycja()));
            int roznicaMiedzyPolamiABS = Math.abs(obecnaPozycja.roznicaMiedzyKolumnami(pole.getPozycja()));
            for(int i = 1; i < roznicaMiedzyPolamiABS; i++){
                int r = obecnaPozycja.getRzad() + kierunekRzedy*i;
                int k = obecnaPozycja.getKolumna() + kierunekKolumny*i;
                if(!plansza.getPole(new PozycjaNaPlanszy(r, k)).czyPuste()) return false;
            }
            return true;
        }
        return false;
    }
}
