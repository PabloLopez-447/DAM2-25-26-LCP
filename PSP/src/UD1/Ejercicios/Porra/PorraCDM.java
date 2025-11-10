package UD1.Ejercicios.Porra;

import java.util.*;

public class PorraCDM {

    static final int NUM_EMPLEADOS = 10;
    static final int NUM_APUESTAS_EMPLEADO = 5;
    static final int MAX_GOLES = 4;
    static final double APUESTA = 1.0;
    static double bote = 0.0;
    static List<Apuesta> apuestas = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Iniciando la porra de CDM...\n");

        Empleado[] empleados = new Empleado[NUM_EMPLEADOS];
        for (int i = 0; i < NUM_EMPLEADOS; i++) {
            empleados[i] = new Empleado(i + 1);
            empleados[i].start();
        }

        for (Empleado e : empleados) {
            e.join();
        }

        Random rand = new Random();
        int golesDM2 = rand.nextInt(MAX_GOLES * 4 + 1);
        int golesPRF = rand.nextInt(MAX_GOLES + 1);

        System.out.println("\n=== RESULTADO DEL PARTIDO ===");
        System.out.println("DM2 " + golesDM2 + " - " + golesPRF + " PRF");

        List<Apuesta> ganadores = new ArrayList<>();
        for (Apuesta a : apuestas) {
            if (a.golesDM2 == golesDM2 && a.golesPRF == golesPRF) {
                ganadores.add(a);
            }
        }

        System.out.println("\n=== RESULTADOS DE LA PORRA ===");
        System.out.printf("Total apostado: %.2f €\n", bote);

        if (ganadores.isEmpty()) {
            System.out.println("No hubo ganadores ");
        } else {
            double premio = bote / ganadores.size();
            System.out.println("Número de ganadores: " + ganadores.size());
            for (Apuesta g : ganadores) {
                System.out.printf("Empleado %d gana %.2f €\n", g.idEmpleado, premio);
            }
        }
    }

    public static synchronized void registrarApuesta(Apuesta a) {
        bote += APUESTA;
        apuestas.add(a);
    }

    static class Empleado extends Thread {
        int idEmpleado;
        Random rand = new Random();

        Empleado(int id) {
            this.idEmpleado = id;
        }

        @Override
        public void run() {
            for (int i = 0; i < NUM_APUESTAS_EMPLEADO; i++) {
                try {
                    Thread.sleep(100 + rand.nextInt(201));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                int golesDM2 = rand.nextInt(MAX_GOLES * 4 + 1);
                int golesPRF = rand.nextInt(MAX_GOLES + 1);

                PorraCDM.registrarApuesta(new Apuesta(idEmpleado, golesDM2, golesPRF));
            }
        }
    }

    static class Apuesta {
        int idEmpleado;
        int golesDM2;
        int golesPRF;

        Apuesta(int idEmpleado, int golesDM2, int golesPRF) {
            this.idEmpleado = idEmpleado;
            this.golesDM2 = golesDM2;
            this.golesPRF = golesPRF;
        }
    }
}
