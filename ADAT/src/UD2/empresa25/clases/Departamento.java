package UD2.empresa25.clases;

public class Departamento {

    private int numDepartamento;
    private String nombre;
    private String nssDirector;

    public Departamento(int numDepartamento, String nombre, String nssDirector) {
        this.numDepartamento = numDepartamento;
        this.nombre = nombre;
        this.nssDirector = nssDirector;
    }

    public Departamento(int numDepartamento, String nomeDepartamento) {
        this.numDepartamento = numDepartamento;
        this.nombre = nomeDepartamento;
    }

    public int getNumDepartamento() { return numDepartamento; }
    public String getNombre() { return nombre; }
    public String getNssDirector() { return nssDirector; }
}
