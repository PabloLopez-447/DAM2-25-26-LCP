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
        String sql = "SELECT DISTINCT d.NumDepartamento,\n" +
                "                d.NomeDepartamento,\n" +
                "                e.Nome,' ' ,\n" +
                "                e.Apelido1,' ' ,\n" +
                "                COALESCE(e.Apelido2, '') AS NOMECOMPLETO\n" +
                "FROM departamento d\n" +
                "         INNER JOIN empregado e\n" +
                "                    ON e.NSS = d.NSSDirector\n" +
                "         INNER JOIN proxecto p\n" +
                "                    ON d.NumDepartamento = p.NumDepartControla;"; //corregir consulta nga
        try{
            return GestorConexion.ejecutarConsulta(con, sql);
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

}
