package PERSITENCIA;

import DTO.DepartamentoMaxEmpregadosDTO;
import DTO.DirectorDepartamentoDTO;
import DTO.EmpregadoListadoDTO;
import DTO.ListadoProxectosDTO;
import POJOS.Empregado;
import POJOS.Proxecto;
import Utilidades.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class EmpresaHBConsultasDAO {

    //Ejercicio 1:  
    public static List<Proxecto> listarProxectosDAO() {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            String hql = """
            from Proxecto p
            where p.lugar in ('Vigo', 'Santiago')
            order by p.numProxecto
        """;

            return session.createQuery(hql, Proxecto.class).list();
        }
    }

    /*
    VERSIÓN 2 ? Persistencia devolviendo List<Object[]>
    DAO (SELECT parcial)
     */
    public static List<Object[]> listarProxectosArrayDAO() {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            String hql = """
            select p.numProxecto, p.nomeProxecto, p.lugar
            from Proxecto p
            where p.lugar in ('Vigo', 'Santiago')
            order by p.numProxecto
        """;

            return session.createQuery(hql, Object[].class).list();
        }
    }

    /*  VERSIÓN 3 ? Persistencia devolviendo un DTO 
    DTO: ListadoProxectosDTO
    DAO (SELECT parcial) y  con constructor expression
     */
    public static List<ListadoProxectosDTO> listarProxectosDTODAO() {

        try (Session sesion = HibernateUtil.getSessionFactory().openSession()) {

            String hql = """
            select new DTO.ListadoProxectosDTO(
                p.numProxecto,
                p.nomeProxecto,
                p.lugar
            )
            from Proxecto p
            where p.lugar in ('Vigo', 'Santiago')
            order by p.numProxecto
        """;

            return sesion.createQuery(hql, ListadoProxectosDTO.class).list();
        }
    }

    //EJERCICIO 2
    //vERSION  NO FUNCIONA ASI por que los telefonos se cargarían fuera de la sesion y daría error
    public static List<Empregado> listarEmpregadosOrdenadosDAO() {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            /*
        esta hql no funcionaría por que los telefonos nos los carga y al cerrrar la sesion al hacer 
        e.getTelefonos().size() en la logica, Pero telefonos es un Map lazy, así que Hibernate intenta cargarlo?
         pero ya no hay sesión ? error.  */
//         String hql = """            
//            from Empregado e           
//            order by e.apelido1, e.apelido2, e.nome
//        """;
            //Utilizar join fetch esto ace que crge los telefonos antes de cerrar la sesion
            String hql = """
            select distinct e
            from Empregado e
            left join fetch e.telefonos
            left join fetch e.departamento
            order by e.apelido1, e.apelido2, e.nome
        """;

            return session.createQuery(hql, Empregado.class).list();
        }
    }

    public static List<EmpregadoListadoDTO> listarEmpregadosOrdenadosDTODAO() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = """
            select new DTO.EmpregadoListadoDTO(
                e.nss, concat(e.apelido1, ' ', coalesce(e.apelido2, ''), ', ', e.nome),
                e.departamento.nomeDepartamento, 
                case 
                    when type(e) = POJOS.Empregadofixo then 'fijo' 
                    else 'temporal' 
                end,
                size(e.telefonos)
            )
            from Empregado e inner join e.departamento d
            order by e.apelido1 asc, e.apelido2 asc, e.nome asc
            """;

            return session.createQuery(hql, EmpregadoListadoDTO.class).list();
        } catch (Exception e) {
            return null;
        }
    }

    public static List<Object[]> listarEmpregadosOrdenadosObjectDAO() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Eliminamos el "new DTO.EmpregadoListadoDTO"
            String hql = """
            select 
                e.nss, concat(e.apelido1, ' ', coalesce(e.apelido2, ''), ', ', e.nome),
                e.departamento.nomeDepartamento, 
                case 
                    when type(e) = POJOS.Empregadofixo then 'fijo' 
                    else 'temporal' 
                end,
                size(e.telefonos)
            from Empregado e  inner join e.departamento d
            order by e.apelido1 asc, e.apelido2 asc, e.nome asc
            """;

            // Importante: Usar Object[].class
            return session.createQuery(hql, Object[].class).list();
        } catch (Exception e) {
            return null;
        }
    }

//Exercicio 3:  
    public static List<Object[]> listarDepartamentosNumEmpleadosDAO() {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
// size(d.empregados) devuelve 0 si no hay empleados
            String hql = """
            select d.numDepartamento, d.nomeDepartamento, size(d.empregados)  
            from Departamento d
            order by size(d.empregados) desc
        """;

            return session.createQuery(hql, Object[].class).list();
        }
    }

