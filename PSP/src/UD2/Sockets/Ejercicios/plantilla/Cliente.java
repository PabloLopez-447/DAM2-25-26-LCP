package UD2.Sockets.Ejercicios.plantilla;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {

        try (Socket socket = new Socket("localhost", Config.PUERTO);
             DataInputStream in = new DataInputStream(socket.getInputStream());
             DataOutputStream out = new DataOutputStream(socket.getOutputStream());
             Scanner sc = new Scanner(System.in)) {

            boolean salir = false;

            while (!salir) {
                String comando = sc.nextLine();
                out.writeUTF(comando);

                if (comando.equalsIgnoreCase(Config.CMD_SALIR) ||
                        comando.equalsIgnoreCase(Config.CMD_APAGAR)) {
                    salir = true;
                } else {
                    System.out.println(in.readUTF());
                }
            }

        } catch (IOException e) {
            System.out.println("Error cliente");
        }
    }
}
