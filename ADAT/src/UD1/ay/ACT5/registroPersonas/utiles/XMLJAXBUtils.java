package UD1.ay.ACT5.registroPersonas.utiles;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;

public class XMLJAXBUtils {

    public static <T> void marshall(T objeto, String rutaArchivo) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(objeto.getClass());

        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); // Con esquema XSD

        marshaller.marshal(objeto,new File(rutaArchivo));
    }

    public static <T> T unmarshall(Class<T> clase, String rutaArchivo) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(clase);

        Unmarshaller unmarshaller = context.createUnmarshaller();

        return clase.cast(unmarshaller.unmarshal(new File(rutaArchivo)));

    }
}
