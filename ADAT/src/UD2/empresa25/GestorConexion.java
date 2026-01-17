package UD2.empresa25;

import java.sql.*;

public class GestorConexion {

    public static Connection getConnection(TipoSGBD tipo, String baseDatos, String usuario, String pass) throws ClassNotFoundException, SQLException {

        // String driverClass = switch (tipo) {
        // case SQLSERVER -> "com.microsoft.sqlserver.jdbc.SQLServerDriver" ;
        // case MYSQL -> "com.mysql.cj.jdbc.Driver";
        // case SQLITE -> "org.sqlite.JDBC";
        // };
        //
        // Class.forName(driverClass);

        String url;
        url = switch (tipo) {
            case SQLSERVER ->
                    "jdbc:sqlserver://localhost:1433;" + "databaseName=" + baseDatos + ";" + "encrypt=true;" + "trustServerCertificate=true";

            case SQLITE -> "jdbc:sqlite:" + baseDatos;
            case MYSQL -> "jdbc:mysql://localhost:3306/" + baseDatos + "?serverTimeZone=UTC";
            default -> throw new UnsupportedOperationException("Error al obtener conexion");
        };

        if (tipo == TipoSGBD.SQLITE) {
            Connection con = DriverManager.getConnection(url);
            try (Statement st = con.createStatement()){
                st.execute("PRAGMA foreign_keys=ON;");
            }
            return con;
        } else {
            return DriverManager.getConnection(url, usuario, pass);
        }
    }

    public static ResultSet ejecutarConsulta(Connection con, String sqlConsulta, Object... parametros) throws SQLException {
        PreparedStatement stmt = con.prepareStatement(sqlConsulta);
        for (int i = 0; i < parametros.length; i++) {
            stmt.setObject(i + 1, parametros[i]);
        }
        return stmt.executeQuery();
    }

    public static void borrarTablas(Connection con, String... tablas) throws SQLException {
        try {
            con.setAutoCommit(false);
            try (Statement st = con.createStatement()) {
                for (String tabla : tablas) {
                    if (tablaExiste(con, tabla)) {
                        st.addBatch("DROP TABLE " + tabla);
                    }
                }
                st.executeBatch();
                con.commit();
            } catch (SQLException ex) {
                con.rollback();
            }
        } finally {
            con.setAutoCommit(true);
        }
    }

    private static boolean tablaExiste(Connection con, String tabla) {
        try (ResultSet rs = con.getMetaData().getTables(null, null, tabla, null)) {
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void ejecutarLoteTransaccion(Connection con, String... sqlSentencias) throws SQLException {
        try {
            con.setAutoCommit(false);
            try (Statement st = con.createStatement()) {
                for (String parametro : sqlSentencias) {
                    st.addBatch(parametro);
                }
                st.executeBatch();
                con.commit();
            } catch (SQLException e) {
                con.rollback();
            }
        } finally {
            con.setAutoCommit(true);
        }
    }

    public static String obtenerMetadatos(Connection con) throws SQLException {
        DatabaseMetaData meta = con.getMetaData();
        StringBuilder sb = new StringBuilder();
        ResultSet rs = meta.getTables(null, null, null, new String[]{"TABLE"});


        while (rs.next()) {
            sb.append(rs.getString("TABLE_NAME"));
            sb.append(" ");
        }
        return sb.toString();
    }
}

