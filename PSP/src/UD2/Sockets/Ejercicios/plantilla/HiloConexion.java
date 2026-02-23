package UD2.Sockets.Ejercicios.plantilla;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class HiloConexion extends Thread {

    private Gestor gestor;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    public HiloConexion(Gestor gestor, Socket socket) {
        this.gestor = gestor;
        this.socket = socket;

        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            System.out.println("Error creando streams");
        }

        gestor.nuevaConexion(this);
    }

    @Override
    public void run() {
        boolean salir = false;

        try {
            while (!salir) {
                String comando = in.readUTF();

                switch (comando) {

                    case Config.CMD_SALIR:
                        gestor.cerrarConexion(this);
                        salir = true;
                        break;

                    case Config.CMD_APAGAR:
                        gestor.shutdown();
                        salir = true;
                        break;

                    default:
                        String respuesta = gestor.procesarComando(comando, this);
                        out.writeUTF(respuesta);
                }
            }

        } catch (IOException e) {
            System.out.println("Cliente desconectado");
        }
    }

    public void cerrar() {
        try { socket.close(); }
        catch (IOException e) { System.out.println("Error cerrando socket"); }
    }
}