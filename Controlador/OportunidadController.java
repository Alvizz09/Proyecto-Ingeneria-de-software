package Controlador;

import Modelo.ConexionBaseDatos;
import Modelo.Oportunidad;
import Modelo.Sistema;
import Modelo.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

public class OportunidadController {

    @FXML
    private TextField nameInput;
    @FXML
    private TextArea descriptionInput;
    @FXML
    private TextArea creartagsInput;
    @FXML
    private Button nextButton;
    @FXML
    private Button backButton;
    @FXML
    private ComboBox<String> tipoOportunidadComboBox;

    ObservableList<String> TipoOportunidad = FXCollections.observableArrayList("Startups", "Proyectos", "Grupo estudiantil", "Semillero", "Otro");

    private ConexionBaseDatos conexionBaseDatos = new ConexionBaseDatos();

    @FXML
    private void initialize() {
        tipoOportunidadComboBox.setItems(TipoOportunidad);
        nextButton.setOnAction(event -> {
            try {
                crearOportunidad();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        backButton.setOnAction(event -> {
            try {
                volver();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void crearOportunidad() throws IOException {
        String id = UUID.randomUUID().toString();
        String nombre = nameInput.getText();
        String descripcion = descriptionInput.getText();
        boolean esPrivada = false;
        String tags = creartagsInput.getText();
        String tipo = tipoOportunidadComboBox.getValue();
        ArrayList<String> miembros = new ArrayList<>();
        String owner = Sistema.getUsuarioActual().getIdUsuario();

        boolean creada = Sistema.crearOportunidad(id, nombre, descripcion, esPrivada, tags, tipo, miembros, owner);
        if (creada) {

            mostrarMensaje("Oportunidad creada exitosamente.");
            volver();
        } else {
            mostrarMensaje("Error al crear la oportunidad.");
        }
    }

    private void volver() throws IOException {
        SceneManager.getInstance().switchScene("../recursos/MenuPrincipalView.fxml", false);
    }

    private void mostrarMensaje(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Mensaje");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}