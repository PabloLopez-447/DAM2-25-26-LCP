package cocina22;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import PERSISTENCIA.CocinaDAO;
import POJOS.Cocinero;
import POJOS.Contactococinero;
import POJOS.Premio;
import org.hibernate.Session;

import java.util.ArrayList;

/**
 *
 * @author usuario
 */
public class CcocinaHB22 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      //prueba de la conexión
        Session sesion = HibernateUtil1.getSessionFactory().openSession();

        System.out.println("conexión realizada con éxito");
        sesion.close();
//
        //1 DATOS Y EXEC DEL METODO:
//        Cocinero cocineroBien = new Cocinero("Pablo", "Lopez", "Couso", 'H', "Manin");
//        Contactococinero contacto = new Contactococinero("pablo@dominio.com", "211222333", "333222211");
//
//        ArrayList<Premio> premios = new ArrayList<>();
//        Premio p1 = new Premio ("CUCHILLO PLATINO", 2020); //Repetido
//        Premio p2 = new Premio ("CUCHILLO PLATINO", 2025); //Nuevo
//        premios.add(p1);
//        premios.add(p2);
//
        CocinaDAO dao = new CocinaDAO();
//        dao.insertarCocineroPremioContacto(cocineroBien, premios, contacto);
//        System.out.println("-----------");

        // 3 BORRAR RECETA MOSTRANDO INFO AUTOR
//        System.out.println("\n----------METODO 3----------");
//        dao.borrarReceta(14);
//        dao.borrarReceta(300);

        // 4 MOSTRAR DUEÑO DADO UN RESTAURANTE
        System.out.println("\n----------METODO 4----------");
        dao.mostrarDueño("Casa Xian");
        dao.mostrarDueño("incorrecto");
    }
    
}
