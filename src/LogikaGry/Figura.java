package LogikaGry;

import Wyjatki.BrakFiguryDoZbiciaException;
import Wyjatki.ZabronionyRuchException;

public abstract class Figura {
    protected Plansza plansza;
    protected Pole pole;
    protected RodzajFigury rodzajFigury;

    public Figura(Plansza plansza, Pole pole) throws ZabronionyRuchException {
        this.plansza = plansza;
        this.pole = pole;
        this.pole.przesunFigureNaPole(this);
    }
    public abstract boolean czyMozliwyRuchBezZbicia(Pole pole);
    public abstract Pole poleZbijane(Pole poleNa) throws ZabronionyRuchException;

    public boolean czyMozliwyRuch(Pole pole){
        if(pole == null)return false;
        if(!pole.czyPuste()) return false;
        return czyMozliweZbicie(pole) || czyMozliwyRuchBezZbicia(pole);
    }

    public boolean czyMozliweZbicie(Pole pole){
        try{
            if(poleZbijane(pole) != null) return true;
            return false;
        }catch (ZabronionyRuchException e){
            return false;
        }
    }

    public Kolor getKolor(){
        return rodzajFigury.getKolor();
    }

    public void przesunNa(Pole poleNa) throws ZabronionyRuchException {
        if(! poleNa.czyPuste()) throw new ZabronionyRuchException();
        if(this.czyMozliwyRuch(poleNa)){
            this.pole.ustawPuste();
            poleNa.przesunFigureNaPole(this);
            this.pole = poleNa;
        }
        else throw new ZabronionyRuchException();
    }
}
