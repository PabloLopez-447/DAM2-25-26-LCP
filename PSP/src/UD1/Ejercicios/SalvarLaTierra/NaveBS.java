package UD1.Ejercicios.SalvarLaTierra;

public class NaveBS extends Nave {

    public NaveBS(HWWC hwwc) {
        super(hwwc);

    }

    @Override
    public void run() {
        while (hwwc.quedanMeteoritos()) {
            Meteorito m = hwwc.obtenerMeteorito();

            if (m.explotaRepostar()) {
                hwwc.eliminarMeteorito(m);
                System.out.println("\tDestruyó meteorito ");
            }
            try {
                Thread.sleep(599);
            } catch (Exception e) {

            }
        }
        System.out.println("\tNaveBS regresando a la base.");
    }

}
