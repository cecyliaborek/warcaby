package LogikaGry;

public class Gracz {
    private String nazwa;
    private int liczbaFigur;

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

    public void zmniejszLiczbeFigur()  {
        this.liczbaFigur --;
    }
}
