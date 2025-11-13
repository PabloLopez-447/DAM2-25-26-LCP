package UD1.ACT4.persistenciaStAX.persistenciaStAXEventos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import UD1.ACT4.persistenciaDOM.TipoValidacion;

public class XMLStAXUtilsEventos {
    public static XMLEventReader cargarDocumentoStAX(String ruta, TipoValidacion validacion) throws Exception {
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
            return factory.createXMLEventReader(new FileInputStream(ruta), "UTF-8");
        } catch (Exception e) {
            throw new Exception();
        }
    }

    public static XMLEventWriter crearWriterStax(String rutaSalida) throws Exception {
        try {
            return XMLOutputFactory.newInstance().createXMLEventWriter(new FileWriter(rutaSalida));
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
            Schema schema = factory.newSchema();
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(filexml));
        } catch (Exception e) {

        }
    }

    public static String leerTexto(XMLEvent event) {
        if (event.isCharacters()) {
            return event.asCharacters().getData().trim();
        } else {
            return null;
        }
    }

    public static String leerAtributo(XMLEvent event, String nombre) {
        if (event.isStartElement()) {
            StartElement start = event.asStartElement();
            QName nombreCalificado = new QName(nombre);
            Attribute attribute = start.getAttributeByName(nombreCalificado);
            return attribute != null ? attribute.getValue() : null;
        } else
            return null;
    }

    public static String obtenerNombreEtiqueta(XMLEvent event) {
        if (event.isStartElement()) {
            return event.asStartElement().getName().getLocalPart();
        } else if (event.isEndElement()) {
            return event.asEndElement().getName().getLocalPart();

        } else
            return null;
    }

    public static void addSaltoLinea(XMLEventWriter writer, int nivel, XMLEventFactory factory) {
        try {
            String indentacion = "\n" + "\t".repeat(nivel);
            writer.add(factory.createCharacters(indentacion));

        } catch (Exception e) {

        }
    }
}
