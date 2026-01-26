
package LOGICA;

import PERSITENCIA.EmpresaHBDAO;
import POJOS.Empregado;
import POJOS.Proxecto;


public class GestorEmpresaHB {
    public static void comprobarConexion() {
        int resultado = EmpresaHBDAO.conectarHibernate();

        if (resultado == 0) {
            System.out.println("Conexión correcta");
        
        } else {
            System.out.println("Error de conexión ");
            
        }
    }
  public static void visualizarProxecto(int proxecto) {
    try {
        Proxecto p = EmpresaHBDAO.buscarProxecto(proxecto);

        if (p == null) {
            System.out.println("No existe el proyecto con código " + proxecto);
        } else {
            System.out.println("Proyecto encontrado:");
            System.out.println("Número: " + p.getNumProxecto());
            System.out.println("Nombre: " + p.getNomeProxecto());
        }

    } catch (RuntimeException e) {
        System.out.println("Error de acceso a la base de datos: " + e.getMessage());
    }
}

    public static void visualizarDatosEmpregado(String nss){
        try {
            Empregado e = EmpresaHBDAO.buscarEmpregado(nss);

            if (e == null) {
                System.out.println("No existe el proyecto con código " + nss);
            } else {
                System.out.println("Empregado encontrado:");
                System.out.println("NSS: " + e.getNss());
                System.out.println("Nombre: " + e.getNome());
            }

        } catch (Exception e) {
            System.out.println("Error de acceso a la base de datos: " + e.getMessage());
        }
    }

}

