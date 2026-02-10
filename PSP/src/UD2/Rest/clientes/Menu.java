import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

public class Menu {
    static Scanner scanner = new Scanner(System.in);

    static URL url = null;
    static HttpURLConnection con = null;

    public static void main(String[] args) {
        displayMenu();
        int choice = scanner.nextInt();

        do {
            switch (choice) {
                case 1:
                    getClientes();
                    break;

                case 2:
                    String nombreProvincia = scanner.nextLine();
                    insertProvincia(nombreProvincia);
                    System.out.println("Insertar nombre del cliente:");
                    String nombre = scanner.nextLine();
                    do {
                        inserCliente(nombre, 0, false); // el 0 deberia ser el código de la provincia
                        
                    } while (!nombre.isEmpty());
                    
                    break;
                default:
                    break;
            }
            displayMenu();
            choice = scanner.nextInt();
        } while (choice != 3);
    }

    public static void displayMenu() {
        System.out.println("Menu:");
        System.out.println("1. GET Clientes");
        System.out.println("2. POST Provincia");
        System.out.println("3. Exit");
        System.out.print("Please select an option: ");
    }

    public static void insertProvincia(String nombreProvincia) {
        try {
            String parametros = "nombre=" + URLEncoder.encode(nombreProvincia, "UTF-8");
            String strURL = "http://localhost/clientes/rest.php/provincias";
            url = new URL(strURL);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            PrintWriter out = new PrintWriter(con.getOutputStream());
            out.print(parametros);
            out.close();

            con.connect();
            if (con.getResponseCode() == 201)
                System.out.printf("Provincia insertada");
            else
                System.out.println("Problemas.Respuesta: (" + con.getResponseCode() + ") " + con.getResponseMessage());
        } catch (IOException ex) {
            System.out.println("Error en la conexión");
        }
    }

    public static void inserCliente(String nombre, int codProvincia, boolean vip) {
        try {
            String parametros =
                    "nombre=" + URLEncoder.encode(nombre, "UTF-8") +
                            "&codProvincia=" + codProvincia +
                            "&vip=" + (vip ? 1 : 0);

            String strURL = "http://localhost/clientes/rest.php/clientes";
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
                /* Si en la inserción devolvemos un JSON con la clave generada, aquí deberíamos
                recuperar el JSON y analizarlo para obtenerla por si la necesitamos */
                System.out.println("Inserción correcta");
            } else {
                System.out.println("Problemas.Respuesta: (" + con.getResponseCode() + ") " + con.getResponseMessage());
            }
        } catch (IOException ex) {
            System.out.println("Error en la conexión");
        }
    }

    public static void getClientes() {
        try {
            String json = "";
            String strURL = "http://localhost/clientes/rest.php/clientes";
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
}
