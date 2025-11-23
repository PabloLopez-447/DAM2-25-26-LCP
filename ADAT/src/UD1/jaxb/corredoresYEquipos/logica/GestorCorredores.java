package UD1.jaxb.corredoresYEquipos.logica;

import UD1.jaxb.corredoresYEquipos.clases.corredores.Corredor;
import UD1.jaxb.corredoresYEquipos.persistencia.CorredoresJAXB;

import java.util.List;

public class GestorCorredores {
    String rutaArchivo;

    public GestorCorredores(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public void visualizarCorredores(){
        CorredoresJAXB corredoresJAXB = new CorredoresJAXB();
        List<Corredor> corredors = corredoresJAXB.listarCorredores(rutaArchivo);

        for (Corredor corredor : corredors) {
            System.out.println(corredor);
        }
    }

    public static void main(String[] args) {
        GestorCorredores g = new GestorCorredores("D:\\Datos\\DAM\\DAM2-25-26-LCP\\ADAT\\src\\UD1\\jaxb\\xml\\Corredores.xml");
        g.visualizarCorredores();
    }
}
