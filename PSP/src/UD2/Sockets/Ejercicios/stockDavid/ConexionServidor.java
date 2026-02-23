package UD2.Sockets.Ejercicios.stockDavid;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConexionServidor extends Thread {

    private String codCliente;
    private GestorConexiones gestorConexiones;
    private DataInputStream input;
    private DataOutputStream output;

    private boolean salir = false;

    ConexionServidor(GestorConexiones gestorConexiones, Socket socket) {
        this.codCliente = socket.getRemoteSocketAddress().toString();
        this.gestorConexiones = gestorConexiones;
        try {
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            System.out.println("Error al establecer la nueva conexion");
        }
        salir = !gestorConexiones.login(codCliente);
    }

    @Override
    public void run() {
        try {
            output.writeBoolean(!salir);
            while (!salir) {
                String entradaUsuario = input.readUTF();
                switch (entradaUsuario) {
                    case Config.CMD_INFO:
                        output.writeUTF(gestorConexiones.getInfo());
                        break;
                    case Config.CMD_SALIR:
                        output.writeUTF(gestorConexiones.getInfoUsuario(codCliente));
                        salir = true;
                        break;
                    case Config.CMD_APAGAR:
                        boolean sePuedeApagar = gestorConexiones.apagar();
                        output.writeBoolean(sePuedeApagar);
                        if (sePuedeApagar) salir = true;
                        break;
                    default:
                        Pattern pattern = Pattern.compile(Config.CMD_REGEXP, Pattern.CASE_INSENSITIVE);
                        Matcher matcher = pattern.matcher(entradaUsuario);
                        if (matcher.matches()) {
                            String comando = matcher.group(1).toUpperCase();
                            String material = matcher.group(2).toUpperCase();
                            int cantidad;
                            try {
                                cantidad = Integer.parseInt(matcher.group(3));
                            } catch (NumberFormatException x) {
                                output.writeUTF(Config.STR_ERROR_CANTIDAD);
                                break;
                            }
                            switch (comando) {
                                case Config.CMD_GET:
                                    output.writeUTF(gestorConexiones.get(codCliente, material, cantidad));
                                    break;
                                case Config.CMD_PUT:
                                    output.writeUTF(gestorConexiones.put(codCliente, material, cantidad));
                                    break;
                                default:
                                    output.writeUTF(Config.STR_FORMATO_COMANDOS);
                            }
                        } else
                            output.writeUTF(Config.STR_FORMATO_COMANDOS);
                }
            }
        } catch (IOException ex) {
            System.out.printf("Problemas en el servidor");
        }
        gestorConexiones.logout(codCliente);
    }
}