package UD2.empresa25.actividad2.persistencia;

import java.sql.*;
import java.util.*;

import UD2.empresa25.actividad2.dtos.*;

public class EmpresaDAO{

    private final Connection con;

    public EmpresaDAO(Connection con) {
        this.con = con;
    }

    // Ejercicio 1
    public List<DepartamentoDTO> departamentosConProyectos() throws SQLException {
        String sql = """
            SELECT DISTINCT d.NumDepartamento, d.NomeDepartamento
            FROM DEPARTAMENTO d
            JOIN PROXECTO p ON d.NumDepartamento = p.NumDepartControla
        """;
        List<DepartamentoDTO> res = new ArrayList<>();
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                res.add(new DepartamentoDTO(rs.getInt(1), rs.getString(2)));
            }
        }
        return res;
    }

    // Ejercicio 2
    public List<DepartamentoDirectorDTO> departamentosConDirector() throws SQLException {
        String sql = """
            SELECT d.NumDepartamento, d.NomeDepartamento, e.Nome, e.Apelido1 + ISNULL(e.Apelido2, '')
            FROM DEPARTAMENTO d
            JOIN PROXECTO p ON d.NumDepartamento = p.NumDepartControla
            JOIN EMPREGADO e ON d.NSSDirector = e.NSS
            GROUP BY d.NumDepartamento, d.NomeDepartamento, e.Nome, e.Apelido1, e.Apelido2
        """;
        List<DepartamentoDirectorDTO> res = new ArrayList<>();
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                res.add(new DepartamentoDirectorDTO(
                        rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4)));
            }
        }
        return res;
    }

    // Ejercicio 3
    public List<EmpleadoEdadDTO> empregadosConIdade() throws SQLException {
        String sql = """
            SELECT NSS, Nome, Apelido1 + ISNULL(e.Apelido2, ''),
                   DATEDIFF(YEAR, DataNacemento, GETDATE())
            FROM EMPREGADO
        """;
        List<EmpleadoEdadDTO> res = new ArrayList<>();
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                res.add(new EmpleadoEdadDTO(
                        rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getInt(5)));
            }
        }
        return res;
    }

    // Ejercicio 4
    public List<EmpleadoTipoDTO> empregadosPorDepartamento(String nomeDepto) throws SQLException {
        String sql = """
            SELECT e.NSS, e.Nome + ' ' + e.Apelido1 + ' ' + ISNULL(e.Apelido2,''),
                   CASE WHEN f.NSS IS NOT NULL THEN 'FIXO' ELSE 'TEMPORAL' END
            FROM EMPREGADO e
            JOIN DEPARTAMENTO d ON e.NumDepartamentoPertenece = d.NumDepartamento
            LEFT JOIN EMPREGADOFIXO f ON e.NSS = f.NSS
            WHERE d.NomeDepartamento = ?
        """;
        List<EmpleadoTipoDTO> res = new ArrayList<>();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nomeDepto);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    res.add(new EmpleadoTipoDTO(
                            rs.getString(1), rs.getString(2), rs.getString(5)));
                }
            }
        }
        return res;
    }

    // Ejercicio 5
    public List<EmpleadoProyectoDTO> empregadosFixosProxectoLocalidade(String prox, String loc) throws SQLException {
        String sql = """
            SELECT e.NSS, e.Nome + ' ' + e.Apelido1 + ' ' + ISNULL(e.Apelido2,''),
                   f.Salario, d.NomeDepartamento
            FROM EMPREGADO e
            JOIN EMPREGADOFIXO f ON e.NSS = f.NSS
            JOIN EMPREGADO_PROXECTO ep ON e.NSS = ep.NSSEmpregado
            JOIN PROXECTO p ON ep.NumProxecto = p.NumProxecto
            JOIN DEPARTAMENTO d ON e.NumDepartamentoPertenece = d.NumDepartamento
            WHERE p.NomeProxecto = ? AND e.Localidade = ?
        """;
        List<EmpleadoProyectoDTO> res = new ArrayList<>();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, prox);
            ps.setString(2, loc);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    res.add(new EmpleadoProyectoDTO(
                            rs.getString(1), rs.getString(2),
                            rs.getDouble(3), rs.getString(4)));
                }
            }
        }
        return res;
    }

    // Ejercicio 6
    public List<DepartamentoConteoDTO> contadoresPorDepartamento() throws SQLException {
        String sql = """
            SELECT d.NumDepartamento, d.NomeDepartamento,
                   SUM(CASE WHEN f.NSS IS NOT NULL THEN 1 ELSE 0 END) AS Fixos,
                   SUM(CASE WHEN t.NSS IS NOT NULL THEN 1 ELSE 0 END) AS Temporais
            FROM DEPARTAMENTO d
            LEFT JOIN EMPREGADO e ON d.NumDepartamento = e.NumDepartamentoPertenece
            LEFT JOIN EMPREGADOFIXO f ON e.NSS = f.NSS
            LEFT JOIN EMPREGADOTEMPORAL t ON e.NSS = t.NSS
            GROUP BY d.NumDepartamento, d.NomeDepartamento
        """;
        List<DepartamentoConteoDTO> res = new ArrayList<>();
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                res.add(new DepartamentoConteoDTO(
                        rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4)));
            }
        }
        return res;
    }

    // Ejercicio 7
    public List<DepartamentoDTO> departamentosConMasDeN(int n) throws SQLException {
        String sql = """
            SELECT d.NumDepartamento, d.NomeDepartamento
            FROM DEPARTAMENTO d
            JOIN EMPREGADO e ON d.NumDepartamento = e.NumDepartamentoPertenece
            GROUP BY d.NumDepartamento, d.NomeDepartamento
            HAVING COUNT(*) > ?
        """;
        List<DepartamentoDTO> res = new ArrayList<>();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, n);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    res.add(new DepartamentoDTO(rs.getInt(1), rs.getString(2)));
                }
            }
        }
        return res;
    }

    // Ejercicio 8
    public List<EmpleadoFijoDTO> fixosConSalarioMaior(double v) throws SQLException {
        String sql = """
            SELECT e.NSS, e.Nome + ' ' + e.Apelido1 + ' ' + ISNULL(e.Apelido2,''), f.Salario
            FROM EMPREGADO e
            JOIN EMPREGADOFIXO f ON e.NSS = f.NSS
            WHERE f.Salario > ?
        """;
        List<EmpleadoFijoDTO> res = new ArrayList<>();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDouble(1, v);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    res.add(new EmpleadoFijoDTO(
                            rs.getString(1), rs.getString(2), rs.getDouble(5)));
                }
            }
        }
        return res;
    }

    // Ejercicio 9 (scroll)
    public ResultSet mellorPagadosPorDepartamento() throws SQLException {
        String sql = """
            SELECT d.NomeDepartamento, e.NSS, e.Nome, e.Apelido1, e.Apelido2, f.Salario
            FROM DEPARTAMENTO d
            JOIN EMPREGADO e ON d.NumDepartamento = e.NumDepartamentoPertenece
            JOIN EMPREGADOFIXO f ON e.NSS = f.NSS
            WHERE f.Salario = (
                SELECT MAX(f2.Salario)
                FROM EMPREGADO e2
                JOIN EMPREGADOFIXO f2 ON e2.NSS = f2.NSS
                WHERE e2.NumDepartamentoPertenece = d.NumDepartamento
            )
            ORDER BY d.NomeDepartamento
        """;
        Statement st = con.createStatement(
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        return st.executeQuery(sql);
    }

    // Ejercicio 10
    public List<DepartamentoDTO> departamentosMaxProxectos() throws SQLException {
        String sql = """
            SELECT d.NumDepartamento, d.NomeDepartamento
            FROM DEPARTAMENTO d
            JOIN PROXECTO p ON d.NumDepartamento = p.NumDepartControla
            GROUP BY d.NumDepartamento, d.NomeDepartamento
            HAVING COUNT(*) = (
                SELECT MAX(c)
                FROM (
                    SELECT COUNT(*) c
                    FROM PROXECTO
                    GROUP BY NumDepartControla
                ) t
            )
        """;
        List<DepartamentoDTO> res = new ArrayList<>();
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                res.add(new DepartamentoDTO(rs.getInt(1), rs.getString(2)));
            }
        }
        return res;
    }
}
