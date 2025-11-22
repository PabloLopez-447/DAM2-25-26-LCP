package UD1.stax.corredoresYEquipos.logica;

import UD1.dom.corredoresYequipos.TipoValidacion;
import UD1.stax.corredoresYEquipos.clases.corredores.Corredor;
import UD1.stax.corredoresYEquipos.persistencia.cursor.CorredorStAXCursor;
import UD1.stax.corredoresYEquipos.persistencia.eventos.CorredorStAXEventos;

import java.util.List;

public class GestorCorredores {
    CorredorStAXCursor corredorStAXCursor;
    CorredorStAXEventos corredorStAXEventos;
    String rutaXML;
    TipoValidacion tipoValidacion;

    public GestorCorredores(String rutaXML, TipoValidacion tipoValidacion) {
        this.rutaXML = rutaXML;
        this.tipoValidacion = tipoValidacion;
    }

    public void visualizarTodosCorredoresCursor() {
        corredorStAXCursor = new CorredorStAXCursor();
        try {
            List<Corredor> corredors =  corredorStAXCursor.cargarTodosCorredoresCursor(rutaXML, tipoValidacion);
            for (Corredor corredor : corredors) {
                System.out.println(corredor.toString());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void visualizarTodosCorredoresEventos() {
        corredorStAXEventos = new CorredorStAXEventos();
        List<Corredor> corredors = corredorStAXEventos.listaCorredores(rutaXML, tipoValidacion);
        for (Corredor corredor : corredors) {
            System.out.println(corredor.toString());
        }
    }

    public static void main(String[] args) {
        GestorCorredores g = new GestorCorredores("D:\\Datos\\DAM\\DAM2-25-26-LCP\\ADAT\\src\\UD1\\stax\\xml\\Corredores.xml", TipoValidacion.NO_VALIDAR);
        g.visualizarTodosCorredoresCursor();
        g.visualizarTodosCorredoresEventos();
    }
}
