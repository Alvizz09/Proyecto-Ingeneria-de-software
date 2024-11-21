package Controlador;

import Modelo.ConexionBaseDatos;
import Modelo.Oportunidad;
import Modelo.Sistema;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.UUID;

public class OportunidadController
{

    @FXML
    private TextField nameInput;
    @FXML
    private TextArea descriptionInput;
    @FXML
    private Button nextButton;

    private ConexionBaseDatos conexionBaseDatos = new ConexionBaseDatos();

    @FXML
    private void initialize() {
        // Inicialización si es necesario
    }

    @FXML
    private void crearOportunidad() {
        try {
            String idOportunidad = UUID.randomUUID().toString();
            String nombre = nameInput.getText().trim();
            String descripcion = descriptionInput.getText().trim();
            boolean esPrivada = false;
            String tags = "";
            String tipo = "";
            ArrayList<String> miembros = new ArrayList<>();
            String owner = "";

            if (nombre.isEmpty() || descripcion.isEmpty()) {
                mostrarMensaje("Todos los campos deben estar llenos");
                return;
            }

            boolean creado = Sistema.crearOportunidad(idOportunidad, nombre, descripcion, esPrivada, tags, tipo, miembros, owner);
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
}