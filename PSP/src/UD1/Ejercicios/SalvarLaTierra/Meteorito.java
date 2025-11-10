package UD1.Ejercicios.SalvarLaTierra;

public class Meteorito {
    private boolean taladrado = false;

    public synchronized void taladrar() {
        try {
            if (taladrado) {
                return;
            }
            System.out.println("Taladrando....");
            Thread.sleep((int) (Math.random() * 2000));
            taladrado = true;
            System.out.println("Taladrado");
            wait();
            System.out.println("Repostado. Nave A se va del meteorito");

        } catch (Exception e) {

        }
    }

    public synchronized boolean explotaRepostar() {
        try {
            if (taladrado) {
                System.out.println("Repostando y poniendo bomba....");
                Thread.sleep((int) (Math.random() * 2000));
                notify();
                System.out.println("NaveBS se va del meteorito");
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {

        }
        return false;
    }
}