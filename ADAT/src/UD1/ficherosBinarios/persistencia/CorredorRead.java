package UD1.ficherosBinarios.persistencia;

import UD1.ficherosBinarios.clases.Corredor;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class CorredorRead extends Archivo{

    ObjectInputStream ois;

    public CorredorRead(String ruta) {
        super(ruta);
    }

    @Override
    public void abrirArchivo() {
        if (!comprobarExistencia()){
            System.out.println("No existe el archivo");
            return;
        }

        try {
            ois = new ObjectInputStream(new FileInputStream(ruta));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void cerrarArchivo() {
        if (!comprobarExistencia()){
            System.out.println("No existe el archivo");
            return;
        }

        try {
            ois.close();
        } catch (IOException e) {

        }
    }

    public Corredor leer(){
        try {
            return (Corredor) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public int obtenerUltimoDorsal(){
        int ultimoDorsal = 0;
        try {
            abrirArchivo();
            if (ois == null){
                return 0;
            }
            Corredor c;
            while ((c = leer()) != null){
                ultimoDorsal = c.getDorsal();
            }
        } finally {
            cerrarArchivo();
        }
        return ultimoDorsal;
    }

    public Corredor buscarPorDorsal(int dorsal){
        Corredor encontrado = null;
        try {
            abrirArchivo();
            if (ois == null){
                return null;
            }
            Corredor c;
            while ((c = leer()) != null){
                if (c.getDorsal() == dorsal){
                    encontrado = c;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            cerrarArchivo();
        }
        return encontrado;
    }

    public void iniciarLectura(){
        abrirArchivo();
    }

    public void finalizarLectura(){
        cerrarArchivo();
    }

}
