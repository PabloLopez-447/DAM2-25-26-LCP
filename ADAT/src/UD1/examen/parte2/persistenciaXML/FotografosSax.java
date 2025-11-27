package UD1.examen.parte2.persistenciaXML;


import UD1.examen.parte2.clases.Fotografo;

import java.util.List;

//Pablo López Couso DNI:77550221V

public class FotografosSax {

    public List<Fotografo> cargarFotografos(String ruta, TipoValidacion validacion) {
        FotografosSaxHandler handler = new FotografosSaxHandler();
        XMLSAXUtils.cargarDocumentoSAX(ruta, handler, validacion);
        return handler.getFotografos();
    }

}
