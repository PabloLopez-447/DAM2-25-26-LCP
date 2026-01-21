package UD2.empresa25.eva.actividad1.persistencia;


import UD2.empresa25.eva.actividad1.logica.GestorEmpresa;
import UD2.empresa25.eva.actividad1.model.Departamento;

import java.sql.Connection;
import java.util.List;

public class EmpresaDAO {

    private Connection con;

    // CORRECCION: DAO con constructor, como en la A2
    public EmpresaDAO(Connection con) {
        this.con = con;
    }

    // ej 6
    public void mostrarDepartamentos() {
        System.out.println("LISTA DE DEPARTAMENTOS:\n");

        List<Departamento> lista = GestorEmpresa.obtenerDepartamentos(con);
        for (Departamento d : lista) {
            System.out.println(d);
        }
    }

    public void insertarProyecto(int num, String nombre, String lugar, int departamento) {

        System.out.println("\nInsertando proyecto...");

        boolean insertado =
                GestorEmpresa.insertarProyecto(con, num, nombre, lugar, departamento);

        if (insertado)
            System.out.println("OK: Proxecto insertado correctamente.");
        else
            System.out.println("ERRO: Xa existe un proyecto con ese nombre.");
    }

    // ej 7
    public void crearFamiliares() {
        System.out.println("\nFAMILIARES:");
        GestorEmpresa.crearTablaFamiliares(con);
        System.out.println("Tabla FAMILIAR creada con éxito.");
    }

    public void crearVehiculos() {
        System.out.println("\nVEHÍCULOS:");
        GestorEmpresa.crearTablasVehiculos(con);
        System.out.println("Tablas de vehículos creadas correctamente.");
    }
}
