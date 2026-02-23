//package UD1.Recuperacion;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class Asistente extends Thread {
//    final static int MAX_BEBIDAS_RICAS = 5;
//
//    Fiesta fiesta;
//    List<Bebida> bebidas = new ArrayList<Bebida>();
//    int bebidasGustadas;
//    int porcentajeGustoAlcohol;
//
//    public Asistente(String name, Fiesta fiesta) {
//        super(name);
//        this.fiesta = fiesta;
//        this.bebidasGustadas = 0;
//        this.porcentajeGustoAlcohol = 10;
//    }
//
//    public int nBebida() {
//        return bebidas.size() + 1;
//    }
//
//    public int getPorcentajeGustoAlcohol() {
//        return porcentajeGustoAlcohol;
//    }
//
//    public void aumentarPorcentajeGustoAlcohol() {
//        porcentajeGustoAlcohol++;
//    }
//
//    public void incrementarBedidaGustadas() {
//        bebidasGustadas++;
//    }
//
//    @Override
//    public void run() {
//        while (bebidas.size() < MAX_BEBIDAS_RICAS) {
//            fiesta.empezarFiesta(this);
//            try {
//                Thread.sleep(300);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }
//
//    }
//}
