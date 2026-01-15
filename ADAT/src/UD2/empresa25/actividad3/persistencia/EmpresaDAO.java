package UD2.empresa25.actividad3.persistencia;

import UD2.empresa25.actividad3.clases.*;
import UD2.empresa25.otros.TipoSGBD;
import UD2.empresa25.persistencia.GestorConexion;

import java.sql.*;
import java.util.List;

public class EmpresaDAO {

    private final String bd = "BDEMPRESA25";
    private final String user = "sa";
    private final String pass = "abc123.";

    private Connection getCon() throws Exception {
        return GestorConexion.getConnection(TipoSGBD.SQLSERVER, bd, user, pass);
    }

    // =========================
    // EJERCICIO 1 → FAMILIAR
    // =========================
    public void insertarFamiliar(Familiar f) throws Exception {
        try (Connection con = getCon()) {

            String sqlNum = "SELECT ISNULL(MAX(NUM_FAM),0)+1 FROM FAMILIAR WHERE NSS_EMP=?";
            int num;
            try (PreparedStatement ps = con.prepareStatement(sqlNum)) {
                ps.setString(1, f.getNssEmpleado());
                ResultSet rs = ps.executeQuery();
                rs.next();
                num = rs.getInt(1);
            }

            f.setNumFam(num);

            String sqlDup = "SELECT COUNT(*) FROM FAMILIAR WHERE NSS_EMP=? AND NSS_FAM=?";
            try (PreparedStatement ps = con.prepareStatement(sqlDup)) {
                ps.setString(1, f.getNssEmpleado());
                ps.setString(2, f.getNssFam());
                ResultSet rs = ps.executeQuery();
                rs.next();
                if (rs.getInt(1) > 0)
                    throw new SQLException("Ese familiar ya está registrado para ese empleado.");
            }

            String insert = """
                INSERT INTO FAMILIAR
                (NSS_EMP, NUM_FAM, NSS_FAM, NOMBRE, APELLIDOS, FECHA_NAC, PARENTESCO, SEXO)
                VALUES (?,?,?,?,?,?,?,?)
                """;

            try (PreparedStatement ps = con.prepareStatement(insert)) {
                ps.setString(1, f.getNssEmpleado());
                ps.setInt(2, f.getNumFam());
                ps.setString(3, f.getNssFam());
                ps.setString(4, f.getNombre());
                ps.setString(5, f.getApellidos());
                ps.setDate(6, Date.valueOf(f.getFechaNac()));
                ps.setString(7, f.getParentesco());
                ps.setString(8, String.valueOf(f.getSexo()));
                ps.executeUpdate();
            }
        }
    }

    // =========================
    // EJERCICIO 2 → VEHÍCULOS
    // =========================
    public int insertarVehiculo(Vehiculo v) throws Exception {
        try (Connection con = getCon()) {
            con.setAutoCommit(false);

            String insertVeh = """
                INSERT INTO VEHICULO (MATRICULA, MARCA, MODELO, COMBUSTIBLE)
                VALUES (?,?,?,?)
                """;

            int idGenerado;

            try (PreparedStatement ps = con.prepareStatement(insertVeh, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, v.getMatricula());
                ps.setString(2, v.getMarca());
                ps.setString(3, v.getModelo());
                ps.setString(4, v.getCombustible());
                ps.executeUpdate();

                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                idGenerado = rs.getInt(1);
                v.setId(idGenerado);
            }

            if (v instanceof VehiculoPropio vp) {
                String sql = "INSERT INTO VEHICULO_PROPIO (ID, FECHA_COMPRA, PRECIO) VALUES (?,?,?)";
                try (PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setInt(1, idGenerado);
                    ps.setDate(2, Date.valueOf(vp.getFechaCompra()));
                    ps.setDouble(3, vp.getPrecio());
                    ps.executeUpdate();
                }
            } else if (v instanceof VehiculoRenting vr) {
                String sql = "INSERT INTO VEHICULO_RENTING (ID, FECHA_INICIO, PRECIO_MENSUAL, MESES) VALUES (?,?,?,?)";
                try (PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setInt(1, idGenerado);
                    ps.setDate(2, Date.valueOf(vr.getFechaInicio()));
                    ps.setDouble(3, vr.getPrecioMensual());
                    ps.setInt(4, vr.getMeses());
                    ps.executeUpdate();
                }
            }

            con.commit();
            return idGenerado;
        }
    }

