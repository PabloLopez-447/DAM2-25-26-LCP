package UD1.sax.corredoresYEquipos.persistencia;

import UD1.sax.corredoresYEquipos.clases.corredores.Corredor;
import UD1.sax.corredoresYEquipos.clases.corredores.Fondista;
import UD1.sax.corredoresYEquipos.clases.corredores.Puntuacion;
import UD1.sax.corredoresYEquipos.clases.corredores.Velocista;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CorredoresSaxHandlerFEquipo extends DefaultHandler {

    private String equipoFiltro;
    private List<Corredor> corredores = new ArrayList<>();
    private Corredor corredorActual;
    private List<Puntuacion> historial;
    private String contenidoActual;
    private String anioActual;

    public CorredoresSaxHandlerFEquipo(String equipoFiltro) {
        this.equipoFiltro = equipoFiltro;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        contenidoActual = "";

        switch (qName) {
            case "velocista", "fondista" -> {
                String equipo = attributes.getValue("equipo");
                if (equipoFiltro.equals(equipo)) {
                    corredorActual = crearCorredor(qName);
                    corredorActual.setCodigo(attributes.getValue("codigo"));
                    corredorActual.setDorsal(Integer.parseInt(attributes.getValue("dorsal")));
                    corredorActual.setEquipo(equipo);
                }
            }
            case "historial" -> {
                if (corredorActual != null) historial = new ArrayList<>();
            }
            case "puntuacion" -> {
                if (corredorActual != null)
                    anioActual = attributes.getValue("anio");
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (corredorActual == null) return;

        switch (qName) {
            case "nombre" -> corredorActual.setNombre(contenidoActual);
            case "fecha_nacimiento" -> corredorActual.setFechaNacimiento(LocalDate.parse(contenidoActual));

            case "velocidad_media" -> {
                if (corredorActual instanceof Velocista v)
                    v.setVelocidadMedia(Float.parseFloat(contenidoActual));
            }

            case "distancia_max" -> {
                if (corredorActual instanceof Fondista f)
                    f.setDistanciaMax(Float.parseFloat(contenidoActual));
            }

            case "puntuacion" -> {
                Puntuacion p = new Puntuacion(
                        Integer.parseInt(anioActual),
                        Float.parseFloat(contenidoActual)
                );
                historial.add(p);
            }

            case "historial" -> corredorActual.setHistorial(historial);

            case "velocista", "fondista" -> corredores.add(corredorActual);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        contenidoActual += new String(ch, start, length);
    }

    public List<Corredor> getCorredores() {
        return corredores;
    }

    private Corredor crearCorredor(String tipo) {
        return tipo.equals("fondista") ? new Fondista() : new Velocista();
    }
}