//Ejercico 4 -A-
//Necesitamos combinar coas coleccións porque son a forma
// en que Hibernate representa as relacións entre entidades, e sen ese join 
// non teriamos acceso aos proxectos asociados a cada empregado.
// Usamos LEFT JOIN porque queremos que aparezan TODOS os empregados, 
// teñan ou non teñan proxectos asignados.
    public static List<Object[]> listarProyectosPorEmpleadoDAO() {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            String hql = """
    select e.nss, p.nomeProxecto
    from Empregado e
    left join e.proxectos ep
    left join ep.proxecto p
    order by e.nss
""";

            return session.createQuery(hql, Object[].class).list();
        }

    }

    //EJERCICIO 4 -B-
    public static List<Object[]> listarEmpregadosConMaisDunProxectoDAO() {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            String hql = """
            select e.nss, p.nomeProxecto
            from Empregado e
            inner join e.proxectos ep
            inner join ep.proxecto p
            where size(e.proxectos) > 1
            order by e.nss
        """;

            return session.createQuery(hql, Object[].class).list();
        }
    }
//EJERCICIO 5

    public static List<Empregado> empregadosSenTelefonoDAO() {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            String hql = """
            from Empregado e
            where e.telefonos is empty
            order by e.nss
        """;

            return session.createQuery(hql, Empregado.class).list();
        }
    }
//EJERCCIO 6 

    /*
 
 falta añadir a Proxecto.hbm.xml el mapeo del departamento que controla 
lo siguiente
  <!--Mapeo del departamento que controla los proyectos -->
    <many-to-one name="departamento"
                     class="POJOS.Departamento"
                     column="NumDepartControla"
                     not-null="true"/>
     */
    public static List<Proxecto> consultaProxectosDepartDAO(int numDept) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            String hql = """
            select p
            from Proxecto p
            where p.departamento.numDepartamento = :num
            order by p.nomeProxecto
        """;

            return session.createQuery(hql, Proxecto.class)
                    .setParameter("num", numDept)
                    .list();
        }
    }

    public static List<Proxecto> consultaProxectosDepartDAO(String nomeDept) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            String hql = """
            select p
            from Proxecto p
            where p.departamento.nomeDepartamento = :nome
            order by p.nomeProxecto
        """;

            return session.createQuery(hql, Proxecto.class)
                    .setParameter("nome", nomeDept)
                    .list();
        }
    }

    //EXERCICIO 7
    //D
    // MÉTODO DAO: devolve unha lista de DTOs cos datos dos directores (DirectorDepartamentoDTO).
// Só trae os campos necesarios, NON trae entidades completas.
    //también se podría traer un List<Object[]>
/*  A consulta usa "select new DTO.DirectorDepartamentoDTO(...)" para que Hibernate
    cree directamente obxectos DTO, evitando traballar con Object[].
    Usamos:    left join d.director.supervisor s
       - Se traen todos os departamentos e os seus directores.
        - Se o director TEN supervisor ? recóllense os seus datos.
        - Se o director NON ten supervisor ? NON se elimina a fila. Simplemente, os campos do supervisor veñen como NULL.
    Por iso se usa LEFT JOIN e non INNER JOIN:
        - INNER JOIN eliminaría os directores sen supervisor.
        - LEFT JOIN garante que todos os directores aparezan no listado.
     */
    public static List<DirectorDepartamentoDTO> listarDirectoresDAO() {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            // Consulta HQL que crea directamente o DTO.
            // Só se traen os campos necesarios, non entidades completas.
            String hql = """
            select new DTO.DirectorDepartamentoDTO(
                d.director.nss,
                concat(d.director.apelido1, ' ', d.director.apelido2, ', ', d.director.nome),
                concat(s.apelido1, ' ', s.apelido2, ', ', s.nome),
                d.director.salario,
                d.nomeDepartamento
            )
            from Departamento d
            left join d.director.supervisor s
            order by d.director.salario asc, d.director.nss asc
        """;

            // Hibernate devolve directamente unha lista de DTOs
            return session.createQuery(hql, DirectorDepartamentoDTO.class).list();
        }
    }
