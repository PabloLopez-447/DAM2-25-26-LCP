
package aluhbpasteleria26;

import LOGICA.GestionHBPasteleria;

// Pablo López Couso DNI:77550221V

public class ALUHBPASTELERIA26 {

    public static void main(String[] args) {
        GestionHBPasteleria.comprobarConexion();
        // EJERCICIO 1
        //Insertar habilidad
        GestionHBPasteleria.insertarHabilidad("P001", "NUEVA", "Iniciado");
        //Actualizar habilidad
        GestionHBPasteleria.insertarHabilidad("P001", "Azucar", "Intermedio");
        //Pastelero inexistente
        GestionHBPasteleria.insertarHabilidad("Inexistente", "Azucar", "Intermedio");

        // EJERCICIO 2
        //Pastelero inexistente
        GestionHBPasteleria.cambiarDuenoPastelero("Inexistente", "Dolce");
        //Pasteleria inexistente
        GestionHBPasteleria.cambiarDuenoPastelero("Seo", "Inexustente");
        //Pastelero = pastelero actual
        GestionHBPasteleria.cambiarDuenoPastelero("Garcí", "Dolce");
        //Cambio
        GestionHBPasteleria.cambiarDuenoPastelero("Seo", "Dolce");

        //EJERCICIO 3
//        //Producto inexistente
        GestionHBPasteleria.borrarProducto(300);
        //Producto correcto
        GestionHBPasteleria.borrarProducto(2);
        //EJERCICIO 4
        GestionHBPasteleria.imprimirPastelerosVarones();
    }


}
