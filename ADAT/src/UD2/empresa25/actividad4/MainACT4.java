package UD2.empresa25.actividad4;

import UD2.empresa25.actividad4.logica.GestorEmpresa;
import UD2.empresa25.actividad4.persistencia.EmpresaDAO;
import UD2.empresa25.GestorConexion;
import UD2.empresa25.TipoSGBD;

import java.sql.Connection;

public class MainACT4 {

    public static void main(String[] args) {

        String bd = "BDEMPRESA25";
        String user = "sa";
        String pass = "abc123.";

        try (Connection con = GestorConexion.getConnection(TipoSGBD.SQLSERVER, bd, user, pass)) {

            EmpresaDAO dao = new EmpresaDAO(con);
            GestorEmpresa gestor = new GestorEmpresa(dao);

            gestor.inicializar();

            gestor.cambiarDomicilio("1111111", "Nova Rúa", "3A", "12", "36201", "Vigo");
            gestor.mostrarDatosProxecto(1);
            gestor.departamentosConProxectos(1);
            gestor.numeroEmpDepartamento("INNOVACIÓN");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
