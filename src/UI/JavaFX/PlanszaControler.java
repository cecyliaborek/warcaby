package UI.JavaFX;

import LogikaGry.Gra;
import LogikaGry.Pole;
import LogikaGry.PozycjaNaPlanszy;
import LogikaGry.RodzajFigury;
import Wyjatki.ZabronionyRuchException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import javax.imageio.IIOException;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class PlanszaControler implements Initializable {
    @FXML
    private GridPane mainGrid;

    private Stage stage;

    private Pole poleStartowe = null;
    private Pole poleKoncowe = null;

    private Image czarnyPionekImage;
    private Image bialyPionekImage;
    private Image czarnaKrolowaImage;
    private Image bialaKrolowaImage;

    private HashMap<Pole, Button> przyciskiPlanszy = new HashMap<>();
    private Gra gra;

    public PlanszaControler(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            gra = new Gra("Bialy", "Czarny");
        }catch (RuntimeException e){
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
                    if(k % 2 == 0) kolor = "#444";
                    else kolor = "#ddd";
                }else{
                    if(k % 2 == 1) kolor = "#444";
                    else kolor = "#ddd";
                }
                button.setStyle("-fx-background-color: "+kolor);
                przyciskiPlanszy.put(obecnePole, button);
                button.setOnMousePressed(mouseEvent -> kliknieciePrzycisku(obecnePole));
            }
        }
        czarnyPionekImage = new Image(getClass().getResourceAsStream("/resources/img/czarnyPionek.png"));
        bialyPionekImage = new Image(getClass().getResourceAsStream("/resources/img/bialyPionek.png"));
        czarnaKrolowaImage = new Image(getClass().getResourceAsStream("/resources/img/czarnaKrolowa.png"));
        bialaKrolowaImage = new Image(getClass().getResourceAsStream("/resources/img/bialaKrolowa.png"));
        odswiezPlansze();

    }

    public void odswiezPlansze(){
        for(int r = 0; r < 8; r++){
            for(int k = 0; k < 8; k++){
                Pole pole = gra.getPlansza().getPole(new PozycjaNaPlanszy(r, k));
                if(pole.getFigura() == null)
                    przyciskiPlanszy.get(pole).setGraphic(null);
                else if(pole.getFigura().getRodzajFigury() == RodzajFigury.CZARNY_PIONEK)
                    przyciskiPlanszy.get(pole).setGraphic(new ImageView(czarnyPionekImage));
                else if(pole.getFigura().getRodzajFigury() == RodzajFigury.BIALY_PIONEK)
                    przyciskiPlanszy.get(pole).setGraphic(new ImageView(bialyPionekImage));
                else if(pole.getFigura().getRodzajFigury() == RodzajFigury.CZARNA_KROLOWA)
                    przyciskiPlanszy.get(pole).setGraphic(new ImageView(czarnaKrolowaImage));
                else if(pole.getFigura().getRodzajFigury() == RodzajFigury.BIALA_KROLOWA)
                    przyciskiPlanszy.get(pole).setGraphic(new ImageView(bialaKrolowaImage));
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
                if (poleStartowe.getFigura().czyMozliwyRuch(pole)) {
                    poleKoncowe = pole;
                    zrobRuch();
                    poleStartowe = null;
                    poleKoncowe = null;
                }
            }
        }
    }

    public void zrobRuch(){
        try {
            gra.zrobRuch(poleStartowe.getPozycja(), poleKoncowe.getPozycja());
            odswiezPlansze();
            czyKtosWygral();
        }catch (ZabronionyRuchException e){
            e.printStackTrace();
        }
    }

    private void czyKtosWygral(){
        if(gra.wylonZwyciezce() != null){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/resources/fxml/koniecGry.fxml"));
                Parent root = fxmlLoader.load();
                Scene scene = new Scene(root, 500, 500);
                stage.setScene(scene);
            }catch (IOException e){
                e.printStackTrace();
                stage.close();
            }
        }
    }
}
