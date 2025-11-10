package UD1.Ejercicios.PorraCorregido;

import java.util.ArrayList;

public class PartidoCDM {
    static final int NUM_EMPLEADOS = 3;

    public static void main(String[] args) {
        // Creamos las posibles apuestas
        Bote bote = new Bote();
        // Creamos los hilos empleados
        ArrayList<Empleado> listaEmpleados = new ArrayList<>();
        for (int i = 0; i < NUM_EMPLEADOS; i++)
            listaEmpleados.add(new Empleado("E" + i, bote));
        // Lanzamos los hilos
        for (Empleado empleado : listaEmpleados)
            empleado.start();
        // Esperamos a que todos acaben sus apuestas
        for (Empleado empleado : listaEmpleados)
            try {
                empleado.join();
            } catch (InterruptedException ex) {
            }
        // Se juega el partido
        String resultadoPartido = Bote.getFormatoApuesta();
        System.out.println("Resultado partido: " + resultadoPartido);

        // Visualizamos ganadores y totales
        ApuestaResultado apuestaGanadoraPartido = bote.getApuesta(resultadoPartido);
        int importeApuestasGanadoras = apuestaGanadoraPartido.importe;
        int numApuestasGanadoras = apuestaGanadoraPartido.numApuestas;
        System.out.printf("%d € a repartir entre %d apuestas\n",
                importeApuestasGanadoras,
                numApuestasGanadoras);
        System.out.println(bote);
        System.out.println("Total a repartir: " + bote.getRecaudacion() + "€");
        System.out.println("Ganadores:");
        for (Empleado empleado : listaEmpleados) {
            ApuestaResultado apuestaGanadora = empleado.getApuestaGanadora(resultadoPartido);
            if (apuestaGanadora != null) {
                int apuestaDelEmpleado = apuestaGanadora.importe;
                int numApuestasDelEmpleado = apuestaGanadora.numApuestas;
                float porcentajeGananciaEmpleado = (float) apuestaDelEmpleado / importeApuestasGanadoras;
                float gananciaDelEmpleado = bote.getRecaudacion() * porcentajeGananciaEmpleado;
                System.out.printf("%s apostó %d€ en %d apuesta/s y ganó %.2f€\n",
                        empleado.getName(),
                        apuestaDelEmpleado,
                        numApuestasDelEmpleado,
                        gananciaDelEmpleado);
            }
        }
    }
}
