package UD2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
}