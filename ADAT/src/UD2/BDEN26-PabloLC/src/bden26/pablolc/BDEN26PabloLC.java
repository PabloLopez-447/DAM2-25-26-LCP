//package bden26.pablolc;
//
//import bden26.pablolc.Clases.Documental;
//import bden26.pablolc.Clases.Fotografia;
//import bden26.pablolc.logica.GestorFotografias;
//
//import java.sql.Connection;
//import java.sql.Date;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
////Pablo López Couso DNI: 77550221V
//public class BDEN26PabloLC {
//
//    public static void main(String[] args) {
//        List<Fotografia> fotos = new ArrayList<>();
//        Documental doc = new Documental(0,0,0,"doc", "3x3", "r", Date.valueOf(LocalDate.now()), "tipo");
//        fotos.add(doc);
//        try (Connection con = GestorConexion.getConnection("BDEXPOSICION26", "sa", "abc123.")){
//            GestorFotografias g = new GestorFotografias(con);
//            g.ejercicio1();
//            g.ejercicio2("NUNO MARTINEZ", "INVISIBLE", fotos);
//            g.ejercicio3("FASCINUS", "INVISIBLE");
//        }catch (Exception e){
//
//        }
//    }
//
//}
