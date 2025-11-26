package UD1.stax.corredoresYEquipos.persistencia.cursor;

import UD1.dom.corredoresYequipos.TipoValidacion;
import UD1.stax.util.XMLStAXUtilsCursor;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.XMLStreamReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

public class DonacionesTotalesStAXCursor {

    public void calcularTotales(String rutaEquipos, String rutaSalida) throws Exception {

        // -------- 1. MAPA PARA ACUMULAR DONACIONES --------
        Map<String, Float> totales = new HashMap<>();

        XMLStreamReader reader =
                XMLStAXUtilsCursor.cargarDocumentoStAX(rutaEquipos, TipoValidacion.NO_VALIDAR);

        String tagActual = null;
        String nombrePatrocinador = null;
        Float cantidad = null;

        String contenidoActual = null;

        // -------- 2. LECTURA CURSOR --------
        while (reader.hasNext()) {
            int event = reader.next();

            switch (event) {

                case XMLStreamReader.START_ELEMENT -> {
                    tagActual = XMLStAXUtilsCursor.obtenerNombreEtiqueta(reader);

                    if (tagActual.equals("patrocinador")) {
                        cantidad = Float.parseFloat(XMLStAXUtilsCursor.leerAtributo(reader, "donacion"));
                    }
                }

                case XMLStreamReader.CHARACTERS -> {
                    contenidoActual = XMLStAXUtilsCursor.leerTexto(reader);
                }

                case XMLStreamReader.END_ELEMENT -> {
                    String fin = XMLStAXUtilsCursor.obtenerNombreEtiqueta(reader);

                    if (fin.equals("patrocinador")) {

                        nombrePatrocinador = contenidoActual;

                        totales.put(nombrePatrocinador,
                                totales.getOrDefault(nombrePatrocinador, 0f) + cantidad);
                    }
                }
            }
        }

        // -------- 3. ESCRIBIR XML DE SALIDA --------
        XMLStreamWriter writer = XMLStAXUtilsCursor.crearWriterStax(rutaSalida);

        writer.writeStartDocument("UTF-8", "1.0");
        writer.writeStartElement("donaciones_totales");

        for (Map.Entry<String, Float> e : totales.entrySet()) {
            XMLStAXUtilsCursor.addSaltoLinea(writer, 1);

            writer.writeStartElement("patrocinador");
            writer.writeAttribute("nombre", e.getKey());
            writer.writeAttribute("total", String.valueOf(e.getValue()));
            writer.writeEndElement();
        }

        XMLStAXUtilsCursor.addSaltoLinea(writer, 0);
        writer.writeEndElement(); // donaciones_totales
        writer.writeEndDocument();

        writer.close();
    }
}

