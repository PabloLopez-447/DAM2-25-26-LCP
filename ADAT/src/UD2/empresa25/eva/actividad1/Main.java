package UD2.empresa25.eva.actividad1;



import UD2.empresa25.eva.actividad1.persistencia.EmpresaDAO;
import UD2.empresa25.eva.actividad1.utiles.TipoSGBD;
import UD2.empresa25.eva.actividad1.utiles.Utilidades;

import java.sql.Connection;

public class Main {

    public static void main(String[] args) {

        ejecutar(TipoSGBD.SQLSERVER, "BDEMPRESA25", "sa", "abc123.");
        //ejecutar(TipoSGBD.MYSQL, "BDEMPRESA25", "root", "abc123.");
        //ejecutar(TipoSGBD.SQLITE, "bdempresa25.db", null, null);
    }

    private static void ejecutar(TipoSGBD tipo, String bd, String user, String pass) {

        System.out.println("\nSGBD: " + tipo);

        try (Connection con = Utilidades.getConnection(tipo, bd, user, pass)) {

            EmpresaDAO dao = new EmpresaDAO(con);

            dao.mostrarDepartamentos();
            dao.insertarProyecto(80, "PROYECTO2", "PONTEVEDRA", 3);
            dao.crearFamiliares();
            dao.crearVehiculos();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
