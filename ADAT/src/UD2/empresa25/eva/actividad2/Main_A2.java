package UD2.empresa25.eva.actividad2;

import UD2.empresa25.eva.actividad2.persistencia.EmpresaDAO;
import UD2.empresa25.eva.actividad2.utiles.Utilidades;
import UD2.empresa25.eva.actividad2.utiles.TipoSGBD;
import java.sql.Connection;

public class Main_A2 {

    public static void main(String[] args) {

        ejecutar(TipoSGBD.SQLSERVER, "BDEmpresa25", "sa", "abc123.");
//        ejecutar(TipoSGBD.MYSQL, "BDEmpresa25", "root", "abc123.");
//        ejecutar(TipoSGBD.SQLITE, "C://sqlite3//bdempresa25.db", null, null);
    }


    private static void ejecutar(TipoSGBD tipo, String ruta, String user, String pass) {

        System.out.println("\nSGBD: " + tipo);

        try (Connection con = Utilidades.getConnection(tipo, ruta, user, pass)) {

            EmpresaDAO dao = new EmpresaDAO(con);

            dao.ejercicio1();
            dao.ejercicio2();
            dao.ejercicio3();
            dao.ejercicio4("TÉCNICO");
            dao.ejercicio5("PORTAL", "SANTIAGO");
            dao.ejercicio6();
            dao.ejercicio7(5);
            dao.ejercicio8(2000);
            dao.ejercicio9();
            dao.ejercicio10();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
