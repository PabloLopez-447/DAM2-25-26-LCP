package UD2.Rest.hotel.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

// POST /reservas -> idReservaInsertada /201 - 406 - 400
// GET /habitaciones -> json array todas las habitaciones / 200
// GET /reservas/{idHabitacion} -> json array reservas de la habitacion / 200
// DELETE /reservas/{codReserva} -> NADA / 204 - 404

public class Menu {

    static URL url = null;
    static HttpURLConnection con = null;

    public static void main(String[] args) {
        displayMenu();
        int choice = new Scanner(System.in).nextInt();

        do {
            switch (choice) {
                case 1:
                    getHabitaciones();
                    System.out.println();
                    System.out.println("Introduce el codigo de la habiación de la que quieres ver las reservas");
                    String id = new Scanner(System.in).nextLine();
                    getReservasPorHabitacion(id);

                    break;

                case 2:
                    getHabitaciones();
                    System.out.println();
                    System.out.println("Introduce el codigo de la habiación que quieres reservar");
                    String idHabReservar = new Scanner(System.in).nextLine();
                    System.out.println("Nombre de la reserva");
                    String nombre = new Scanner(System.in).nextLine();
                    System.out.println("Dia de entrada");
                    int dia = new Scanner(System.in).nextInt();
                    System.out.println("Numero de dias de la reserva");
                    int numDias = new Scanner(System.in).nextInt();

                    insertReserva(idHabReservar, nombre, dia, numDias);
                    break;

                case 3:
                    getHabitaciones();
                    System.out.println();
                    System.out.println("Introduce el codigo de la habiación de la que quieres ver las reservas");
                    String idHabitacion = new Scanner(System.in).nextLine();
                    getReservasPorHabitacion(idHabitacion);
                    System.out.println("Introduce el codigo de la reserva que quieres borrar");
                    String idReserva = new Scanner(System.in).nextLine();
                    borrarReserva(idReserva);
                    break;

                default:
                    break;
            }
            displayMenu();
            choice = new Scanner(System.in).nextInt();
        } while (choice != 4);
    }

    public static void displayMenu() {
        System.out.println("Menu:");
        System.out.println("1. Ver reservas por habitacion");
        System.out.println("2. Insertar reserva");
        System.out.println("3. Borrar Reserva");
        System.out.println("4. Exit");
        System.out.print("Selecciona una opcion: ");
    }

    public static int insertReserva(String idHabitacion, String nombre, int dia, int numDias) {
        String json = "";
        try {
            String parametros = "idHabitacion=" + URLEncoder.encode(idHabitacion, "UTF-8") + "&nombre=" + nombre + "&dia=" + dia + "&numDias=" + numDias;
            String strURL = "http://localhost/hotel/index.php/reservas";
            url = new URL(strURL);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            PrintWriter out = new PrintWriter(con.getOutputStream());
            out.print(parametros);
            out.close();

            con.connect();
            if (con.getResponseCode() == 201) {
                BufferedReader bufferIn = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String linea;
                while ((linea = bufferIn.readLine()) != null)
                    json += linea;
                bufferIn.close();
                System.out.println("Inserción correcta");
                JSONObject object = new JSONObject(json);
                return object.getInt("id");
            } else
                System.out.println("Problemas.Respuesta: (" + con.getResponseCode() + ") " + con.getResponseMessage());
            return -1;
        } catch (IOException ex) {
            System.out.println("Error en la conexión");
        }
        return -1;
    }

    public static void getHabitaciones() {
        try {
            String json = "";
            String strURL = "http://localhost/hotel/index.php/habitaciones";
            url = new URL(strURL);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            if (con.getResponseCode() == 200) {
                BufferedReader bufferIn = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String linea;
                while ((linea = bufferIn.readLine()) != null)
                    json += linea;
                bufferIn.close();

                JSONArray datos = new JSONArray(json);
                for(int i=0; i < datos.length(); i++) {
                    JSONObject habitacion = datos.getJSONObject(i);
                    int idHabitacion = habitacion.getInt("idHabitacion");
                    String nombre = habitacion.getString("nombre");

                    System.out.println("ID: " + idHabitacion + " Nombre: " + nombre);
                }
            } else {
                System.out.println("Problemas.Respuesta: (" + con.getResponseCode() + ") " + con.getResponseMessage());
            }
        } catch (IOException ex) {
            System.out.println("Error en la conexión");
        }
    }

    public static void getReservasPorHabitacion(String idHabitacion) {
        try {
            String json = "";
            String strURL = "http://localhost/hotel/index.php/reservas/" + idHabitacion;
            url = new URL(strURL);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            if (con.getResponseCode() == 200) {
                BufferedReader bufferIn = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String linea;
                while ((linea = bufferIn.readLine()) != null)
                    json += linea;
                bufferIn.close();

                JSONArray datos = new JSONArray(json);
                for(int i=0; i < datos.length(); i++) {
                    JSONObject reserva = datos.getJSONObject(i);
                    int codReserva = reserva.getInt("codReserva");
                    int idHab = reserva.getInt("idHabitacion");
                    String nombre = reserva.getString("nombre");
                    int dia = reserva.getInt("dia");
                    int numDias = reserva.getInt("numDias");

                    System.out.println("ID reserva: " + codReserva + " ID habitacion: " + idHab + " Nombre: " + nombre + " Dia: " + dia + " NumDias: " + numDias);
                }
            } else {
                System.out.println("Problemas.Respuesta: (" + con.getResponseCode() + ") " + con.getResponseMessage());
            }
        } catch (IOException ex) {
            System.out.println("Error en la conexión");
        }
    }

    public static void borrarReserva(String idReserva) {
        try {

            String strURL = "http://localhost/hotel/index.php/reservas/" + idReserva;
            url = new URL(strURL);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("DELETE");
            con.connect();
            if (con.getResponseCode() == 204) {
                System.out.println("Se borro la reserva con id: " + idReserva);
            } else {
                System.out.println("Problemas.Respuesta: (" + con.getResponseCode() + ") " + con.getResponseMessage());
            }
        } catch (IOException ex) {
            System.out.println("Error en la conexión");
        }
    }
}
