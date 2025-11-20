package UD1.ay.ACT4.persistenciaSAX;

import java.util.List;

import UD1.ay.ACT4.clases.Corredor;
import UD1.ay.ACT4.persistenciaDOM.TipoValidacion;

public class CorredoresSAX {
    public List<Corredor> cargarTodosCorredores(String ruta, TipoValidacion val) {
        CorredoresSaxHandler handler = new CorredoresSaxHandler();
        XMLSAXUtils.cargarDocumentoSAX(ruta, handler, val);
        return handler.getCorredores();
    }

}
