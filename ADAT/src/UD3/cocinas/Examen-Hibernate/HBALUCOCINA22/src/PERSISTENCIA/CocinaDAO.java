package PERSISTENCIA;

import POJOS.Cocinero;
import POJOS.Contactococinero;
import POJOS.Premio;
import POJOS.Receta;
import cocina22.HibernateUtil1;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CocinaDAO {


    public void testConnection() {
        try {
            Session session = HibernateUtil1.getSessionFactory().openSession();
            if (session.isConnected()) {
                System.out.println("Conexion realizada con exito");
                session.close();
            } else {
                System.out.println("Ha ocurrido un error posterior al inicio.");
            }

        } catch (HibernateException e) {
            System.out.println("Error de Conexion.");
            e.printStackTrace();

        }
    }

    public void insertarCocineroPremioContacto(Cocinero c, Collection<Premio> premios, Contactococinero cc) {
        Transaction ts = null;
        try (Session session = HibernateUtil1.getSessionFactory().openSession()) {
            ts = session.beginTransaction();

            Cocinero cExistente = session.get(POJOS.Cocinero.class, c.getCodigo());
            if (cExistente != null) {
                throw new Exception("Xa existe un cocinero co NSS " + c.getCodigo());
            }
            List<Premio> premiosAdd = new ArrayList<>();
            for (Premio p : premios) {
                Premio pExistente = session.get(POJOS.Premio.class, p.getIdPremio());
                if (pExistente == null) {
                    p.setCocinero(c);
                    premiosAdd.add(p);
                }
            }

            c.setContactococinero(cc);
            c.getPremios().addAll(premiosAdd);
            cc.setCocinero(c);
            session.save(cc);
            session.save(c);
            ts.commit();


        } catch (Exception e) {
            if (ts != null) {
                ts.rollback();
            }
            System.out.println("Error al insertar el cocinero.");
            e.printStackTrace();
        }
    }

    public void borrarReceta(int idReceta) {
        Transaction ts = null;
        Session session = null;

        try {
            session = HibernateUtil1.getSessionFactory().openSession();
            ts = session.beginTransaction();

            Receta receta = session.get(Receta.class, idReceta);
            if (receta == null) {
                throw new Exception("Non existe a receta co ID " + idReceta);
            }

            Cocinero autor = receta.getCocinero();
            System.out.println("Autor: " + autor.getNombre());

            session.delete(receta);
            ts.commit();

        } catch (Exception e) {
            if (ts != null) ts.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
    }

    public void mostrarDueño(String nombreRestaurante){
        Transaction ts = null;
        try (Session session = HibernateUtil1.getSessionFactory().openSession()){
            ts = session.beginTransaction();

            String hql = "from Cocinero c join c.restaurantes r where r.nombrerestaurante = :nombreRestaurante";
            Query query = session.createQuery(hql);
            query.setParameter("nombreRestaurante", nombreRestaurante);

            List<Cocinero> cocineros = query.list();

            if (cocineros == null ||cocineros.isEmpty()){
                System.out.println("Non se atopou ningún cocinero para o restaurante " + nombreRestaurante);
                ts.rollback();
                return;
            }

            for (Cocinero c : cocineros) {
                System.out.println("Cocinero: " + c.getNombre() + " " + c.getApellido1() + " " + c.getApellido2());
            }
            ts.commit();

        } catch (Exception e){
            if (ts != null) ts.rollback();
            e.printStackTrace();
        }

    }
}
