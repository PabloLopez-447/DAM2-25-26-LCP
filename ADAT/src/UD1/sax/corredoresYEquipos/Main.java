package UD1.sax.corredoresYEquipos;

import UD1.dom.corredoresYequipos.TipoValidacion;
import UD1.sax.corredoresYEquipos.logica.GestorCorredores;
import UD1.sax.corredoresYEquipos.persistencia.CorredoresSAX;

public class Main {
    public static void main(String[] args) {
        GestorCorredores g = new GestorCorredores(new CorredoresSAX(), "D:\\Datos\\DAM\\DAM2-25-26-LCP\\ADAT\\src\\UD1\\sax\\xml\\Corredores.xml", TipoValidacion.DTD);
        System.out.println("=== TODOS LOS CORREDORES (SAX) ===");
        g.visualizarCorredores();

        System.out.println("\n=== CORREDORES DEL EQUIPO E1 ===");
        g.mostrarCorredoresPorEquipo("E1");

//        GestorEquipos gestor = new GestorEquipos();
//
//        gestor.actualizar(
//                "D:\\Datos\\DAM\\DAM2-25-26-LCP\\ADAT\\src\\UD1\\sax\\xml\\Equipos.xml",
//                "D:\\Datos\\DAM\\DAM2-25-26-LCP\\ADAT\\src\\UD1\\sax\\xml\\ActualizacionesEquipos.xml",
//                "D:\\Datos\\DAM\\DAM2-25-26-LCP\\ADAT\\src\\UD1\\sax\\xml\\equiposUpdate.xml");
    }
}
