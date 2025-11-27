package UD1.examen.parte1.ejercicio2;


import UD1.examen.parte1.ejercicio2.logica.GestorAlojamientos;
import UD1.examen.parte1.ejercicio2.persistencia.TipoValidacion;

// Pablo López Couso DNI:77550221V
public class Main {
    public static void main(String[] args) {
        GestorAlojamientos g = new GestorAlojamientos("src/exud1p1pablolc/ejercicio2/xml/AlojamientosHotel.xml", TipoValidacion.DTD);

        String rutaSalida = "src/exud1p1pablolc/ejercicio2/xml/AlojamientosUpdate.xml";

        //Comentar y descomentar los casos que se quieran probar individualmente

        //Suite sin reservas
        g.insertarReserva("S201", "R089", "Pablo Lopez Couso", "12345678V", "2025-11-24" , rutaSalida);
        //Habitacion con reservas
        g.insertarReserva("H104", "R099", "Pablo Lopez Couso", "12345678V", "2025-11-24" , rutaSalida);
        //Habitacion no existe
        g.insertarReserva("2", "R099", "Pablo Lopez Couso", "12345678V", "2025-11-24" , rutaSalida);
        //Ya existe el codigo de la reserva
        g.insertarReserva("H104", "R015", "Pablo Lopez Couso", "12345678V", "2025-11-24" , rutaSalida);
        //Habitacion ya ocupada
        g.insertarReserva("H101", "R099", "Pablo Lopez Couso", "12345678V", "2025-11-24" , rutaSalida);
    }
}
