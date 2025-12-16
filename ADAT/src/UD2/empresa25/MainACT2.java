package UD2.empresa25;

import UD2.empresa25.logica.GestorConsultas;
import UD2.empresa25.otros.TipoSGBD;
import UD2.empresa25.persistencia.GestorConexion;

import java.sql.Connection;

public class MainACT2 {
    public static void main(String[] args) {
        //         CAMBIA AQUÍ EL SGBD QUE QUIERAS PROBAR
//         --- Para SQLite ---
//        TipoSGBD tipo = TipoSGBD.SQLITE;
//        String bd = "C:\\sqlite3\\bdempresa25.db";
//        String user = null;
//        String pass = null;

//         --- Para MySQL ---
         TipoSGBD tipo = TipoSGBD.MYSQL;
         String bd = "bdempresa25";
         String user = "root";
         String pass = "abc123.";

//         --- Para SQL Server ---
//         TipoSGBD tipo = TipoSGBD.SQLSERVER;
//         String bd = "BDEMPRESA25";
//         String user = "sa";
//         String pass = "abc123.";

        try (Connection con = GestorConexion.getConnection(tipo, bd, user, pass)) {
            System.out.println("Conectado correctamente a " + tipo);

            GestorConsultas g = new GestorConsultas(con);
            g.exEjercicio1().forEach(System.out::println);

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
