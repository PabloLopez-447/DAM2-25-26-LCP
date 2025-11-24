package UD1.examen.parte1.ejercicio1.logica;



import UD1.examen.parte1.ejercicio1.clases.Alojamiento;
import UD1.examen.parte1.ejercicio1.clases.Alojamientos;
import UD1.examen.parte1.ejercicio1.clases.Reserva;
import UD1.examen.parte1.ejercicio1.persistenciaJAXB.AlojamientosJAXB;

import java.util.ArrayList;
import java.util.List;

// Pablo López Couso DNI:77550221V
public class GestorAlojamientos {
    String ruta;

    public GestorAlojamientos(String ruta) {
        this.ruta = ruta;
    }

    public void visualizarAlojamientosReservados(){
        AlojamientosJAXB alojamientosJAXB = new AlojamientosJAXB();

        Alojamientos al = alojamientosJAXB.listarAlojamientos(ruta);

        List<Alojamiento> alojamientos = new ArrayList<>();

        for(Alojamiento a : al.getAlojamientos()){
            for(Reserva reserva : a.getReservas()){
                if (reserva.getFechaFin() == null){
                    alojamientos.add(a);
                }
            }
        }

        for (Alojamiento a : alojamientos){
            System.out.println(a);
        }
    }

}
