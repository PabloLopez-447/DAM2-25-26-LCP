package UD2.Sockets.Ejercicios.stock2;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.HashMap;

public class Gestor {

    private ServerSocket serverSocket;
    private ArrayList<HiloConexion> conexiones;
    private HashMap<String, Integer> stock;
    private String historicoComandos = "Histórico de comandos: ";

    public Gestor(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
        conexiones = new ArrayList<>();
        stock = new HashMap<>();
    }

    public synchronized void nuevaConexion(HiloConexion h) {
        conexiones.add(h);
    }

    public synchronized void cerrarConexion(HiloConexion h) {
        conexiones.remove(h);
        h.cerrar();
    }

    public synchronized String procesarComando(String comando, HiloConexion h) {
        String[] partes = comando.split(" ");
        switch (partes[0]) {
            case Config.PUT ->{
                if (partes.length < 3) {
                    return "Comando put incorrecto. Uso: put <producto> <cantidad>";
                }
                String producto = partes[1];
                int cantidadAdd = Integer.parseInt(partes[2]);
                return put(producto, cantidadAdd, h);
            }
            case Config.GET ->{
                if (partes.length < 3) {
                    return "Comando get incorrecto. Uso: get <producto> <cantidad>";
                }
                String producto = partes[1];
                int cantidadRet = Integer.parseInt(partes[2]);
                return get(producto, cantidadRet, h);
            }
            case Config.INFO -> {
                return info();
            }
            default -> {
                return "Comando no reconocido";
            }
        }
    }

    public synchronized String put(String producto, int cantidad, HiloConexion h) {
        int cantidadActual = stock.getOrDefault(producto, 0);
        stock.put(producto, cantidadActual + cantidad);
        historicoComandos += "\n" + h.getIdHilo() + " añadió " + cantidad + " unidades de " + producto;
        return "Se añadieron " + cantidad + " unidades de " + producto;
    }

    public synchronized String get(String producto, int cantidad, HiloConexion h) {
        if (!stock.containsKey(producto)) {
            return "No existe " + producto;
        }
        int cantidadActual = stock.getOrDefault(producto, 0);
        if (cantidadActual < cantidad) {
            return "No hay suficiente stock de " + producto;
        }
        stock.put(producto, cantidadActual - cantidad);
        historicoComandos += "\n" + h.getIdHilo() + " retiró " + cantidad + " unidades de " + producto;
        return "Se retiraron " + cantidad + " unidades de " + producto;
    }

    public synchronized String info() {
        return historicoComandos;
    }

    public synchronized void shutdown() {
        try { serverSocket.close(); }
        catch (IOException e) {}

        for (HiloConexion h : conexiones) {
            h.cerrar();
        }
    }
}
