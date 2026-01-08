package UD1.Repaso.Meteoritos;

public class Main {
    static final int NUM_METEORITOS = 10;
    static final int NUM_NAVES_A = 5;
    static final int NUM_NAVES_BS = 3;

    public static void main(String[] args) {
        NaveA[] navesA = new NaveA[NUM_NAVES_A];
        NaveBS[] navesBS = new NaveBS[NUM_NAVES_BS];

        HWWC hwwc = new HWWC(NUM_METEORITOS);

        for (int i = 0; i < NUM_NAVES_A; i++) {
            navesA[i] = new NaveA(hwwc);
            navesA[i].start();
        }
        for (int i = 0; i < NUM_NAVES_BS; i++) {
            navesBS[i] = new NaveBS(hwwc);
            navesBS[i].start();
        }

        for (NaveA naveA : navesA) {
            try {
                naveA.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        for (NaveBS naveBS : navesBS) {
            try {
                naveBS.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
