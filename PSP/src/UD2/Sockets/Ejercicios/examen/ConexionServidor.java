package UD2.Sockets.Ejercicios.examen;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ConexionServidor extends Thread {
    private GestorConexiones gestorConexiones;
    private Socket socket;
    private DataInputStream input;
    private DataOutputStream output;

    ConexionServidor(GestorConexiones gestorConexiones, Socket socket) {
        this.gestorConexiones = gestorConexiones;
        this.socket = socket;
        try {
            input = new DataInputStream(this.socket.getInputStream());
            output = new DataOutputStream(this.socket.getOutputStream());
        } catch (IOException ex) {
            System.out.println("Error al establecer la nueva conexion");
        }
        gestorConexiones.nuevaConexionSinAutorizar(this);
    }

    @Override
    public void run() {
        String nombreUsuario = "";
        try {
            nombreUsuario = input.readUTF();
            boolean autorizacion = gestorConexiones.autorizarNuevaConexion(nombreUsuario);
            output.writeBoolean(autorizacion);
            if (autorizacion) {
                System.out.println("Conectado " + nombreUsuario);
                boolean salir = false;
                do {
                    String pregunta = input.readUTF();
                    do {
                        String respuesta = input.readUTF();
                        gestorConexiones.introducirPregunta(pregunta, respuesta, nombreUsuario);
                        pregunta = input.readUTF();

                    } while (!pregunta.equals(""));

                    for (String n : gestorConexiones.getUsuarios().keySet()){
                        if (!n.equals(nombreUsuario)){
                            for (Pregunta p : gestorConexiones.getUsuarios().get(n).getPreguntas()){
                                output.writeUTF(p.pregunta);
                                String respuesta = input.readUTF();
                                if (!gestorConexiones.responder(p, respuesta)) {
                                    gestorConexiones.cortarConexion(nombreUsuario, this);
                                    break;
                                }
                                else  {
                                    gestorConexiones.aumentarPuntuacion(nombreUsuario);
                                }
                                output.writeBoolean(false);
                            }
                        }
                    }
                    output.writeUTF("Has ganado " + gestorConexiones.verPuntuaciones());
                    gestorConexiones.cortarConexion(nombreUsuario, this);
                    salir = true;
                }
                while (!salir);
            }
        } catch (IOException ex) {
            System.out.printf("Conexion con %s finalizada por el servidor\n", nombreUsuario);
        }

    }

    void cerrarCliente() {
        try { socket.close(); }
        catch (IOException ex) {
            System.out.println("Problemas cerrando cliente");
        }
    }
}
