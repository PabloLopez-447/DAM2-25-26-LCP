package UD2;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            Connection c;
            if ((c = GestorConexion.getConnection(TipoSGBD.SQLITE, "C:/sqlite3/bdempresa25.db", "root", "abc123.")) != null) {
                System.out.println("se conecto");
            } else {
                System.out.println("no se conecto");
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
