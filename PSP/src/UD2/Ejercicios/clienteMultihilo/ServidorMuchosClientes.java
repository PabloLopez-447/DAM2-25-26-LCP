package UD2.Ejercicios.clienteMultihilo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

public class ServidorMuchosClientes extends Thread {
    int puerto = 7; // puerto ECHO
    ServerSocket serverSocket;
    boolean salir = false;

    public ServidorMuchosClientes() throws IOException {
        serverSocket = new ServerSocket(puerto);
        System.out.println("Servidor arriba");
    }

    public static void main(String[] args) {
        try {
            new ServidorMuchosClientes().start();
        } catch (IOException ex) {
            System.out.println("Error iniciando el servidor");
        }
    }

    @Override
    public void run() {
        do {
            Socket socket;
            try {
                socket = serverSocket.accept();
            } catch (IOException ex) {
                System.out.println("Servidor abajo");
                return;
            }
            if (!salir) new HiloEcho(this, socket).start();
        } while (!salir);
    }

    public void shutdown() {
        salir = true;
        try {
            serverSocket.close();
        } catch (IOException ex) {
            System.out.println("Problemas durante el apagado del servidor");
        }
    }
}