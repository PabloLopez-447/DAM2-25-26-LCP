package UD2.Sockets.Ejercicios.examen;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.HashMap;

public class GestorConexiones {
    private ServerSocket serverSocket;
    private HashMap<String, Usuario> usuarios;
    private ArrayList<ConexionServidor> listaConexiones;

    public GestorConexiones(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
        usuarios = new HashMap<>();
        listaConexiones = new ArrayList<>();
    }

    public synchronized void nuevaConexionSinAutorizar(ConexionServidor nuevoHilo) {
        listaConexiones.add(nuevoHilo);
    }

    boolean autorizarNuevaConexion(String nombreUsuario) {
        Usuario usuario = usuarios.get(nombreUsuario);
        if (usuario == null) usuarios.put(nombreUsuario, new Usuario(nombreUsuario));
        else {
            if (usuario.estaONLINE()) return false;
            usuario.login();
        }
        return true;
    }

    synchronized void introducirPregunta(String pregunta, String respuesta, String nombreUsuario) {
        Usuario usuario = usuarios.get(nombreUsuario);
        usuario.getPreguntas().add(new Pregunta(pregunta, respuesta));
    }

    boolean responder(Pregunta pregunta, String respuesta) {
        return respuesta.equals(pregunta.respuesta);
    }

    void cortarConexion(String nombreUsuario, ConexionServidor conexion) {
        setUsuarioOFFLINE(nombreUsuario);
        conexion.cerrarCliente();
        listaConexiones.remove(conexion);
    }

    void aumentarPuntuacion(String nombreUsuario) {
        Usuario usuario = usuarios.get(nombreUsuario);
        usuario.aumentarPuntuacion();
    }

    String verPuntuaciones(){
        StringBuilder historico = new StringBuilder();

        for (Usuario u : usuarios.values()) {
            historico.append(u.toString());
        }

        return historico.toString();
    }

    private void setUsuarioOFFLINE(String nombreUsuario) {
        Usuario usuario = usuarios.get(nombreUsuario);
        if (usuario != null) usuario.logout();
    }

    public synchronized HashMap<String, Usuario> getUsuarios() {
        return usuarios;
    }
}
