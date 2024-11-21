package Controlador;

import Modelo.ConexionBaseDatos;
import Modelo.Oportunidad;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;

import java.util.ArrayList;

public class BuscarOportunidadController {

    @FXML
    private ComboBox<String> interesesComboBox;
    @FXML
    private ListView<Oportunidad> oportunidadesListView;

    private ConexionBaseDatos conexionBaseDatos = new ConexionBaseDatos();

    @FXML
    private void initialize() {
        interesesComboBox.getItems().addAll("Startups", "Proyectos", "Grupo estudiantil", "Semillero", "Otro");
        interesesComboBox.setOnAction(event -> filtrarOportunidades());
    }

    private void filtrarOportunidades() {
        String filtro = interesesComboBox.getValue();
        ArrayList<Oportunidad> oportunidades = conexionBaseDatos.getOportunidadesDb();
        ArrayList<Oportunidad> filtradas = new ArrayList<>();

        for (Oportunidad oportunidad : oportunidades) {
            if (oportunidad.getTipo().equals(filtro)) {
                filtradas.add(oportunidad);
            }
        }

        oportunidadesListView.getItems().setAll(filtradas);
    }
}