package UD1.ay.ACT3.Actividad3.persistencia;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import UD1.ay.ACT3.Actividad3.dominio.Corredor;

public class LeerCorredores {
        private static ObjectInputStream in;

    private static void abrirArchivo() {
        try {
            in = new ObjectInputStream(new FileInputStream("Corredores.dat"));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void cerrarArchivo() {
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Corredor> leerCorredores() {
        abrirArchivo();

        try {
            ArrayList<Corredor> corredores = (ArrayList<Corredor>) in.readObject(); 
            return corredores;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        
        cerrarArchivo();
        return new ArrayList<>();
    }

}
