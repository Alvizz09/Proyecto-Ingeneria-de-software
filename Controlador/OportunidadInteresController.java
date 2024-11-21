package Controlador;

import Modelo.ConexionBaseDatos;
import Modelo.Oportunidad;
import Modelo.Sistema;
import Modelo.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.util.ArrayList;

public class OportunidadInteresController {

    @FXML
    private Button cerrarButton;
    @FXML
    private TextArea mostrarMisOportunidades;

    private ConexionBaseDatos conexionBaseDatos = new ConexionBaseDatos();

    @FXML
    private void initialize() {
        mostrarOportunidadesUsuario();
    }

    @FXML
    private void mostrarOportunidadesUsuario() {
        Usuario usuarioActual = Sistema.getUsuarioActual();
        ArrayList<Oportunidad> oportunidades = conexionBaseDatos.getOportunidadesDb();
        ArrayList<Oportunidad> oportunidadesUsuario = new ArrayList<>();

        for (Oportunidad oportunidad : oportunidades) {
            if (oportunidad.getOwner().equals(usuarioActual.getIdUsuario())) {
                oportunidadesUsuario.add(oportunidad);
            }
        }

    }

    @FXML
    private void volverALMenu() throws IOException {
        SceneManager.getInstance().switchScene("../recursos/MenuPrincipalView.fxml", false);
    }
}