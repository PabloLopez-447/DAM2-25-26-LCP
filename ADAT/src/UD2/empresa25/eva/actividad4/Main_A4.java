package UD2.empresa25.eva.actividad4;


import UD2.empresa25.eva.actividad2.utiles.TipoSGBD;
import UD2.empresa25.eva.actividad2.utiles.Utilidades;
import UD2.empresa25.eva.actividad4.logica.GestorEmpresa;

import java.sql.Connection;

public class Main_A4 {

    public static void main(String[] args) {
        String bd = "BDEmpresa25";
        String usuario = "sa";
        String password = "abc123.";

        try (Connection con = Utilidades.getConnection(TipoSGBD.SQLSERVER, bd, usuario, password)) {

            //1 -  cambio de domicilio de un empleado
//            GestorEmpresa.ejercicio1(con,
//                    "0010010",          // nss existente
//                    "Rua Madrid",
//                    46,
//                    "3A",
//                    "36966",
//                    "Sanxenxo"
//            );

            // 2 - obtencion de datos de un proyecto
//            GestorEmpresa.ejercicio2(con, 1);                  // num proxecto existente);

            // 3 - departamentos que controlan n o más proyectos
//            GestorEmpresa.ejercicio3(con, 2);

            //4 - numero de empleados de un departamento
            GestorEmpresa.ejercicio4(con, "PERSOAL");

            // 5 - empleados asociados a un proyecto
            //GestorEmpresa.ejercicio6(con, 1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
