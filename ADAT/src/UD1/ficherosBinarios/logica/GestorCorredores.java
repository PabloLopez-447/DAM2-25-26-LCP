package UD1.ficherosBinarios.logica;

import UD1.ficherosBinarios.clases.Corredor;
import UD1.ficherosBinarios.clases.Fondista;
import UD1.ficherosBinarios.clases.Puntuacion;
import UD1.ficherosBinarios.clases.Velocista;
import UD1.ficherosBinarios.persistencia.CorredorRead;
import UD1.ficherosBinarios.persistencia.CorredorWrite;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GestorCorredores {
    private final String ruta = "corredores.dat";

    public void guardarCorredores(Corredor c) {
        if (c == null) {
            System.out.println("No existe el corredor");
            return;
        }

        CorredorWrite cw = new CorredorWrite(ruta);
        CorredorRead cr = new CorredorRead(ruta);

        try {
            int ultimoDorsal = cr.obtenerUltimoDorsal();
            c.setDorsal(ultimoDorsal + 1);
            cw.iniciarEscritura();
            cw.escribirCorredor(c);
            System.out.println("Corredor escribido");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void GuardarListaCorredores(List<Corredor> lista) {
        if (lista == null || lista.isEmpty()) {
            System.out.println("Lista invalida");
            return;
        }

        CorredorWrite cw = new CorredorWrite(ruta);
        CorredorRead cr = new CorredorRead(ruta);

        try {
            int dorsal = cr.obtenerUltimoDorsal();
            cw.iniciarEscritura();

            try {
                for (Corredor c : lista) {
                    dorsal++;
                    c.setDorsal(dorsal);
                    cw.escribirCorredor(c);
                    System.out.println("Corredor escribido");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Corredor buscarCorredorPorDorsal(int dorsal) {
        CorredorRead cr = new CorredorRead(ruta);
        return cr.buscarPorDorsal(dorsal);
    }

    public void mostrarCorredorPorDorsal(int dorsal) {
        Corredor c = buscarCorredorPorDorsal(dorsal);
        if (c == null) {
            System.out.println("No existe el corredor");
        }

        String tipoCorredor = "";

        if (c instanceof Fondista) {
            tipoCorredor = "CORREDOR FONDISTA";
        } else if (c instanceof Velocista) {
            tipoCorredor = "CORREDOR VELOCISTA";
        }

        System.out.println(tipoCorredor + ": DORSAL " + dorsal);
        visualizarCorredor(c);
    }

    public void visualizarCorredor(Corredor c) {
        if (c == null) {
            System.out.println("No existe el corredor");
        }

        System.out.println("===================================================================================");
        System.out.println(c.toString());

        if (c.getHistorial() != null && !c.getHistorial().isEmpty()) {
            int numPatrocinadores = c.getHistorial().size();

            String etiqueta = (numPatrocinadores == 1) ? "PUNTUACION" : "PUNTUACIONES";
            System.out.printf("%s: %s\n", etiqueta, c.getHistorial().toString());
        } else {
            System.out.println("PUNTUACIONES: sin datos registrados");
        }

        System.out.println("===================================================================================");
    }

    public void listarTodosLosCorredores() {
        CorredorRead cr = new CorredorRead(ruta);
        Corredor c;
        int contador = 0;

        System.out.println("\n--- Inicio del listado de corredores ---");
        cr.iniciarLectura();
        try {
            while ((c = cr.leer()) != null) {
                mostrarCorredorPorDorsal(c.getDorsal());
                contador++;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            cr.finalizarLectura();
        }

        if (contador == 0) {
            System.out.println("Lista de corredores vacia");
        } else {
            System.out.println("--- Fin del listado de corredores ---");
        }
    }

    public void eliminarCorredor(int dorsal) {
        if (dorsal <= 0) {
            System.out.println("No existe el corredor");
            return;
        }
        final String ARCHIVO_AUX = "auxiliar.dat";
        CorredorRead readCheck = new CorredorRead(ruta);
        if (readCheck.buscarPorDorsal(dorsal) == null) {
            System.out.println("No existe el corredor");
            return;
        }
        CorredorRead cr = new CorredorRead(ruta);
        CorredorWrite cw = new CorredorWrite(ARCHIVO_AUX);

        try {
            cr.iniciarLectura();
            cw.iniciarEscritura();
            Corredor c;

            while ((c = cr.leer()) != null) {
                if (c.getDorsal() != dorsal) {
                    cw.escribirCorredor(c);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            cr.finalizarLectura();
            cw.finalizarEscritura();
        }

        if (!cr.borrarArchivo(new File(ruta))) {
            System.out.println("no se pudo borrar el archivo original");
            return;
        }

        if (!cw.renombrarArchivo(new File(ARCHIVO_AUX), ruta)) {
            System.out.println("no se pudo renombrar el archivo auxiliar");
            return;
        }
        System.out.println("Corredor con dorsal: " + dorsal + " borrado");

    }

    public void agregarPuntuacion(int dorsal, Puntuacion nuevaPuntuacion) {
        if (dorsal <= 0) {
            System.out.println("Dorsal invalido");
        }
        final String ARCHIVO_AUX = "auxiliar.dat";
        CorredorRead readCheck = new CorredorRead(ruta);
        if (readCheck.buscarPorDorsal(dorsal) == null) {
            System.out.println("No existe el corredor");
            return;
        }

        CorredorRead cr = new CorredorRead(ruta);
        CorredorWrite cw = new CorredorWrite(ARCHIVO_AUX);
        try {
            cr.iniciarLectura();
            cw.iniciarEscritura();
            Corredor c;
            while ((c = cr.leer()) != null) {
                if (c.getDorsal() == dorsal) {
                    List<Puntuacion> historial = c.getHistorial();
                    if (historial == null) {
                        historial = new ArrayList<>();
                    }
                    boolean actualizado = false;
                    for (Puntuacion p : historial) {
                        if (p.getAnio() == nuevaPuntuacion.getAnio()) {
                            p.setPuntos(nuevaPuntuacion.getPuntos());
                            actualizado = true;
                            break;
                        }
                    }
                    if (!actualizado) {
                        historial.add(nuevaPuntuacion);
                    }
                    c.setHistorial(historial);
                }
                cw.escribirCorredor(c);
            }
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            cr.finalizarLectura();
            cw.finalizarEscritura();
        }

        if (!cr.borrarArchivo(new File(ruta))) {
            System.out.println("no se pudo borrar el archivo original");
        }

        if (!cw.renombrarArchivo(new File(ARCHIVO_AUX), ruta)) {
            System.out.println("no se pudo renombrar el archivo auxiliar");
        }


    }
}
