package LogikaGry;

import Wyjatki.ZabronionyRuchException;

import java.util.HashMap;

public class Plansza {
    private final int szerokosc = 8;
    private final int wysokosc = 8;

    private HashMap<PozycjaNaPlanszy, Pole> pola = new HashMap<>();

    public Plansza() {
        for (int r = 0; r < wysokosc; r++) {
            for (int c = 0; c < szerokosc; c++) {
                PozycjaNaPlanszy pozycja = new PozycjaNaPlanszy(r, c);
                pola.put(pozycja, new Pole(pozycja));
            }
        }
        for (int c = 0; c < 4; c++) {
            new Pionek(this, pola.get(new PozycjaNaPlanszy(0, 2 * c)), RodzajFigury.BIALY_PIONEK);
            new Pionek(this, pola.get(new PozycjaNaPlanszy(1, 2 * c + 1)), RodzajFigury.BIALY_PIONEK);
            new Pionek(this, pola.get(new PozycjaNaPlanszy(2, 2 * c)), RodzajFigury.BIALY_PIONEK);
        }

        for (int c = 0; c < 4; c++) {
            new Pionek(this, pola.get(new PozycjaNaPlanszy(5, 2 * c + 1)), RodzajFigury.CZARNY_PIONEK);
            new Pionek(this, pola.get(new PozycjaNaPlanszy(6, 2 * c)), RodzajFigury.CZARNY_PIONEK);
            new Pionek(this, pola.get(new PozycjaNaPlanszy(7, 2 * c + 1)), RodzajFigury.CZARNY_PIONEK);
        }
    }

    public void zamienPionkaWKrolowaJesliJestNaOdpowiednimPolu(PozycjaNaPlanszy pozycjaPionka){
        Pole polePionka = this.pola.get(pozycjaPionka);
        if(pozycjaPionka.getRzad() == 0 && this.pola.get(pozycjaPionka).getFigura().getRodzajFigury() == RodzajFigury.CZARNY_PIONEK){
            polePionka.ustawPuste();
            new Krolowa(this, polePionka, RodzajFigury.CZARNA_KROLOWA);
        }
        else if(pozycjaPionka.getRzad() == wysokosc - 1 && this.pola.get(pozycjaPionka).getFigura().getRodzajFigury() == RodzajFigury.BIALY_PIONEK){
            polePionka.ustawPuste();
            new Krolowa(this, polePionka, RodzajFigury.BIALA_KROLOWA);
        }

    }

    public boolean zrobRuchOrazCzyByloBicie(Pole polePoczatkowe, Pole poleKoncowe) throws ZabronionyRuchException{
        if(poleKoncowe == null) throw new ZabronionyRuchException();
        boolean czyByloBicie = false;
        if(polePoczatkowe.getFigura().czyMozliweZbicie(poleKoncowe)){
            Pole poleDoZbicia = polePoczatkowe.getFigura().poleZbijane(poleKoncowe);
            polePoczatkowe.getFigura().przesunNa(poleKoncowe);
            poleDoZbicia.ustawPuste();
            czyByloBicie = true;
        }else{
            polePoczatkowe.getFigura().przesunNa(poleKoncowe);
        }
        zamienPionkaWKrolowaJesliJestNaOdpowiednimPolu(poleKoncowe.getPozycja());
        return czyByloBicie;

    }

    public boolean czyGraczMozeSieRuszyc(Kolor kolorGracza){
        for(int r = 0; r < wysokosc; r++){
            for(int k = 0; k < szerokosc; k++){
                if(this.getPole(new PozycjaNaPlanszy(r, k)).getFigura() != null && this.getPole(new PozycjaNaPlanszy(r, k)).getFigura().getKolor() == kolorGracza){
                    boolean czyMozeRuszyc = false;
                    for(int i = -1; i <=1; i+=2){
                        for(int j = -1; j <=1; j +=2){
                            if(this.getPole(new PozycjaNaPlanszy(r,k)).getFigura().czyMozliwyRuch(this.getPole(new PozycjaNaPlanszy(r+i,j+k))))czyMozeRuszyc=true;
                        }
                    }
                    if(czyMozeRuszyc)return true;
                }
            }
        }
        return false;
    }

    public Pole getPole(PozycjaNaPlanszy pozycja){
        return this.pola.get(pozycja);
    }

    @Override
    public String toString(){
        String ret = "";
        for(int r=0;r<8;r++){
            ret+="|";
            for(int c=0;c<8;c++){
                ret+=getPole(new PozycjaNaPlanszy(r,c)).toString()+"|";
            }
            ret+="\n";
        }
        return ret;
    }

}
