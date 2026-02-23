package UD2.Sockets.Ejercicios.stockDavid;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor extends Thread {
    
    private ServerSocket serverSocket;
    public boolean salir = false;
    private GestorConexiones gestorConexiones;

    @Override
    public void run() {
        try { serverSocket = new ServerSocket(Config.PUERTO); } 
        catch (IOException ex) {
            System.out.println("Error al iniciar el servidor");
            return;
        }
        System.out.println("Servidor arriba, esperando conexiones");
        gestorConexiones = new GestorConexiones(serverSocket);

        while (!salir) {
            try {
                Socket socket = serverSocket.accept();
                new ConexionServidor(gestorConexiones, socket).start();
            } catch (IOException ex) {
                System.out.println("Servidor abajo");
                return;
            }
        }
    }
    
    public static void main(String[] args) {
        new Servidor().start();
    }
}
