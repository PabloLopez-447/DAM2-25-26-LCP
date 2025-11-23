package UD1.stax.corredoresYEquipos.logica;

import UD1.dom.XMLDOMUtils;
import UD1.dom.corredoresYequipos.TipoValidacion;
import UD1.stax.corredoresYEquipos.clases.equipos.ActualizacionPatrocinador;
import UD1.stax.corredoresYEquipos.persistencia.eventos.ActualizacionEquiposStAXEventos;
import UD1.stax.corredoresYEquipos.persistencia.eventos.EquiposDOM;
import org.w3c.dom.*;

import javax.xml.xpath.XPathConstants;
import java.util.List;

public class GestorEquipos {

    public void aplicarActualizaciones(
            String rutaEquipos,
            String rutaSalida,
            String rutaActualizaciones) throws Exception {

        Document doc = XMLDOMUtils.cargarDocumentoXMLDOM(rutaEquipos, TipoValidacion.NO_VALIDAR);
        EquiposDOM equiposDOM = new EquiposDOM(rutaEquipos);
        ActualizacionEquiposStAXEventos ac = new ActualizacionEquiposStAXEventos();
        List<ActualizacionPatrocinador>actualizaciones = ac.leerActualizaciones(rutaActualizaciones);

        equiposDOM.aplicarActualizaciones(actualizaciones);
        equiposDOM.guardar(rutaSalida);
    }

    public static void main(String[] args) {
        GestorEquipos g = new GestorEquipos();
        try {
            g.aplicarActualizaciones("D:\\Datos\\DAM\\DAM2-25-26-LCP\\ADAT\\src\\UD1\\stax\\xml\\Equipos.xml",
                    "D:\\Datos\\DAM\\DAM2-25-26-LCP\\ADAT\\src\\UD1\\stax\\xml\\equiposupdate.xml",
                    "D:\\Datos\\DAM\\DAM2-25-26-LCP\\ADAT\\src\\UD1\\stax\\xml\\ActualizacionesEquipos.xml");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

