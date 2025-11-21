package UD1.dom.corredoresYequipos.persistencia;

import UD1.dom.XMLDOMUtils;
import UD1.dom.corredoresYequipos.clases.Corredor;
import UD1.dom.corredoresYequipos.clases.Fondista;
import UD1.dom.corredoresYequipos.clases.Puntuacion;
import UD1.dom.corredoresYequipos.clases.Velocista;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.XMLConstants;
import javax.xml.xpath.XPathConstants;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CorredoresDOM {
    Document doc;

    public CorredoresDOM(Document doc) {
        this.doc = doc;
    }

    public List<Corredor> getCorredores() {
        List<Corredor> corredores = new ArrayList<>();
        NodeList nodes = doc.getDocumentElement().getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (node instanceof Element) {
                Corredor corredor = parseCorredor((Element) node);
                corredores.add(corredor);
            }
        }
        return corredores;
    }

    public Corredor buscarPorCodigo(String codigo) {
        Element element = doc.getElementById(codigo);
        if (element != null) {
            return parseCorredor(element);
        }
        return null;
    }

    public Corredor buscarPorDorsal(int dorsal) {
        String xpath = String.format(".//*[@dorsal='%d']", dorsal);
        Element element = (Element) XMLDOMUtils.evaluarXPath(doc, xpath, XPathConstants.NODE);
        if (element != null) {
            return parseCorredor(element);
        }
        return null;
    }

    public boolean insertarCorredore(Corredor corredor) {
        Element element = null;

        if (corredor instanceof Velocista) {
            element = XMLDOMUtils.addElement(doc, "velocista", null, doc.getDocumentElement());
        }else {
            element = XMLDOMUtils.addElement(doc, "fondista", null, doc.getDocumentElement());
        }

        XMLDOMUtils.addAtributoId(doc, "codigo" ,corredor.getCodigo(), element);
        XMLDOMUtils.addAtributo(doc, "dorsal" , String.valueOf(corredor.getDorsal()), element);
        XMLDOMUtils.addAtributo(doc, "equipo" , corredor.getEquipo(), element);

        XMLDOMUtils.addElement(doc, "nombre", corredor.getNombre(), element);
        XMLDOMUtils.addElement(doc, "fecha_nacimiento", corredor.getFechaNacimiento().toString(), element);

        if (corredor instanceof Fondista f) {
            XMLDOMUtils.addElement(doc, "distancia_max", String.valueOf(f.getDistanciaMax()), element);
        }

        if (corredor instanceof Velocista v) {
            XMLDOMUtils.addElement(doc, "velocidad_media", String.valueOf(v.getVelocidadMedia()), element);
        }

        if (!corredor.getHistorial().isEmpty()) {
            Element historial = XMLDOMUtils.addElement(doc, "historial", null, element);

            for (Puntuacion p : corredor.getHistorial()) {
                Element puntuacion = XMLDOMUtils.addElement(doc, "puntuacion", String.valueOf(p.getPuntos()), historial);
                XMLDOMUtils.addAtributo(doc, "anio", String.valueOf(p.getAnio()), puntuacion);
            }

        }

        return true;
    }

    public boolean borrarCorredorPorCodigo(String codigo) {
        Element element = doc.getElementById(codigo);
        if (element != null) {
            return XMLDOMUtils.eliminarElemento(element);
        }
        return false;
    }

    public boolean addOrUpdatePuntuacion(String codigo, int anio, float puntos) {

        Element corredor = doc.getElementById(codigo);
        if (corredor == null) return false;

        Element historial = (Element)
                XMLDOMUtils.evaluarXPath(corredor, "historial", XPathConstants.NODE);

        if (historial == null) {
            historial = doc.createElement("historial");
            corredor.appendChild(historial);
        }


        String xpath = String.format("puntuacion[@anio='%d']", anio);
        Element punt = (Element)
                XMLDOMUtils.evaluarXPath(historial, xpath, XPathConstants.NODE);

        if (punt != null) {
            punt.setTextContent(String.valueOf(puntos));
            return true; // modificado
        }


        Element nuevo = doc.createElement("puntuacion");
        nuevo.setAttribute("anio", String.valueOf(anio));
        nuevo.setTextContent(String.valueOf(puntos));
        historial.appendChild(nuevo);

        return true; // añadido
    }

    public boolean eliminarPuntuacionPorAnio(String codigo, int anio) {
        Element corredor = doc.getElementById(codigo);
        if (corredor == null) return false;

        String xpath = String.format("historial/puntuacion[@anio='%d']", anio);
        Element puntuacion = (Element) XMLDOMUtils.evaluarXPath(doc, xpath, XPathConstants.NODE);

        if (puntuacion != null) {
            return XMLDOMUtils.eliminarElemento(puntuacion);
        }
        return false;
    }

    public int obtenerSiguienteDorsal() {
        String xpath = "//*[@dorsal]";
        NodeList nodos = (NodeList) XMLDOMUtils.evaluarXPath(
                doc,
                xpath,
                javax.xml.xpath.XPathConstants.NODESET
        );

        int max = 0;

        for (int i = 0; i < nodos.getLength(); i++) {
            Element el = (Element) nodos.item(i);
            int dorsal = Integer.parseInt(el.getAttribute("dorsal"));
            if (dorsal > max) max = dorsal;
        }

        return max + 1;
    }

    // VERSION SIN XPATH
//    public int obtenerSiguienteDorsal() {
//        NodeList nodos = doc.getDocumentElement().getChildNodes();
//        int max = 0;
//
//        for (int i = 0; i < nodos.getLength(); i++) {
//            Node n = nodos.item(i);
//            if (n instanceof Element el) {
//                int dorsal = Integer.parseInt(el.getAttribute("dorsal"));
//                if (dorsal > max) {
//                    max = dorsal;
//                }
//            }
//        }
//        return max + 1;
//    }



    public Corredor parseCorredor(Element element){
        String codigo = element.getAttribute("codigo");
        int dorsal = Integer.parseInt(element.getAttribute("dorsal"));
        String equipo = element.getAttribute("equipo");
        String nombre = element.getElementsByTagName("nombre").item(0).getTextContent();
        LocalDate fecha = LocalDate.parse(element.getElementsByTagName("fecha_nacimiento").item(0).getTextContent());

        Corredor corredor;

        NodeList nodelist = element.getElementsByTagName("puntuacion");
        List<Puntuacion> historial = new ArrayList<>();

        for(int i = 0; i < nodelist.getLength(); i++){
            Element puntuacion = (Element) nodelist.item(i);
            int anio = Integer.parseInt(puntuacion.getAttribute("anio"));
            float puntos = Float.parseFloat(puntuacion.getTextContent());
            Puntuacion punto = new Puntuacion(anio, puntos);
            historial.add(punto);
        }

        if (element.getTagName().equals("velocista")) {
            float velocidad = Float.parseFloat(element.getElementsByTagName("velocidad_media").item(0).getTextContent());
            corredor = new Velocista(codigo, dorsal, equipo, nombre, fecha, historial, velocidad);
        }
        else{
            float distancia = Float.parseFloat(element.getElementsByTagName("distancia_max").item(0).getTextContent());
            corredor = new Fondista(codigo, dorsal, equipo, nombre, fecha, historial, distancia);
        }

        return corredor;
    }
}
