package UD2.empresa25.persistencia;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsultasSQL {
    Connection con;

    public ConsultasSQL(Connection con) {
        this.con = con;
    }

    public ResultSet ejercicio1() {
        String sql = "SELECT NumDepartamento, NomeDepartamento FROM departamento INNER JOIN bdempresa25.proxecto p on departamento.NumDepartamento = p.NumDepartControla";
        try {
            return GestorConexion.ejecutarConsulta(con, sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet ejercicio2() {
        String sql = "SELECT d.NumDepartamento, d.NomeDepartamento, e.Nome, e.Apelido1, e.Apelido2 FROM DEPARTAMENTO d JOIN EMPREGADO e ON d.NSSDIRECTOR = e.NSS WHERE EXISTS (SELECT 1 FROM EMPLEADO_PROXECTO ep WHERE ep.NSSEmpregado=e.NSS)";
        try{
            return GestorConexion.ejecutarConsulta(con, sql);
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public ResultSet ejercicio3() {
        String sql = "";
        try {
            return GestorConexion.ejecutarConsulta(con, sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

/*-- =========================================================
-- Ejercicio 1
-- Visualizar o número e nome dos departamentos que teñen proxectos asignados
-- =========================================================
SELECT DISTINCT d.NumDepartamento, d.NomeDepartamento
FROM DEPARTAMENTO d
JOIN PROXECTO p ON d.NumDepartamento = p.NumDepartControla;


-- =========================================================
-- Ejercicio 2
-- Número e nome do departamento e nome completo do director
-- =========================================================
SELECT DISTINCT d.NumDepartamento,
       d.NomeDepartamento,
       e.Nome,
       e.Apelido1,
       e.Apelido2
FROM DEPARTAMENTO d
JOIN PROXECTO p ON d.NumDepartamento = p.NumDepartControla
JOIN EMPREGADO e ON d.NSSDirector = e.NSS;


-- =========================================================
-- Ejercicio 3
-- NSS, nome completo e idade de todos os empregados
-- =========================================================
SELECT NSS,
       CONCAT(Nome, ' ', Apelido1, ' ', ISNULL(Apelido2, '')) AS NomeCompleto,
       DATEDIFF(YEAR, DataNacemento, GETDATE()) AS Idade
FROM EMPREGADO;


-- =========================================================
-- Ejercicio 4
-- Dado o nome dun departamento, empregados indicando se son fixos ou temporais
-- =========================================================
DECLARE @nomeDepto VARCHAR(25) = 'TÉCNICO';

SELECT e.NSS,
       CONCAT(e.Nome, ' ', e.Apelido1, ' ', ISNULL(e.Apelido2,'')) AS NomeCompleto,
       CASE
           WHEN f.NSS IS NOT NULL THEN 'FIXO'
           WHEN t.NSS IS NOT NULL THEN 'TEMPORAL'
       END AS Tipo
FROM EMPREGADO e
JOIN DEPARTAMENTO d ON e.NumDepartamentoPertenece = d.NumDepartamento
LEFT JOIN EMPREGADOFIXO f ON e.NSS = f.NSS
LEFT JOIN EMPREGADOTEMPORAL t ON e.NSS = t.NSS
WHERE d.NomeDepartamento = @nomeDepto;


-- =========================================================
-- Ejercicio 5
-- Fixos dun proxecto nunha localidade concreta
-- =========================================================
DECLARE @proxecto VARCHAR(25) = 'PORTAL';
DECLARE @localidade VARCHAR(25) = 'Vigo';

SELECT e.NSS,
       CONCAT(e.Nome, ' ', e.Apelido1, ' ', ISNULL(e.Apelido2,'')) AS NomeCompleto,
       f.Salario,
       d.NomeDepartamento
FROM EMPREGADO e
JOIN EMPREGADOFIXO f ON e.NSS = f.NSS
JOIN EMPREGADO_PROXECTO ep ON e.NSS = ep.NSSEmpregado
JOIN PROXECTO p ON ep.NumProxecto = p.NumProxecto
JOIN DEPARTAMENTO d ON e.NumDepartamentoPertenece = d.NumDepartamento
WHERE p.NomeProxecto = @proxecto
  AND e.Localidade = @localidade;


-- =========================================================
-- Ejercicio 6
-- Para cada departamento, nº de fixos e temporais
-- =========================================================
SELECT d.NumDepartamento,
       d.NomeDepartamento,
       SUM(CASE WHEN f.NSS IS NOT NULL THEN 1 ELSE 0 END) AS NumFixos,
       SUM(CASE WHEN t.NSS IS NOT NULL THEN 1 ELSE 0 END) AS NumTemporais
FROM DEPARTAMENTO d
LEFT JOIN EMPREGADO e ON d.NumDepartamento = e.NumDepartamentoPertenece
LEFT JOIN EMPREGADOFIXO f ON e.NSS = f.NSS
LEFT JOIN EMPREGADOTEMPORAL t ON e.NSS = t.NSS
GROUP BY d.NumDepartamento, d.NomeDepartamento;


-- =========================================================
-- Ejercicio 7
-- Departamentos con máis de N empregados
-- =========================================================
DECLARE @N INT = 5;

SELECT d.NumDepartamento, d.NomeDepartamento
FROM DEPARTAMENTO d
JOIN EMPREGADO e ON d.NumDepartamento = e.NumDepartamentoPertenece
GROUP BY d.NumDepartamento, d.NomeDepartamento
HAVING COUNT(*) > @N;


-- =========================================================
-- Ejercicio 8
-- Empregados fixos que cobran máis ca un valor
-- =========================================================
DECLARE @salarioMin DECIMAL(10,2) = 2000;

SELECT e.*, f.Salario
FROM EMPREGADO e
JOIN EMPREGADOFIXO f ON e.NSS = f.NSS
WHERE f.Salario > @salarioMin;


-- =========================================================
-- Ejercicio 9
-- Empregado fixo que máis gaña en cada departamento
-- =========================================================
SELECT d.NomeDepartamento,
       e.NSS,
       CONCAT(e.Nome, ' ', e.Apelido1, ' ', ISNULL(e.Apelido2,'')) AS NomeCompleto,
       f.Salario
FROM DEPARTAMENTO d
JOIN EMPREGADO e ON d.NumDepartamento = e.NumDepartamentoPertenece
JOIN EMPREGADOFIXO f ON e.NSS = f.NSS
WHERE f.Salario = (
    SELECT MAX(f2.Salario)
    FROM EMPREGADO e2
    JOIN EMPREGADOFIXO f2 ON e2.NSS = f2.NSS
    WHERE e2.NumDepartamentoPertenece = d.NumDepartamento
)
ORDER BY d.NomeDepartamento;


-- =========================================================
-- Ejercicio 10
-- Departamentos que controlan o máximo número de proxectos
-- =========================================================
SELECT d.NumDepartamento, d.NomeDepartamento
FROM DEPARTAMENTO d
JOIN PROXECTO p ON d.NumDepartamento = p.NumDepartControla
GROUP BY d.NumDepartamento, d.NomeDepartamento
HAVING COUNT(*) = (
    SELECT MAX(conta)
    FROM (
        SELECT COUNT(*) AS conta
        FROM PROXECTO
        GROUP BY NumDepartControla
    ) t
);
 */