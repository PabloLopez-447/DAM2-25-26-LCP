package UD1.examen.parte1.ejercicio2.logica;

import UD1.examen.parte1.ejercicio2.persistencia.AlojamientosDOM;
import UD1.examen.parte1.ejercicio2.persistencia.TipoValidacion;
import UD1.examen.parte1.ejercicio2.persistencia.XMLDOMUtils;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

// Pablo López Couso DNI:77550221V
public class GestorAlojamientos {
    String ruta;
    TipoValidacion tipoValidacion;

    public GestorAlojamientos(String ruta, TipoValidacion tipoValidacion) {
        this.ruta = ruta;
        this.tipoValidacion = tipoValidacion;
    }

    public void insertarReserva(String numero, String codigo, String nombre, String dni, String fecha, String rutaSalida){
        try {
            AlojamientosDOM al = new AlojamientosDOM(XMLDOMUtils.cargarDocumentoXMLDOM(ruta, tipoValidacion));

            al.addReserva(numero, codigo, nombre, dni, fecha);
            al.guardar(rutaSalida);
            System.out.println("Reserva guardada");

        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }
    }
}
