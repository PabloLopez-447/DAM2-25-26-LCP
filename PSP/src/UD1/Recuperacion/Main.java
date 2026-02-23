//package UD1.Recuperacion;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//public class Main {
//    final static int MAX_ASISTENTES = 6;
//    final static String CMD_FIN = "fin";
//    static Scanner sc = new Scanner(System.in);
//
//    public static void main(String[] args) {
//        Fiesta fiesta = new Fiesta();
//        List<Asistente> asistentes = new ArrayList<>();
//        String nombre = "";
//        int i = 0;
//
//        while (i < MAX_ASISTENTES){
//            nombre = sc.nextLine();
//            if (nombre.equals(CMD_FIN)) break;
//            asistentes.add(new Asistente(nombre, fiesta));
//            asistentes.get(i).start();
//            i++;
//        }
//
//        fiesta.empezar();
//
//        for (Asistente asistente : asistentes) {
//            try {
//                asistente.join();
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }
//
//        for (Asistente asistente : asistentes) {
//            System.out.println(asistente.getName());
//            System.out.print("\t");
//            System.out.println(asistente.bebidas);
//            System.out.print("\t");
//            System.out.println("Le gustaron " +  asistente.bebidasGustadas);
//        }
//    }
//}
