//package bden26.pablolc;
//
//import java.sql.*;
//
//public class GestorConexion {
//
//    public static Connection getConnection(String baseDatos, String usuario, String pass) throws ClassNotFoundException, SQLException {
//        String url;
//        url = "jdbc:sqlserver://localhost:1433;" + "databaseName=" + baseDatos + ";" + "encrypt=true;" + "trustServerCertificate=true";
//        return DriverManager.getConnection(url, usuario, pass);
//    }
//}
//
