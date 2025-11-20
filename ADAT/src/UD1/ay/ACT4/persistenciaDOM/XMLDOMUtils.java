package UD1.ay.ACT4.persistenciaDOM;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLDOMUtils {
    static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
    static final String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";

    public static Document cargarFichero(String ruta, TipoValidacion validacion) {
        DocumentBuilderFactory dbf = configurarFactory(validacion);
        Document documento = null;
        try {
            DocumentBuilder constructor = dbf.newDocumentBuilder();
            documento = constructor.parse(new File(ruta));
            System.out.println("Documento cargado con exito");
        } catch (ParserConfigurationException e) {
            System.err.println("No se ha podido crear una instancia de DocumentBuilder");
        } catch (SAXException e) {
            System.err.println("Error SAX al parsear el archivo");
        } catch (IOException e) {
            System.err.println("Se ha producido un error de entrada salida");
        }
        return documento;
    }

    public static DocumentBuilderFactory configurarFactory(TipoValidacion validacion) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        switch (validacion) {
            case DTD:
                dbf.setValidating(true);
                break;
            case XSD:
                dbf.setValidating(true);
                dbf.setNamespaceAware(true);
                dbf.setAttribute(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA);
                break;
            case NO_VALIDAR:
                dbf.setValidating(false);
                break;

            default:
                break;
        }
        return dbf;
    }

    public static void guardarDocumentoXML(Document doc, String rutaDestino) {
        try {
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new FileWriter(rutaDestino));
            transformer.transform(source, result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static Attr addAtributo(Document doc, String nombre, String valor, Element element) {
        Attr atrib = doc.createAttribute(nombre);
        atrib.setValue(valor);
        element.setAttribute(nombre, valor);
        return atrib;
    }

    public static Attr addAtributoId(Document doc, String nombre, String valorId, Element element) {
        Attr atrib = doc.createAttribute(nombre);
        atrib.setValue(valorId);
        element.setAttributeNode(atrib);

        element.setIdAttributeNode(atrib, true);
        return atrib;
    }

    public static Element addElement(Document doc, String nombre, String valor, Element padre) {
        Element elemento = doc.createElement(nombre);
        padre.appendChild(elemento);
        return elemento;
    }

    public static boolean eliminarElemento(Element elemento) {
        if (elemento != null && elemento.getParentNode() != null) {
            elemento.getParentNode().removeChild(elemento);
            return true;
        }
        return false;
    }

    public static void modificarAtributo(Element elemento, String nombre, Object valor) {
        String valorStr = String.valueOf(valor);
        elemento.setAttribute(nombre, valorStr);
    }

    public static void modificarValorElemento(Element elemento, Object valor) {
        elemento.setTextContent(String.valueOf(valor));
    }

    public static Element buscarElementoPorId(Document doc, String idValue) {
        return doc.getElementById(idValue);
    }

    public static Object evaluarXPath(Object contexto, String expresion, QName tipoResultado) {
        try {
            XPath xPath = XPathFactory.newInstance().newXPath();
            return xPath.evaluate(expresion, contexto, tipoResultado);
        } catch (Exception e) {

        }
        return null;
    }

    public Node evaluarXPathNodo(Object contexto, String expresion, QName tipoResultado) {
        return (Node) evaluarXPath(contexto, expresion, tipoResultado);
    }

    public NodeList evaluarXPathNodeList(Object contexto, String expresion, QName tipoResultado) {
        return (NodeList) evaluarXPath(contexto, expresion, tipoResultado);
    }

    public boolean evaluarXPathBoolean(Object contexto, String expresion, QName tipoResultado) {
        return (boolean) evaluarXPath(contexto, expresion, tipoResultado);
    }

    public double evaluarXPathNumero(Object contexto, String expresion, QName tipoResultado) {
        return (double) evaluarXPath(contexto, expresion, tipoResultado);
    }
}
