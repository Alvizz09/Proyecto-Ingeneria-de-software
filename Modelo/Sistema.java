package Modelo;

import java.util.ArrayList;

public class Sistema {
    private ConexionBaseDatos conexionBaseDatos;
    private ArrayList<Usuario> usuarios;
    private ArrayList<Oportunidad> oportunidades;

    public Sistema() {
        conexionBaseDatos = new ConexionBaseDatos();
        usuarios =conexionBaseDatos.getUserDb();
        oportunidades = conexionBaseDatos.getOportunidadesDb();
        System.out.println(usuarios.toString());
        System.out.println(oportunidades.toString());
    }

    public void registrarUsuario(String nombre, String apellido, String correo, String contrasena,
                                 Integer edad, String carrera, String universidad) {
        Usuario nuevoUsuario = new Usuario(nombre, apellido, correo, contrasena, edad, carrera, universidad);
        conexionBaseDatos.guardarUsuario(nuevoUsuario);
    }
}
