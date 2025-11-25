package UD1.stax.corredoresYEquipos.persistencia.eventos;

import UD1.dom.corredoresYequipos.TipoValidacion;
import UD1.stax.corredoresYEquipos.clases.equipos.ActualizacionPatrocinador;
import UD1.stax.util.XMLStAXUtilsEventos;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.events.XMLEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ActualizacionEquiposStAXEventos {
    public List<ActualizacionPatrocinador> leerActualizaciones(String rutaXML) throws Exception {

        List<ActualizacionPatrocinador> lista = new ArrayList<>();

        XMLEventReader reader = XMLStAXUtilsEventos.cargarDocumentoStAX(rutaXML, TipoValidacion.NO_VALIDAR);

        ActualizacionPatrocinador act = null;
        String contenidoActual = null;

        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();
            String tag = XMLStAXUtilsEventos.obtenerNombreEtiqueta(event);

            switch (event.getEventType()) {

                case XMLEvent.START_ELEMENT -> {

                    switch (tag) {

                        case "Patrocinador" -> {
                            act = new ActualizacionPatrocinador();
                            act.setIdEquipo(XMLStAXUtilsEventos.leerAtributo(event, "idEquipo"));
                            act.setNombreEquipo(XMLStAXUtilsEventos.leerAtributo(event, "nombreEquipo"));
                        }

                        case "Donacion" -> {
                            act.setFecha(LocalDate.parse(XMLStAXUtilsEventos.leerAtributo(event,"fecha")));
                        }
                    }
                }

                case XMLEvent.CHARACTERS -> {
                    String txt = XMLStAXUtilsEventos.leerTexto(event);
                    if (!txt.isBlank()) contenidoActual = txt;
                }

                case XMLEvent.END_ELEMENT -> {

                    switch (tag) {

                        case "nombre" -> {
                            if (act != null) {
                                act.setNombrePatrocinador(contenidoActual);
                            }
                        }

                        case "Donacion" -> {
                            act.setDonacion(Float.parseFloat(contenidoActual));
                        }

                        case "Patrocinador" -> {
                            lista.add(act);
                        }
                    }
                }
            }
        }

        return lista;
    }

}
