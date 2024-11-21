package Modelo;

import java.util.ArrayList;

public class Sistema {
    private static ConexionBaseDatos conexionBaseDatos;
    private static ArrayList<Usuario> usuarios;
    private static ArrayList<Oportunidad> oportunidades;
    private static ArrayList<Carreras> Carreras;
    private static ArrayList<Universidad> Universidades;
    private static Usuario usuarioActual;

    public Sistema() {
        conexionBaseDatos = new ConexionBaseDatos();
        usuarios = conexionBaseDatos.getUserDb();
        oportunidades = conexionBaseDatos.getOportunidadesDb();
        Carreras = conexionBaseDatos.getCarrerasDb();
        Universidades = conexionBaseDatos.getUniversidadesDb();
        System.out.println(usuarios);
        System.out.println(oportunidades);
    }

    public static boolean registrarUsuario(String id, String nombre, String apellido, String correo, String contrasena,
                                           Integer edad, String carrera, String universidad) {
        Usuario nuevoUsuario = new Usuario(id, nombre, apellido, correo, contrasena, edad, carrera, universidad);
        boolean guardado = conexionBaseDatos.guardarUsuario(nuevoUsuario);
        if (guardado) {
            usuarios.add(nuevoUsuario);
            usuarioActual = nuevoUsuario; //El usuario
        }
        return guardado;
    }

    public static boolean validateUser(String correo, String contraseña) {
        Usuario user = null;
        for (Usuario u : usuarios) {
            if (u.getCorreo().equals(correo)) {
                user = u;
                break;
            }
        }
        if (user != null && user.getContrasena().equals(contraseña)) {
            usuarioActual = user; //Establecemos el usuario
            return true;
        } else {
            return false;
        }
    }

    public static Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public static boolean crearOportunidad(String id, String nombre, String descripcion, boolean esPrivada, String tags, String tipo, ArrayList<String> miembros, String owner) {
        Oportunidad nuevaOportunidad = new Oportunidad(id, nombre, descripcion, esPrivada, tags, tipo, miembros, owner);
        boolean guardado = conexionBaseDatos.guardarOportunidad(nuevaOportunidad);
        if (guardado) {
            oportunidades.add(nuevaOportunidad);
        }
        return guardado;
    }
}