package Modelo;

import java.util.ArrayList;

public class Sistema {
    private static ConexionBaseDatos conexionBaseDatos;
    private static ArrayList<Usuario> usuarios;
    private static ArrayList<Oportunidad> oportunidades;

    public Sistema() {
        conexionBaseDatos = new ConexionBaseDatos();
        usuarios = conexionBaseDatos.getUserDb();
        oportunidades = conexionBaseDatos.getOportunidadesDb();
        System.out.println( usuarios);
        System.out.println( oportunidades);
    }

    public static boolean registrarUsuario(String id, String nombre, String apellido, String correo, String contrasena,
                                        Integer edad, String carrera, String universidad) {
        Usuario nuevoUsuario = new Usuario(id, nombre, apellido, correo, contrasena, edad, carrera, universidad);
        boolean guardado = conexionBaseDatos.guardarUsuario(nuevoUsuario);
        if (guardado) {
            usuarios.add(nuevoUsuario);
        }
        return guardado;
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

    {}
    public static boolean crearOportunidad(String id, String nombre, String descripcion, boolean esPrivada, String tags, String tipo, ArrayList<String> miembros, String owner) {
        Oportunidad nuevaOportunidad = new Oportunidad(id, nombre, descripcion, esPrivada, tags, tipo, miembros, owner);
        boolean guardado = conexionBaseDatos.guardarOportunidad(nuevaOportunidad);
        if (guardado) {
            oportunidades.add(nuevaOportunidad);
        }
        return guardado;
    }



}
