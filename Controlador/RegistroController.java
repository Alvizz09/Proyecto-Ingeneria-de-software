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
    private Button iniciarButton, loginButton, registerButton, volverButton;
    private TextField emailTextField, passwordTextField;

    @FXML
    public void iniciarButtonOnAction() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../recursos/IniciarSesionView.fxml"));
            Stage stage = (Stage) iniciarButton.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void loginButtonOnAction() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../recursos/MainView.fxml"));
            Stage stage = (Stage) loginButton.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
