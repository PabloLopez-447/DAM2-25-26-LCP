package UD2.empresa25;

import UD2.empresa25.eva.actividad4.utiles.TipoSGBD;
import UD2.empresa25.eva.actividad4.utiles.Utilidades;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class test {

    public static void imprimirDept(String nombre){
        Connection con = Utilidades.getConnection(TipoSGBD.SQLSERVER, "BDEMPRESA25", "sa", "abc123.");

        String sql = """
                SELECT NumDepartamento, NSSDirector FROM DEPARTAMENTO WHERE NomeDepartamento = ?
                """;

        try (PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1,nombre);

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                System.out.println(rs.getString(1));
                System.out.println(rs.getString(2));
            }
        } catch (SQLException e){

        }
    }

    public static void main(String[] args) {
        imprimirDept("PERSOAL");
    }
}
