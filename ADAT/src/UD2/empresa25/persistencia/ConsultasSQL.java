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
