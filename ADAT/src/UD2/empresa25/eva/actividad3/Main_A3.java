package UD2.empresa25.eva.actividad3;
import UD2.empresa25.eva.actividad2.model.Familiar;
import UD2.empresa25.eva.actividad2.utiles.TipoSGBD;
import UD2.empresa25.eva.actividad2.utiles.Utilidades;
import UD2.empresa25.eva.actividad3.persistencia.EmpresaDAO;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

//public class Main_A3 {

//    public static void main(String[] args) {

//        String bd = "BDEmpresa25";
//        String usuario = "sa";
//        String password = "abc123.";
//
//        try (Connection con =
//                     Utilidades.getConnection(TipoSGBD.SQLSERVER, bd, usuario, password)) {
//
//            EmpresaDAO.ejercicio1(con, "0010010", "Eva", "Filla");
//            Familiar f = new Familiar("0010010","0011111","Eva","Otero Názara",Date.valueOf("1998-01-30"),"Filla", 'M');
//
//            EmpresaDAO.ejercicio1(con, f);

//            Vehiculo v1 = new VehiculoPropio(
//                    "1111AAA", "Toyota", "Corolla", "G",
//                    Date.valueOf("2023-05-15"), 15000);
//
//            Vehiculo v2 = new VehiculoRenting(
//                    "2222BBB", "Ford", "Fiesta", "D",
//                    Date.valueOf("2023-07-01"), 200, 24);
//
//            EmpresaDAO.ejercicio2(con, v1);
//            EmpresaDAO.ejercicio2(con, v2);
//
//            EmpresaDAO.ejercicio3(con, "INFORMÁTICA", "PORTAL");
//            EmpresaDAO.ejercicio4(con, 10);
//
//            List<String> empregados =
//                    List.of("11111111A", "22222222B", "33333333C");
//
//            EmpresaDAO.ejercicio5(con, 100, empregados);
//
//            Proxecto p = new Proxecto(50, "PROXECTO50", "VIGO", 1);
//            EmpresaDAO.ejercicio6(con, p);
//
//            EmpresaDAO.ejercicio7(con, 50, 1);
//            EmpresaDAO.ejercicio8(con, 1);

//            EmpresaDAO.borrarEmpleadoRecursivoAlternativo(
//                    con, "1111111", "22333421"
//            );

//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
