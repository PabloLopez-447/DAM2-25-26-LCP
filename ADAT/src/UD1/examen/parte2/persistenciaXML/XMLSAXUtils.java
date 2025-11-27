package UD1.examen.parte2.persistenciaXML;

import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

//Pablo López Couso DNI:77550221V

public class XMLSAXUtils {
    public static void cargarDocumentoSAX(String rutaXML, DefaultHandler handler, TipoValidacion validacion) {
        try {
            if (rutaXML != null && validacion != null) {
                SAXParserFactory factory = configurarFarctory(validacion);
                SAXParser parser = factory.newSAXParser();
                parser.parse(rutaXML, handler);

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static SAXParserFactory configurarFarctory(TipoValidacion validacion) {
        SAXParserFactory factory = SAXParserFactory.newInstance();

        switch (validacion) {
            case DTD:
                factory.setValidating(true);
                break;

            case XSD:
                factory.setValidating(true);
                factory.setNamespaceAware(true);
                try {
                    factory.setFeature("http://xml.org/sax/features/validation", true);
                    factory.setFeature("http://apache.org/xml/features/validation/schema", true);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;

            case NO_VALIDAR:
                factory.setValidating(false);
                break;
            default:
                break;
        }
        return factory;
    }
}
