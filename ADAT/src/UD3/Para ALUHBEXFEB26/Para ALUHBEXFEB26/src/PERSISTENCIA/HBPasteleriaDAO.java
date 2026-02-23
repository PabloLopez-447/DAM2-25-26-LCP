package PERSISTENCIA;


import POJOS.Pasteleria;
import POJOS.Pastelero;
import POJOS.Producto;
import UTILIDADES.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Map;

// Pablo López Couso DNI:77550221V

public class HBPasteleriaDAO {

    public static int conectarHibernateDAO() {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        if (sesion != null) {

            sesion.close();
            return 0;
        } else {
            return -1;
        }
    }

    public static Boolean insertarHabilidad(String codPastelero, String tecnica, String nivel){

        Transaction tx = null;
        boolean actualizado = false;

        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {

            tx = sesion.beginTransaction();

            Pastelero p = sesion.get(Pastelero.class, codPastelero);

            if (p == null) {   //Si no existe devolvemos null
                return null;
            }

            Map<String, String> tecnicas = p.getTecnicas();

            // Se existe -> actualizar
            if (tecnicas.containsKey(tecnica)) {
                tecnicas.put(tecnica, nivel);
                actualizado = true;
            } else {
                // Se non existe ->inserir
                tecnicas.put(tecnica, nivel);
            }

            tx.commit();
            return actualizado;

        } catch (Exception ex) {
            if (tx != null) {
                tx.rollback();
            }
            throw new RuntimeException("Erro ao gardar o tel?fono do empregado", ex);
        }
    }

    public static String mostrarDuenoPorNombrePasteleria(String nombrePasteleria){
        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {

            String hql = "FROM Pasteleria p WHERE p.nome = :nome";

            Pasteleria pasteleria = sesion.createQuery(hql, Pasteleria.class).setParameter("nome", nombrePasteleria).uniqueResult();

            if (pasteleria == null){
                return ""; // si la pasteleria no existe devuelve comillas vacias
            }

            return pasteleria.getPastelero().getAlias();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar a pasteleria " + nombrePasteleria);
        }
    }

    public static int cambiarDuenoPasteleria(String alias, String nombrePasteleria){

        Transaction tx = null;
        try(Session sesion = HibernateUtil.getSessionFactory().openSession()){
            tx = sesion.beginTransaction();

            //Comprobar que existe el pastelero
            Pastelero p = sesion.createQuery("FROM Pastelero p where p.alias=:alias", Pastelero.class).setParameter("alias", alias).uniqueResult();

            if (p == null){
                tx.rollback();
                return -1; // el pastelero no existe
            }

            //Comprobar que existe la pasteleria
            Pasteleria pa = sesion.createQuery("FROM Pasteleria pa where pa.nome=:nombre", Pasteleria.class).setParameter("nombre", nombrePasteleria).uniqueResult();

            if (pa == null){
                tx.rollback();
                return -2; // la pasteleria no existe
            }

            // Comprobar que el pastelero no sea el mismo
            if (p.getAlias().equalsIgnoreCase(pa.getPastelero().getAlias())){
                tx.rollback();
                return -3; // es el mismo pastelero
            }

            // Actualizar los datos
            pa.setPastelero(p);
            p.getPastelerias().add(pa);

            tx.commit();
        }
        return 0;
    }

    public static boolean borrarProducto(int codProducto){

        Transaction tx = null;
        try(Session sesion = HibernateUtil.getSessionFactory().openSession()){
            tx = sesion.beginTransaction();

            Producto p = sesion.get(Producto.class, codProducto);
            // comprobar si el producto existe
            if (p == null){
                tx.rollback();
                return false;
            }

            //quitar el producto de todas las patelerias donde este
            for (Pasteleria pa : p.getPastelerias()){
                pa.getProductos().remove(p);
            }
            //quitar el producto de la base de datos
            sesion.remove(p);
            tx.commit();
        }
        return true;
    }

    public static List<Object[]> mostrarPasteleroVarones(){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            String hql = """
            select p.codigo, concat(p.nome, ' ', p.apelidos), c.numlicencia, coalesce(co.email, ''), size(p.tecnicas) 
            from Pastelero p
            join p.contacto co
            join p.certificacion c
            where p.sexo = 'H'
        """;

            return session.createQuery(hql, Object[].class).list();
        }
    }

}