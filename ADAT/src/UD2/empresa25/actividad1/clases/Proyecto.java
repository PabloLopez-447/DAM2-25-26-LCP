package UD2.empresa25.actividad1.clases;

public class Proyecto {

    private int numProyecto;
    private String nombre;
    private String lugar;
    private int departamento;

    public Proyecto(int numProyecto, String nombre, String lugar, int departamento) {
        this.numProyecto = numProyecto;
        this.nombre = nombre;
        this.lugar = lugar;
        this.departamento = departamento;
    }

    public int getNumProyecto() {
        return numProyecto;
    }

    public void setNumProyecto(int numProyecto) {
        this.numProyecto = numProyecto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public int getDepartamento() {
        return departamento;
    }

    public void setDepartamento(int departamento) {
        this.departamento = departamento;
    }
}