    // =========================
    // EJERCICIO 3
    // =========================
    public void cambiarDepartamentoProyecto(String nombreDepto, String nombreProyecto) throws Exception {
        try (Connection con = getCon()) {

            String sqlDepto = "SELECT NumDepartamento FROM DEPARTAMENTO WHERE NomeDepartamento=?";
            Integer numDepto = null;
            try (PreparedStatement ps = con.prepareStatement(sqlDepto)) {
                ps.setString(1, nombreDepto);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) numDepto = rs.getInt(1);
            }
            if (numDepto == null)
                throw new SQLException("El departamento no existe.");

            String update = "UPDATE PROXECTO SET NumDepartControla=? WHERE NomeProxecto=?";
            try (PreparedStatement ps = con.prepareStatement(update)) {
                ps.setInt(1, numDepto);
                ps.setString(2, nombreProyecto);
                if (ps.executeUpdate() == 0)
                    throw new SQLException("El proyecto no existe.");
            }
        }
    }

    // =========================
    // EJERCICIO 4
    // =========================
    public void eliminarProyecto(int numProyecto) throws Exception {
        try (Connection con = getCon()) {
            con.setAutoCommit(false);

            String info = "SELECT * FROM PROXECTO WHERE NumProxecto=?";
            try (PreparedStatement ps = con.prepareStatement(info)) {
                ps.setInt(1, numProyecto);
                ResultSet rs = ps.executeQuery();
                if (!rs.next())
                    throw new SQLException("El proyecto no existe.");

                System.out.println("PROYECTO: " + rs.getString("NomeProxecto"));
            }

            String emp = """
                SELECT e.NSS, e.Nome, e.Apelido1, e.Apelido2
                FROM EMPREGADO e
                JOIN EMPREGADO_PROXECTO ep ON e.NSS = ep.NSSEmpregado
                WHERE ep.NumProxecto=?
                """;

            try (PreparedStatement ps = con.prepareStatement(emp)) {
                ps.setInt(1, numProyecto);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    System.out.println(rs.getString(1) + " " +
                            rs.getString(2) + " " +
                            rs.getString(3) + " " +
                            rs.getString(4));
                }
            }

            try (PreparedStatement ps = con.prepareStatement("DELETE FROM EMPREGADO_PROXECTO WHERE NumProxecto=?")) {
                ps.setInt(1, numProyecto);
                ps.executeUpdate();
            }

            try (PreparedStatement ps = con.prepareStatement("DELETE FROM PROXECTO WHERE NumProxecto=?")) {
                ps.setInt(1, numProyecto);
                ps.executeUpdate();
            }

            con.commit();
        }
    }

    // =========================
    // EJERCICIO 5
    // =========================
    public int incrementarSalarios(double inc, List<String> nss) throws Exception {
        try (Connection con = getCon()) {
            con.setAutoCommit(false);
            String sql = "UPDATE EMPREGADOFIXO SET Salario = Salario + ? WHERE NSS=?";
            int cont = 0;

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                for (String s : nss) {
                    ps.setDouble(1, inc);
                    ps.setString(2, s);
                    ps.addBatch();
                }
                int[] r = ps.executeBatch();
                for (int i : r) cont += i;
            }

            con.commit();
            return cont;
        }
    }

    // =========================
    // EJERCICIO 6
    // =========================
    public boolean existeProyecto(int num, String nombre) throws Exception {
        try (Connection con = getCon()) {
            String sql = "SELECT COUNT(*) FROM PROXECTO WHERE NumProxecto=? OR NomeProxecto=?";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, num);
                ps.setString(2, nombre);
                ResultSet rs = ps.executeQuery();
                rs.next();
                return rs.getInt(1) > 0;
            }
        }
    }

    public boolean existeDepartamento(int num) throws Exception {
        try (Connection con = getCon()) {
            String sql = "SELECT COUNT(*) FROM DEPARTAMENTO WHERE NumDepartamento=?";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, num);
                ResultSet rs = ps.executeQuery();
                rs.next();
                return rs.getInt(1) > 0;
            }
        }
    }

    public void insertarProyectoRS(Proxecto p) throws Exception {
        try (Connection con = getCon();
             Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {

            ResultSet rs = st.executeQuery("SELECT * FROM PROXECTO");

            rs.moveToInsertRow();
            rs.updateInt("NumProxecto", p.getNum());
            rs.updateString("NomeProxecto", p.getNome());
            rs.updateString("Lugar", p.getLugar());
            rs.updateInt("NumDepartControla", p.getNumDepartamento());
            rs.insertRow();
        }
    }

    // =========================
    // EJERCICIO 7
    // =========================
    public void incrementarSalariosDepartamento(double inc, int depto) throws Exception {
        try (Connection con = getCon();
             PreparedStatement ps = con.prepareStatement("""
                 SELECT ef.Salario
                 FROM EMPREGADOFIXO ef
                 JOIN EMPREGADO e ON ef.NSS = e.NSS
                 WHERE e.NumDepartamentoPertenece=?""",
                     ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {

            ps.setInt(1, depto);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                double s = rs.getDouble(1);
                rs.updateDouble(1, s + inc);
                rs.updateRow();
            }
        }
    }

    // =========================
    // EJERCICIO 8
    // =========================
    public ResultSet consultaScrollable(int min) throws Exception {
        Connection con = getCon();

        String sql = """
            SELECT e.NSS,
                   e.Nome + ' ' + e.Apelido1 + ' ' + ISNULL(e.Apelido2,'') AS NomeCompleto,
                   e.Localidade,
                   ef.Salario,
                   COUNT(ep.NumProxecto) AS NumProx
            FROM EMPREGADO e
            JOIN EMPREGADOFIXO ef ON e.NSS = ef.NSS
            JOIN EMPREGADO_PROXECTO ep ON e.NSS = ep.NSSEmpregado
            GROUP BY e.NSS, e.Nome, e.Apelido1, e.Apelido2, e.Localidade, ef.Salario
            HAVING COUNT(ep.NumProxecto) > ?
            """;

        PreparedStatement ps = con.prepareStatement(sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ps.setInt(1, min);
        return ps.executeQuery();
    }

    // Extra: borrar un empleado -> Reflexiva y todas las tablas en las que pueda ser referenciado
}
