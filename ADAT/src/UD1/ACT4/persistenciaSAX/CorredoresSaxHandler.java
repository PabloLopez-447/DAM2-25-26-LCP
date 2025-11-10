package UD1.ACT4.persistenciaSAX;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import UD1.ACT4.clases.Corredor;
import UD1.ACT4.clases.Fondista;
import UD1.ACT4.clases.Puntuacion;
import UD1.ACT4.clases.Velocista;

public class CorredoresSaxHandler extends DefaultHandler {
    List<Corredor> corredores = new ArrayList<>();
    Corredor corredorActual;
    List<Puntuacion> historial = new ArrayList<>();
    String contenidoActual;
    String anioActual;

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        contenidoActual += new String(ch, start, length);

    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        contenidoActual = "";

        switch (qName) {
            case "velocista", "fondista" -> {
                corredorActual = crearCorredor(qName);
                asignarAtributosCorredor(attributes);
            }
            case "puntuacion" -> {
                anioActual = attributes.getValue("anio");
            }
        }

        super.startElement(uri, localName, qName, attributes);
    }

    private void asignarAtributosCorredor(Attributes attributes) {
        corredorActual.setCodigo(attributes.getValue("codigo"));
        corredorActual.setDorsal(Integer.parseInt(attributes.getValue("dorsal")));
        corredorActual.setEquipo(attributes.getValue("equipo"));

    }

    private Corredor crearCorredor(String qName) {
        if (qName == "velocista") {
            return new Velocista();
        } else {
            return new Fondista();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (corredorActual == null) {
            return;
        }
        switch (qName) {
            case "nombre" -> corredorActual.setNombre(contenidoActual);
            case "fecha_nacimiento" -> corredorActual.setFechaNacimiento(LocalDate.parse(contenidoActual));
            case "puntuacion" -> historial.add(new Puntuacion(Integer.parseInt(anioActual), Float.parseFloat(contenidoActual)));
        }
        corredorActual.setHistorial(historial);
        super.endElement(uri, localName, qName);
    }

    public List<Corredor> getCorredores() {
        return corredores;
    }
}