//E
/*   MÉTODO: totalSalariosDirectoresDAO
    Este método calcula a suma total dos salarios dos directores de departamento.
    --------------
    - Cada Departamento ten un director (Empregadofixo). A consulta HQL accede a d.director.salario.
    - Usamos a función SUM() de HQL para sumar todos os salarios.
    - Só se trae un único dato: o total.
    - Se non hai directores, SUM devolvería null, así que devolvemos 0.0 para evitar problemas.
     */
    public static Double totalSalariosDirectoresDAO() {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            String hql = """
            select sum(d.director.salario)
            from Departamento d
        """;

            Double total = session.createQuery(hql, Double.class).uniqueResult();
            // Se SUM devolve null (non debería), devolvemos 0.0
            return total != null ? total : 0.0;
        }
    }

    /*F
    MÉTODO: departamentosConSalarioSuperiorDAO
    Este método devolve unha lista de departamentos nos queo total dos salarios dos seus empregados fixos supera unha
    cantidade indicada por parámetro.
    - Cada Departamento ten un SET de empregados fixos. Sumamos os salarios deses empregados fixos usando SUM().
    - Agrupamos por departamento para obter un total por cada un.
    - Usamos HAVING para filtrar só os que superan a cantidade indicada.
    - A consulta é parametrizada: :cantidade
    - Ordenamos polo total de salario DESC (de maior a menor).    
     */
    public static List<Object[]> departamentosConSalarioSuperiorDAO(double cantidade) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            String hql = """
            select  d.numDepartamento, d.nomeDepartamento, count(e), sum(e.salario)     
            from Departamento d
            inner join d.empregados e                         
            where type(e) = POJOS.Empregadofixo        
            group by d.numDepartamento, d.nomeDepartamento
            having sum(e.salario) > :cantidade
            order by sum(e.salario) desc
        """;

            return session.createQuery(hql, Object[].class)
                    .setParameter("cantidade", cantidade)
                    .list();
        }
    }

//exercicio 8
/*    Consulta que obtén os departamentos que teñen asignado  o maior número de empregados fixos.
 Unimos cada departamento cos seus empregados mediante  d.empregados.
 Filtramos só os empregados fixos usando:  type(e) = Empregadofixo.Isto garante que só se contan empregados fixos.
Agrupamos por departamento e director para poder calcular o número de empregados fixos por cada un.
A cláusula HAVING compara o número de empregados fixos de cada departamento co valor máximo existente.
Para iso utilizamos:    count(e) >= ALL (subconsulta)
 A subconsulta devolve o número de empregados de cada departamento, e 
 ALL selecciona só aqueles que teñen  o valor máximo.
     */
    public static List<DepartamentoMaxEmpregadosDTO> departamentosConMaisEmpregadosDAO() {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = """
            select new DTO.DepartamentoMaxEmpregadosDTO(
                d.nomeDepartamento,   count(e),
                d.director.apelido1 || ' ' || coalesce(d.director.apelido2,'') || ', ' || d.director.nome
            )
            from Departamento d  join d.empregados e
           group by d.nomeDepartamento, d.director.apelido1, d.director.apelido2, d.director.nome
            having count(e) >= ALL (
                select size(d2.empregados)
                from Departamento d2
                group by d2.numDepartamento
            )
        """;
            return session.createQuery(hql, DepartamentoMaxEmpregadosDTO.class).list();
        }
    }
//Exercico 9
//A
/*    Método que devolve os empregados fixos que teñen o salario máis alto.    
   1) Ao consultar sobre 'Empregadofixo', Hibernate realiza automaticamente o INNER JOIN 
      coa superclase 'Empregado' para obter os datos comúns (nome, apelidos, depto).
   2) A subconsulta '(select max(f2.salario) from Empregadofixo f2)' identifica 
      o valor salarial máis alto de forma dinámica.
   3) O WHERE filtra para que só se devolvan os empregados cuxo salario coincida 
      exactamente con ese máximo, permitindo listar varios se hai un empate.
     */
    public static List<Object[]> empregadosFixosQueGananMaisDAO() {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            String hql = """
           select f.nss, f.apelido1 || ' ' || coalesce(f.apelido2,'') || ', ' || f.nome,
           f.departamento.nomeDepartamento, f.salario
           from Empregadofixo f
            where f.salario = (select max(f2.salario) from Empregadofixo f2)
            """;

            return session.createQuery(hql, Object[].class).list();
        }
    }
