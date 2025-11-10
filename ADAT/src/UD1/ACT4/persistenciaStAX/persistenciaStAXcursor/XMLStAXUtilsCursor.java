package UD1.ACT4.persistenciaStAX.persistenciaStAXcursor;

import java.io.File;
import java.io.FileInputStream;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import UD1.ACT4.persistenciaDOM.TipoValidacion;

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

    public static String leerTexto(XMLStreamReader reader) {
        return reader.getEventType() == XMLStreamConstants.CHARACTERS ? reader.getText().trim() : "";
    }

    public static String leerAtributo(XMLStreamReader reader, String nombre) {
        return reader.getAttributeValue(null, nombre);
    }

    public static String obtenerNombreEtiqueta(XMLStreamReader reader) {
        return reader.isStartElement() || reader.isEndElement() ? reader.getLocalName() : null;

    }
}
