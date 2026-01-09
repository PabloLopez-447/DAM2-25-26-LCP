package UD2.empresa25.dtos;

public class DepartamentoDirectorDTO {
    int numeroDepartamento;
    String nomDepartamento;
    String nomDirector;

    public DepartamentoDirectorDTO(int numeroDepartamento, String nomDepartamento, String nomDirector) {
        this.numeroDepartamento = numeroDepartamento;
        this.nomDepartamento = nomDepartamento;
        this.nomDirector = nomDirector;
    }

    @Override
    public String toString() {
        return "DepartamentoDirectorDTO{" +
                "numeroDepartamento=" + numeroDepartamento +
                ", nomDepartamento='" + nomDepartamento + '\'' +
                ", nomDirector='" + nomDirector + '\'' +
                '}';
    }
}
