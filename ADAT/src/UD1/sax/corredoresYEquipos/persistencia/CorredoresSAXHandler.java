package UD1.sax.corredoresYEquipos.persistencia;

import UD1.sax.corredoresYEquipos.clases.corredores.Corredor;
import UD1.sax.corredoresYEquipos.clases.corredores.Fondista;
import UD1.sax.corredoresYEquipos.clases.corredores.Puntuacion;
import UD1.sax.corredoresYEquipos.clases.corredores.Velocista;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CorredoresSAXHandler extends DefaultHandler {

    List<Corredor> corredores = new ArrayList<>();
    Corredor corredorActual;
    List<Puntuacion> historial = new ArrayList<>();
    String contenidoActual;
    String anioActual;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        contenidoActual = "";
        switch(qName){
            case "velocista", "fondista" -> {
                corredorActual = crearCorredor(qName);
                setAtributosCorredor(attributes);
            }
            case "puntuacion" -> {
                anioActual = attributes.getValue("anio");
            }
            case "historial" -> {
                historial = new ArrayList<>();
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (corredorActual==null) {
            return;
        }
        switch(qName){
            case "nombre" -> corredorActual.setNombre(contenidoActual);
            case "fecha_nacimiento" -> corredorActual.setFechaNacimiento(LocalDate.parse(contenidoActual));
            case "velocidad_media" -> {
                if (corredorActual instanceof Velocista v) {
                    v.setVelocidadMedia(Float.parseFloat(contenidoActual));
                }
            }
            case "distancia_max" -> {
                if (corredorActual instanceof Fondista f) {
                    f.setDistanciaMax(Float.parseFloat(contenidoActual));
                }
            }
            case "puntuacion" -> {
                Puntuacion p = new Puntuacion(Integer.parseInt(anioActual), Float.parseFloat(contenidoActual));
                historial.add(p);
            }
            case "historial" -> {
                corredorActual.setHistorial(historial);
            }
            case "velocista", "fondista" -> {
                corredores.add(corredorActual);
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        contenidoActual += new String(ch, start, length);
    }

    public Corredor crearCorredor(String tipo) {
        return tipo.equals("fondista") ? new Fondista() : new Velocista();
    }

    public void setAtributosCorredor(Attributes attributes) {
        corredorActual.setCodigo(attributes.getValue("codigo"));
        corredorActual.setDorsal(Integer.parseInt(attributes.getValue("dorsal")));
        corredorActual.setEquipo(attributes.getValue("equipo"));
    }

    public List<Corredor> getCorredores() {
        return corredores;
    }
}
