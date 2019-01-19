package LogikaGry;

import Wyjatki.GraczNieMaFigurException;
import Wyjatki.WybranaFiguraZlegoGraczaException;
import Wyjatki.ZabronionyRuchException;

import java.util.HashMap;

public class Gra {
    protected Plansza plansza;
    protected HashMap<Kolor, Gracz> gracze = new HashMap<>();

    protected Kolor kolorObecnegoGracza = Kolor.BIALY;

    public Gra(String nazwaBialegoGracza, String nazwaCzarnegoGracza) throws ZabronionyRuchException {
        this.plansza = new Plansza();
        this.gracze.put(Kolor.BIALY, new Gracz(nazwaBialegoGracza, 12));
        this.gracze.put(Kolor.CZARNY, new Gracz(nazwaCzarnegoGracza, 12));
    }

    //public void zarejestrujRuch(PozycjaNaPlanszy zPoz, PozycjaNaPlanszy doPoz){}

    public void zrobRuch(PozycjaNaPlanszy zPoz, PozycjaNaPlanszy doPoz) throws ZabronionyRuchException, GraczNieMaFigurException {
        if(this.plansza.getPole(zPoz) == null || this.plansza.getPole(zPoz).getFigura() == null)throw new ZabronionyRuchException();
        if(this.plansza.getPole(zPoz).getFigura().getKolor() != kolorObecnegoGracza) throw new WybranaFiguraZlegoGraczaException();
        if(this.plansza.zrobRuchOrazCzyByloBicie(plansza.getPole(zPoz), plansza.getPole(doPoz)))this.gracze.get(this.kolorObecnegoGracza).zmniejszLiczbeFigur();
        this.kolorObecnegoGracza = this.kolorObecnegoGracza.opposite();
    }


    public Kolor wylonZwyciezce(){
        if(!this.gracze.get(Kolor.BIALY).czyMaFigury())return Kolor.CZARNY;
        else if(!this.gracze.get(Kolor.CZARNY).czyMaFigury())return Kolor.BIALY;
        else if(!this.plansza.czyGraczMozeSieRuszyc(this.kolorObecnegoGracza))return this.kolorObecnegoGracza.opposite();
        return null;
    }


    public Plansza getPlansza() {
        return plansza;
    }

    public Gracz getCurrentPlayer(){
        return gracze.get(kolorObecnegoGracza);
    }

    public Kolor getCurrentPlayerColor(){
        return kolorObecnegoGracza;
    }

    @Override
    public String toString(){
        return plansza.toString();
    }

}
