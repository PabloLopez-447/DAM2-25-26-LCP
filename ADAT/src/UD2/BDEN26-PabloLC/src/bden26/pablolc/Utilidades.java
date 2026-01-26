//package bden26.pablolc;
//
//import java.sql.*;
////Pablo López Couso DNI: 77550221V
//
//public class Utilidades {
//
//    public static ResultSet ejecutarConsulta(Connection con, String consulta, Object... parametros) throws SQLException {
//        PreparedStatement stat = con.prepareStatement(consulta);
//
//        for (int i = 0; i < parametros.length; i++) {
//            stat.setObject(i + 1, parametros[i]);
//        }
//
//        return stat.executeQuery();
//    }
//
//    public static void ejecutarSentencia(Connection con, String consulta, Object... parametros) throws SQLException {
//        try (
//                PreparedStatement ps = con.prepareStatement(consulta)) {
//            setParametros(ps, parametros);
//            ps.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    private static void setParametros(PreparedStatement ps, Object... parametros) throws SQLException {
//        for (int i = 0; i < parametros.length; i++) {
//            ps.setObject(i + 1, parametros[i]);
//        }
//    }
//
//
//    public static int insertarFotoYretornarClave(Connection con, String sql, Object ... params) {
//        try (PreparedStatement ps = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS)){
//            setParametros(ps,params);
//            ps.executeUpdate();
//            try (ResultSet rs = ps.getGeneratedKeys()){
//                if (rs.next()) {
//                    return rs.getInt(1);
//                } else {
//                    throw new SQLException("No se generó la clave primaria");
//                }
//            }
//        } catch (SQLException e) {
//
//            throw new RuntimeException(e);
//        }
//    }
//}
