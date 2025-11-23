package UD1.stax.corredoresYEquipos.persistencia.eventos;

import UD1.dom.XMLDOMUtils;
import UD1.dom.corredoresYequipos.TipoValidacion;
import UD1.stax.corredoresYEquipos.clases.equipos.ActualizacionPatrocinador;
import org.w3c.dom.*;

import javax.xml.xpath.XPathConstants;
import java.util.List;

public class EquiposDOM {

    private Document doc;

    public EquiposDOM(String rutaXML) throws Exception {
        this.doc = XMLDOMUtils.cargarDocumentoXMLDOM(rutaXML, TipoValidacion.NO_VALIDAR);
    }

    // ------------------------------------------------------------
    // Buscar equipo por id
    // ------------------------------------------------------------
    private Element buscarEquipoPorId(String idEquipo) {
        return (Element) XMLDOMUtils.evaluarXPath(
                doc,
                "/equipos/equipo[@id='" + idEquipo + "']",
                XPathConstants.NODE
        );
    }

    // ------------------------------------------------------------
    // Buscar patrocinador por nombre dentro de un equipo
    // ------------------------------------------------------------
    private Element buscarPatrocinador(Element equipo, String nombrePatro) {

        return (Element) XMLDOMUtils.evaluarXPath(
                equipo,
                "./patrocinadores/patrocinador[text()='" + nombrePatro + "']",
                XPathConstants.NODE
        );
    }

    // ------------------------------------------------------------
    // Aplicar una sola actualización
    // ------------------------------------------------------------
    public void aplicarActualizacion(ActualizacionPatrocinador act) {

        Element equipo = buscarEquipoPorId(act.getIdEquipo());

        // ------------------------------------------------------------
        // Caso: equipo NO existe → crear equipo
        // ------------------------------------------------------------
        if (equipo == null) {

            Element raiz = doc.getDocumentElement();

            equipo = XMLDOMUtils.addElement(doc, "equipo", null, raiz);
            XMLDOMUtils.addAtributo(doc, "id", act.getIdEquipo(), equipo);

            // nombre del nuevo equipo
            Element nombre = XMLDOMUtils.addElement(doc, "nombre", act.getNombreEquipo(), equipo);

            // nodo patrocinadores
            Element listaPat = XMLDOMUtils.addElement(doc, "patrocinadores", null, equipo);
            XMLDOMUtils.addAtributo(doc, "numPatrocinadores", "1", listaPat);

            // añadir patrocinador
            Element pat = XMLDOMUtils.addElement(doc, "patrocinador", act.getNombrePatrocinador(), listaPat);
            XMLDOMUtils.addAtributo(doc, "donacion", String.valueOf(act.getDonacion()), pat);
            XMLDOMUtils.addAtributo(doc, "fecha_inicio", act.getFecha().toString(), pat);

            return;
        }

        // ------------------------------------------------------------
        // Caso: equipo existe → buscar patrocinador
        // ------------------------------------------------------------
        Element patrocinador = buscarPatrocinador(equipo, act.getNombrePatrocinador());

        // ------------------------------------------------------------
        // Caso: patrocinador existe → actualizar
        // ------------------------------------------------------------
        if (patrocinador != null) {
            XMLDOMUtils.modificarAtributo(patrocinador, "donacion", act.getDonacion());
            XMLDOMUtils.modificarAtributo(patrocinador, "fecha_inicio", act.getFecha().toString());
            return;
        }

        // ------------------------------------------------------------
        // Caso: NO existe → añadir patrocinador nuevo
        // ------------------------------------------------------------
        Element lista = (Element) XMLDOMUtils.evaluarXPath(
                equipo,
                "./patrocinadores",
                XPathConstants.NODE
        );

        Element nuevo = XMLDOMUtils.addElement(doc, "patrocinador", act.getNombrePatrocinador(), lista);
        XMLDOMUtils.addAtributo(doc, "donacion", String.valueOf(act.getDonacion()), nuevo);
        XMLDOMUtils.addAtributo(doc, "fecha_inicio", act.getFecha().toString(), nuevo);

        // actualizar contador
        int num = Integer.parseInt(lista.getAttribute("numPatrocinadores"));
        lista.setAttribute("numPatrocinadores", String.valueOf(num + 1));
    }

    // ------------------------------------------------------------
    // Aplicar lista completa de actualizaciones
    // ------------------------------------------------------------
    public void aplicarActualizaciones(List<ActualizacionPatrocinador> lista) {
        for (ActualizacionPatrocinador a : lista) {
            aplicarActualizacion(a);
        }
    }

    // ------------------------------------------------------------
    // Guardar XML
    // ------------------------------------------------------------
    public void guardar(String rutaDestino) {
        XMLDOMUtils.guardarDocumentoXML(doc, rutaDestino);
    }
}
