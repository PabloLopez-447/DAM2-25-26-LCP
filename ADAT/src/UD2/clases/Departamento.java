package UD2.clases;

public class Departamento {

    private int numDepartamento;
    private String nombre;
    private String nssDirector;

    public Departamento(int numDepartamento, String nombre, String nssDirector) {
        this.numDepartamento = numDepartamento;
        this.nombre = nombre;
        this.nssDirector = nssDirector;
    }

    public int getNumDepartamento() { return numDepartamento; }
    public String getNombre() { return nombre; }
    public String getNssDirector() { return nssDirector; }
}
