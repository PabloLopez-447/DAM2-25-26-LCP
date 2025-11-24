package UD1.examen.parte1.ejercicio1.persistenciaJAXB;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;

// Pablo López Couso DNI:77550221V
public class XMLJAXBUtils {
    public static <T> T unmarshal(Class<T> clase, String rutaArchivo) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(clase);

        Unmarshaller unmarshaller = context.createUnmarshaller();

        return clase.cast(unmarshaller.unmarshal(new File(rutaArchivo)));
    }
}
