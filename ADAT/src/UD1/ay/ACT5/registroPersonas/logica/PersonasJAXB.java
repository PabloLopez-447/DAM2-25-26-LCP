package UD1.ay.ACT5.registroPersonas.logica;


import UD1.ay.ACT5.registroPersonas.clases.Registro;
import UD1.ay.ACT5.registroPersonas.utiles.XMLJAXBUtils;
import jakarta.xml.bind.JAXBException;

public class PersonasJAXB {
    private String rutaXML;

    public PersonasJAXB() {
    }

    public PersonasJAXB(String rutaXML) {
        this.rutaXML = rutaXML;
    }

    public static void leerPersonasXML(String rutaXML) {
        try{
            XMLJAXBUtils.unmarshall(Registro.class,rutaXML);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}
