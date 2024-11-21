package Controlador;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class ControladorPrincipal {
    public Button salir;
    public Label mensajeInicioDeSesion;
    public Button iniciarSesion;
    public Button registrarse,registarEstudiante,registrarUniEmp,volver,confirmarRegistro;
    public PasswordField password;
    public TextField eMail;

    @FXML
    public void registroOnAction() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../resource/RegisterView.fxml"));
            Stage stage = (Stage) registrarse.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void salirOnAction(){
        Stage stage= (Stage) salir.getScene().getWindow();
        stage.close();
    }


    private void mostrarMensaje(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Mensaje");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    @FXML
    private void validarRegistro(){

    }

    @FXML
    public void registroEstudianteOnAction() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../resource/RegistroEstudianteView.fxml"));
            Stage stage = (Stage) registarEstudiante.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void registroUniEmpoOnAction() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../resource/RegistroEmpresa/UniversidadView.fxml"));
            Stage stage = (Stage) registrarUniEmp.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void volverOnAction() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../resource/RegisterView.fxml"));
            Stage stage = (Stage) volver.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void confirmarRegistroOnAction() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../resource/RegisterView.fxml"));
            Stage stage = (Stage) confirmarRegistro.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
