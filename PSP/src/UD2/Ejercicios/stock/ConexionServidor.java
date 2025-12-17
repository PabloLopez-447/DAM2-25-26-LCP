package UD2.Ejercicios.stock;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ConexionServidor extends Thread {

    private GestorConexiones gestor;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private String clienteId;

    public ConexionServidor(GestorConexiones gestor, Socket socket) {
        this.gestor = gestor;
        this.socket = socket;
        clienteId = socket.getRemoteSocketAddress().toString();

        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {}
    }

    @Override
    public void run() {
        try {
            if (!gestor.permitirConexion()) {
                out.writeUTF("Servidor lleno");
                socket.close();
                return;
            }

            gestor.nuevaConexion(this, clienteId);
            out.writeUTF("Conectado como " + clienteId);

            boolean salir = false;
            while (!salir) {
                String comando = in.readUTF();

                if (comando.equalsIgnoreCase(Config.CMD_SALIR)) {
                    out.writeUTF(gestor.infoCliente(clienteId));
                    salir = true;
                } else {
                    out.writeUTF(gestor.ejecutarComando(clienteId, comando));
                }
            }

            gestor.cerrarConexion(this, clienteId);
            socket.close();

        } catch (IOException e) {}
    }
}
