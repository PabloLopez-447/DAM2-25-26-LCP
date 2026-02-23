package UD2.Sockets.Ejercicios.stockDavid;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;

public class GestorConexiones {
    private static final int MAX_CONEXIONES=3;
    private static int numConexiones;
        
    private ServerSocket serverSocket;
    private HashMap<String,Integer> materiales;
    private HashMap<String,StringBuilder> usuarios;
    
    public GestorConexiones(ServerSocket serverSocket) {
        this.serverSocket=serverSocket;
        materiales=new HashMap<>();
        usuarios=new HashMap<>();
        numConexiones=0;
    }
    
    public boolean apagar() {
        if(numConexiones>1) return false;
        try { serverSocket.close(); }
        catch (IOException ex) {System.out.println("Servidor abajo");}
        return true;
    }
    
    private synchronized void insertaLog(String usuario,String linea) {
        usuarios.get(usuario).append(linea).append("\n");
    }
    
    public synchronized boolean login(String usuario) { 
        String strLog="Login";
        boolean loginPermitido=numConexiones++<MAX_CONEXIONES;
        if(!loginPermitido) strLog+=" error."+Config.STR_CONEXIONES_ACTIVAS;
        usuarios.put(usuario, new StringBuilder(strLog+"\n"));
        return loginPermitido;
    }
    public synchronized void logout(String usuario) {
        insertaLog(usuario,"Logout");
        numConexiones--; 
    }
    public boolean existeMaterial(String material) { return materiales.containsKey(material); }
    
    public String get(String usuario,String material,int cantidad) { 
        if(!existeMaterial(material)) return Config.STR_MATERIAL_DESCONOCIDO;
        if(materiales.get(material)<cantidad) return Config.STR_SIN_STOCK;
        return put(usuario,material,-cantidad);
    }
    
    public synchronized String put(String usuario, String material, int cantidad) {
		// probar oldCantidad=replace(...) if null then 0 y luego put(material,oldCantidad+cantidad)
        if(!existeMaterial(material)) materiales.put(material, 0);
        materiales.put(material,materiales.get(material)+cantidad);
        insertaLog(usuario,String.format("%s %s %d",(cantidad<0)?"GET":"PUT",material,cantidad));
        return getInfoMaterial(material);
    }
    
    public String getInfo() {
        StringBuilder sb=new StringBuilder();
        for (HashMap.Entry<String, StringBuilder> usuario : usuarios.entrySet())
            sb.append(usuario.getKey() +":\n"+usuario.getValue().toString());
        return sb.toString();
    }
    
    private String getInfoMaterial(String material) {
        return String.format("%s %d",material,materiales.get(material));
    }
    
    public String getInfoUsuario(String usuario) {
        return String.format("%s %s",usuario,usuarios.get(usuario));    
    }
    
    
    
    

    
    
}
