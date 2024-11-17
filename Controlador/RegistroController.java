package Controlador;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class RegistroController {
    @FXML
    private Button iniciarButton, loginButton, registerButton, volverButton, nextRegButton, volverRegButton;
    @FXML
    private TextField emailTextField, passwordTextField, emailRegTextField, passwordRegTextField, nombresRegTextField, ageRegTextField;

    private void changeScene(Button button, String fxmlFilePath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFilePath));
            Stage stage = (Stage) button.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void iniciarButtonOnAction() {
        changeScene(iniciarButton, "../recursos/IniciarSesionView.fxml");
    }

    @FXML
    public void volverButtonOnAction() {
        changeScene(volverButton, "../recursos/MainView.fxml");
    }

    @FXML
    public void registerButtonOnAction() {
        changeScene(registerButton, "../recursos/RegistrarUsuarioView.fxml");
    }
    @FXML
    public void volverRegButtonOnAction() {
        changeScene(volverRegButton, "../recursos/IniciarSesionView.fxml");
    }
}
