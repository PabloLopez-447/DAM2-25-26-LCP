package UD2.empresa25.actividad2.dtos;

public class DepartamentoDirectorDTO {

    private int numero;
    private String nombreDepto;
    private String nombreDirector;
    private String apellidosDirector;

    public DepartamentoDirectorDTO(int numero, String nombreDepto,
                                   String nombreDirector, String apellidosDirector) {
        this.numero = numero;
        this.nombreDepto = nombreDepto;
        this.nombreDirector = nombreDirector;
        this.apellidosDirector = apellidosDirector;
    }

    @Override
    public String toString() {
        return numero + " - " + nombreDepto +
                " | Director: " + nombreDirector + " " + apellidosDirector;
    }
}
