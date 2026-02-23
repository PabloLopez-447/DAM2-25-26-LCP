//package UD1.Recuperacion;
//
//import java.util.Random;
//
//public class Fiesta {
//    static Random rand = new Random();
//
//    public boolean empezo = false;
//
//    public synchronized void empezarFiesta(Asistente asistente) {
//        while (!empezo) {
//            try {
//                System.out.println(asistente.getName() + " ha llegado y se pone a esperar");
//                wait();
//            } catch (InterruptedException _) {
//            }
//        }
//
//        Bebida bebida = new Bebida(asistente.nBebida());
//        asistente.bebidas.add(bebida);
//
//        if (bebida.esAlcoholica()) {
//            int n = rand.nextInt(100);
//            if (n < asistente.getPorcentajeGustoAlcohol()) {
//                System.out.println("A " + asistente.getName() + " le ha gustado su bebida n.º " + asistente.nBebida());
//                asistente.aumentarPorcentajeGustoAlcohol();
//                asistente.incrementarBedidaGustadas();
//            }
//            else  {
//                System.out.println("A " + asistente.getName() + " no le ha gustado su bebida n.º " + asistente.nBebida());
//            }
//        } else {
//            System.out.println("A " + asistente.getName() + " le ha gustado su bebida n.º " + asistente.nBebida());
//            asistente.incrementarBedidaGustadas();
//        }
//
//
//    }
//
//    public synchronized void empezar(){
//        empezo = true;
//        notifyAll();
//    }
//
//}
