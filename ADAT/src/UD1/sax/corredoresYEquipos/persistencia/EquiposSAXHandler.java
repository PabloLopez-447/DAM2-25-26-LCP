package UD1.sax.corredoresYEquipos.persistencia;

import UD1.sax.corredoresYEquipos.clases.equipos.Equipo;
import UD1.sax.corredoresYEquipos.clases.equipos.Patrocinador;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EquiposSAXHandler extends DefaultHandler {
    Patrocinador patrocinadorActual;
    Equipo equipoActual;
    List<Equipo> listaEquipos = new ArrayList<Equipo>();
    String contenidoActual;
    Set<Patrocinador> listaPatrocinadores = new HashSet<Patrocinador>();

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        contenidoActual = "";

        switch (qName) {
            case "equipo" -> {
                equipoActual = new Equipo();
                listaPatrocinadores.clear();
                equipoActual.setId(attributes.getValue("id"));
            }
            case "patrocinadores" -> {
                equipoActual.setNumPatrocinadores(Integer.parseInt(attributes.getValue("numPatrocinadores")));
            }
            case "patrocinador" -> {
                patrocinadorActual = new Patrocinador();
                patrocinadorActual.setDonacion(Float.parseFloat(attributes.getValue("donacion")));
                patrocinadorActual.setFechaInicio(LocalDate.parse(attributes.getValue("fecha_inicio")));
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case "equipo" -> {
                listaEquipos.add(equipoActual);
            }
            case "patrocinadores" -> {
                equipoActual.setPatrocinadores(listaPatrocinadores);
            }
            case "patrocinador" -> {
                patrocinadorActual.setNombre(contenidoActual);
                listaPatrocinadores.add(patrocinadorActual);
            }
            case "nombre" -> {
                equipoActual.setNombre(contenidoActual);
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        contenidoActual += new String(ch, start, length);
    }

    public List<Equipo> getListaEquipos() {
        return listaEquipos;
    }
}
