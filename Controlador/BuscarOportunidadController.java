package Controlador;

import Modelo.ConexionBaseDatos;
import Modelo.Oportunidad;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.ArrayList;

public class BuscarOportunidadController {

    @FXML
    public ImageView DescubrirButton;

    @FXML
    private ComboBox<String> interesesComboBox, SeleccionInteresComboBox;

    @FXML
    private Button buscarOportunidadButton, confimarSeleccionButton, impulsarButton, volverButton;

    @FXML
    private TextArea TagsBuscarTextArea;

    @FXML
    private TextArea mostrarOportunidades;

    private ConexionBaseDatos conexionBaseDatos = new ConexionBaseDatos();

    @FXML
    private void initialize() {
        interesesComboBox.getItems().addAll("Startup", "Proyectos", "Grupo estudiantil", "Semillero", "Otro");
    }

    @FXML
    private void filtrarOportunidades() {
        String filtro = interesesComboBox.getValue();
        String tagsFiltro = SeleccionInteresComboBox.getValue();
        ArrayList<Oportunidad> oportunidades = conexionBaseDatos.getOportunidadesDb();
        ArrayList<Oportunidad> filtradas = new ArrayList<>();
        ArrayList<String> tagsList = new ArrayList<>();

        for (Oportunidad oportunidad : oportunidades) {
            // Extraer los tags y guardarlos en tagsList

            String[] tagsArray = oportunidad.getTags().split(",");
            for (String tag : tagsArray) {
                tagsList.add(tag.trim());
            }

            // Imprimir la oportunidad actual
            System.out.println("Oportunidad: " + oportunidad.getNombre());
            System.out.println("Tags: " + tagsList);
            System.out.println("Tipo: " + oportunidad.getTipo());

            if (oportunidad.getTipo().equals(filtro)) {
                filtradas.add(oportunidad);
                System.out.println("Esta sirve: " + oportunidad.getNombre());
            }
            else {System.out.println("Esta NO sirve: " + oportunidad.getNombre());}

            // Limpiar la lista de tags para la siguiente iteración
            tagsList.clear();
        }

        mostrarOportunidades(filtradas);
        imprimirOportunidadesEnTerminal(filtradas);

    }

    @FXML
    private void mostrarOportunidades(ArrayList<Oportunidad> oportunidades) {
        StringBuilder sb = new StringBuilder();
        for (Oportunidad oportunidad : oportunidades) {
            sb.append("Nombre: ").append(oportunidad.getNombre()).append("\n");
            sb.append("Descripción: ").append(oportunidad.getDescripcion()).append("\n");
            sb.append("Tags: ").append(oportunidad.getTags()).append("\n");
            sb.append("Tipo: ").append(oportunidad.getTipo()).append("\n");
            sb.append("Miembros: ").append(String.join(", ", oportunidad.getMiembros())).append("\n");
            sb.append("Owner: ").append(oportunidad.getOwner()).append("\n\n");
        }
        mostrarOportunidades.setText(sb.toString());
    }

    @FXML
    private void imprimirOportunidadesEnTerminal(ArrayList<Oportunidad> oportunidades) {
        for (Oportunidad oportunidad : oportunidades) {
            System.out.println("Nombre: " + oportunidad.getNombre());
            System.out.println("Descripción: " + oportunidad.getDescripcion());
            System.out.println("Tags: " + oportunidad.getTags());
            System.out.println("Tipo: " + oportunidad.getTipo());
            System.out.println("Miembros: " + String.join(", ", oportunidad.getMiembros()));
            System.out.println("Owner: " + oportunidad.getOwner());
            System.out.println();
        }
    }

    @FXML
    private void escogerOportunidad(ArrayList<String> nombresAceptadas)
    {
        SeleccionInteresComboBox.getItems().clear();
        SeleccionInteresComboBox.getItems().addAll(nombresAceptadas);
    }

    @FXML
    private void elegirOportunidadButtonOnAction() {
        String seleccionadoNombre = SeleccionInteresComboBox.getValue();
        ArrayList<Oportunidad> oportunidades = conexionBaseDatos.getOportunidadesDb();
        Usuario usuarioActual = Sistema.getUsuarioActual();

        for (Oportunidad oportunidad : oportunidades) {
            if (oportunidad.getNombre().equals(seleccionadoNombre)) {
                oportunidad.getMiembros().add(usuarioActual.getNombre());
                break;
            }
        }
    }

    @FXML
    public String getSelectedInteres()
    {
        return interesesComboBox.getValue();
    }

    @FXML
    private void impulsarButtonOnAction() throws IOException {
        SceneManager.getInstance().switchScene("../recursos/CrearOportunidad.fxml", false);
    }


    @FXML
    private void volverMenu() throws IOException {
        SceneManager.getInstance().switchScene("../recursos/MenuPrincipalView.fxml", false);
    }

}