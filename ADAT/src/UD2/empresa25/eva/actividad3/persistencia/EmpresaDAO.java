package UD2.empresa25.eva.actividad3.persistencia;

import UD2.empresa25.eva.actividad2.model.*;
import UD2.empresa25.eva.actividad3.logica.GestorEmpresa;

import java.sql.Connection;
import java.util.List;

public class EmpresaDAO {

    public static void ejercicio1(Connection con, Familiar f) {
        System.out.println("EJERCICIO 1");
        int r = GestorEmpresa.insertarFamiliar(con, f);
        System.out.println("resultado: " + r);
    }

    public static void ejercicio2(Connection con, Vehiculo v) {
        System.out.println("EJERCICIO 2");
        int r = GestorEmpresa.insertarVehiculo(con, v);
        System.out.println("resultado: " + r);
    }

    public static void ejercicio3(Connection con, String d, String p) {
        System.out.println("EJERCICIO 3");
        System.out.println("resultado: " +
                GestorEmpresa.cambiarDepartamentoProyecto(con, d, p));
    }

    public static void ejercicio4(Connection con, int n) {
        System.out.println("EJERCICIO 4");
        System.out.println("resultado: " +
                GestorEmpresa.eliminarProxecto(con, n));
    }

    public static void ejercicio5(Connection con, double i, List<String> l) {
        System.out.println("EJERCICIO 5");
        System.out.println("resultado: " +
                GestorEmpresa.incrementarSalarios(con, i, l));
    }

    public static void ejercicio6(Connection con, Proxecto p) {
        System.out.println("EJERCICIO 6");
        System.out.println("resultado: " +
                GestorEmpresa.insertarProxecto(con, p));
    }

    public static void ejercicio7(Connection con, double i, int d) {
        System.out.println("EJERCICIO 7");
        System.out.println("resultado: " +
                GestorEmpresa.incrementarSalarioDepartamento(con, i, d));
    }

    public static void ejercicio8(Connection con, int n) {
        System.out.println("EJERCICIO 8");
        System.out.println("resultado: " +
                GestorEmpresa.consultaEmpregados(con, n));
    }

    public static void ejercicioExtraBorrado(
            Connection con,
            String nssBorrar,
            String nssSupervisorNuevo,
            String nssDirectorNuevo) throws Exception {

        System.out.println("BORRADO EMPLEADO RECURSIVO");

        int r = GestorEmpresa.borrarEmpleadoRecursivo_sinDTO(
                con, nssBorrar, nssSupervisorNuevo, nssDirectorNuevo
        );

        switch (r) {
            case 0 -> System.out.println("empleado borrado correctamente"); //se supone que siempre va a ser fijo o temporal anyway
            case 1 ->  System.out.println("empleado fijo borrado correctamente");
            case 2 -> System.out.println("Empleado temporal borrado correctamente");
            case -1 -> System.out.println("error de integridad referencial");
            default -> System.out.println("error inesperado");
        }
    }

    public static void borrarEmpleadoRecursivoAlternativo(
            Connection con,
            String nssBorrar,
            String nssSustituto) {

        System.out.println("BORRADO EMPLEADO (RECURSIVO ALTERNATIVO)");

        int resultado = GestorEmpresa.borrarEmpleadoRecursivo_alternativo(
                con, nssBorrar, nssSustituto
        );

        switch (resultado) {
            case 0 ->
                    System.out.println("empregado borrado correctamente");
            case -1 ->
                    System.out.println("erro: non existe relación reflexiva");
            case -99 ->
                    System.out.println("erro inesperado no borrado");
            default ->
                    System.out.println("resultado descoñecido: " + resultado);
        }
    }

}
