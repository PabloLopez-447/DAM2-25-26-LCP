package UD2.empresa25.actividad1;

import UD2.empresa25.actividad1.logica.EmpresaDAO;
import UD2.empresa25.TipoSGBD;
import UD2.empresa25.ordenar.persistencia.GestorConexion;

import java.sql.Connection;

public class MainACT1 {

    public static void main(String[] args) {

//         CAMBIA AQUÍ EL SGBD QUE QUIERAS PROBAR
//         --- Para SQLite ---
//        TipoSGBD tipo = TipoSGBD.SQLITE;
//        String bd = "C:\\sqlite3\\bdempresa25.db";
//        String user = null;
//        String pass = null;

//         --- Para MySQL ---
//         TipoSGBD tipo = TipoSGBD.MYSQL;
//         String bd = "bdempresa25";
//         String user = "root";
//         String pass = "abc123.";

//         --- Para SQL Server ---
         TipoSGBD tipo = TipoSGBD.SQLSERVER;
         String bd = "BDEMPRESA25";
         String user = "sa";
         String pass = "abc123.";

        try (Connection con = GestorConexion.getConnection(tipo, bd, user, pass)) {

            System.out.println("Conectado correctamente a " + tipo);

            // DAO
            EmpresaDAO dao = new EmpresaDAO(con, tipo);

            // ==========================
            // EJERCICIO 5 → METADATOS
            // ==========================
            System.out.println("Tablas existentes:");
            System.out.println(GestorConexion.obtenerMetadatos(con));

            // ==========================
            // EJERCICIO 6 → CONSULTA
            // ==========================
            System.out.println("\nDepartamentos:");
            dao.obtenerDepartamentos()
                    .forEach(d -> System.out.println(
                            d.getNumDepartamento() + " - " + d.getNombre()
                    ));

            // ==========================
            // EJERCICIO 7 → DDL FAMILIARES
            // ==========================
            System.out.println("\nCreando tabla FAMILIAR...");
            dao.crearTablaFamiliares();
            System.out.println("Tabla FAMILIAR creada.");

            // ==========================
            // EJERCICIO 7 → DDL VEHÍCULOS
            // ==========================
            System.out.println("\nCreando tablas VEHÍCULOS...");
            dao.crearTablasVehiculos();
            System.out.println("Tablas VEHÍCULOS creadas.");

            // ==========================
            // FIN
            // ==========================
            System.out.println("\nTODO OK. Pruebas completadas.");

        } catch (Exception e) {
            System.err.println("Error en la ejecución:");
            e.printStackTrace();
        }
    }
}
