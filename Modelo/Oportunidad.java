package Modelo;

import java.util.ArrayList;
import java.util.Arrays;

public class Oportunidad {
    private String idOportunidad;
    private String nombre;
    private String descripcion;
    private boolean esPrivada;
    private String tags;
    private String tipo;
    private ArrayList<String> miembros;
    private String owner;

    // Constructor
    public Oportunidad(String idOportunidad, String nombre, String descripcion, boolean esPrivada, String tags, String tipo, ArrayList<String> miembros, String owner) {
        this.idOportunidad = idOportunidad;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.esPrivada = esPrivada;
        this.tags = tags;
        this.tipo = tipo;
        this.miembros = miembros;
        this.owner = owner;
    }

    // Getters and Setters
    public String getIdOportunidad() {
        return idOportunidad;
    }

    public void setIdOportunidad(String idOportunidad) {
        this.idOportunidad = idOportunidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isEsPrivada() {
        return esPrivada;
    }

    public void setEsPrivada(boolean esPrivada) {
        this.esPrivada = esPrivada;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public ArrayList<String> getMiembros() {
        return miembros;
    }

    public void setMiembros(ArrayList<String> miembros) {
        this.miembros = miembros;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Oportunidad{" +
                "idOportunidad='" + idOportunidad + '\'' +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", esPrivada=" + esPrivada +
                ", tags='" + tags + '\'' +
                ", tipo='" + tipo + '\'' +
                ", miembros=" + miembros +
                ", owner=" + owner +
                '}';
    }
}