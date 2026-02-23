package UD2.Sockets.Ejercicios.stockDavid;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) throws IOException {
        Socket socket;
        DataInputStream input = null;
        DataOutputStream output = null;
        Scanner sc;
        try {
            socket = new Socket("localhost", Config.PUERTO);
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            System.out.println("Error al conectar con el servidor");
            return;
        }
        sc = new Scanner(System.in);
        try {
            boolean salir=!input.readBoolean();
            if(salir) System.out.println(Config.STR_NO_CONEXION);
            while (!salir) {
                System.out.printf("[%s]> ",socket.getLocalSocketAddress());
                String comando = sc.nextLine().toUpperCase().trim();
                output.writeUTF(comando);
                switch(comando) {
                    case Config.CMD_SALIR:
                        System.out.println(input.readUTF());
                        salir=true; break;
                    case Config.CMD_APAGAR:
                        if(input.readBoolean())
                            salir=true;
                        else
                            System.out.println(Config.STR_CONEXIONES_ACTIVAS);
                        break;
                    default:
                        System.out.println(input.readUTF());
                }
            }
        } catch (IOException ex) {
            System.out.println("Problemas con la conexión.");
        }
        sc.close();
        socket.close();
    }
}
