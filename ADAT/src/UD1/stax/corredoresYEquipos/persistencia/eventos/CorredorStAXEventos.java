package UD1.stax.corredoresYEquipos.persistencia.eventos;

import UD1.dom.corredoresYequipos.TipoValidacion;
import UD1.stax.corredoresYEquipos.clases.corredores.Corredor;
import UD1.stax.corredoresYEquipos.clases.corredores.Fondista;
import UD1.stax.corredoresYEquipos.clases.corredores.Puntuacion;
import UD1.stax.corredoresYEquipos.clases.corredores.Velocista;
import UD1.stax.util.XMLStAXUtilsEventos;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CorredorStAXEventos {

    public List<Corredor> listaCorredores(String ruta, TipoValidacion tipoValidacion) {
        List<Corredor> corredores = new ArrayList<>();

        Corredor corredorActual = null;
        List<Puntuacion> historialActual = null;
        Puntuacion puntuacion = null;
        String contenidoActual = null;

        try {
            XMLEventReader reader = XMLStAXUtilsEventos.cargarDocumentoStAX(ruta, tipoValidacion);

            while (reader.hasNext()) {
                XMLEvent event = reader.nextEvent();
                switch (event.getEventType()) {
                    case XMLEvent.START_ELEMENT -> {
                        StartElement startElement = event.asStartElement();
                        switch (startElement.getName().getLocalPart()) {
                            case "velocista", "fondista" -> {
                                historialActual = new ArrayList<>();
                                corredorActual = startElement.getName().getLocalPart().equals("velocista") ? new Velocista() : new Fondista();
                                corredorActual.setCodigo(XMLStAXUtilsEventos.leerAtributo(event, "codigo"));
                                corredorActual.setDorsal(Integer.parseInt(XMLStAXUtilsEventos.leerAtributo(startElement, "dorsal")));
                                corredorActual.setEquipo(XMLStAXUtilsEventos.leerAtributo(event, "equipo"));
                            }
                            case "puntuacion" -> {
                                puntuacion = new Puntuacion();
                                puntuacion.setAnio(Integer.parseInt(XMLStAXUtilsEventos.leerAtributo(event, "anio")));
                            }
                        }
                    }
                    case XMLEvent.END_ELEMENT -> {
                        EndElement endElement = event.asEndElement();
                        switch (endElement.getName().getLocalPart()) {
                            case "velocista", "fondista" -> {
                                corredores.add(corredorActual);
                            }
                            case "puntuacion" -> {
                                puntuacion.setPuntos(Float.parseFloat(contenidoActual));
                                historialActual.add(puntuacion);
                            }
                            case "historial" -> {
                                corredorActual.setHistorial(historialActual);
                            }
                            case "nombre" -> {
                                corredorActual.setNombre(contenidoActual);
                            }
                            case "fecha_nacimiento" -> {
                                corredorActual.setFechaNacimiento(LocalDate.parse(contenidoActual));
                            }
                            case "velocidad_media" -> {
                                if (corredorActual instanceof Velocista v) {
                                    v.setVelocidadMedia(Float.parseFloat(contenidoActual));
                                }
                            }
                            case "distancia_max" -> {
                                if  (corredorActual instanceof Fondista f) {
                                    f.setDistanciaMax(Float.parseFloat(contenidoActual));
                                }
                            }
                        }
                    }
                    case XMLEvent.CHARACTERS -> {
                        contenidoActual = XMLStAXUtilsEventos.leerTexto(event);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        return corredores;
    }

    public List<Corredor> leerCorredoresPorEquipo(String ruta, TipoValidacion tipoValidacion, String equipoBuscado) throws Exception {

        XMLEventReader reader = XMLStAXUtilsEventos.cargarDocumentoStAX(ruta, tipoValidacion);
        List<Corredor> lista = new ArrayList<>();

        Corredor corredorActual = null;
        List<Puntuacion> historialActual = null;
        Puntuacion puntuacion = null;
        String contenidoActual = null;

        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();
            String tag = XMLStAXUtilsEventos.obtenerNombreEtiqueta(event);

            switch (event.getEventType()) {

                case XMLEvent.START_ELEMENT -> {

                    switch (tag) {

                        case "velocista", "fondista" -> {
                            historialActual = new ArrayList<>();
                            corredorActual = tag.equals("velocista") ? new Velocista() : new Fondista();

                            corredorActual.setCodigo(XMLStAXUtilsEventos.leerAtributo(event, "codigo"));
                            corredorActual.setDorsal(Integer.parseInt(XMLStAXUtilsEventos.leerAtributo(event, "dorsal")));
                            corredorActual.setEquipo(XMLStAXUtilsEventos.leerAtributo(event, "equipo"));
                        }

                        case "puntuacion" -> {
                            puntuacion = new Puntuacion();
                            puntuacion.setAnio(Integer.parseInt(XMLStAXUtilsEventos.leerAtributo(event, "anio")));
                        }
                    }
                }

                case XMLEvent.CHARACTERS -> {
                    String txt = XMLStAXUtilsEventos.leerTexto(event);
                    if (!txt.isBlank()) contenidoActual = txt;
                }

                case XMLEvent.END_ELEMENT -> {

                    switch (tag) {

                        case "velocista", "fondista" -> {
                            if (corredorActual.getEquipo().equals(equipoBuscado)) {
                                lista.add(corredorActual);
                            }
                        }

                        case "puntuacion" -> {
                            puntuacion.setPuntos(Float.parseFloat(contenidoActual));
                            historialActual.add(puntuacion);
                        }

                        case "historial" -> corredorActual.setHistorial(historialActual);

                        case "nombre" -> corredorActual.setNombre(contenidoActual);

                        case "fecha_nacimiento" ->
                                corredorActual.setFechaNacimiento(LocalDate.parse(contenidoActual));

                        case "velocidad_media" -> {
                            if (corredorActual instanceof Velocista v) {
                                v.setVelocidadMedia(Float.parseFloat(contenidoActual));
                            }
                        }

                        case "distancia_max" -> {
                            if (corredorActual instanceof Fondista f) {
                                f.setDistanciaMax(Float.parseFloat(contenidoActual));
                            }
                        }
                    }
                }
            }
        }

        return lista;
    }

}
