package UD2.Rest.instituto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;
import org.json.JSONObject;

public class Menu {

    static URL url = null;
    static HttpURLConnection con = null;

    public static void main(String[] args) {
        displayMenu();
        int choice = new Scanner(System.in).nextInt();

        do {
            switch (choice) {
                case 1:
                    String nombreCurso;
                    int aforo;
                    System.out.println("Insertar nombre del curso:");
                    nombreCurso = new Scanner(System.in).nextLine();
                    System.out.println("Insertar el aforo del curso:");
                    aforo = new Scanner(System.in).nextInt();
                    int codCurso = insertCurso(nombreCurso, aforo);
                    String nombreAlumno;
                    int alumnosInsertados = 0;
                    System.out.println("Insertar nombre del alumno:");
                    do {
                        nombreAlumno = new Scanner(System.in).nextLine();
                        insertAlumno(nombreAlumno, codCurso);
                        alumnosInsertados++;
                        System.out.println("Insertar nombre del alumno:");
                    } while (!nombreAlumno.isEmpty() || alumnosInsertados < aforo);

                    break;

                case 2:
                    System.out.println("Inserta nombre del curso para ver los alumnos");
                    nombreCurso = new Scanner(System.in).nextLine();
                    getAlumnosPorCurso(nombreCurso);
                    break;

                case 3:
                    System.out.println("Inserta nombre del curso para borrar");
                    nombreCurso = new Scanner(System.in).nextLine();
                    borrarCurso(nombreCurso);
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
        System.out.println("1. Insertar Curso");
        System.out.println("2. Ver alumnos");
        System.out.println("3. Borrar Curso");
        System.out.println("4. Exit");
        System.out.print("Selecciona una opcion: ");
    }

    public static int insertCurso(String nombreCurso, int aforo) {
        String json = "";
        try {
            String parametros = "nombre=" + URLEncoder.encode(nombreCurso, "UTF-8") + "&aforo=" + aforo;
            String strURL = "http://localhost/instituto/index.php/cursos";
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

    public static int insertAlumno(String nombre, int idCurso) {
        String json = "";
        if (nombre.isEmpty()) {
            return -1;
        }
        try {
            String parametros = "nombre=" + URLEncoder.encode(nombre, "UTF-8") +
                    "&idCurso=" + idCurso;

            String strURL = "http://localhost/instituto/index.php/alumnos";
            url = new URL(strURL);
            con = (HttpURLConnection) url.openConnection();

            // le pasamos los parámetros en el cuerpo de la petición
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            PrintWriter out = new PrintWriter(con.getOutputStream());
            out.print(parametros);
            out.close();

            con.connect();
            if (con.getResponseCode() == 201) {
                /*
                 * Si en la inserción devolvemos un JSON con la clave generada, aquí deberíamos
                 * recuperar el JSON y analizarlo para obtenerla por si la necesitamos
                 */
                BufferedReader bufferIn = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String linea;
                while ((linea = bufferIn.readLine()) != null)
                    json += linea;
                bufferIn.close();
                System.out.println("Inserción correcta");
                JSONObject object = new JSONObject(json);
                return object.getInt("id");
            } else {
                System.out.println("Problemas.Respuesta: (" + con.getResponseCode() + ") " + con.getResponseMessage());
                return -1;
            }
        } catch (IOException ex) {
            System.out.println("Error en la conexión");
        }
        return -1;
    }

    public static void getAlumnosPorCurso(String nombreCurso) {
        try {
            String json = "";
            String strURL = "http://localhost/instituto/index.php/alumnos/" + nombreCurso;
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
                System.out.println(json);
            } else {
                System.out.println("Problemas.Respuesta: (" + con.getResponseCode() + ") " + con.getResponseMessage());
            }
        } catch (IOException ex) {
            System.out.println("Error en la conexión");
        }
    }

    public static void borrarCurso(String nombreCurso) {
        try {

            String strURL = "http://localhost/instituto/index.php/cursos/" + nombreCurso;
            url = new URL(strURL);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("DELETE");
            con.connect();
            if (con.getResponseCode() == 204) {
                System.out.println("Se borro el curso: " + nombreCurso);
            } else {
                System.out.println("Problemas.Respuesta: (" + con.getResponseCode() + ") " + con.getResponseMessage());
            }
        } catch (IOException ex) {
            System.out.println("Error en la conexión");
        }
    }
}
