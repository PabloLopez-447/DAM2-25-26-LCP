package UD2.empresa25.actividad3.persistencia;

import java.sql.*;
import java.util.List;

public class EmpresaDAO {

    private final Connection con;

    public EmpresaDAO(Connection con) {
        this.con = con;
    }

    // =========================
    // EJERCICIO 1 – FAMILIAR
    // =========================
    public void insertarFamiliar(String nssEmp, String nssFam, String nombre, String apellidos,
                                 Date fechaNac, String parentesco, char sexo) throws SQLException {

        String sqlNum = "SELECT ISNULL(MAX(NUM_FAM),0)+1 FROM FAMILIAR WHERE NSS_EMP = ?";
        int numFam;
        try (PreparedStatement ps = con.prepareStatement(sqlNum)) {
            ps.setString(1, nssEmp);
            ResultSet rs = ps.executeQuery();
            rs.next();
            numFam = rs.getInt(1);
        }

        String dup = "SELECT 1 FROM FAMILIAR WHERE NSS_EMP=? AND NSS_FAM=?";
        try (PreparedStatement ps = con.prepareStatement(dup)) {
            ps.setString(1, nssEmp);
            ps.setString(2, nssFam);
            if (ps.executeQuery().next()) {
                throw new SQLException("O familiar xa existe para ese empregado");
            }
        }

        String ins = """
                INSERT INTO FAMILIAR (NSS_EMP, NUM_FAM, NSS_FAM, NOMBRE, APELLIDOS, FECHA_NAC, PARENTESCO, SEXO)
                VALUES (?,?,?,?,?,?,?,?)
                """;
        try (PreparedStatement ps = con.prepareStatement(ins)) {
            ps.setString(1, nssEmp);
            ps.setInt(2, numFam);
            ps.setString(3, nssFam);
            ps.setString(4, nombre);
            ps.setString(5, apellidos);
            ps.setDate(6, fechaNac);
            ps.setString(7, parentesco);
            ps.setString(8, String.valueOf(sexo));
            ps.executeUpdate();
        }
    }

    // =========================
    // EJERCICIO 2 – VEHÍCULOS
    // =========================
    public int insertarVehiculoBase(String matricula, String marca, String modelo, String combustible) throws SQLException {
        String sql = "INSERT INTO VEHICULO (MATRICULA, MARCA, MODELO, COMBUSTIBLE) VALUES (?,?,?,?)";
        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, matricula);
            ps.setString(2, marca);
            ps.setString(3, modelo);
            ps.setString(4, combustible);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            return rs.getInt(1);
        }
    }

    public void insertarVehiculoPropio(int id, Date fecha, double precio) throws SQLException {
        String sql = "INSERT INTO VEHICULO_PROPIO (ID, FECHA_COMPRA, PRECIO) VALUES (?,?,?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.setDate(2, fecha);
            ps.setDouble(3, precio);
            ps.executeUpdate();
        }
    }

    public void insertarVehiculoRenting(int id, Date inicio, double precioMensual, int meses) throws SQLException {
        String sql = "INSERT INTO VEHICULO_RENTING (ID, FECHA_INICIO, PRECIO_MENSUAL, MESES) VALUES (?,?,?,?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.setDate(2, inicio);
            ps.setDouble(3, precioMensual);
            ps.setInt(4, meses);
            ps.executeUpdate();
        }
    }

    // =========================
    // EJERCICIO 3 – CAMBIAR DPTO PROXECTO
    // =========================
    public int cambiarDepartamentoProyecto(String nomeDepto, String nomeProx) throws SQLException {
        String sql = """
            UPDATE PROXECTO
            SET NumDepartControla = (SELECT NumDepartamento FROM DEPARTAMENTO WHERE NomeDepartamento = ?)
            WHERE NomeProxecto = ?
        """;
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nomeDepto);
            ps.setString(2, nomeProx);
            return ps.executeUpdate();
        }
    }

    // =========================
    // EJERCICIO 5 – BATCH SALARIOS
    // =========================
    public int incrementarSalarios(double inc, List<String> nss) throws SQLException {
        String sql = "UPDATE EMPREGADOFIXO SET Salario = Salario + ? WHERE NSS = ?";
        con.setAutoCommit(false);
        int total;
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            for (String s : nss) {
                ps.setDouble(1, inc);
                ps.setString(2, s);
                ps.addBatch();
            }
            int[] r = ps.executeBatch();
            total = r.length;
            con.commit();
        } catch (SQLException e) {
            con.rollback();
            throw e;
        } finally {
            con.setAutoCommit(true);
        }
        return total;
    }
}
