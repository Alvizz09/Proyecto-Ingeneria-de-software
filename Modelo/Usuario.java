package Modelo;

import java.util.Date;

public class Usuario {
    private String idUsuario;
    private String nombre;
    private String apellido;
    private String correo;
    private String contrasena;
    private Integer edad;
    private String carrera;
    private String universidad;

    public Usuario(String idUsuario, String nombre, String apellido, String correo, String contrasena, Integer edad, String carrera, String universidad) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.contrasena = contrasena;
        this.edad = edad;
        this.carrera = carrera;
        this.universidad = universidad;
    }

public String getIdUsuario() {
    return idUsuario;
}

public void setIdUsuario(String idUsuario) {
    this.idUsuario = idUsuario;
}

public String getNombre() {
    return nombre;
}

public void setNombre(String nombre) {
    this.nombre = nombre;
}

public String getApellido() {
    return apellido;
}

public void setApellido(String apellido) {
    this.apellido = apellido;
}

public String getCorreo() {
    return correo;
}

public void setCorreo(String correo) {
    this.correo = correo;
}

public String getContrasena() {
    return contrasena;
}

public void setContrasena(String contrasena) {
    this.contrasena = contrasena;
}

public Integer getEdad() {
    return edad;
}

public void setEdad(Integer edad) {
    this.edad = edad;
}

public String getCarrera() {
    return carrera;
}

public void setCarrera(String carrera) {
    this.carrera = carrera;
}

public String getUniversidad() {
    return universidad;
}

public void setUniversidad(String universidad) {
    this.universidad = universidad;
}

}
