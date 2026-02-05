package PERSITENCIA;

import POJOS.*;
import Utilidades.HibernateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.PersistenceException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class EmpresaHBDAO {

    public static int conectarHibernateDAO() {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        if (sesion != null) {

            sesion.close();
            return 0;
        } else {
            return -1;
        }
    }

    public static Proxecto buscarProxectoDAO(int proxecto) {
        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {
            return sesion.get(Proxecto.class, proxecto);
        } catch (HibernateException e) {
            // Lanzamos un RuntimeException 
            throw new RuntimeException("No se pudo abrir la sesi�n de Hibernate", e);
        }
    }

    public static void guardarEmpregadoDAO(Empregado empregado) {

        Transaction tx = null;
        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {
            tx = sesion.beginTransaction();
            // Comprobar se xa existe
            Empregado existente = sesion.get(Empregado.class, empregado.getNss());
            if (existente != null) {
                throw new RuntimeException("Xa existe un empregado co NSS " + empregado.getNss());
            }
            // Gardar
            sesion.save(empregado);
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new RuntimeException("Erro de Hibernate ao crear empregado", e);
        }
    }

    public static Empregado buscarEmpregadoDAO(String nss) {
        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {
            return sesion.get(Empregado.class, nss);
        } catch (HibernateException e) {
            throw new RuntimeException("Error al buscar empleado en BD", e);
        }
    }

    public static Departamento buscarDepartamentoDAO(int numDepartamento) {
        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {
            return sesion.get(Departamento.class, numDepartamento);
        } catch (HibernateException e) {
            throw new RuntimeException("Erro ao buscar departamento na BD", e);
        }
    }

    public static void guardarFuncionDeptDAO(int numDepartamento, String funcion) {

        Transaction tx = null;
        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {
            tx = sesion.beginTransaction();
            // Buscar o departamento
            Departamento d = sesion.get(Departamento.class, numDepartamento);

            if (d == null) {
                throw new RuntimeException("O departamento " + numDepartamento + " non existe.");
            }
            // Se a funci�n xa existe ? erro espec�fico
            if (d.getFunciones().contains(funcion)) {
                throw new RuntimeException("A funci�n '" + funcion + "' xa est� asignada ao departamento " + numDepartamento + ".");
            }
            // Engadir a funci�n
            d.getFunciones().add(funcion);
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new RuntimeException("Erro de Hibernate ao gardar a funci�n no departamento", e);

        }

    }

    public static void eliminarFuncionDeptDAO(int numDepartamento, String funcion) {

        Transaction tx = null;

        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {
            tx = sesion.beginTransaction();
            Departamento d = sesion.get(Departamento.class, numDepartamento);
            if (d == null) {
                throw new RuntimeException(
                        "O departamento " + numDepartamento + " non existe."
                );
            }
            // La funci�n no pertenece al departamento
            if (!d.getFunciones().contains(funcion)) {
                throw new RuntimeException(
                        "A funci�n '" + funcion + "' non pertence ao departamento " + numDepartamento + "."
                );
            }

            // Eliminamos la funci�n
            d.getFunciones().remove(funcion);

            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new RuntimeException(
                    "Erro de Hibernate ao eliminar a funci�n do departamento",
                    e
            );
        }
    }

    public static boolean engadirFaseProxectoDAO(int numProxecto, ProxectoFase fase) {
        boolean actualizado = false;
        Transaction tx = null;
        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {
            tx = sesion.beginTransaction();
            Proxecto p = sesion.get(Proxecto.class, numProxecto);
            if (p == null) {
                throw new RuntimeException("O proxecto " + numProxecto + " non existe.");
            }

            // ver si est�  //sobrescrito en metodo equal y hascode
            if (p.getFases().contains(fase)) {
                for (ProxectoFase f : p.getFases()) {
                    if (f.getNomeFase().equals(fase.getNomeFase())) {
                        f.setEstado(fase.getEstado());
                        actualizado = true;
                        break; // salir del bucle, ya encontramos la fase
                    }
                }

            }
            if (!actualizado) {
                p.getFases().add(fase);
            }
            tx.commit();
            return actualizado;

        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new RuntimeException("Erro de Hibernate ao gardar a fase do proxecto", e);
        }
    }

    public static Boolean engadirTelefonoDAO(String nss, String numero, String tipo) {

        Transaction tx = null;
        boolean actualizado = false;

        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {

            tx = sesion.beginTransaction();

            Empregado e = sesion.get(Empregado.class, nss);

            if (e == null) {   //Si no existe devolvemos null
                return null;
            }

            Map<String, String> telefonos = e.getTelefonos();

            // Se existe -> actualizar
            if (telefonos.containsKey(numero)) {
                telefonos.put(numero, tipo);
                actualizado = true;
            } else {
                // Se non existe ->inserir
                telefonos.put(numero, tipo);
            }

            tx.commit();
            return actualizado;

        } catch (Exception ex) {
            if (tx != null) {
                tx.rollback();
            }
            throw new RuntimeException("Erro ao gardar o tel�fono do empregado", ex);
        }
    }

    public static Boolean borrarTelefonoDAO(String nss, String numero) {

        Transaction tx = null;
        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {
            tx = sesion.beginTransaction();
            Empregado e = sesion.get(Empregado.class, nss);
            // Se o empregado non existe -> devolvemos null
            if (e == null) {
                return null;
            }
            Map<String, String> telefonos = e.getTelefonos();
            // Se o tel�fono non existe -> devolvemos false
            if (!telefonos.containsKey(numero)) {
                return false;
            }
            // Se existe -> borr�molo
            telefonos.remove(numero);
            tx.commit();
            return true; // borrado correcto

        } catch (Exception ex) {
            if (tx != null) {
                tx.rollback();
            }
            throw new RuntimeException("Erro ao borrar o tel�fono do empregado", ex);
        }
    }

    public static Boolean engadirFamiliarDAO(String nssEmpregado, Familiar familiar) {
        Transaction tx = null;

        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {
            tx = sesion.beginTransaction();

            // 1. Buscar empregado
            Empregado e = sesion.get(Empregado.class, nssEmpregado);
            if (e == null) {
                return null; // empregado non existe
            }

            // 2. Comprobar se xa existe un familiar co mesmo NSS
            for (Familiar f : e.getFamiliares()) {
                if (f.getNss().equals(familiar.getNss())) {
                    return false; // familiar duplicado
                }
            }

            // 3. Engadir familiar -> Hibernate xera o �ndice automaticamente
            e.getFamiliares().add(familiar);

            tx.commit();
            return true; // engadido correctamente

        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new RuntimeException("Erro inesperado ao engadir o familiar", e);
        }
    }

    /*
    Puntos clave:  HQL trabaja con atributos Java, no columnas SQL
     enderezo.localidade -> componente embebido 
      Devuelve List<Empregado>
    Siempre que haces un FROM en HQL debes usar un alias si vas a:
     Acceder a atributos y  Usar WHERE, SELECT, ORDER BY, etc.
     */
    public static List<Empregado> obterEmpregadosPorLocalidadeDAO(String localidade) {

        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {
            /*
        La conslta devuelve una lista de objetos de una clase asociada: Empregado
             */
            String hql = """
                        FROM Empregado e
                        WHERE e.enderezo.localidade = :localidade
                    """;

            return sesion.createQuery(hql, Empregado.class)
                    .setParameter("localidade", localidade)
                    .getResultList();   // query.list() en HB5 sigue pero est� en desuso (deprecated conceptualmente),  en HB6 lo iliminaron 
            /*
        por parte ser�a 
        Query<Empregado> query = sesion.createQuery(hql, Empregado.class);
        query.setParameter("localidade", localidade);
         return query.getResultList();
             */

        } catch (HibernateException e) {
            throw new RuntimeException("Erro de Hibernate ao consultar empregados por localidade", e);
        }
    }

    public static Departamento buscarDepartamentoPorNombreDAO(String nomeDepartamento) {

        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {

            String hql = "FROM Departamento d WHERE d.nomeDepartamento = :nome";

            return sesion.createQuery(hql, Departamento.class)
                    .setParameter("nome", nomeDepartamento)
                    .uniqueResult();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar o departamento", e);
        }
    }

    /*
 C�digos de retorno:
  0  ? Operaci�n correcta
 -1  ? El empleado ya existe
 -2  ? El departamento no existe
 -9  ? Error inesperado (Hibernate)
     */
 /*
 Comentario:
 En asociaciones bidireccionales de Hibernate, **es necesario actualizar ambos lados**:
 1. `empregado.setDepartamento(departamento)` asegura que el empleado conoce su departamento.
 2. `departamento.getEmpregados().add(empregado)` asegura que el departamento conoce a su empleado.
 
 Si solo actualizamos un lado, la relaci�n en memoria queda inconsistente y Hibernate puede:
   - No persistir correctamente la relaci�n en la base de datos.
   - Dar comportamientos inesperados al navegar por la colecci�n del departamento.
 
 Por eso, para mantener la **coherencia entre objetos Java y la base de datos**, siempre se deben sincronizar ambos lados de la asociaci�n.
     */
    public static int crearEmpregadoConDepartamentoDAO(
            Empregado empregado, String nomeDepartamento) {
        Transaction tx = null;

        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {

            tx = sesion.beginTransaction();

            // 1. Comprobar si existe el empleado
            if (sesion.get(Empregado.class, empregado.getNss()) != null) {
                return -1; // empleado ya existe
            }
//            String hql = "FROM Departamento d WHERE d.nomeDepartamento = :nome";
//            Departamento departamento=sesion.createQuery(hql, Departamento.class)
//                    .setParameter("nome", nomeDepartamento)
//                    .uniqueResult();
//            // 2. Buscar departamento
            //Es transistorio , no est� asociado con la sessin actual si lo traigo en una funcion
            Departamento departamento = buscarDepartamentoPorNombreDAO(nomeDepartamento);

            if (departamento == null) {
                return -2; // departamento no existe
            }
            //Existe en la base de datos y hay que traerlo a a memoria para la cache de persistencia
            departamento = sesion.get(Departamento.class, departamento.getNumDepartamento());
            // 3. Asociaci�n bidireccional; importante en lso dos sentidos
            empregado.setDepartamento(departamento);          // Lado "muchos" ? establecemos el departamento del empleado
            departamento.getEmpregados().add(empregado);     // Lado "uno" ? a�adimos el empleado a la colecci�n del departamento

            // 4. Persistencia
            /*
           empregado es transitaorio y por eso lo tenemos que persisteir
           Departameto lo hemos obtenido con get y ya est� en el contexto de persistencia
           Si hacemos session.persist(departamento):
            Hibernate intenta insertarlo como nuevo registro, y si el departamento ya tiene un ID, puede dar error de clave primaria duplicada.
            Solo usar�amos persist() para nuevos objetos que queremos insertar.
             */

            sesion.save(empregado);  //equivalente a Save (solo Hibernate). Persist es de JPA

            tx.commit();

            return 0; // OK

        } catch (HibernateException e) {
            System.out.println("ee " + e.getMessage());
            if (tx != null) {
                tx.rollback();
            }
            return -9; // error inesperado
        }
    }
    
    /*
 Para cambiar una relaci�n Many-to-One bidireccional es obligatorio:
 - Eliminar el empleado del departamento antiguo
 - A�adirlo al nuevo departamento
 - Actualizar el atributo departamento del empleado

 Hibernate NO mantiene la coherencia de ambos lados autom�ticamente.
*/

    public static int cambiarDepartamentoEmpregadoDAO(
            String nssEmpregado, int numNovoDepartamento) {

        Transaction tx = null;

        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {

            tx = sesion.beginTransaction();

            // 1. Buscar empleado
            Empregado empregado = sesion.get(Empregado.class, nssEmpregado);
            if (empregado == null) {
                return -1; // empleado no existe
            }

            // 2. Buscar nuevo departamento
            Departamento novoDepartamento =
                    sesion.get(Departamento.class, numNovoDepartamento);

            if (novoDepartamento == null) {
                return -2; // departamento no existe
            }

            // 3. Comprobar si ya pertenece a ese departamento
            Departamento deptActual = empregado.getDepartamento();
            if (deptActual != null && deptActual.equals(novoDepartamento)) {
                return -3; // ya est� en ese departamento
            }

            // 4. Quitar del departamento actual (si lo tiene)
            if (deptActual != null) {
                deptActual.getEmpregados().remove(empregado);
            }

            // 5. Asociar al nuevo departamento (BIDIRECCIONAL)
            empregado.setDepartamento(novoDepartamento);        // lado MANY
            novoDepartamento.getEmpregados().add(empregado);   // lado ONE

            // 6. Commit (empregado es persistente)
            tx.commit();

            return 0; // OK

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            return -9; // error inesperado
        }
    }

    public static int cambiarDepartamentoEmpregadoHQL(
            String nssEmpregado, int numDepartamento) {

        Transaction tx = null;

        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {

            tx = sesion.beginTransaction();

            String hql = """
                        UPDATE Empregado e
                        SET e.departamento.numDepartamento = :numDept
                        WHERE e.nss = :nss
                    """;

            int filas = sesion.createQuery(hql)
                    .setParameter("numDept", numDepartamento)
                    .setParameter("nss", nssEmpregado)
                    .executeUpdate();

            tx.commit();

            if (filas == 0) {
                return -1; // empleado o departamento no existe
            }

            return 0; // OK

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            return -9;
        }
    }

    /*
Al pasar la Session como argumento, evitas el coste de abrir y cerrar conexiones constantemente.

*/
    public static Habilidade buscarHabilidadePorNome(String nome, Session session) {

        return session.createQuery("FROM Habilidade WHERE nome = :n", Habilidade.class)
                .setParameter("n", nome)
                .uniqueResult();
    }


    /**
     * Inserta solo las habilidades que no existen.
     *
     * @param listaEntrada
     * @return Lista de objetos Habilidade que han sido insertados con éxito.
     */
    public static List<Habilidade> insertarHabilidadesDAO(List<Habilidade> listaEntrada) throws HibernateException {
        List<Habilidade> insertadas = new ArrayList<>();
        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            for (Habilidade hab : listaEntrada) {
                // Comprobamos si ya existe en la BD usando la sesión actual
                Habilidade existente = buscarHabilidadePorNome(hab.getNome(), session);

                if (existente == null) {
                    session.save(hab); // SQL Server genera el ID aquí
                    insertadas.add(hab);
                }
            }

            tx.commit();
            return insertadas;
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        }

    }

    public static int borrarHabilidadeDAO(int idHab) throws HibernateException {
        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            Habilidade h = session.get(Habilidade.class, idHab);
            if (h == null) {
                return -1; // non existe
            }
// 2. Quitamos la habilidad de cada empleado (esto limpia la tabla intermedia)
            for (Empregado emp : h.getEmpregados()) {
                emp.getHabilidades().remove(h);
            }
            session.delete(h); // borra tamén EMPREGADO_HABILIDADE
            tx.commit();
            return 0;

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

}
