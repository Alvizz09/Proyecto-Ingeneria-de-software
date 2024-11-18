package Modelo;

import java.util.ArrayList;

public class Sistema {
    private static ConexionBaseDatos conexionBaseDatos;
    private static ArrayList<Usuario> usuarios;
    private ArrayList<Oportunidad> oportunidades;

    public Sistema() {
        conexionBaseDatos = new ConexionBaseDatos();
        usuarios = conexionBaseDatos.getUserDb();
        oportunidades = conexionBaseDatos.getOportunidadesDb();
        System.out.println(usuarios.toString());
        System.out.println(oportunidades.toString());
    }

    public static void registrarUsuario(String id, String nombre, String apellido, String correo, String contrasena,
                                        Integer edad, String carrera, String universidad) {
        Usuario nuevoUsuario = new Usuario(id, nombre, apellido, correo, contrasena, edad, carrera, universidad);
        usuarios.add(nuevoUsuario);
        conexionBaseDatos.guardarUsuario(nuevoUsuario);
    }

    public static boolean validateUser(String correo, String contraseña) {
        Usuario user = new Usuario();
        boolean encontrado;
        for (Usuario u : usuarios) {
            if (u.getCorreo().equals(correo)) {
                user = u;
            }
        }
        if (user.getContrasena().equals(contraseña)) {
            return true;
        } else
            return false;

    }
}
