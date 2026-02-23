package UD2.Sockets.Ejercicios.stock2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    private static ServerSocket serverSocket;
    public static boolean salir = false;
    private static Gestor gestor;

    public static void main(String[] args) {
        try {
            serverSocket = new ServerSocket(Config.PUERTO);
            gestor = new Gestor(serverSocket);
            System.out.println("Servidor escuchando...");
        } catch (IOException e) {
            System.out.println("Error iniciando servidor");
            return;
        }

        while (!salir) {
            try {
                Socket socket = serverSocket.accept();
                new HiloConexion(gestor, socket).start();
            } catch (IOException e) {
                System.out.println("Servidor cerrado");
                return;
            }
        }
    }
}