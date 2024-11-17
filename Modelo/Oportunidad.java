package Modelo;

public class Oportunidad {
    private String idOportunidad;
    private String nombre;
    private String descripcion;
    private boolean esPrivada;
    private String tags[];
    private String tipo;

    // Constructor
    public Oportunidad(String idOportunidad, String nombre, String descripcion, boolean esPrivada, String[] tags, String tipo) {
        this.idOportunidad = idOportunidad;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.esPrivada = esPrivada;
        this.tags = tags;
        this.tipo = tipo;
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

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}

