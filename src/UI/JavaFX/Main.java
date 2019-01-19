package UI.JavaFX;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/boardContainer.fxml"));
        fxmlLoader.setController(new PlanszaControler());
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 500, 500);

        stage.setTitle("warcaby");
        stage.setScene(scene);
        stage.show();
    }
}
