package UD1.ACT5.corredoresYEquipos.util;

import UD1.ACT5.corredoresYEquipos.clases.Equipos;
import jakarta.xml.bind.JAXBException;

public class EquiposJAXB {
    public Equipos leerEquipos(String rutaXML) {
        try {
            return XMLJAXBUtils.unmarshal(Equipos.class, rutaXML);
        } catch (JAXBException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void addEquiposNuevos(String rutaXML, String rutaTexto, String rutaOutput) {

    }
}
