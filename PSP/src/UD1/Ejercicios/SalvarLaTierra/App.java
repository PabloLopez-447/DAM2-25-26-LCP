package UD1.Ejercicios.SalvarLaTierra;

import java.util.ArrayList;
import java.util.List;

public class App {
    static final int NUM_METEORITOS = 10;
    static final int NUM_NAVES_A = 5;
    static final int NUM_NAVES_BS = 3;

    public static void main(String[] args) {
        HWWC hwwc = new HWWC(NUM_METEORITOS);
        List<Nave> naves = new ArrayList<>();

        for (int i = 0; i < NUM_NAVES_A; i++) {
            NaveA naveA = new NaveA(hwwc);
            naveA.start();
            naves.add(naveA);
        }

        for (int i = 0; i < NUM_NAVES_BS; i++) {
            NaveBS naveBS = new NaveBS(hwwc);
            naveBS.start();
            naves.add(naveBS);
        }

        for (Nave nave : naves) {
            try {
                nave.join();
            } catch (Exception e) {
            }
        }

        System.out.println("\nSe salvo la tierra chat");
    }
}
