package UD1.Repaso.Meteoritos;

public class NaveBS extends Thread {
    HWWC hwwc;

    public NaveBS(HWWC HWWC) {
        this.hwwc = HWWC;
    }

    @Override
    public void run() {
        while (hwwc.hayMeteorito()) {
            hwwc.BOOM();
        }
        System.out.println("NaveBS volviendo a la tierra");
    }
}
