package gestionTrabajadores;

import java.net.InetSocketAddress;
import java.net.Proxy;
import okhttp3.OkHttpClient;

public class SupabaseConfig {
    // Cambia id_proyecto y supabase_annon_key por las credenciales de tu proyecto en Supabase.

    public static final String SUPABASE_URL = "https://<id_proyecto>.supabase.co";
    public static final String SUPABASE_KEY = "<supabase_annon_key>" 
	 // En el terminal de Windows (set o setx)
	 // set SUPABASE_KEY=mi_clave_aqui
	 // set id_proyecto=id_proyecto
    // public static final String SUPABASE_URL = System.getenv("SUPABASE_URL");
    // public static final String SUPABASE_KEY = System.getenv("SUPABASE_KEY");
    

    // --- CONFIGURACIÓN DEL PROXY ---
    private static final boolean USE_PROXY = true; // cambia a false para desactivar
    private static final String PROXY_HOST = "192.168.0.11";
    private static final int PROXY_PORT = 3128;

    /**
     * Devuelve un OkHttpClient con o sin proxy, según configuración.
     */
    public static OkHttpClient getHttpClient() {
        OkHttpClient.Builder client = new OkHttpClient.Builder();

        if (USE_PROXY) {
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(PROXY_HOST, PROXY_PORT));
            client.proxy(proxy);
            System.out.println("🔌 Usando proxy: " + PROXY_HOST + ":" + PROXY_PORT);
        } else {
            System.out.println("🌐 Sin proxy (conexión directa).");
        }

        return client.build();
    }
}
