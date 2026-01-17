package UD2.empresa25.actividad2;

import java.sql.Connection;
import UD2.empresa25.actividad2.persistencia.EmpresaDAO;
import UD2.empresa25.actividad2.logica.GestorEmpresa;
import UD2.empresa25.GestorConexion;
import UD2.empresa25.TipoSGBD;

public class MainACT2 {

    public static void main(String[] args) {
        String bd = "BDEMPRESA25";
        String user = "sa";
        String pass = "abc123.";

        try (Connection con = GestorConexion.getConnection(
                TipoSGBD.SQLSERVER, bd, user, pass)) {

            EmpresaDAO dao = new EmpresaDAO(con);
            GestorEmpresa gestor = new GestorEmpresa(dao);
            gestor.ejecutarTodo();

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
