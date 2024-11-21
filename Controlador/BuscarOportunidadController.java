package Controlador;

import Modelo.ConexionBaseDatos;
import Modelo.Oportunidad;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class BuscarOportunidadController {

    @FXML
    public ImageView DescubrirButton;
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
        mostrarOportunidades(filtradas);
    }

    private void mostrarOportunidades(ArrayList<Oportunidad> oportunidades) {
        StringBuilder sb = new StringBuilder();
        for (Oportunidad oportunidad : oportunidades) {
            sb.append("Nombre: ").append(oportunidad.getNombre()).append("\n");
            sb.append("Descripción: ").append(oportunidad.getDescripcion()).append("\n");
            sb.append("Tags: ").append(String.join(", ", oportunidad.getTags())).append("\n");
            sb.append("Tipo: ").append(oportunidad.getTipo()).append("\n");
            sb.append("Miembros: ").append(String.join(", ", oportunidad.getMiembros())).append("\n");
            sb.append("Owner: ").append(oportunidad.getOwner()).append("\n\n");
        }
        mostrarOportunidades.setText(sb.toString());
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