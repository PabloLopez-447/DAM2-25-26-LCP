package UD1.ACT5.logica;

import UD1.ACT5.clases.Equipos;
import UD1.ACT5.util.EquiposJAXB;

public class GestorEquipos {
    EquiposJAXB equiposJAXB = new EquiposJAXB();

    public void mostrarTodosEquiposJAXB(String rutaXML) {
        try{
            Equipos equipos = equiposJAXB.leerEquipos(rutaXML);
            if (equipos == null || equipos.getEquipo() == null){
                System.out.println("no hay equipos");
            }
            else {
                System.out.println(equipos);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
