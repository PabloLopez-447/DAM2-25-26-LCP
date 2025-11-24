package UD1.examen.parte1.ejercicio2.persistencia;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.xpath.XPathConstants;

// Pablo López Couso DNI:77550221V
public class AlojamientosDOM {
    Document doc;

    public AlojamientosDOM(Document doc) {
        this.doc = doc;
    }

    public void addReserva(String numero, String codigo, String nombre, String dni, String fecha){
        Element alojamiento = buscarAlojamientopornumero(numero);

        if (alojamiento == null){
            return;
        }

        if (!comprobarReserva(codigo)){
            return;
        }

        if (estaOcupado(alojamiento)){
            return;
        }

        Element reservas = (Element) XMLDOMUtils.evaluarXPath(alojamiento, "Reservas", XPathConstants.NODE);
        if (reservas == null){
            reservas = XMLDOMUtils.addElement(doc, "Reservas", null, alojamiento);
        }

        Element reserva = XMLDOMUtils.addElement(doc, "Reserva", null, reservas);
        reserva.setAttribute("codigo", codigo);
        Element usuario = XMLDOMUtils.addElement(doc, "Usuario", null, reserva);
        XMLDOMUtils.addAtributo(doc, "dni", dni,  usuario);
        XMLDOMUtils.modificarValorElemento(usuario, nombre);
        Element fechaInicio = XMLDOMUtils.addElement(doc, "FechaInicio", null, reserva);
        XMLDOMUtils.modificarValorElemento(fechaInicio, fecha);
        XMLDOMUtils.modificarAtributo(alojamiento, "estado", "ocupada");
    }

    public boolean estaOcupado(Element element){
        String xpath = String.format("//*[@estado='%s']", "ocupada");

        NodeList nodes = (NodeList) XMLDOMUtils.evaluarXPath(doc, xpath, XPathConstants.NODESET);

        for(int i = 0; i < nodes.getLength(); i++){
            Element elem = (Element) nodes.item(i);
            if(elem.equals(element)){
                System.out.println("El alojamiento esta ocupado");
                return true;
            }
        }
        return false;
    }

    public boolean comprobarReserva(String codigo){
        String xpath = String.format("//Reserva[@codigo='%s']", codigo);
        Element reserva = (Element) XMLDOMUtils.evaluarXPath(doc, xpath, XPathConstants.NODE);

        if(reserva!=null){
            System.out.println("El codigo de la reserva ya esta registrado");
            return false;
        }
        else return true;
    }

    public Element buscarAlojamientopornumero(String numero) {
        Element element = XMLDOMUtils.buscarElementoPorId(doc, numero);

        if (element != null) {
            return element;
        }
        System.out.println("No existe el alojamiento "+numero);
        return null;
    }

    public void guardar(String rutaSalida){
        XMLDOMUtils.guardarDocumentoXML(doc , rutaSalida);
    }
}
