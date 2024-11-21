package Controlador;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SceneManager {
    private static SceneManager instance;
    private Stage primaryStage;
    private Stage secondaryStage;
    //private final Map<String, Object> controllers = new HashMap<>();

    private SceneManager() {}

    public static SceneManager getInstance() {
        if (instance == null) {
            instance = new SceneManager();
        }
        return instance;
    }
    /*
    public Map<String, Object> getControllers() {
        return controllers;
    }

     */
    public void setPrimaryStage(Stage stage) {
        this.primaryStage = stage;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public <T> T getController(String fxmlPath) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            if (loader.getLocation() == null) {
                return null;
            }
            loader.load();
            return loader.getController();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void switchScene(String fxmlPath, boolean secondary) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        //loader.setController(controllers.get(fxmlPath));
        try {
            loader.load();
        }catch (Exception e){
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Scene scene = new Scene(root);
        scene.setUserData(loader.getController());
        if(!secondary) {
            primaryStage.setScene(scene);
        }
        else{
            System.out.println(loader.getController().getClass().getName());
            secondaryStage.setScene(scene);
        }
        /*
        if (fxmlPath.equals("/Pantallas/pantallaInscripcion.fxml") || fxmlPath.equals("/Pantallas/PantallaProfesores.fxml")) {
            primaryStage.setMaximized(true);
        }

         */
    }

    public Stage openNewWindow(String fxmlPath, String title, boolean isModal) throws IOException {
        Stage newStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        //loader.setController(controllers.get(fxmlPath));
        try {
            loader.load();
        }catch (Exception e){
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Scene scene = new Scene(root);
        scene.setUserData(loader.getController());
        newStage.setScene(scene);
        newStage.setTitle(title);

        if (isModal) {
            newStage.initModality(Modality.APPLICATION_MODAL);
            newStage.initOwner(primaryStage);
        }
        secondaryStage = newStage;
        return newStage;
    }
}