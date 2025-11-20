package UD1.ay.ACT3.Actividad3.aplicacion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import UD1.ay.ACT3.Actividad3.dominio.Corredor;
import UD1.ay.ACT3.Actividad3.dominio.Fondista;
import UD1.ay.ACT3.Actividad3.dominio.Velocista;
import UD1.ay.ACT3.Actividad3.persistencia.EscribirCorredores;
import UD1.ay.ACT3.Actividad3.persistencia.LeerCorredores;

public class App {
    private static String nombreArchivo = "Corredores.dat";

    public static void main(String[] args) {
        Corredor corredor1 = new Velocista("Juan Pérez", LocalDate.of(2000, 5, 12), 1, 10.34f);
        Corredor corredor2 = new Fondista("Ana Gómez", LocalDate.of(1995, 3, 22), 2, 42.195f);
        Corredor corredor3 = new Velocista("Carlos Ruiz", LocalDate.of(2002, 11, 30), 3, 9.75f);
        Corredor corredor4 = new Fondista("María López", LocalDate.of(2000, 7, 15), 1, 21.097f);
        Corredor corredor5 = new Velocista("Pedro García", LocalDate.of(1995, 8, 5), 1, 11.20f);
        Corredor corredor6 = new Fondista("Laura Martínez", LocalDate.of(2002, 9, 10), 4, 35.00f);

        ArrayList<Corredor> corredores = new ArrayList<>(List.of(corredor1, corredor2, corredor3));

        EscribirCorredores.anhadirCorredores(corredores);

        System.out.println(LeerCorredores.leerCorredores());

    }

}
