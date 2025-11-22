package UD1.sax.corredoresYEquipos.logica;

import UD1.dom.corredoresYequipos.TipoValidacion;
import UD1.sax.corredoresYEquipos.persistencia.CorredoresSAX;
import UD1.sax.corredoresYEquipos.clases.corredores.Corredor;

import java.util.List;


public class GestorCorredores {
    CorredoresSAX corredoresSAX = new CorredoresSAX();
    String rutaXML;
    TipoValidacion tipoValidacion;

    public GestorCorredores(CorredoresSAX corredoresSAX, String rutaXML, TipoValidacion tipoValidacion) {
        this.corredoresSAX = corredoresSAX;
        this.rutaXML = rutaXML;
        this.tipoValidacion = tipoValidacion;
    }

    public void visualizarCorredores(){
        for (Corredor c : corredoresSAX.cargarTodosCorredores(rutaXML,  tipoValidacion)){
            System.out.println(c);
        }
    }

    public void mostrarCorredoresPorEquipo(String equipo) {
        List<Corredor> lista = corredoresSAX.getCorredoresPorEquipo(rutaXML, equipo);

        if (lista.isEmpty()) {
            System.out.println("No hay corredores del equipo: " + equipo);
            return;
        }

        for (Corredor c : lista) {
            System.out.println(c);
        }
    }
}
