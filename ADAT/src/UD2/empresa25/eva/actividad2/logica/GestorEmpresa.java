package UD2.empresa25.eva.actividad2.logica;

import UD2.empresa25.eva.actividad2.dto.*;
import utiles.Utilidades;
import java.sql.*;
import java.util.*;

public class GestorEmpresa {

    // estándar
    // EJERCICIO 1: Visualizar o número e nome dos departamentos que teñen proxectos asignados.
    public static List<DepartamentoDTO> ejercicio1(Connection con) {

        String sql = """
            SELECT DISTINCT
                d.NumDepartamento AS numDepartamento,
                d.NomeDepartamento AS nomeDepartamento
            FROM DEPARTAMENTO d
            JOIN PROXECTO p ON d.NumDepartamento = p.NumDepartControla
        """;

        // lista donde se guardan los dto creados a partir del resultset
        List<DepartamentoDTO> lista = new ArrayList<>();

        try (ResultSet rs = Utilidades.ejecutarConsulta(con, sql)) {

            // rs es el resultset: contiene todas las filas devueltas por la consulta
            // al principio el cursor está antes de la primera fila
            while (rs.next()) {

                // rs.next() mueve el cursor a la siguiente fila
                // devuelve false cuando ya no quedan filas

                // se leen los valores de la fila actual usando los alias del sql
                int num = rs.getInt("numDepartamento");
                String nome = rs.getString("nomeDepartamento");

                //esto es igual siempre que se usen:

                // se crea el dto con los datos de esa fila
                DepartamentoDTO dto = new DepartamentoDTO(num, nome);

                // se imprime el dto
                System.out.println(dto);

                // se guarda el dto en la lista
                lista.add(dto);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }

    // EJERCICIO 2: Visualizar o número e nome, nombre e apelidos do director dos departamentos que teñen proxectos
    // asignados.
    // CONCAT no funciona en SQLite

    // MySQL y SQL Server
    public static List<DepartamentoDirectorDTO> ejercicio2_mysql_sqlserver(Connection con) {

        String sql = """
            SELECT DISTINCT
                d.NumDepartamento AS numDepartamento,
                d.NomeDepartamento AS nomeDepartamento,
                e.Nome AS nomeDirector,
                CONCAT(e.Apelido1,' ',COALESCE(e.Apelido2,'')) AS apelidosDirector
            FROM DEPARTAMENTO d
            JOIN EMPREGADO e ON d.NSSDirector = e.NSS
            JOIN PROXECTO p ON d.NumDepartamento = p.NumDepartControla
        """;

        List<DepartamentoDirectorDTO> lista = new ArrayList<>();

        try (ResultSet rs = Utilidades.ejecutarConsulta(con, sql)) {

            // rs contiene una fila por cada departamento con proyectos
            while (rs.next()) {

                // se leen los datos de la fila actual
                int num = rs.getInt("numDepartamento");
                String nomeDep = rs.getString("nomeDepartamento");
                String nomeDir = rs.getString("nomeDirector");
                String apelidos = rs.getString("apelidosDirector");

                // se crea el dto usando los datos leídos
                DepartamentoDirectorDTO dto =
                        new DepartamentoDirectorDTO(num, nomeDep, nomeDir, apelidos);

                System.out.println(dto);
                lista.add(dto);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }

    // SQLite
    public static List<DepartamentoDirectorDTO> ejercicio2_sqlite(Connection con) {

        String sql = """
            SELECT DISTINCT
                d.NumDepartamento AS numDepartamento,
                d.NomeDepartamento AS nomeDepartamento,
                e.Nome AS nomeDirector,
                e.Apelido1 || ' ' || COALESCE(e.Apelido2,'') AS apelidosDirector
            FROM DEPARTAMENTO d
            JOIN EMPREGADO e ON d.NSSDirector = e.NSS
            JOIN PROXECTO p ON d.NumDepartamento = p.NumDepartControla
        """;

        List<DepartamentoDirectorDTO> lista = new ArrayList<>();

        try (ResultSet rs = Utilidades.ejecutarConsulta(con, sql)) {

            // el funcionamiento del resultset es exactamente el mismo
            while (rs.next()) {

                DepartamentoDirectorDTO dto =
                        new DepartamentoDirectorDTO(
                                rs.getInt("numDepartamento"),
                                rs.getString("nomeDepartamento"),
                                rs.getString("nomeDirector"),
                                rs.getString("apelidosDirector")
                        );

                System.out.println(dto);
                lista.add(dto);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }

    // EJERCICIO 3: Visualizar o NSS, o nome e apelidos e a idade dos empregados da empresa.
    // fechas

    // MySQL
    public static List<EmpregadoIdadeDTO> ejercicio3_mysql(Connection con) {

        String sql = """
            SELECT
                NSS AS nss,
                CONCAT(Nome,' ',Apelido1) AS nome,
                TIMESTAMPDIFF(YEAR, DataNacemento, CURDATE()) AS idade
            FROM EMPREGADO
        """;

        List<EmpregadoIdadeDTO> lista = new ArrayList<>();

        try (ResultSet rs = Utilidades.ejecutarConsulta(con, sql)) {

            // cada fila representa un empleado
            while (rs.next()) {

                EmpregadoIdadeDTO dto =
                        new EmpregadoIdadeDTO(
                                rs.getString("nss"),
                                rs.getString("nome"),
                                rs.getInt("idade")
                        );

                System.out.println(dto);
                lista.add(dto);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }

    // SQLite
    public static List<EmpregadoIdadeDTO> ejercicio3_sqlite(Connection con) {

        String sql = """
            SELECT
                NSS AS nss,
                Nome || ' ' || Apelido1 AS nome,
                CAST((strftime('%Y','now') - strftime('%Y', DataNacemento)) AS INTEGER) AS idade
            FROM EMPREGADO
        """;

        List<EmpregadoIdadeDTO> lista = new ArrayList<>();

        try (ResultSet rs = Utilidades.ejecutarConsulta(con, sql)) {

            // mismo recorrido fila a fila
            while (rs.next()) {

                EmpregadoIdadeDTO dto =
                        new EmpregadoIdadeDTO(
                                rs.getString("nss"),
                                rs.getString("nome"),
                                rs.getInt("idade")
                        );

                System.out.println(dto);
                lista.add(dto);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }

    // SQL Server
    public static List<EmpregadoIdadeDTO> ejercicio3_sqlserver(Connection con) {

        String sql = """
            SELECT
                NSS AS nss,
                CONCAT(Nome,' ',Apelido1) AS nome,
                DATEDIFF(YEAR, DataNacemento, GETDATE()) AS idade
            FROM EMPREGADO
        """;

        List<EmpregadoIdadeDTO> lista = new ArrayList<>();

        try (ResultSet rs = Utilidades.ejecutarConsulta(con, sql)) {

            // el resultset se recorre exactamente igual
            while (rs.next()) {

                EmpregadoIdadeDTO dto =
                        new EmpregadoIdadeDTO(
                                rs.getString("nss"),
                                rs.getString("nome"),
                                rs.getInt("idade")
                        );

                System.out.println(dto);
                lista.add(dto);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }

    // EJERCICIO 4
    // MySQL
    public static List<EmpregadoResumoDTO> ejercicio4_mysql(Connection con, String departamento) {

        String sql = """
            SELECT
                e.NSS AS nss,
                CONCAT(e.Nome,' ',e.Apelido1) AS nome,
                IF(f.NSS IS NOT NULL,'FIXO','TEMPORAL') AS tipo
            FROM EMPREGADO e
            JOIN DEPARTAMENTO d ON e.NumDepartamentoPertenece = d.NumDepartamento
            LEFT JOIN EMPREGADOFIXO f ON e.NSS = f.NSS
            WHERE d.NomeDepartamento = ?
        """;

        List<EmpregadoResumoDTO> lista = new ArrayList<>();

        try (ResultSet rs = Utilidades.ejecutarConsulta(con, sql, departamento)) {

            while (rs.next()) {

                EmpregadoResumoDTO dto =
                        new EmpregadoResumoDTO(
                                rs.getString("nss"),
                                rs.getString("nome"),
                                rs.getString("tipo")
                        );

                System.out.println(dto);
                lista.add(dto);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }

    // SQLite y SQL Server
    public static List<EmpregadoResumoDTO> ejercicio4_sqlite_sqlserver(Connection con, String departamento) {

        String sql = """
            SELECT
                e.NSS AS nss,
                e.Nome + ' ' + e.Apelido1 AS nome,
                CASE WHEN f.NSS IS NOT NULL THEN 'FIXO' ELSE 'TEMPORAL' END AS tipo
            FROM EMPREGADO e
            JOIN DEPARTAMENTO d ON e.NumDepartamentoPertenece = d.NumDepartamento
            LEFT JOIN EMPREGADOFIXO f ON e.NSS = f.NSS
            WHERE d.NomeDepartamento = ?
        """;

        List<EmpregadoResumoDTO> lista = new ArrayList<>();

        try (ResultSet rs = Utilidades.ejecutarConsulta(con, sql, departamento)) {

            while (rs.next()) {

                EmpregadoResumoDTO dto =
                        new EmpregadoResumoDTO(
                                rs.getString("nss"),
                                rs.getString("nome"),
                                rs.getString("tipo")
                        );

                System.out.println(dto);
                lista.add(dto);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }

    // EJERCICIO 5
    public static List<EmpregadoResumoDTO> ejercicio5_mysql_sqlserver(Connection con, String proxecto, String lugar) {

        String sql = """
            SELECT
                e.NSS AS nss,
                CONCAT(e.Nome,' ',e.Apelido1) AS nome,
                f.Salario AS salario
            FROM EMPREGADO e
            JOIN EMPREGADOFIXO f ON e.NSS = f.NSS
            JOIN EMPREGADO_PROXECTO ep ON e.NSS = ep.NSSEmpregado
            JOIN PROXECTO p ON ep.NumProxecto = p.NumProxecto
            WHERE p.NomeProxecto = ? AND p.Lugar = ?
        """;

        List<EmpregadoResumoDTO> lista = new ArrayList<>();

        try (ResultSet rs = Utilidades.ejecutarConsulta(con, sql, proxecto, lugar)) {

            while (rs.next()) {

                EmpregadoResumoDTO dto =
                        new EmpregadoResumoDTO(
                                rs.getString("nss"),
                                rs.getString("nome"),
                                rs.getString("salario")
                        );

                System.out.println(dto);
                lista.add(dto);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }

    // SQLite
    public static List<EmpregadoResumoDTO> ejercicio5_sqlite(Connection con, String proxecto, String lugar) {

        String sql = """
            SELECT
                e.NSS AS nss,
                e.Nome || ' ' || e.Apelido1 AS nome,
                f.Salario AS salario
            FROM EMPREGADO e
            JOIN EMPREGADOFIXO f ON e.NSS = f.NSS
            JOIN EMPREGADO_PROXECTO ep ON e.NSS = ep.NSSEmpregado
            JOIN PROXECTO p ON ep.NumProxecto = p.NumProxecto
            WHERE p.NomeProxecto = ? AND p.Lugar = ?
        """;

        List<EmpregadoResumoDTO> lista = new ArrayList<>();

        try (ResultSet rs = Utilidades.ejecutarConsulta(con, sql, proxecto, lugar)) {

            while (rs.next()) {

                EmpregadoResumoDTO dto =
                        new EmpregadoResumoDTO(
                                rs.getString("nss"),
                                rs.getString("nome"),
                                rs.getString("salario")
                        );

                System.out.println(dto);
                lista.add(dto);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }

    // EJERCICIO 6
    public static List<DepartamentoEmpleadosDTO> ejercicio6_mysql(Connection con) {

        String sql = """
            SELECT
                d.NomeDepartamento AS departamento,
                SUM(f.NSS IS NOT NULL) AS fixos,
                SUM(t.NSS IS NOT NULL) AS temporais
            FROM DEPARTAMENTO d
            LEFT JOIN EMPREGADO e ON d.NumDepartamento = e.NumDepartamentoPertenece
            LEFT JOIN EMPREGADOFIXO f ON e.NSS = f.NSS
            LEFT JOIN EMPREGADOTEMPORAL t ON e.NSS = t.NSS
            GROUP BY d.NomeDepartamento
        """;

        List<DepartamentoEmpleadosDTO> lista = new ArrayList<>();

        try (ResultSet rs = Utilidades.ejecutarConsulta(con, sql)) {

            while (rs.next()) {

                DepartamentoEmpleadosDTO dto =
                        new DepartamentoEmpleadosDTO(
                                rs.getString("departamento"),
                                rs.getInt("fixos"),
                                rs.getInt("temporais")
                        );

                System.out.println(dto);
                lista.add(dto);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }

    // SQLite y SQL Server
    public static List<DepartamentoEmpleadosDTO> ejercicio6_sqlite_sqlserver(Connection con) {

        String sql = """
            SELECT
                d.NomeDepartamento AS departamento,
                SUM(CASE WHEN f.NSS IS NOT NULL THEN 1 ELSE 0 END) AS fixos,
                SUM(CASE WHEN t.NSS IS NOT NULL THEN 1 ELSE 0 END) AS temporais
            FROM DEPARTAMENTO d
            LEFT JOIN EMPREGADO e ON d.NumDepartamento = e.NumDepartamentoPertenece
            LEFT JOIN EMPREGADOFIXO f ON e.NSS = f.NSS
            LEFT JOIN EMPREGADOTEMPORAL t ON e.NSS = t.NSS
            GROUP BY d.NomeDepartamento
        """;

        List<DepartamentoEmpleadosDTO> lista = new ArrayList<>();

        try (ResultSet rs = Utilidades.ejecutarConsulta(con, sql)) {

            while (rs.next()) {

                DepartamentoEmpleadosDTO dto =
                        new DepartamentoEmpleadosDTO(
                                rs.getString("departamento"),
                                rs.getInt("fixos"),
                                rs.getInt("temporais")
                        );

                System.out.println(dto);
                lista.add(dto);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }

    // EJERCICIO 7
    public static List<DepartamentoDTO> ejercicio7(Connection con, int n) {

        String sql = """
        SELECT
            d.NumDepartamento AS numDepartamento,
            d.NomeDepartamento AS nomeDepartamento
        FROM DEPARTAMENTO d
        JOIN EMPREGADO e ON d.NumDepartamento = e.NumDepartamentoPertenece
        GROUP BY d.NumDepartamento, d.NomeDepartamento
        HAVING COUNT(*) > ?
    """;

        List<DepartamentoDTO> lista = new ArrayList<>();

        try (ResultSet rs = Utilidades.ejecutarConsulta(con, sql, n)) {

            while (rs.next()) {

                DepartamentoDTO dto =
                        new DepartamentoDTO(
                                rs.getInt("numDepartamento"),
                                rs.getString("nomeDepartamento")
                        );

                System.out.println(dto);
                lista.add(dto);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }


    // EJERCICIO 8
    public static List<EmpregadoResumoDTO> ejercicio8_mysql_sqlserver(Connection con, double salario) {

        String sql = """
            SELECT
                e.NSS AS nss,
                CONCAT(e.Nome,' ',e.Apelido1) AS nome,
                f.Salario AS salario
            FROM EMPREGADO e
            JOIN EMPREGADOFIXO f ON e.NSS = f.NSS
            WHERE f.Salario > ?
        """;

        List<EmpregadoResumoDTO> lista = new ArrayList<>();

        try (ResultSet rs = Utilidades.ejecutarConsulta(con, sql, salario)) {

            while (rs.next()) {

                EmpregadoResumoDTO dto =
                        new EmpregadoResumoDTO(
                                rs.getString("nss"),
                                rs.getString("nome"),
                                rs.getString("salario")
                        );

                System.out.println(dto);
                lista.add(dto);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }

    // SQLite
    public static List<EmpregadoResumoDTO> ejercicio8_sqlite(Connection con, double salario) {

        String sql = """
            SELECT
                e.NSS AS nss,
                e.Nome || ' ' || e.Apelido1 AS nome,
                f.Salario AS salario
            FROM EMPREGADO e
            JOIN EMPREGADOFIXO f ON e.NSS = f.NSS
            WHERE f.Salario > ?
            ORDER BY f.Salario DESC
        """;

        List<EmpregadoResumoDTO> lista = new ArrayList<>();

        try (ResultSet rs = Utilidades.ejecutarConsulta(con, sql, salario)) {

            while (rs.next()) {

                EmpregadoResumoDTO dto =
                        new EmpregadoResumoDTO(
                                rs.getString("nss"),
                                rs.getString("nome"),
                                rs.getString("salario")
                        );

                System.out.println(dto);
                lista.add(dto);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }

    // EJERCICIO 9
    public static List<String> ejercicio9_mysql_sqlserver(Connection con) throws SQLException {

        List<String> lista = new ArrayList<>();

        String sql = """
            SELECT
                d.NomeDepartamento AS departamento,
                CONCAT(e.Nome,' ',e.Apelido1) AS nome
            FROM EMPREGADOFIXO f
            JOIN EMPREGADO e ON f.NSS = e.NSS
            JOIN DEPARTAMENTO d ON e.NumDepartamentoPertenece = d.NumDepartamento
            WHERE f.Salario = (
                SELECT MAX(f2.Salario)
                FROM EMPREGADOFIXO f2
                JOIN EMPREGADO e2 ON f2.NSS = e2.NSS
                WHERE e2.NumDepartamentoPertenece = d.NumDepartamento
            )
            ORDER BY d.NomeDepartamento
        """;

        PreparedStatement ps = con.prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);

        ResultSet rs = ps.executeQuery();
        rs.afterLast(); // empieza por el final

        while (rs.previous()) {

            String fila =
                    rs.getString("departamento") + " | " +
                            rs.getString("nome");

            System.out.println(fila);
            lista.add(fila);
        }

        return lista;
    }

    // EJERCICIO 10
    public static List<DepartamentoDTO> ejercicio10_mysql_sqlserver(Connection con) {

        String sql = """
            SELECT
                d.NumDepartamento AS numDepartamento,
                d.NomeDepartamento AS nomeDepartamento
            FROM DEPARTAMENTO d
            LEFT JOIN PROXECTO p ON d.NumDepartamento = p.NumDepartControla
            GROUP BY d.NumDepartamento, d.NomeDepartamento
            HAVING COUNT(p.NumProxecto) = (
                SELECT MAX(conta)
                FROM (
                    SELECT COUNT(*) AS conta
                    FROM PROXECTO
                    GROUP BY NumDepartControla
                ) t
            )
        """;

        List<DepartamentoDTO> lista = new ArrayList<>();

        try (ResultSet rs = Utilidades.ejecutarConsulta(con, sql)) {

            while (rs.next()) {

                DepartamentoDTO dto =
                        new DepartamentoDTO(
                                rs.getInt("numDepartamento"),
                                rs.getString("nomeDepartamento")
                        );

                System.out.println(dto);
                lista.add(dto);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }
}
