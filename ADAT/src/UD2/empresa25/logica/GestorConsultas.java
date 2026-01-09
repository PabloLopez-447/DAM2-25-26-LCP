package UD2.empresa25.logica;

import UD2.empresa25.clases.Departamento;
import UD2.empresa25.dtos.DepartamentoDirectorDTO;
import UD2.empresa25.persistencia.ConsultasSQL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GestorConsultas {
    Connection con;
    ConsultasSQL consultasSQL;

    public GestorConsultas(Connection con) {
        this.con = con;
        consultasSQL = new ConsultasSQL(con);
    }

    public List<Departamento> exEjercicio1() throws SQLException {
        List<Departamento> departamentos = new ArrayList<>();

        try (ResultSet rs = consultasSQL.ejercicio1()) {
            while (rs.next()) {
                departamentos.add(new Departamento(
                        rs.getInt("NumDepartamento"),
                        rs.getString("NomeDepartamento"))
                );
            }
        }

        return departamentos;
    }

    public List<DepartamentoDirectorDTO> exEjercicio2() throws SQLException {
        List<DepartamentoDirectorDTO> departamentosDirector = new ArrayList<>();
        try (ResultSet rs = consultasSQL.ejercicio2()) {
            while (rs.next()) {
                departamentosDirector.add(new DepartamentoDirectorDTO(
                        rs.getInt("NumDepartamento"),
                        rs.getString("NomeDepartamento"),
                        rs.getString("Nome")));
            }
        }
        return departamentosDirector;
    }
}
