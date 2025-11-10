package UD1.Ejercicios.PorraCorregido;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Bote {
    static final int MAX_GOLES = 3;
    static Random r = new Random();

    private HashMap<String, ApuestaResultado> apuestas;

    public Bote() {
        apuestas = new HashMap<>();
        for (int golesLocal = 0; golesLocal < MAX_GOLES; golesLocal++)
            for (int golesVisitante = 0; golesVisitante < MAX_GOLES; golesVisitante++) {
                String resultado = Bote.getFormatoApuesta(golesLocal, golesVisitante);
                apuestas.put(resultado, new ApuestaResultado(resultado));
            }
    }

    public void apuesta(String resultado, int importe) {
        apuestas.get(resultado).apuesta(importe);
    }

    public ApuestaResultado getApuesta(String resultado) {
        return apuestas.get(resultado);
    }

    public static String getFormatoApuesta(int golesLocal, int golesVisitante) {
        return golesLocal + "-" + golesVisitante;
    }

    public static String getFormatoApuesta() {
        int golesLocal = r.nextInt(MAX_GOLES);
        int golesVisitante = r.nextInt(MAX_GOLES);
        return Bote.getFormatoApuesta(golesLocal, golesVisitante);
    }

    public int getRecaudacion() {
        int recaudacion = 0;
        for (Map.Entry<String, ApuestaResultado> entry : apuestas.entrySet())
            recaudacion += entry.getValue().importe;
        return recaudacion;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("");
        for (Map.Entry<String, ApuestaResultado> entry : apuestas.entrySet())
            sb.append(entry.getKey())
                    .append(":")
                    .append(entry.getValue())
                    .append("\n");
        return sb.toString();
    }
}
