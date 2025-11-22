package UD1.sax.corredoresYEquipos.persistencia;

import UD1.dom.XMLDOMUtils;
import UD1.dom.corredoresYequipos.TipoValidacion;
import UD1.sax.XMLSAXUtils;
import UD1.sax.corredoresYEquipos.clases.equipos.ActualizacionPatrocinador;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.File;
import java.util.List;

public class EquiposDOMUpdater {

    public void actualizarEquipos(String rutaEquipos, String rutaActualizaciones, String salida) throws Exception {

        // 1. Leer actualizaciones con SAX
        ActualizacionesSAXHandler handler = new ActualizacionesSAXHandler();
        XMLSAXUtils.cargarDocumentoSAX(rutaActualizaciones, handler, TipoValidacion.DTD);
        List<ActualizacionPatrocinador> lista = handler.getActualizaciones();

        // 2. Cargar equipos.xml con DOM
        Document doc = XMLDOMUtils.cargarDocumentoXMLDOM(rutaEquipos, TipoValidacion.DTD);

        // 3. Aplicar actualizaciones
        for (ActualizacionPatrocinador act : lista) {
            procesarActualizacion(doc, act);
        }

        // 4. Guardar resultado
        XMLDOMUtils.guardarDocumentoXML(doc, salida);
    }

    private void procesarActualizacion(Document doc, ActualizacionPatrocinador act) {

        Element equipo = buscarEquipo(doc, act.getIdEquipo());

        if (equipo == null) {
            equipo = crearEquipo(doc, act);
            doc.getDocumentElement().appendChild(equipo);
        }

        Element patrocinador = buscarPatrocinador(equipo, act.getNombrePatrocinador());

        if (patrocinador == null) {
            patrocinador = crearPatrocinador(doc, act);
            equipo.getElementsByTagName("patrocinadores").item(0).appendChild(patrocinador);
        } else {
            actualizarPatrocinador(patrocinador, act);
        }

        actualizarNumPatrocinadores(equipo);
    }

    private Element buscarEquipo(Document doc, String id) {
        NodeList equipos = doc.getElementsByTagName("equipo");
        for (int i = 0; i < equipos.getLength(); i++) {
            Element e = (Element) equipos.item(i);
            if (e.getAttribute("id").equals(id))
                return e;
        }
        return null;
    }

    private Element crearEquipo(Document doc, ActualizacionPatrocinador act) {

        Element equipo = doc.createElement("equipo");
        equipo.setAttribute("id", act.getIdEquipo());

        Element nombre = doc.createElement("nombre");
        nombre.setTextContent(act.getNombreEquipo());
        equipo.appendChild(nombre);

        Element patrocinadores = doc.createElement("patrocinadores");
        patrocinadores.setAttribute("numPatrocinadores", "0");
        patrocinadores.appendChild(crearPatrocinador(doc, act));

        equipo.appendChild(patrocinadores);

        return equipo;
    }

    private Element buscarPatrocinador(Element equipo, String nombre) {
        NodeList pats = equipo.getElementsByTagName("patrocinador");
        for (int i = 0; i < pats.getLength(); i++) {
            Element p = (Element) pats.item(i);
            if (p.getTextContent().equals(nombre))
                return p;
        }
        return null;
    }

    private Element crearPatrocinador(Document doc, ActualizacionPatrocinador act) {

        Element p = doc.createElement("patrocinador");
        p.setAttribute("donacion", String.valueOf(act.getDonacion()));
        p.setAttribute("fecha_inicio", act.getFecha().toString());
        p.setTextContent(act.getNombrePatrocinador());

        return p;
    }

    private void actualizarPatrocinador(Element p, ActualizacionPatrocinador act) {
        p.setAttribute("donacion", String.valueOf(act.getDonacion()));
        p.setAttribute("fecha_inicio", act.getFecha().toString());
    }

    private void actualizarNumPatrocinadores(Element equipo) {
        Element pats = (Element) equipo.getElementsByTagName("patrocinadores").item(0);
        int count = pats.getElementsByTagName("patrocinador").getLength();
        pats.setAttribute("numPatrocinadores", String.valueOf(count));
    }
}
