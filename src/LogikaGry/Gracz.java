package LogikaGry;

import Wyjatki.GraczNieMaFigurException;

public class Gracz {
    protected String nazwa;
    protected int liczbaFigur;

    public Gracz(String nazwa, int liczbaFigur){
        this.nazwa = nazwa;
        this.liczbaFigur = liczbaFigur;
    }

    public String getNazwa(){
        return nazwa;
    }

    public int getLiczbaFigur() {
        return liczbaFigur;
    }

    public boolean czyMaFigury(){
        return liczbaFigur > 0;
    }

    public void zmniejszLiczbeFigur() throws GraczNieMaFigurException {
        if(!czyMaFigury()) throw new GraczNieMaFigurException();
        this.liczbaFigur --;
    }
}
