package UI.JavaFX;

import LogikaGry.Gra;
import LogikaGry.Pole;
import LogikaGry.PozycjaNaPlanszy;
import Wyjatki.GraczNieMaFigurException;
import Wyjatki.ZabronionyRuchException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class PlanszaControler implements Initializable {
    @FXML
    private GridPane mainGrid;

    Pole poleStartowe = null;
    Pole poleKoncowe = null;

    private HashMap<Pole, Button> przyciskiPlanszy = new HashMap<>();
    private Gra gra;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            gra = new Gra("krowaBiala", "krowaCzarna");
        }catch (ZabronionyRuchException e){
            e.printStackTrace();
            return;
        }
        for(int r = 0; r < 8; r++){
            for(int k = 0; k < 8; k++){
                final Pole obecnePole = gra.getPlansza().getPole(new PozycjaNaPlanszy(r,k));
                Button button = new Button();
                mainGrid.add(button, k, r);
                button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                String kolor;
                if(r % 2 == 0){
                    if(k % 2 == 0) kolor = "#f00";
                    else kolor = "#ddd";
                }else{
                    if(k % 2 == 1) kolor = "#f00";
                    else kolor = "#ddd";
                }
                button.setStyle("-fx-background-color: "+kolor);
                przyciskiPlanszy.put(obecnePole, button);
                button.setOnMousePressed(mouseEvent -> kliknieciePrzycisku(obecnePole));
            }
        }
        odswiezPlansze();

    }

    public void odswiezPlansze(){
        for(int r = 0; r < 8; r++){
            for(int k = 0; k < 8; k++){
                Pole pole = gra.getPlansza().getPole(new PozycjaNaPlanszy(r, k));
                przyciskiPlanszy.get(pole).setText(pole.toString());
            }
        }
    }

    public void kliknieciePrzycisku(Pole pole){
        if(poleStartowe == null){
            if(!pole.czyPuste()){
                if(pole.getFigura().getKolor() == gra.getCurrentPlayerColor()){
                    poleStartowe = pole;
                }
            }
        }
        else if(poleKoncowe == null){
            if(!pole.czyPuste()){
                if(pole.getFigura().getKolor() == gra.getCurrentPlayerColor()){
                    poleStartowe = pole;
                }
            }
            else if(pole.czyPuste()){
                try {
                    if (poleStartowe.getFigura().czyMozliwyRuch(pole)) {
                        poleKoncowe = pole;
                        try {
                            gra.zrobRuch(poleStartowe.getPozycja(), poleKoncowe.getPozycja());
                        }catch (GraczNieMaFigurException e){
                            e.printStackTrace();
                        }
                        poleStartowe = null;
                        poleKoncowe = null;
                        odswiezPlansze();
                    }
                }catch (ZabronionyRuchException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
