package UD2.empresa25.actividad3.logica;

import UD2.empresa25.actividad3.persistencia.EmpresaDAO;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class GestorEmpresa {

    private final EmpresaDAO dao;

    public GestorEmpresa(EmpresaDAO dao) {
        this.dao = dao;
    }

    public void altaFamiliar(String nssEmp, String nssFam, String nombre, String apellidos,
                             Date fecha, String parentesco, char sexo) {
        try {
            dao.insertarFamiliar(nssEmp, nssFam, nombre, apellidos, fecha, parentesco, sexo);
            System.out.println("Familiar inserido correctamente.");
        } catch (SQLException e) {
            System.err.println("Erro ao inserir familiar: " + e.getMessage());
        }
    }

    public void insertarVehiculoPropio(String mat, String marca, String modelo, String tipo,
                                       Date fecha, double precio) {
        try {
            int id = dao.insertarVehiculoBase(mat, marca, modelo, tipo);
            dao.insertarVehiculoPropio(id, fecha, precio);
            System.out.println("Vehículo propio insertado con ID " + id);
        } catch (SQLException e) {
            System.err.println("Erro ao inserir vehículo: " + e.getMessage());
        }
    }

    public void insertarVehiculoRenting(String mat, String marca, String modelo, String tipo,
                                        Date inicio, double mensual, int meses) {
        try {
            int id = dao.insertarVehiculoBase(mat, marca, modelo, tipo);
            dao.insertarVehiculoRenting(id, inicio, mensual, meses);
            System.out.println("Vehículo renting insertado con ID " + id);
        } catch (SQLException e) {
            System.err.println("Erro ao inserir vehículo: " + e.getMessage());
        }
    }

    public void cambiarDeptoProyecto(String depto, String prox) {
        try {
            int filas = dao.cambiarDepartamentoProyecto(depto, prox);
            if (filas == 0) {
                System.err.println("Non existe o proxecto ou o departamento.");
            } else {
                System.out.println("Proxecto actualizado.");
            }
        } catch (SQLException e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }

    public void incrementarSalarios(double inc, List<String> nss) {
        try {
            int total = dao.incrementarSalarios(inc, nss);
            System.out.println("Empregados actualizados: " + total);
        } catch (SQLException e) {
            System.err.println("Erro ao actualizar salarios: " + e.getMessage());
        }
    }
}
