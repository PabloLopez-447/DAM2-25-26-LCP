package UD1.ACT5.corredoresYEquipos.logica;

import UD1.ACT5.corredoresYEquipos.clases.Corredores;
import UD1.ACT5.corredoresYEquipos.util.CorredoresJAXB;

public class GestorCorredores {
    CorredoresJAXB corredoresJAXB = new CorredoresJAXB();

    public static void main(String[] args) {
        GestorCorredores gestorCorredores = new GestorCorredores();
        gestorCorredores.mostrarTodosCorredoresJAXB("D:\\plopecous\\DAM2-25-26-LCP\\ADAT\\src\\UD1\\ACT5\\corredoresYEquipos\\xml\\Corredores.xml");
    }

    public void mostrarTodosCorredoresJAXB(String rutaXML) {
        try {
            Corredores corredores = corredoresJAXB.leerCorredores(rutaXML);
            if (corredores == null) {
                System.out.println("no hay corredores");
            } else {
                System.out.println(corredores);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
