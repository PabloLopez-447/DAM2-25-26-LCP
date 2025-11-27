package UD1.examen.parte2.persistenciaXML;


import UD1.examen.parte2.clases.Fotografia;
import UD1.examen.parte2.clases.Fotografo;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//Pablo López Couso DNI:77550221V

public class FotografosSaxHandler extends DefaultHandler {
    List<Fotografo> fotografos = new ArrayList<>();
    String contenidoActual;
    Fotografo fotografoActual;
    List<Fotografia> fotografiasActuales;
    Fotografia fotografiaActual;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        contenidoActual = "";
        switch (qName) {
            case "Fotografo" -> {
                fotografoActual = new Fotografo();
                fotografoActual.setNumLicencia(attributes.getValue("NumeroLicencia"));
            }
            case "Fotografias" -> {
                fotografiasActuales = new ArrayList<>();
            }
            case "Fotografia" -> {
                fotografiaActual = new Fotografia();
                fotografiaActual.setTipo(attributes.getValue("Formato"));
                fotografiaActual.setFechaToma(LocalDate.parse(attributes.getValue("fecha")));
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (fotografoActual == null) {
            return;
        }
        switch (qName) {
            case "Fotografo" -> {
                fotografos.add(fotografoActual);
            }
            case "Fotografias" -> {
                fotografoActual.setFotografias(fotografiasActuales);
            }
            case "Fotografia" -> {
                fotografiaActual.setTitulo(contenidoActual);
                fotografiasActuales.add(fotografiaActual);
            }
            case "Nombre" -> {
                fotografoActual.setNombre(contenidoActual);
            }
            case "Estudio" -> {
                fotografoActual.setEstudio(contenidoActual);
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        contenidoActual += new String(ch, start, length);
    }

    public List<Fotografo> getFotografos() {
        return fotografos;
    }
}
