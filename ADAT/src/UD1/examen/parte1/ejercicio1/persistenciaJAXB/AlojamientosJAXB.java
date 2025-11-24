package UD1.examen.parte1.ejercicio1.persistenciaJAXB;


import UD1.examen.parte1.ejercicio1.clases.Alojamientos;
import jakarta.xml.bind.JAXBException;

// Pablo López Couso DNI:77550221V
public class AlojamientosJAXB {

    public Alojamientos listarAlojamientos(String ruta){
        try {
            return XMLJAXBUtils.unmarshal(Alojamientos.class, ruta);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}
