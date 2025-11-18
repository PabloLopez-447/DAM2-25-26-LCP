package UD1.ACT5.corredoresYEquipos.util;

import UD1.ACT5.corredoresYEquipos.clases.Corredores;
import jakarta.xml.bind.JAXBException;

public class CorredoresJAXB {
    public Corredores leerCorredores(String rutaXML) throws Exception {
        try {
            return XMLJAXBUtils.unmarshal(Corredores.class, rutaXML);
        } catch (JAXBException e) {
            throw new Exception();
        }
    }
}
