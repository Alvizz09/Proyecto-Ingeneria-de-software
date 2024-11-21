package Controlador;

import Modelo.ConexionBaseDatos;
import Modelo.Oportunidad;
import Modelo.Sistema;
import Modelo.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.ArrayList;

public class OportunidadInteresController {

    @FXML
    private ListView<Oportunidad> oportunidadesListView;

    private ConexionBaseDatos conexionBaseDatos = new ConexionBaseDatos();

    @FXML
    private void initialize() {
        cargarOportunidadesUsuario();
    }

    private void cargarOportunidadesUsuario() {
        Usuario usuarioActual = Sistema.getUsuarioActual();
        ArrayList<Oportunidad> oportunidades = conexionBaseDatos.getOportunidadesDb();
        ArrayList<Oportunidad> oportunidadesUsuario = new ArrayList<>();

        for (Oportunidad oportunidad : oportunidades) {
            if (oportunidad.getOwner().equals(usuarioActual.getIdUsuario())) {
                oportunidadesUsuario.add(oportunidad);
            }
        }

        oportunidadesListView.getItems().setAll(oportunidadesUsuario);
    }

    private void CrearOportunidad() {

    }
}