package UD1.Ejercicios.LlamadaPuerta;

public class AppLlamarPuerta extends Thread {
    int paciencia;

    public AppLlamarPuerta(int paciencia) {
        this.paciencia = paciencia;
    }

    @Override
    public void run() {
        try {
            int numToques=0;
            System.out.println("Currando");

            while (numToques<paciencia) {
                
            }

            if (Thread.interrupted()) {
                System.out.println("toc toc");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static void main(String[] args) {
        AppLlamarPuerta trabajador = new AppLlamarPuerta(5);
        trabajador.start();

        while (trabajador.isAlive()) {
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            if (trabajador.paciencia != 0) {
                try {
                    trabajador.paciencia--;
                    trabajador.interrupt();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
