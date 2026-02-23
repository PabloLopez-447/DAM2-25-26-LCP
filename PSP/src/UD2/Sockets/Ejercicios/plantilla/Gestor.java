package UD2.Sockets.Ejercicios.plantilla;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class Gestor {

    private ServerSocket serverSocket;
    private ArrayList<HiloConexion> conexiones;

    public Gestor(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
        conexiones = new ArrayList<>();
    }

    public synchronized void nuevaConexion(HiloConexion h) {
        conexiones.add(h);
    }

    public synchronized void cerrarConexion(HiloConexion h) {
        conexiones.remove(h);
        h.cerrar();
    }

    public synchronized String procesarComando(String comando, HiloConexion h) {
        return "Eco: " + comando;
    }

    public synchronized void shutdown() {
        try { serverSocket.close(); }
        catch (IOException e) {}

        for (HiloConexion h : conexiones) {
            h.cerrar();
        }
    }
}
