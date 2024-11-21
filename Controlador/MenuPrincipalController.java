package Controlador;

import javafx.scene.control.*;

public class MenuPrincipalController {
    public Button crearOportunidad;
    public Button descubrirOportunidad;

    private void mostrarMensaje(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Mensaje");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }


}
