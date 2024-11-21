package Controlador;

import Modelo.ConexionBaseDatos;
import Modelo.Oportunidad;
import Modelo.Sistema;
import Modelo.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class OportunidadController {

    @FXML
    private TextField nameInput;
    @FXML
    private TextArea descriptionInput;
    @FXML
    private CheckBox privateCheckBox;
    @FXML
    private Button nextButton;
    @FXML
    private Button backButton;
    @FXML
    private ComboBox<String> tipoOportunidadComboBox;

    private ConexionBaseDatos conexionBaseDatos = new ConexionBaseDatos();

    @FXML
    private void initialize() {
        nextButton.setOnAction(event -> crearOportunidad());
        backButton.setOnAction(event -> {
            try {
                volver();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        tipoOportunidadComboBox.getItems().addAll("Startups", "Proyectos", "Grupo estudiantil", "Semillero", "Otro");
    }

    @FXML
    private void crearOportunidad() {
        try {
            String idOportunidad = UUID.randomUUID().toString();
            String nombre = nameInput.getText().trim();
            String descripcion = descriptionInput.getText().trim();
            boolean esPrivada = privateCheckBox.isSelected();
            String tipo = tipoOportunidadComboBox.getValue();
            ArrayList<String> miembros = new ArrayList<>();
            Usuario usuarioActual = Sistema.getUsuarioActual(); // Obtener el usuario actual
            String owner = usuarioActual.getIdUsuario();

            if (nombre.isEmpty() || descripcion.isEmpty() || tipo == null) {
                mostrarMensaje("Todos los campos deben estar llenos");
                return;
            }

            boolean creado = Sistema.crearOportunidad(idOportunidad, nombre, descripcion, esPrivada, "", tipo, miembros, owner);
            if (creado) {
                mostrarMensaje("Oportunidad creada con éxito");
            } else {
                mostrarMensaje("Error al crear la oportunidad");
            }
        } catch (Exception e) {
            mostrarMensaje("Error al crear la oportunidad");
            e.printStackTrace();
        }
    }

    private void mostrarMensaje(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Mensaje");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @FXML
    private void volver() throws IOException {
        try {
            SceneManager.getInstance().switchScene("../recursos/OportunidadInteresView.fxml", false);
        } catch (IOException e) {
            mostrarMensaje("Error al volver a la vista anterior");
        }
    }
}