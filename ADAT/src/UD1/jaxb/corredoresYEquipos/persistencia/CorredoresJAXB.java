package UD1.jaxb.corredoresYEquipos.persistencia;

import UD1.jaxb.XMLJAXBUtils;
import UD1.jaxb.corredoresYEquipos.clases.corredores.Corredor;
import UD1.jaxb.corredoresYEquipos.clases.corredores.Corredores;
import jakarta.xml.bind.JAXBException;

import java.util.ArrayList;
import java.util.List;

public class CorredoresJAXB {

    public List<Corredor> listarCorredores(String rutaArchivo){
        try {
            Corredores corredores= XMLJAXBUtils.unmarshal(Corredores.class, rutaArchivo);
            return corredores.getCorredores();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }

    }
}
