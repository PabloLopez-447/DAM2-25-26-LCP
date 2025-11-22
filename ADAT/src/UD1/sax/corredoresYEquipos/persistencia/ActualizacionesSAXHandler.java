package UD1.sax.corredoresYEquipos.persistencia;

import UD1.sax.corredoresYEquipos.clases.equipos.ActualizacionPatrocinador;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ActualizacionesSAXHandler extends DefaultHandler {

    private List<ActualizacionPatrocinador> actualizaciones = new ArrayList<>();
    private ActualizacionPatrocinador actual;
    private String contenidoActual;

    @Override
    public void startElement(String uri, String ln, String qName, Attributes att) {
        contenidoActual = "";

        if (qName.equals("Patrocinador")) {
            actual = new ActualizacionPatrocinador();
            actual.setIdEquipo(att.getValue("idEquipo"));
            actual.setNombreEquipo(att.getValue("nombreEquipo"));
        }

        if (qName.equals("Donacion")) {
            actual.setFecha(LocalDate.parse(att.getValue("fecha")));
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        contenidoActual += new String(ch, start, length);
    }

    @Override
    public void endElement(String uri, String ln, String qName) {

        switch (qName) {
            case "nombre" -> actual.setNombrePatrocinador(contenidoActual);

            case "Donacion" -> actual.setDonacion(Float.parseFloat(contenidoActual));

            case "Patrocinador" -> actualizaciones.add(actual);
        }
    }

    public List<ActualizacionPatrocinador> getActualizaciones() {
        return actualizaciones;
    }
}
