package UD1.stax.corredoresYEquipos.persistencia.cursor;


import UD1.dom.corredoresYequipos.TipoValidacion;
import UD1.stax.corredoresYEquipos.clases.corredores.Corredor;
import UD1.stax.corredoresYEquipos.clases.corredores.Fondista;
import UD1.stax.corredoresYEquipos.clases.corredores.Puntuacion;
import UD1.stax.corredoresYEquipos.clases.corredores.Velocista;
import UD1.stax.util.XMLStAXUtilsCursor;

import javax.xml.stream.XMLStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CorredorStAXCursor {
    public List<Corredor> cargarTodosCorredoresCursor(String ruta, TipoValidacion tipoValidacion) throws Exception {
        XMLStreamReader reader = XMLStAXUtilsCursor.cargarDocumentoStAX(ruta, tipoValidacion);

        List<Corredor> corredores = new ArrayList<>();
        Corredor corredorActual = null;
        List<Puntuacion> historialActual = null;
        Puntuacion puntuacion = null;
        String contenidoActual = null;

        try {
            while (reader.hasNext()) {
                int eventType = reader.next();
                switch (eventType) {
                    case XMLStreamReader.START_ELEMENT -> {
                        String nombreEtiqueta = XMLStAXUtilsCursor.obtenerNombreEtiqueta(reader);

                        switch (nombreEtiqueta) {
                            case "velocista", "fondista" -> {
                                historialActual = new ArrayList<>();
                                corredorActual = nombreEtiqueta.equals("velocista") ? new Velocista() : new Fondista();
                                corredorActual.setCodigo(XMLStAXUtilsCursor.leerAtributo(reader, "codigo"));
                                corredorActual.setDorsal(Integer.parseInt(XMLStAXUtilsCursor.leerAtributo(reader, "dorsal")));
                                corredorActual.setEquipo(XMLStAXUtilsCursor.leerAtributo(reader, "equipo"));
                            }
                            case "puntuacion" -> {
                                puntuacion = new Puntuacion();
                                puntuacion.setAnio(Integer.parseInt(XMLStAXUtilsCursor.leerAtributo(reader, "anio")));
                            }
                        }
                    }
                    case XMLStreamReader.END_ELEMENT -> {
                        String nombreEtiquta = XMLStAXUtilsCursor.obtenerNombreEtiqueta(reader);

                        switch (nombreEtiquta) {
                            case "velocista", "fondista" -> {
                                corredores.add(corredorActual);
                            }
                            case "puntuacion" -> {
                                puntuacion.setPuntos(Float.parseFloat(contenidoActual));
                                historialActual.add(puntuacion);
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
                                if (corredorActual instanceof Fondista f) {
                                    f.setDistanciaMax(Float.parseFloat(contenidoActual));
                                }
                            }
                            case "historial" -> {
                                corredorActual.setHistorial(historialActual);
                            }
                        }
                    }
                    case XMLStreamReader.CHARACTERS -> {
                        contenidoActual = XMLStAXUtilsCursor.leerTexto(reader);
                    }
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return corredores;
    }

    public List<Corredor> leerCorredoresPorEquipo(String ruta, TipoValidacion tipoValidacion, String equipoBuscado) throws Exception {

        List<Corredor> lista = new ArrayList<>();

        Corredor corredorActual = null;
        List<Puntuacion> historialActual = null;
        Puntuacion puntuacion = null;
        String contenidoActual = null;

        XMLStreamReader reader = XMLStAXUtilsCursor.cargarDocumentoStAX(ruta, tipoValidacion);

        while (reader.hasNext()) {
            int event = reader.next();
            String tag = XMLStAXUtilsCursor.obtenerNombreEtiqueta(reader);

            switch (event) {

                // -------------------------------------------------------
                // START ELEMENT
                // -------------------------------------------------------
                case XMLStreamReader.START_ELEMENT -> {

                    switch (tag) {

                        case "velocista", "fondista" -> {
                            historialActual = new ArrayList<>();
                            corredorActual = tag.equals("velocista") ? new Velocista() : new Fondista();

                            corredorActual.setCodigo(
                                    XMLStAXUtilsCursor.leerAtributo(reader, "codigo")
                            );
                            corredorActual.setDorsal(Integer.parseInt(
                                    XMLStAXUtilsCursor.leerAtributo(reader, "dorsal")
                            ));
                            corredorActual.setEquipo(
                                    XMLStAXUtilsCursor.leerAtributo(reader, "equipo")
                            );
                        }

                        case "puntuacion" -> {
                            puntuacion = new Puntuacion();
                            puntuacion.setAnio(Integer.parseInt(
                                    XMLStAXUtilsCursor.leerAtributo(reader, "anio")
                            ));
                        }
                    }
                }

                // -------------------------------------------------------
                // CHARACTERS
                // -------------------------------------------------------
                case XMLStreamReader.CHARACTERS -> {
                    String txt = XMLStAXUtilsCursor.leerTexto(reader);
                    if (!txt.isBlank()) contenidoActual = txt;
                }

                // -------------------------------------------------------
                // END ELEMENT
                // -------------------------------------------------------
                case XMLStreamReader.END_ELEMENT -> {

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

                        case "historial" ->
                                corredorActual.setHistorial(historialActual);

                        case "nombre" ->
                                corredorActual.setNombre(contenidoActual);

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
