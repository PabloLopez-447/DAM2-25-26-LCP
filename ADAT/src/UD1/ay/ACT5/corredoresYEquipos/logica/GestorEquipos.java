package UD1.ay.ACT5.corredoresYEquipos.logica;

import UD1.ay.ACT5.corredoresYEquipos.clases.Equipos;
import UD1.ay.ACT5.corredoresYEquipos.util.EquiposJAXB;

public class GestorEquipos {
    EquiposJAXB equiposJAXB = new EquiposJAXB();

    public static void main(String[] args) {
        GestorEquipos gestorEquipos = new GestorEquipos();
        gestorEquipos.mostrarTodosEquiposJAXB("D:\\plopecous\\DAM2-25-26-LCP\\ADAT\\src\\UD1\\ACT5\\corredoresYEquipos\\xml\\Equipos.xml");
    }

    public void mostrarTodosEquiposJAXB(String rutaXML) {
        try {
            Equipos equipos = equiposJAXB.leerEquipos(rutaXML);
            if (equipos == null || equipos.getEquipo() == null) {
                System.out.println("no hay equipos");
            } else {
                System.out.println(equipos);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
