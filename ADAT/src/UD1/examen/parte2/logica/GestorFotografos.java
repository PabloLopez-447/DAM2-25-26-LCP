package UD1.examen.parte2.logica;



import UD1.examen.parte2.clases.Fotografia;
import UD1.examen.parte2.clases.Fotografo;
import UD1.examen.parte2.persistencia.EscribirTexto;
import UD1.examen.parte2.persistencia.FotografoRandom;
import UD1.examen.parte2.persistenciaXML.FotografosSax;
import UD1.examen.parte2.persistenciaXML.TipoValidacion;

import java.util.ArrayList;
import java.util.List;

//Pablo López Couso DNI:77550221V

public class GestorFotografos {
    private final FotografoRandom archivo;
    String ruta;

    public GestorFotografos(String ruta) {
        archivo = new FotografoRandom(ruta);
        this.ruta = ruta;
        try {
            archivo.abrirArchivo();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void mostrarFotografos() {
        Fotografo fotografo = null;
        for (int id = 1; id < archivo.numeroRegistrosConBorrados(); id++) {
            try {
                fotografo = archivo.leerFotografo(id);

                if (fotografo != null && !fotografo.isBorrado()) {
                    System.out.println(fotografo);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void escribirFotografosPorEstudio() {
        EscribirTexto et = new EscribirTexto("src/ficheros/FotografosEstudio.txt");
        List<String> estudios = obtenerEstudios();
        et.abrirArchivo();
        for (String estudio : estudios) {
            et.escribirLinea("----------------------------------------------");
            et.escribirLinea("Estudio" + estudio);
            et.escribirLinea("----------------------------------------------");

            Fotografo fotografo = null;
            for (int id = 1; id < archivo.numeroRegistrosConBorrados(); id++) {
                try {
                    fotografo = archivo.leerFotografo(id);
                    if (fotografo != null && !fotografo.isBorrado()) {
                        if (fotografo.getEstudio().equals(estudio)) {
                            et.escribirLinea(fotografo.toString());
                        }
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

        }
        et.cerrarArchivo();
    }

    public List<String> obtenerEstudios() {
        Fotografo fotografo = null;
        List<String> listaEstudios = new ArrayList<>();
        for (int id = 1; id < archivo.numeroRegistrosConBorrados(); id++) {
            try {
                fotografo = archivo.leerFotografo(id);
                if (fotografo != null && !fotografo.isBorrado()) {
                    String estudio = fotografo.getEstudio();
                    if (!listaEstudios.contains(estudio)) {
                        listaEstudios.add(estudio);
                    }
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return listaEstudios;
    }

    public void procesarActualizaciones(String rutaXML) {
        FotografosSax fotografosSax = new FotografosSax();
        List<Fotografo> fotografos = fotografosSax.cargarFotografos(rutaXML, TipoValidacion.NO_VALIDAR);

        for (Fotografo fotografo : fotografos) {
            if (buscarFotografoPorLicencia(fotografo.getNumLicencia()) != null) {
                if (fotografo.bytesSerializacionEquipo() > FotografoRandom.TAM_REGISTRO) {
                    System.out.println("El equipo excede el tamaño maximo de registro");
                    return;
                }
                fotografo.setBorrado(false);
                fotografo.setCodigo(archivo.calcularNuevoId());
                fotografo.setNumFotografia(fotografo.getFotografias().size());
                for (Fotografia fotografia : fotografo.getFotografias()) {
                    fotografia.setTamanoMB(2);
                }
                archivo.escribirFotografo(fotografo);
            }
        }

    }

    public Fotografo buscarFotografoPorLicencia(String licencia) {
        if (licencia == null || licencia.isEmpty()) {
            System.out.println("LICENCIA INVALIDA");
            return null;
        }

        int id = 1;
        Fotografo fotografo = null;
        while ((fotografo = archivo.leerFotografo(id)) != null) {
            if (fotografo.getNumLicencia().equals(licencia)) {
                return fotografo;
            }
            id++;
        }
        return null;

    }
}
