package UD1.dom.corredoresYequipos;

import UD1.dom.corredoresYequipos.clases.Fondista;
import UD1.dom.corredoresYequipos.clases.Velocista;
import UD1.dom.corredoresYequipos.logica.GestorCorredores;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        GestorCorredores gestor = new GestorCorredores();

        // Cargar el XML
        if (!gestor.cargarDocumento("D:\\Datos\\DAM\\DAM2-25-26-LCP\\ADAT\\src\\UD1\\dom\\xml\\Corredores.xml")) {
            return;
        }

        int opcion;

        do {
            System.out.println("\n--- MENÚ CORREDORES ---");
            System.out.println("1. Listar corredores");
            System.out.println("2. Buscar por código");
            System.out.println("3. Buscar por dorsal");
            System.out.println("4. Insertar corredor");
            System.out.println("5. Eliminar corredor");
            System.out.println("6. Añadir/modificar puntuación");
            System.out.println("7. Eliminar puntuación");
            System.out.println("8. Guardar");
            System.out.println("0. Salir");
            System.out.print("Opción: ");

            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {

                case 1 -> gestor.listarTodos();

                case 2 -> {
                    System.out.print("Código: ");
                    gestor.mostrarPorCodigo(sc.nextLine());
                }

                case 3 -> {
                    System.out.print("Dorsal: ");
                    gestor.mostrarPorDorsal(Integer.parseInt(sc.nextLine()));
                }

                case 4 -> {
                    System.out.print("Tipo (V/F): ");
                    String tipo = sc.nextLine().toUpperCase();

                    System.out.print("Código: ");
                    String codigo = sc.nextLine();

                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();

                    System.out.print("Fecha (YYYY-MM-DD): ");
                    LocalDate fecha = LocalDate.parse(sc.nextLine());

                    System.out.print("Equipo: ");
                    String equipo = sc.nextLine();

                    if (tipo.equals("V")) {
                        System.out.print("Velocidad media: ");
                        float vel = Float.parseFloat(sc.nextLine());
                        gestor.insertarCorredor(new Velocista(0,codigo, nombre, equipo, fecha, vel));
                    } else {
                        System.out.print("Distancia max: ");
                        float dist = Float.parseFloat(sc.nextLine());
                        gestor.insertarCorredor(new Fondista(0, codigo, nombre, equipo, fecha, dist));
                    }
                }

                case 5 -> {
                    System.out.print("Código: ");
                    gestor.eliminarCorredor(sc.nextLine());
                }

                case 6 -> {
                    System.out.print("Código: ");
                    String c = sc.nextLine();
                    System.out.print("Año: ");
                    int anio = Integer.parseInt(sc.nextLine());
                    System.out.print("Puntos: ");
                    float p = Float.parseFloat(sc.nextLine());
                    gestor.actualizarPuntuacion(c, anio, p);
                }

                case 7 -> {
                    System.out.print("Código: ");
                    String c = sc.nextLine();
                    System.out.print("Año: ");
                    int anio = Integer.parseInt(sc.nextLine());
                    gestor.eliminarPuntuacion(c, anio);
                }

                case 8 -> gestor.guardarDocumento("corredores.xml");

                case 0 -> System.out.println("Saliendo...");

                default -> System.out.println("Opción incorrecta");
            }

        } while (opcion != 0);
    }
}
