package Controlador;

import Modelo.Sistema;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class MenuPrincipalController {

    @FXML
    public Button CrearButton;
    @FXML
    public Button BuscarButton;

    private void mostrarMensaje(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Mensaje");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @FXML
    public void CrearButtonOnAction() {
        try {
            SceneManager.getInstance().switchScene("../recursos/CrearTipoOportunidadesView.fxml", false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void BuscarButtonOnAction() {
        try {
            SceneManager.getInstance().switchScene("../recursos/BuscarOportunidadView.fxml", false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
