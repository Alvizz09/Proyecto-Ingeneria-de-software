package Controlador;

import Modelo.ConexionBaseDatos;
import Modelo.Oportunidad;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

import java.util.ArrayList;

public class BuscarOportunidadController {

    @FXML
    private ComboBox<String> interesesComboBox, SeleccionInteresComboBox;
    @FXML
    private ListView<Oportunidad> oportunidadesListView;

    @FXML
    private Button buscarOportunidadButton;

    @FXML
    private TextArea TagsBuscarTextArea;

    @FXML
    private TextArea mostrarOportunidades;

    private ConexionBaseDatos conexionBaseDatos = new ConexionBaseDatos();

    @FXML
    private void initialize() {
        interesesComboBox.getItems().addAll("Startups", "Proyectos", "Grupo estudiantil", "Semillero", "Otro");
    }

    @FXML
    private void filtrarOportunidades() {
        String filtro = interesesComboBox.getValue();
        String tagsFiltro = SeleccionInteresComboBox.getValue();
        ArrayList<Oportunidad> oportunidades = conexionBaseDatos.getOportunidadesDb();
        ArrayList<Oportunidad> filtradas = new ArrayList<>();

        for (Oportunidad oportunidad : oportunidades) {
            if (oportunidad.getTipo().equals(filtro) && oportunidad.getTags().contains(tagsFiltro)) {
                filtradas.add(oportunidad);
            }
        }

        oportunidadesListView.getItems().setAll(filtradas);
    }

    

    public String getSelectedInteres()
    {
        return interesesComboBox.getValue();
    }

    public Oportunidad getSelectedOportunidad()
    {
        return oportunidadesListView.getSelectionModel().getSelectedItem();
    }




}