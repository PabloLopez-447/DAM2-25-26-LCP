package UD1.Ejercicios.SalvarLaTierra;

public class NaveA extends Nave {

    public NaveA(HWWC hwwc) {
        super(hwwc);

    }

    @Override
    public void run() {
        try {
            while (hwwc.quedanMeteoritos()) {
                Meteorito m = hwwc.obtenerMeteorito();

                m.taladrar();
            }
            System.out.println("NaveA regresando a la base.");
        } catch (Exception e) {
        }
    }
}