//B

    /* Método que devolve os empregados fixos que gañan máis que o director mellor pagado.
   1) Ao consultar sobre 'Empregadofixo', Hibernate realiza automaticamente o JOIN 
      coa superclase 'Empregado' para obter os datos comúns (nome, apelidos, depto).
   2) A subconsulta identifica o salario máis alto exclusivamente entre os directores, 
      localizando os seus NSS na entidade 'Departamento' mediante a cláusula 'IN'.
   3) O WHERE filtra os empregados fixos cuxo salario sexa estrictamente superior (>) 
      ao valor máximo obtido nesa subconsulta de directores.
     */
    public static List<Object[]> empregadosQueGananMaisQueDirectorMaxDAO() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            String hql = """
            select f.nss, f.apelido1 || ' ' || coalesce(f.apelido2,'') || ', ' || f.nome,
                   f.departamento.nomeDepartamento, f.salario
            from Empregadofixo f
            where f.salario > (
                select max(f2.salario) 
                from Empregadofixo f2 
                where f2.nss in (select d.director.nss from Departamento d)
            )
        """;

            return session.createQuery(hql, Object[].class).list();
        }
    }

    //C    
    /*
    Método que devolve os empregados fixos varóns que:
        - son supervisores y teñen un salario comprendido entre dous límites
     1) Consultamos diretamente 'Empregadofixo'. Hibernate traz os dados de 'Empregado' automaticamente.
     2) 'f.supervisados is not empty': Verifica se o empregado tem pessoas a cargo (é supervisor).
     3) 'f.sexo = 'H'': Filtra apenas os varões.
     4) 'between :limInf and :limSup': Filtra o intervalo de salário.
       
     */
    public static List<Object[]> empregadosVaronsSupervisoresEntreSalariosDAO(double limInf, double limSup) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            String hql = """
            select  f.nss, f.apelido1 || ' ' || coalesce(f.apelido2,'') || ', ' || f.nome,
                f.departamento.nomeDepartamento, f.salario
            from Empregadofixo f
            where f.sexo = 'H'
              and f.supervisados is not empty
              and f.salario between :limInf and :limSup
        """;

            return session.createQuery(hql, Object[].class)
                    .setParameter("limInf", limInf)
                    .setParameter("limSup", limSup)
                    .list();
        }
    }
    //D

    /*    Sube o salario nun porcentaxe aos empregados fixos que teñen asignado o maior número de proxectos.

    1)  Cada empregado pode ter varios proxectos asignados (relación e.proxectos).
    2)  A subconsulta:        select max(size(e2.proxectos)) from Empregado e2
        devolve o número máximo de proxectos asignados a un empregado.
    3)  Facemos JOIN con Empregadofixo porque o salario só existe na subclase.
    4)  Actualizamos só os empregados fixos que teñen ese número máximo de proxectos.
    5)  O salario increméntase multiplicando por (1 + porcentaxe/100).
     */
 /*
    PriMero obtenemo el valimno de los proyectos asignados a los empleados
   group by e.nss: Agrupa os proxectos por cada empregado.
   count(p): Conta cantos proxectos ten cada un deses grupos.
   order by count(p) desc: Pon o número máis alto (o que máis proxectos ten) na primeira posición da lista.
   setMaxResults(1): Hibernate engade un TOP 1 (en SQL Server) á consulta para que só viaxe un único número dende a base de datos: o máis alto.
     */
    public static Integer obterMaximoProxectosDAO() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
             //ordena para despues coger el primero que será el máximo
            String hql = """
                    select count(p) from Empregado e 
                    inner join e.proxectos p 
                    group by e.nss 
                    order by count(p) desc 
                    """;

            // Executamos a consulta e quedámonos só co primeiro resultado
            //Cando usas a función de agregado count() en HQL, Hibernate segue a especificación JPA. 
            //Segundo esta norma, calquera operación de conta (count) debe devolver un Long por defecto.
            Long resultado = session.createQuery(hql, Long.class)
                    .setMaxResults(1) //selecciona só o máximo,
                    .uniqueResult();

            return (resultado != null) ? resultado.intValue() : 0;
        } catch (Exception e) {
            return 0;
        }
    }

    public static int subirSalarioEmpregadosConMaisProxectosDAO(double porcentaxe) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            // 1. Obtemos o máximo número de proxectos usando o el método anterior
            Integer maxP = obterMaximoProxectosDAO();

            // Se ninguén ten proxectos, non hai nada que actualizar
            if (maxP == 0) {
                return 0;
            }
            Transaction tx = session.beginTransaction();
            String hqlUpdate = """
                    update Empregadofixo f
                    set f.salario = f.salario * (1 + :porc / 100.0)
                    where size(f.proxectos) = :maxP
                    """;

            /* Utilizando Group By en una sola consulta 
           String hql = """
            update Empregadofixo f 
            set f.salario = f.salario * (1 + :porcentaxe / 100)
            where f.nss in (
                select e.nss from Empregado e 
                join e.empregadoProxectos ep
                group by e.nss
                having count(ep) >= all (
                    select count(ep2) from Empregado e2 
                    join e2.empregadoProxectos ep2 
                    group by e2.nss
                )
            )
        """; 
            
             */
            int afectados = session.createQuery(hqlUpdate)
                    .setParameter("porc", porcentaxe)
                    .setParameter("maxP", maxP)
                    .executeUpdate(); 

            tx.commit();
            return afectados;
        }
    }

}
