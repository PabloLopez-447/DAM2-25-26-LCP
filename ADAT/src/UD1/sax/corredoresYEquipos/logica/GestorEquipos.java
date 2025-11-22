package UD1.sax.corredoresYEquipos.logica;

import UD1.sax.corredoresYEquipos.persistencia.EquiposDOMUpdater;

public class GestorEquipos {

    private EquiposDOMUpdater updater = new EquiposDOMUpdater();

    public void actualizar(String equipos, String actualizaciones, String salida) {
        try {
            updater.actualizarEquipos(equipos, actualizaciones, salida);
            System.out.println("Archivo actualizado generado en: " + salida);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
