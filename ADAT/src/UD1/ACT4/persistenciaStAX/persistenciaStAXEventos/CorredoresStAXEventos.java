package UD1.ACT4.persistenciaStAX.persistenciaStAXEventos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;

import UD1.ACT4.clases.Corredor;
import UD1.ACT4.clases.Fondista;
import UD1.ACT4.clases.Puntuacion;
import UD1.ACT4.clases.Velocista;
import UD1.ACT4.persistenciaStAX.persistenciaStAXcursor.XMLStAXUtilsCursor;

public class CorredoresStAXEventos {
    public List<Corredor> leeCorredors(XMLStreamReader reader) {
        List<Corredor> corredores = new ArrayList<>();

        Corredor corredorActual = null;
        List<Puntuacion> historialActual = null;
        String anioActual = null;
        String contenidoActual = null;


        return corredores;
    }
}
