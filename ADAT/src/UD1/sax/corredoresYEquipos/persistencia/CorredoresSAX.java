package UD1.sax.corredoresYEquipos.persistencia;

import UD1.dom.corredoresYequipos.TipoValidacion;
import UD1.sax.XMLSAXUtils;
import UD1.sax.corredoresYEquipos.clases.corredores.Corredor;

import java.util.List;

public class CorredoresSAX {

    public List<Corredor> cargarTodosCorredores(String rutaXML, TipoValidacion tipoValidacion) {
        CorredoresSAXHandler corredoresSAXHandler = new CorredoresSAXHandler();
        XMLSAXUtils.cargarDocumentoSAX(rutaXML, corredoresSAXHandler, tipoValidacion);
        return corredoresSAXHandler.getCorredores();
    };

    public List<Corredor> getCorredoresPorEquipo(String rutaXML, String equipo) {

        CorredoresSaxHandlerFEquipo handler = new CorredoresSaxHandlerFEquipo(equipo);

        XMLSAXUtils.cargarDocumentoSAX(
                rutaXML,
                handler,
                TipoValidacion.XSD
        );

        return handler.getCorredores();
    }
}
