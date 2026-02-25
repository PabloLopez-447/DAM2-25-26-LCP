package UD2.Sockets.Ejercicios.examen;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        Socket socket;
        DataInputStream input = null;
        DataOutputStream output = null;
        try {
            socket = new Socket("localhost", Config.PUERTO);
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            System.out.println("Error al conectar con el servidor"); return;
        }
        try {
            System.out.print("Introduzca su usuario: ");
            String usuario= new Scanner(System.in).nextLine();
            output.writeUTF(usuario);
            boolean loginOk=input.readBoolean();
            if (loginOk) {
                boolean salir=false;
                boolean acabo=false;
                do {
                    if (!acabo){
                        System.out.println("Introduce tu pregunta");
                        String pregunta= new Scanner(System.in).nextLine();
                        output.writeUTF(pregunta);
                        do {
                            System.out.println("Introduce la respuesta");
                            String respuesta= new Scanner(System.in).nextLine();
                            output.writeUTF(respuesta);
                            System.out.println("Introduce tu pregunta");
                            pregunta= new Scanner(System.in).nextLine();
                            output.writeUTF(pregunta);
                        } while (!pregunta.isEmpty());
                        acabo=true;
                    }

                    do {
                        System.out.println(input.readUTF());
                        String respuesta= new Scanner(System.in).nextLine();
                        output.writeUTF(respuesta);
                    }
                    while (!input.readBoolean());

                    System.out.println(input.readUTF());

                }
                while (!salir);
            }
            else {
                System.out.println("El usuario con ese nombre ya esta logeado");
            }
        } catch (IOException ex) { System.out.println("La conexion fue finalizada."); }
    }

}
