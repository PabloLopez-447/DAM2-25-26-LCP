package UD1.Repaso.Interrupt;

public class HiloTrabajador extends Thread {
    int paciencia;

    public HiloTrabajador(int paciencia) {
        this.paciencia = paciencia;
    }

    @Override
    public void run() {
        int num_toc_toc = 0;
        int i = 0;
        System.out.println("Comienza el hilo a trabajar");
        while (num_toc_toc < paciencia) {
            System.out.println(i++ + " ");
            if (Thread.interrupted())
                if (++num_toc_toc < paciencia)
                    System.out.println(num_toc_toc + "º interrupción. PASO");
        }
        System.out.println("Qué pesados!. Tendré que parar de trabajar e ir ver quién es.");
    }

    public static void main(String[] args) {

        HiloTrabajador hilo = new HiloTrabajador(3);
        hilo.start();
        int num_toc_toc = 0;
        while (hilo.isAlive()) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
            }
            if (!hilo.isInterrupted()) {
                System.out.println("TOC TOC! (" + ++num_toc_toc + ")");
                hilo.interrupt();
            }
        }
    }
}
