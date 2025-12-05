package UD2.Ejercicios.clienteMultihilo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketAddress;

class HiloEcho extends Thread {
    String finHilo = "fin", finServidor = "shutdown";
    ServidorMuchosClientes servidor;
    Socket socket;

    public HiloEcho(ServidorMuchosClientes servidor, Socket socket) {
        this.servidor = servidor;
        this.socket = socket;
    }

    @Override
    public void run() {
        SocketAddress clientAddress = socket.getRemoteSocketAddress();
        System.out.println("Ha conectado " + clientAddress);
        DataInputStream in;
        DataOutputStream out;
        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            System.out.println("Problemas creando la conexión");
            return;
        }
        String str;
        boolean salir = false;
        while (!salir) {
            try {
                str = in.readUTF();
                if (str.equalsIgnoreCase(finServidor)) servidor.shutdown();
                out.writeUTF(str);
            } catch (IOException ex) {
                System.out.println("Error en la transmisión");
                break;
            }
            if (str.equalsIgnoreCase(finHilo)) salir = true;
            else {
                System.out.println("Servidor retransmite: " + str);
                System.out.println("***************************");
            }
        }
        try {
            socket.close();
        } catch (IOException ex) {
            System.out.println("Error cerrando conexión");
        }
    }
}