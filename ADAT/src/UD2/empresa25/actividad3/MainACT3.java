package UD2.empresa25.actividad3;

import UD2.empresa25.actividad3.logica.GestorEmpresa;
import UD2.empresa25.actividad3.persistencia.EmpresaDAO;
import UD2.empresa25.ordenar.persistencia.GestorConexion;
import UD2.empresa25.TipoSGBD;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

public class MainACT3 {

    public static void main(String[] args) {

        String bd = "BDEMPRESA25";
        String user = "sa";
        String pass = "abc123.";

        try (Connection con = GestorConexion.getConnection(TipoSGBD.SQLSERVER, bd, user, pass)) {

            EmpresaDAO dao = new EmpresaDAO(con);
            GestorEmpresa gestor = new GestorEmpresa(dao);

            gestor.altaFamiliar("1111111", "F001", "Luis", "López Pérez",
                    Date.valueOf("2010-05-10"), "Fillo", 'H');

            gestor.insertarVehiculoPropio(
                    "1234ABC", "Toyota", "Corolla", "G",
                    Date.valueOf("2023-05-15"), 15000
            );

            gestor.insertarVehiculoRenting(
                    "5678XYZ", "Ford", "Fiesta", "D",
                    Date.valueOf("2023-07-01"), 200, 24
            );

            gestor.cambiarDeptoProyecto("INNOVACIÓN", "PORTAL");

            gestor.incrementarSalarios(100, List.of("1010001", "1100222"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
