package UD2;

import java.sql.*;

public class GestorConexion {

    public static Connection getConnection(TipoSGBD tipo, String baseDatos, String usuario, String pass) throws ClassNotFoundException, SQLException {

//        String driverClass = switch (tipo) {
//            case SQLSERVER -> "com.microsoft.sqlserver.jdbc.SQLServerDriver" ;
//            case MYSQL -> "com.mysql.cj.jdbc.Driver";
//            case SQLITE ->  "org.sqlite.JDBC";
//        };
//
//        Class.forName(driverClass);

        String url;
        url = switch (tipo) {
            case SQLSERVER ->
                    "jdbc:sqlserver://localhost:1433;" + "databaseName=" + baseDatos + ";" + "encrypt=true;" + "trustServerCertificate=true";

            case SQLITE -> "jdbc:sqlite:" + baseDatos;
            case MYSQL -> "jdbc:mysql://localhost:3306/" + baseDatos;
        };

        if (tipo == TipoSGBD.SQLITE) {
            return DriverManager.getConnection(url);
        } else {
            return DriverManager.getConnection(url, usuario, pass);
        }
    }

    public static void  cerrarConexion(Connection connection) throws ClassNotFoundException, SQLException {
        connection.close();
    }

    public static ResultSet ejecutarConsulta(Connection con, String consulta, Object... parametros) throws ClassNotFoundException, SQLException {
        PreparedStatement sentencia = con.prepareStatement(consulta);
        for (int i = 0; i < parametros.length; i++) {
            sentencia.setObject(i + 1, parametros[i]);
        }
        return sentencia.executeQuery();
    }

    public static void ejecutarSentencia(Connection con, String sentencia, Object... parametros) throws ClassNotFoundException, SQLException {
        try (PreparedStatement ps = con.prepareStatement(sentencia)) {
            setParametros(ps, parametros);
            ps.executeUpdate();
        }
    }

    public static void setParametros(PreparedStatement ps, Object... parametros) throws SQLException {
        for (int i = 0; i < parametros.length; i++) {
            ps.setObject(i + 1, parametros[i]);
        }
    }
}