
package LOGICA;

import PERSISTENCIA.HBPasteleriaDAO;
import POJOS.Pasteleria;

import java.util.List;
import java.util.Objects;

// Pablo López Couso DNI:77550221V

public class GestionHBPasteleria {

    public static void comprobarConexion() {
        int resultado = HBPasteleriaDAO.conectarHibernateDAO();

        if (resultado == 0) {
            System.out.println("Conexi�n correcta");

        } else {
            System.out.println("Error de conexi�n ");

        }
    }

    public static void insertarHabilidad(String codPastelero, String tecnica, String nivel){

        Boolean actualizado = HBPasteleriaDAO.insertarHabilidad(codPastelero, tecnica, nivel);

        if (actualizado == null){
            System.out.println("El pastelero no existe");
            return;
        }

        if (actualizado) {
            System.out.println("Se actualizo la habilidad");
        } else {
            System.out.println("Se inserto la habilidad");
        }
    }

    public static void cambiarDuenoPastelero(String alias, String nombrePasteleria){
        String duenoAct = HBPasteleriaDAO.mostrarDuenoPorNombrePasteleria(nombrePasteleria);

        if (!Objects.equals(duenoAct, "")) {
            if (duenoAct == null) {
                System.out.println("No se encuentra el dueño de la pasteleria");
            } else {
                System.out.println("Dueño actual:" + duenoAct);
            }
        }

        System.out.println("Se va a cambiar el dueño");
        switch (HBPasteleriaDAO.cambiarDuenoPasteleria(alias, nombrePasteleria)){
            case -1 -> {
                System.out.println("No existe pastelero con alias: " + alias);
            }

            case -2 -> {
                System.out.println("No existe pasteleria con nombre: " + nombrePasteleria);
            }

            case -3 -> {
                System.out.println("El pastelero ya es el dueño de esa pasteleria");
            }

            case 0 ->{
                System.out.println("Dueño cambiado");
            }
        }

    }

    public static void borrarProducto(int codProducto){
        if (HBPasteleriaDAO.borrarProducto(codProducto)){
            System.out.println("Producto " + codProducto + " borrado");
        } else {
            System.out.println("El producto no existe");
        }
    }

    public static void imprimirPastelerosVarones(){
        List<Object []> campos = HBPasteleriaDAO.mostrarPasteleroVarones();
        if (campos == null || campos.isEmpty()) {
            System.out.println("No hay pasteleros");
            return;
        }
        System.out.println("\n--- INFORME DE PASTELEROS (HOMBRES) ---");

        String formato = "%-6s | %-27s | %-10s | %-25s | %-10s%n";
        System.out.printf(formato, "COD", "NOMBRE COMPLETO", "LICENCIA", "EMAIL", "TECNICAS");

        for (Object[] campo : campos) {
            System.out.printf(formato, campo[0], campo[1], campo[2], campo[3], campo[4]);
        }
    }

}