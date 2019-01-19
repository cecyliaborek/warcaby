package UI.Console;

import LogikaGry.Gra;
import LogikaGry.PozycjaNaPlanszy;
import Wyjatki.GraczNieMaFigurException;
import Wyjatki.ZabronionyRuchException;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            Gra gra = new Gra("Gracz1", "Gracz2");
            while (gra.wylonZwyciezce() == null) {
                try {
                    System.out.println(gra);
                    int r1 = sc.nextInt();
                    int c1 = sc.nextInt();
                    int r2 = sc.nextInt();
                    int c2 = sc.nextInt();
                    gra.zrobRuch(new PozycjaNaPlanszy(r1, c1), new PozycjaNaPlanszy(r2, c2));
                } catch (ZabronionyRuchException e) {
                    System.out.println("Idioci prawa gry w warcaby nie mają - idź kopać rowy");
                } catch (GraczNieMaFigurException e) {
                    System.out.println("Ty debilu - NIE MASZ PIONKÓW");
                }
            }
        }catch (ZabronionyRuchException e){
            e.printStackTrace();
        }
    }
}
