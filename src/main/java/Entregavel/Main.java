package Entregavel;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("primo-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 420, 280);

        // Passa o stage para o controlador para possibilitar troca de cenas
        MainController controller = fxmlLoader.getController();
        controller.setStage(stage);

        stage.setTitle("Verifica Primo e Soma");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
