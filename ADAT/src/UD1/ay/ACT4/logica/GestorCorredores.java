package UD1.ay.ACT4.logica;

import java.util.List;

import UD1.ay.ACT4.clases.Corredor;
import UD1.ay.ACT4.clases.Puntuacion;
import UD1.ay.ACT4.persistenciaDOM.TipoValidacion;
import UD1.ay.ACT4.persistenciaSAX.CorredoresSAX;
import UD1.ay.ACT4.persistenciaStAX.persistenciaStAXcursor.CorredoresStAXCursor;
import UD1.ay.ACT4.persistenciaStAX.persistenciaStAXcursor.XMLStAXUtilsCursor;

public class GestorCorredores {
    CorredoresSAX corredoresSAX = new CorredoresSAX();
    CorredoresStAXCursor gestorStaxCursor;

    public void visualizarCorredoresSAX() {
        List<Corredor> corredores = corredoresSAX.cargarTodosCorredores(
                "D:\\plopecous\\ADAT\\src\\UD1\\ACT4\\xml\\ corredores.xml", TipoValidacion.NO_VALIDAR);

        for (Corredor corredor : corredores) {
            System.out.println(corredor.getClass());
            System.out.println("Datos: ");
            System.out.println(corredor.getNombre());
            System.out.println(corredor.getCodigo());
            System.out.println(corredor.getDorsal());
            System.out.println(corredor.getFechaNacimiento());
            System.out.println(corredor.getEquipo());
            if (corredor.getHistorial().isEmpty()) {
                System.out.println("Puntuacionen`t");
            } else {
                for (Puntuacion puntuacion : corredor.getHistorial()) {
                    System.out.println(puntuacion.getPuntos() + " " + puntuacion.getAnio());
                }
            }
        }

    }

    public void visualizarCorredoresStAXCursor() {
        try {
            List<Corredor> corredores = gestorStaxCursor.leeCorredors(XMLStAXUtilsCursor.cargarDocumentoStAX(
                    "D:\\plopecous\\ADAT\\src\\UD1\\ACT4\\xml\\ corredores.xml", TipoValidacion.NO_VALIDAR));

            for (Corredor corredor : corredores) {
                corredor.toString();
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
