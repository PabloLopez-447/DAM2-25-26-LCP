package bden26.pablolc.persistencia;
import bden26.pablolc.Clases.Artistica;
import bden26.pablolc.Clases.Documental;
import bden26.pablolc.Clases.Fotografia;
import bden26.pablolc.Utilidades;

import java.sql.*;
import java.util.List;

//Pablo López Couso DNI: 77550221V

public class FotografosDAO {
    Connection con;

    public FotografosDAO(Connection con) {
        this.con = con;
    }

    // Ejercicio 2
    public boolean crearTablas(){
        try (Statement st = con.createStatement()) {

            DatabaseMetaData meta = con.getMetaData();

            for (String tabla : new String[]{"COLABORACION", "LABORATORIO"}) {
                ResultSet rs = meta.getTables(null, null, tabla, null);
                if (rs.next()) {
                    st.addBatch("DROP TABLE " + tabla);
                }
            }

            st.addBatch("""
            CREATE TABLE LABORATORIO(
                CODIGOLAB INT IDENTITY(1,1),
                NOMBRELAB VARCHAR(60) UNIQUE,
                ANIOINAUGURACION INT,
                
                CONSTRAINT PK_LAB PRIMARY KEY (CODIGOLAB),
                CONSTRAINT UQ_NOMBRE_LAB UNIQUE (NOMBRELAB)
            )   
        
        """);

            st.addBatch("""
            CREATE TABLE COLABORACION(
                CODFOTOGRAFO INT,
                CODLAB INT,
                FECHAINI DATE,
                FECHAFIN DATE,
                
                CONSTRAINT PK_COLAB PRIMARY KEY (CODFOTOGRAFO, CODLAB, FECHAINI),
                CONSTRAINT FK_CODFOTOGRAFO FOREIGN KEY (CODFOTOGRAFO) REFERENCES FOTOGRAFO(CODIGO),
                CONSTRAINT FK_CODLAB FOREIGN KEY (CODLAB) REFERENCES LABORATORIO(CODIGOLAB)
            )
        """);
            st.executeBatch();
            return true;
    } catch (SQLException e) {
            return false;
        }
    }

    //Ejercicio 2
    public boolean insertarFotos(String nombreFotografo, String nombreExposicion, List<Fotografia> fotos){
        if (existeFotografo(nombreFotografo) && existeExposicion(nombreExposicion)){

            try {
                con.setAutoCommit(false);

                for (Fotografia f : fotos){
                    f.setConFotografo(obtenerClaveFotografo(nombreFotografo));
                    f.setCodExposicion(obtenerClaveExposicion(nombreExposicion));
                    int cod = Utilidades.insertarFotoYretornarClave(con, "INSERT INTO FOTOGRAFIA " +
                                    "(NOME, MEDIDAS, DATA, COD_FOTOGRAFO, COD_EXPOSICION, COLOR)" +
                                    "VALUES(?,?,?,?,?,?)", f.getNome(), f.getMedidas(),f.getData(),  f.getConFotografo()
                            , f.getCodExposicion(), f.getColor());
                    f.setCodigo(cod);
                    if (f instanceof Artistica a){
                        Utilidades.ejecutarSentencia(con, """
                                INSERT INTO ARTISTICA (CODIGO, ENCUADRE, COMPOSICION)
                                VALUES(?,?,?)
                                """, f.getCodigo(), a.getEncuadre(), a.getComposicion());
                    }
                    if (f instanceof Documental d){
                        Utilidades.ejecutarSentencia(con, """
                                INSERT INTO DOCUMENTAL (CODIGO, TIPO)
                                VALUES(?,?)
                                """, f.getCodigo(), d.getTipo());
                    }
                }
                int nFotos;

                try (CallableStatement cs = con.prepareCall("{? = call fn_NFotografias(?)}")){
                    cs.registerOutParameter(1, Types.INTEGER);
                    cs.setString(2, nombreFotografo);
                    cs.execute();

                    nFotos = cs.getInt(1);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                Utilidades.ejecutarSentencia(con,"""
                        UPDATE FOTOGRAFO SET NUMFOTOGRAFIAS = ? WHERE CODIGO = ?
                        """, nFotos, obtenerClaveFotografo(nombreFotografo));

                con.commit();
                return true;

            } catch (SQLException e) {
                try {
                    con.rollback();
                    return false;
                } catch (SQLException ignored) {
                }
            } finally {
                try {
                    con.setAutoCommit(true);
                } catch (SQLException ignored) {
                }
            }

        }
        return false;
    }

    public boolean existeFotografo(String nombreFotografo){

        String sql = """
                SELECT * FROM FOTOGRAFO WHERE NOME = ?
                """;

        try(PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, nombreFotografo);

            ResultSet rs = ps.executeQuery();
            return rs.next();

        }catch (SQLException e){
            System.out.println("No existe el fotografo");
            return false;
        }
    }

    public boolean existeExposicion(String nombreExposicion){

        String sql = """
                SELECT * FROM EXPOSICION WHERE NOME = ?
                """;

        try(PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, nombreExposicion);

            ResultSet rs = ps.executeQuery();
            return rs.next();

        }catch (SQLException e){
            System.out.println("No existe la exposicion");
            return false;
        }
    }

    public int obtenerClaveFotografo(String nombreFotografo){
        ResultSet rs = null;
        try {
            rs = Utilidades.ejecutarConsulta(con, "SELECT CODIGO FROM FOTOGRAFO WHERE NOME = ?", nombreFotografo);
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public int obtenerClaveExposicion(String nombreExposicion){
        try {
            ResultSet rs = rs = Utilidades.ejecutarConsulta(con, "SELECT CODIGO FROM EXPOSICION WHERE NOME = ?", nombreExposicion);
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    //Ejercicio 3
    public int moverFotos(String nombreNuevaExposicion, String nombreViejaExposicion){
        String sql = """
                SELECT COD_EXPOSICION FROM FOTOGRAFIA WHERE COD_EXPOSICION = ?
                """;

        System.out.println(msgPreUpdate(nombreNuevaExposicion, nombreViejaExposicion));

        try (PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)){
            con.setAutoCommit(false);
            ps.setInt(1, obtenerClaveExposicion(nombreViejaExposicion));

            ResultSet rs = ps.executeQuery();

            if (!rs.next()) return -2;
            rs.beforeFirst();

            while (rs.next()) {
                rs.updateDouble("COD_EXPOSICION", obtenerClaveExposicion(nombreNuevaExposicion));
                rs.updateRow();
            }
            con.commit();
            return 1;
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            return -99;
        }
        finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public String msgPreUpdate(String nombreNuevaExposicion, String nombreViejaExposicion){
        String msg = "";
        String localidade = "";
//        String datos = "";

        try (CallableStatement cs1 = con.prepareCall("{call pr_LocalidadProvincia(?,?)}")){
            cs1.setString(1,nombreViejaExposicion);
            cs1.registerOutParameter(2,Types.VARCHAR);
            cs1.execute();
            localidade = cs1.getString(2);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

//        try(CallableStatement cs2 = con.prepareCall("{call pr_datosFotos(?, ?)}")){
//            cs2.setString(1,nombreViejaExposicion);
//            cs2.registerOutParameter(2, Types.VARCHAR);
//            cs2.execute();
//            datos = cs2.getString(2);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

        msg = "NOMBRE DE LA EXPOSICIÓN: " + nombreViejaExposicion + " " + localidade + "\n";
//        msg += datos + "\n";
//        msg += "SE TRASLADAN DE " + nombreViejaExposicion + " a " + nombreNuevaExposicion;

        return msg;
    }

}
