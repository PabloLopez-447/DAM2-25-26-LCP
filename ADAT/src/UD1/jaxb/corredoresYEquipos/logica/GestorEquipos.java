package UD1.jaxb.corredoresYEquipos.logica;

import UD1.jaxb.corredoresYEquipos.clases.equipos.Equipo;
import UD1.jaxb.corredoresYEquipos.persistencia.EquiposJAXB;

import java.util.ArrayList;
import java.util.List;

public class GestorEquipos {
    String ruta;

    public GestorEquipos(String ruta) {
        this.ruta = ruta;
    }

    public void visualizarEquipos(){
        EquiposJAXB equiposJAXB = new EquiposJAXB();

        List<Equipo> equiposes = equiposJAXB.listarEquipos(ruta);

        for(Equipo equipo : equiposes){
            System.out.println(equipo);
        }
    }

    public static void main(String[] args) {
        GestorEquipos g = new GestorEquipos("D:\\Datos\\DAM\\DAM2-25-26-LCP\\ADAT\\src\\UD1\\jaxb\\xml\\Equipos.xml");
        g.visualizarEquipos();
    }
}
