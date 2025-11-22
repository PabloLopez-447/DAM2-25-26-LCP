package UD1.stax.util;



import UD1.dom.corredoresYequipos.TipoValidacion;

import javax.xml.stream.*;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;

public class XMLStAXUtilsCursor {
    public static XMLStreamReader cargarDocumentoStAX(String ruta, TipoValidacion validacion) throws Exception {
        try {
            if (ruta == null || ruta.isBlank()) {
                throw new Exception();
            }
            if (validacion == null) {
                throw new Exception();

            }

            File filexml = new File(ruta);
            if (!filexml.exists()) {
                throw new Exception();
            }

            switch (validacion) {
                case DTD -> validarDTD(filexml);
                case XSD -> validarXSD(filexml);
                case NO_VALIDAR -> {

                }
            }
            XMLInputFactory factory = XMLInputFactory.newInstance();
            return factory.createXMLStreamReader(new FileInputStream(ruta), "UTF-8");
        } catch (Exception e) {
            throw new Exception();
        }
    }

    public static XMLStreamWriter crearWriterStax(String rutaSalida) throws Exception {
        try {
            XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
            return outputFactory.createXMLStreamWriter(new FileWriter(rutaSalida));
        } catch (Exception e) {
            throw new Exception();
        }
    }

    private static void validarDTD(File filexml) {
        System.out.println("no");
    }

    private static void validarXSD(File filexml) {
        try {
            SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
            Schema schema = factory.newSchema(filexml);
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(filexml));
        } catch (Exception e) {

        }
    }

    public static String leerTexto(XMLStreamReader reader) {
        return reader.getEventType() == XMLStreamConstants.CHARACTERS ? reader.getText().trim() : "";
    }

    public static String leerAtributo(XMLStreamReader reader, String nombre) {
        return reader.getAttributeValue(null, nombre);
    }

    public static String obtenerNombreEtiqueta(XMLStreamReader reader) {
        return reader.isStartElement() || reader.isEndElement() ? reader.getLocalName() : null;

    }

    public static void addSaltoLinea(XMLStreamWriter writer, int nivel) {
        try {
            String indentacion = "\n" + "\t".repeat(nivel);
            writer.writeCharacters(indentacion);

        } catch (Exception e) {

        }
    }

    public static void addAtributo(XMLStreamWriter writer, String nombreAtributo, String valor) {
        try {
            writer.writeAttribute(nombreAtributo, valor);
        } catch (Exception e) {
        }
    }

    public static void addTextoElemento(XMLStreamWriter writer, String texto) {
        try {
            if (texto != null) {
                writer.writeCharacters(texto);
            }
        } catch (Exception e) {
        }
    }

    public static void addEndElement(XMLStreamWriter writer) {
        try {
            writer.writeEndElement();
        } catch (Exception e) {
        }
    }

    public static void addElemento(XMLStreamWriter writer, String nombre, String valor) {
        try {
            writer.writeStartElement(nombre);
            writer.writeCharacters(valor);
            writer.writeEndElement();
        } catch (Exception e) {

        }
    }
}
