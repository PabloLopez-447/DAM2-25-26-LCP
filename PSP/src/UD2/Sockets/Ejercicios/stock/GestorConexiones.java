package UD2.Sockets.Ejercicios.stock;

import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.HashMap;

public class GestorConexiones {

    private ServerSocket serverSocket;
    private ArrayList<ConexionServidor> conexiones = new ArrayList<>();

    private HashMap<String, Integer> stock = new HashMap<>();
    private HashMap<String, ArrayList<String>> transacciones = new HashMap<>();

    public GestorConexiones(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public synchronized boolean permitirConexion() {
        return conexiones.size() < Config.MAX_CLIENTES;
    }

    public synchronized void nuevaConexion(ConexionServidor c, String clienteId) {
        conexiones.add(c);
        transacciones.put(clienteId, new ArrayList<>());
    }

    public synchronized void cerrarConexion(ConexionServidor c, String clienteId) {
        conexiones.remove(c);
    }

    public synchronized String ejecutarComando(String clienteId, String comando) {
        String[] partes = comando.split(" ");

        switch (partes[0]) {

            case Config.CMD_PUT: {
                String material = partes[1];
                int cantidad = Integer.parseInt(partes[2]);

                stock.put(material, stock.getOrDefault(material, 0) + cantidad);

                String msg = "PUT " + material + " +" + cantidad +
                        " | Stock actual: " + stock.get(material);
                transacciones.get(clienteId).add(msg);
                return msg;
            }

            case Config.CMD_GET: {
                String material = partes[1];
                int cantidad = Integer.parseInt(partes[2]);

                if (!stock.containsKey(material))
                    return "ERROR: material inexistente";

                int actual = stock.get(material);
                if (cantidad > actual)
                    return "ERROR: stock insuficiente (" + actual + " disponibles)";

                stock.put(material, actual - cantidad);

                String msg = "GET " + material + " -" + cantidad +
                        " | Stock actual: " + stock.get(material);
                transacciones.get(clienteId).add(msg);
                return msg;
            }

            case Config.CMD_INFO: {
                StringBuilder sb = new StringBuilder();
                for (String cliente : transacciones.keySet()) {
                    sb.append("Cliente ").append(cliente).append("\n");
                    for (String t : transacciones.get(cliente))
                        sb.append("  ").append(t).append("\n");
                }
                return sb.toString();
            }

            default:
                return "Comando no reconocido";
        }
    }

    public synchronized String infoCliente(String clienteId) {
        StringBuilder sb = new StringBuilder("Tus transacciones:\n");
        for (String t : transacciones.get(clienteId))
            sb.append("  ").append(t).append("\n");
        return sb.toString();
    }
}
