package LogikaGry;

import java.util.Objects;

public class PozycjaNaPlanszy {
    private int rzad, kolumna;

    public PozycjaNaPlanszy(int rzad, int kolumna){
        this.rzad = rzad;
        this.kolumna = kolumna;
    }

    public int getRzad(){
        return rzad;
    }

    public int getKolumna() {
        return kolumna;
    }

    public int roznicaMiedzyRzedami(PozycjaNaPlanszy pozycja){
        return pozycja.rzad - this.rzad;
    }
    public int roznicaMiedzyKolumnami(PozycjaNaPlanszy pozycja){
        return pozycja.kolumna - this.kolumna;
    }


    public boolean equals(Object o){
        if(o == null || !(o instanceof PozycjaNaPlanszy)){
            return false;
        }
        PozycjaNaPlanszy pozycja = (PozycjaNaPlanszy)o;
        return roznicaMiedzyRzedami(pozycja) == 0 && roznicaMiedzyKolumnami(pozycja) == 0;
    }

    public int hashCode() {
        return Objects.hash(rzad, kolumna);
    }

    public PozycjaNaPlanszy dodaj(int rzad, int kolumna){
        return new PozycjaNaPlanszy(this.rzad+rzad, this.kolumna+kolumna);
    }



}
