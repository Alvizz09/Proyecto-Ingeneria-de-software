package Vista;

import Modelo.ConexionBaseDatos;
import Modelo.Sistema;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class InterfazApp extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root= FXMLLoader.load(getClass().getResource("../recursos/MainView.fxml"));
        //primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setTitle("RentCar");
        primaryStage.setScene(new Scene(root, 520,400));
        primaryStage.show();
        Sistema sistema = new Sistema();

    }
}
