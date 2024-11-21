package Modelo;

public class Carreras {
    private String nombreCarrera;

    public Carreras(String nombreCarrera){
        this.nombreCarrera = nombreCarrera;
    }
    public String getNombreCarrera(){
        return nombreCarrera;
    }
    public void setNombreCarrera(String nombreCarrera){
        this.nombreCarrera = nombreCarrera;
    }
}
