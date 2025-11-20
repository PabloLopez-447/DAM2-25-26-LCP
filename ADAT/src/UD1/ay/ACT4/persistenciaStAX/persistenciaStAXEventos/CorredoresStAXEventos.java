package UD1.ay.ACT4.persistenciaStAX.persistenciaStAXEventos;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.events.XMLEvent;

import UD1.ay.ACT4.clases.Corredor;
import UD1.ay.ACT4.clases.Fondista;
import UD1.ay.ACT4.clases.Puntuacion;
import UD1.ay.ACT4.clases.Velocista;

public class CorredoresStAXEventos {
    public List<Corredor> leeCorredors(XMLEventReader reader) {
        List<Corredor> corredores = new ArrayList<>();

        Corredor corredorActual = null;
        List<Puntuacion> historialActual = null;
        Puntuacion puntuacion = null;
        String contenidoActual = null;

        try {
            while (reader.hasNext()) {
                XMLEvent event = reader.nextEvent();

                switch (event.getEventType()) {
                    case XMLStreamConstants.START_ELEMENT -> {
                        String start = XMLStAXUtilsEventos.obtenerNombreEtiqueta(event);
                        switch (start) {
                            case "velocista", "fondista" -> {
                                corredorActual = start.equals("velocista") ? new Velocista() : new Fondista();
                                corredorActual.setCodigo(XMLStAXUtilsEventos.leerAtributo(event, "codigo"));
                                corredorActual.setDorsal(Integer.parseInt(XMLStAXUtilsEventos.leerAtributo(event, "dorsal")));
                                corredorActual.setEquipo(XMLStAXUtilsEventos.leerAtributo(event, "equipo"));
                            }
                            

                        }
                    }

                }

            }
        } catch (Exception e) {

        }

        return corredores;
    }
}
