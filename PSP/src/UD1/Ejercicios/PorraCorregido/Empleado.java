package UD1.Ejercicios.PorraCorregido;

import java.util.HashMap;

public class Empleado extends Thread {
    static final int NUM_APUESTAS = 5;
    Bote bote;

    HashMap<String, ApuestaResultado> apuestas;

    public Empleado(String nombre, Bote bote) {
        super(nombre);
        this.bote = bote;
        apuestas = new HashMap<>();
    }

    @Override
    public void run() {
        for (int i = 0; i < NUM_APUESTAS; i++) {
            int importeApostado = i + 1;
            String resultado = Bote.getFormatoApuesta();
            bote.apuesta(resultado, importeApostado);

            System.out.println(getName() + " " + importeApostado + "€ a " + resultado);

            // apunto mi apuesta ( es como guardar el ticket)
            // aunque también se guarde en el Bote (allí sin saber el ganador, LOPD!)
            ApuestaResultado apuestaResultado = apuestas.get(resultado);
            if (apuestaResultado == null)
                apuestaResultado = new ApuestaResultado(resultado);
            apuestaResultado.apuesta(importeApostado);
            apuestas.put(resultado, apuestaResultado);
        }
    }

    public ApuestaResultado getApuestaGanadora(String resultadoPartido) {
        return apuestas.get(resultadoPartido);
    }
}