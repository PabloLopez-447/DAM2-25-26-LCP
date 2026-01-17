package UD2.empresa25.actividad1.clases;

public class EmpleadoProyecto {

    private String nssEmpleado;
    private int numProyecto;
    private Integer horas;

    public EmpleadoProyecto(String nssEmpleado, int numProyecto, Integer horas) {
        this.nssEmpleado = nssEmpleado;
        this.numProyecto = numProyecto;
        this.horas = horas;
    }

    public String getNssEmpleado() { return nssEmpleado; }
    public int getNumProyecto() { return numProyecto; }
    public Integer getHoras() { return horas; }
}
