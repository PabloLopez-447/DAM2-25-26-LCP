package UD1.ficherosBinarios.persistencia;

import UD1.ficherosBinarios.clases.Corredor;
import UD1.ficherosBinarios.clases.Equipo;

import java.io.*;

public class CorredorWrite extends Archivo{
    ObjectOutputStream oos;

    public CorredorWrite(String ruta) {
        super(ruta);
    }

    @Override
    public void abrirArchivo() {
        boolean appendMode = comprobarExistencia() && !ruta.isEmpty();

        try {
            if (appendMode) {
                //Archivo con contenido abrir sin cabezera
                oos = new AppendObjectOutputStream(new BufferedOutputStream(new FileOutputStream(ruta, appendMode)));
            }else {
                //Archivo nuevo o vacio crea con cabezera
                oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(ruta)));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void cerrarArchivo() {
        if (oos != null) {
            try {
                oos.close();
            } catch (IOException e) {}
        }
    }

//    public boolean verificarEquipoValido(int idEquipo) {
//        EquipoRandom archivoEquipos = new EquipoRandom("equipos.dat");
//        archivoEquipos.abrirArchivo;
//        try{
//            Equipo equipo = archivoEquipos.leerEquipo(idEquipo);
//            return equipo != null && equipo.isBorrado();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        finally {
//            archivoEquipos.cerrarArchivo;
//        }
//    }

    public void escribirCorredor (Corredor corredor) {
        if (oos == null) {
            System.out.println("el oos es null loquete");
            return;
        }

//        if (!verificarEquipoValido(corredor.getEquipo())){
//            System.out.println("el equipo no es valido");
//            return;
//        }

        try {
            oos.writeObject(corredor);
            oos.flush();
        } catch (IOException e) {}
    }

    public void iniciarEscritura(){
        abrirArchivo();
    }

    public void finalizarEscritura(){
        cerrarArchivo();
    }
}
