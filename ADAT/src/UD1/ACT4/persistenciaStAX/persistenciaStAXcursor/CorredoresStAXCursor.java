package UD1.ACT4.persistenciaStAX.persistenciaStAXcursor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;

import UD1.ACT4.clases.Corredor;
import UD1.ACT4.clases.Fondista;
import UD1.ACT4.clases.Puntuacion;
import UD1.ACT4.clases.Velocista;

public class CorredoresStAXCursor {
    public List<Corredor> leeCorredors(XMLStreamReader reader) {
        List<Corredor> corredores = new ArrayList<>();

        Corredor corredorActual = null;
        List<Puntuacion> historialActual = null;
        Puntuacion puntuacion = null;
        String contenidoActual = null;

        try {
            while (reader.hasNext()) {
                int tipo = reader.next();

                switch (tipo) {
                    case XMLStreamConstants.START_ELEMENT -> {
                        String nombreEtiqueta = XMLStAXUtilsCursor.obtenerNombreEtiqueta(reader);
                        switch (nombreEtiqueta) {
                            case "velocista", "fondista" -> {
                                corredorActual = nombreEtiqueta.equals("velocista") ? new Velocista() : new Fondista();
                                corredorActual.setCodigo(XMLStAXUtilsCursor.leerAtributo(reader, "codigo"));
                                corredorActual
                                        .setDorsal(Integer.parseInt(XMLStAXUtilsCursor.leerAtributo(reader, "dorsal")));
                                corredorActual.setEquipo(XMLStAXUtilsCursor.leerAtributo(reader, "equipo"));
                            }
                            case "historial" -> historialActual = new ArrayList<>();
                            case "puntuacion" -> {
                                puntuacion = new Puntuacion();
                                puntuacion.setAnio(Integer.parseInt(XMLStAXUtilsCursor.leerAtributo(reader, "anio")));
                            }

                        }
                    }
                    case XMLStreamConstants.CHARACTERS -> contenidoActual += XMLStAXUtilsCursor.leerTexto(reader);

                    case XMLStreamConstants.END_ELEMENT -> {
                        String nombreEtiqueta = XMLStAXUtilsCursor.obtenerNombreEtiqueta(reader);
                        switch (nombreEtiqueta) {
                            case "nombre" -> corredorActual.setNombre(contenidoActual);
                            case "fecha_nacimiento" -> corredorActual
                                    .setFechaNacimiento(LocalDate.parse(contenidoActual));
                            case "velocidad_media" -> {
                                if (corredorActual instanceof Velocista v) {
                                    v.setVelocidadMedia(Float.parseFloat(contenidoActual));
                                }
                            }
                            case "puntuacion" -> {
                                puntuacion.setPuntos(Integer.parseInt(contenidoActual));
                                historialActual.add(puntuacion);
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

                }
            }
        } catch (Exception e) {

        }

        return corredores;
    }
}
