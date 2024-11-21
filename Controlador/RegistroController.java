package Controlador;

import Modelo.Sistema;
import Modelo.EmailSender;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.util.UUID;

public class RegistroController {
    @FXML
    private Button iniciarButton, loginButton, registerButton, volverButton, nextRegButton, volverRegButton, finishRegButton;
    @FXML
    private TextField emailTextField, passwordTextField, emailRegTextField, passwordRegTextField, nombresRegTextField, ageRegTextField, apellidoRegTextField, carreerTextField, universityTextField, emailField;

    @FXML
    private ComboBox ComboCarrerButton;

    private EmailSender emailSender = new EmailSender();

    ObservableList<String> carreraList = FXCollections.observableArrayList("Ingenieria sistemas","Administrracion","Artes");

    private void mostrarMensaje(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Mensaje");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @FXML
    public void iniciarButtonOnAction()
    {
        try {
            SceneManager.getInstance().switchScene("../recursos/IniciarSesionView.fxml", false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void volverButtonOnAction() {
        try {
            SceneManager.getInstance().switchScene("../recursos/MainView.fxml", false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void registerButtonOnAction() {
        try {
            SceneManager.getInstance().switchScene("../recursos/RegistrarUsuarioView.fxml", false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void volverRegButtonOnAction() {
        try {
            SceneManager.getInstance().switchScene("../recursos/IniciarSesionView.fxml", false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void registrarUsuario() {
        try {
            String idUsuario = UUID.randomUUID().toString();
            String nombre = obtenerTexto(nombresRegTextField);
            String apellido = obtenerTexto(apellidoRegTextField);
            String correo = obtenerTexto(emailRegTextField);
            String contrasena = obtenerTexto(passwordRegTextField);
            Integer edad = obtenerEdad(ageRegTextField);
            String carrera = obtenerTextoComboBox(ComboCarrerButton);
            String universidad = obtenerTexto(universityTextField);

            boolean registrado = Sistema.registrarUsuario(idUsuario, nombre, apellido, correo, contrasena, edad, carrera, universidad);
            if (registrado) {
                mostrarMensaje("Usuario registrado con éxito");
                // Enviar correo de confirmación
                emailSender.sendEmail(correo, "Registro Exitoso", "Gracias por registrarte en nuestro servicio.");
            } else {
                mostrarMensaje("Error: El correo ya está registrado.");
            }
        } catch (NumberFormatException e) {
            mostrarMensaje("Ingrese un número válido en la edad");
        } catch (IllegalArgumentException e) {
            mostrarMensaje(e.getMessage());
        } catch (Exception e) {
            mostrarMensaje("Error al registrar usuario");
        }
    }

    private String obtenerTexto(TextField textField) {
        String texto = textField.getText().trim();
        if (texto.isEmpty()) {
            throw new IllegalArgumentException("Todos los campos deben estar llenos");
        }
        return texto;
    }

    private String obtenerTextoComboBox(ComboBox comboBox) {
        String texto = comboBox.getSelectionModel().getSelectedItem().toString();
        if(texto.isEmpty()) {
            throw new IllegalArgumentException("Todos los campos deben estar llenos");
        }
        return texto;
    }

    private Integer obtenerEdad(TextField textField) {
        try {
            return Integer.parseInt(textField.getText().trim());
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Ingrese un número válido en la edad");
        }
    }

    @FXML
    public void iniciarSesion() {
        String correo = emailTextField.getText().trim();
        String contrasena = passwordTextField.getText().trim();
        if (Sistema.validateUser(correo, contrasena))
            mostrarMensaje("Inicio de sesion exitoso");
        else
            mostrarMensaje("Contraseña invalida");
    }

    public void ListarCarreras(Event event) {
        LlenarCombo(ComboCarrerButton,carreraList);
    }

    public static void LlenarCombo(ComboBox<String> cmbCarreras, ObservableList<String> infoCarreras) {
        cmbCarreras.setItems(infoCarreras);
    }
}