package Controlador;

import Modelo.Sistema;
import Modelo.EmailSender;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.UUID;

public class RegistroController {
    @FXML
    private Button iniciarButton, loginButton, registerButton, volverButton, nextRegButton, volverRegButton, finishRegButton;
    @FXML
    private TextField emailTextField, passwordTextField, emailRegTextField, passwordRegTextField, nombresRegTextField, ageRegTextField, apellidoRegTextField, carreerTextField, universityTextField, emailField;

    private EmailSender emailSender = new EmailSender();

    private void changeScene(Button button, String fxmlFilePath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFilePath));
            Stage stage = (Stage) button.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        } catch (IOException e) {
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
    public void iniciarButtonOnAction() {
        changeScene(iniciarButton, "../recursos/IniciarSesionView.fxml");
    }

    @FXML
    public void volverButtonOnAction() {
        changeScene(volverButton, "../recursos/MainView.fxml");
    }

    @FXML
    public void registerButtonOnAction() {
        changeScene(registerButton, "../recursos/RegistrarUsuarioView.fxml");
    }

    @FXML
    public void volverRegButtonOnAction() {
        changeScene(volverRegButton, "../recursos/IniciarSesionView.fxml");
    }

    @FXML
    public void nextRegButtonOnAction() {
        changeScene(nextRegButton, "../recursos/RegistrarUsuario2View.fxml");
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
            String carrera = obtenerTexto(carreerTextField);
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
}