package UD1.Repaso.Meteoritos;

public class NaveA extends Thread {
    HWWC HWWC;

    public NaveA(HWWC HWWC) {
        this.HWWC = HWWC;
    }

    @Override
    public void run() {
        while (HWWC.hayMeteorito()) {
            HWWC.Taladrar();
        }
        System.out.println("NaveA volviendo a la tierra");

    }

}
