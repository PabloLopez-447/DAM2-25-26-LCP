package UD2.empresa25.eva.actividad1.logica;

import UD2.empresa25.eva.actividad1.model.Departamento;
import UD2.empresa25.eva.actividad1.utiles.Utilidades;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class GestorEmpresa {

    // estándar
    // EJERCICIO 6
    // CORRECCION: no se debe devolver ResultSet, se transforma en objetos
    public static List<Departamento> obtenerDepartamentos(Connection con) {

        List<Departamento> lista = new ArrayList<>();
        String sql = "SELECT NumDepartamento, NomeDepartamento, NSSDirector FROM DEPARTAMENTO";

        try (ResultSet rs = Utilidades.ejecutarConsulta(con, sql)) {

            while (rs.next()) {
                Departamento d = new Departamento(
                        rs.getInt("NumDepartamento"),
                        rs.getString("NomeDepartamento"),
                        rs.getString("NSSDirector")
                );
                lista.add(d);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }

    // tiene q validar que sea unico
    public static boolean insertarProyecto(Connection con,
                                           int numero,
                                           String nombre,
                                           String lugar,
                                           int departamento) {

        try (PreparedStatement check =
                     // el ? es un placeholder
                     con.prepareStatement(
                             "SELECT COUNT(*) FROM PROXECTO WHERE NomeProxecto = ?")) {

            check.setString(1, nombre);
            ResultSet resultados = check.executeQuery();
            resultados.next();

            if (resultados.getInt(1) > 0) { // ya existe
                System.out.println("sout desde lógica, el proyecto ya existe");
                return false;
            }

            PreparedStatement insert = con.prepareStatement(
                    "INSERT INTO PROXECTO VALUES (?, ?, ?, ?)"
            );

            //lo metes en la columa en la que vaya
            insert.setInt(1, numero);
            insert.setString(2, nombre);
            insert.setString(3, lugar);
            insert.setInt(4, departamento);

            insert.executeUpdate();
            return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //7
    /* * Métodos para obter información sobre as táboas, columnas, claves primarias, foráneas, ...
        ▪ ResultSet getTables (String catálogo, String esquemaPatron, String táboaPatron, String [] tipoTáboas)
            throws SQLException.
             Recupera unha descrición das táboas dispoñibles no catálogo dado.
             Os tipos de táboas son: "TABLE", "VIEW", "SYSTEM TABLE", "GLOBAL TEMPORARY", "LOCAL TEMPORARY", "ALIAS", "SYNONYM". Ao poñer null, devolveranos todos os tipos de táboas.
             No argumento catálogo ou esquema pódese poñer null.
             Neste caso referímonos ao actual.
             As columnas do ResultSet devoltas de uso máis común son:
             – 1. TABLE_CAT String => Catálogo da táboa (pode ser nulo).
             – 2. TABLE_SCHEM String => Esquema da táboa (pode ser nulo).
             – 3. TABLE_NAME String => Nome da táboa.
             – 4. TABLE_TYPE String => Tipo de táboa
             – 5. REMARKS String => Comentario sobre a táboa. *
                    Exemplo: System.out.println(">>>Táboas existentes:"); String patron = "%";
                    //listamos todas as táboas que comecen por C String tipos[] = new String[2]; tipos[0] = "TABLE";
                    //táboas de usuario tipos[1] = "SYSTEM TABLE";
                    //táboas do sistema ResultSet taboas = metadatos.getTables(null, null, patron, tipos); while (taboas.next()) {
                    //Por cada táboa obtemos o seu nome e tipo
                        System.out.println(" Nome:"+ taboas.getString("TABLE_NAME")+ "
                        Tipo:" +taboas.getString("TABLE_TYPE")+ "
                        Esquema:"+ taboas.getString("TABLE_SCHEM")+ "
                        Catalogo: " +taboas.getString("TABLE_CAT")); } * */
    public static void crearTablaFamiliares(Connection con) {

        try (Statement st = con.createStatement()) {

            DatabaseMetaData meta = con.getMetaData();
            ResultSet rs = meta.getTables(null, null, "FAMILIAR", null);

            // como la comprobacion de esto puede ser nulo, igual no hace falta entrar al next
            if (rs.next()) {
                st.executeUpdate("DROP TABLE FAMILIAR");
            }

            // CORRECCION: restriccion check
            st.executeUpdate("""
                CREATE TABLE FAMILIAR (
                    NSS_EMPLEADO VARCHAR(15) NOT NULL,
                    NUM_INTERNO INT NOT NULL,
                    NSS_FAMILIAR VARCHAR(15) NOT NULL,
                    NOME VARCHAR(25) NOT NULL,
                    APELIDOS VARCHAR(50) NOT NULL,
                    DATA_NACEMENTO DATE,
                    PARENTESCO VARCHAR(20),
                    SEXO CHAR(1) DEFAULT 'M',

                    CONSTRAINT PK_FAMILIAR
                        PRIMARY KEY (NSS_EMPLEADO, NUM_INTERNO),

                    CONSTRAINT UQ_FAMILIAR_NSS
                        UNIQUE (NSS_FAMILIAR),

                    CONSTRAINT FK_FAMILIAR_EMPLEADO
                        FOREIGN KEY (NSS_EMPLEADO)
                        REFERENCES EMPREGADO(NSS),

                    CONSTRAINT CK_FAMILIAR_SEXO
                        CHECK (SEXO IN ('H','M'))
                )
            """);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // coches, las 3 tablas tienen que crearse o no crearse ninguna
    public static void crearTablasVehiculos(Connection con) {

        try (Statement st = con.createStatement()) {

            DatabaseMetaData meta = con.getMetaData();

            for (String tabla : new String[]{"VEHICULO_RENTING", "VEHICULO_PROPIO", "VEHICULO"}) {
                ResultSet rs = meta.getTables(null, null, tabla, null);
                if (rs.next()) {
                    st.addBatch("DROP TABLE " + tabla);
                }
            }

            st.addBatch("""
                CREATE TABLE VEHICULO (
                    CODIGO INT IDENTITY(1,1),
                    MATRICULA CHAR(10) NOT NULL,
                    MARCA VARCHAR(50) NOT NULL,
                    MODELO VARCHAR(50) NOT NULL,
                    COMBUSTIBLE CHAR(1) NOT NULL,

                    CONSTRAINT PK_VEHICULO PRIMARY KEY (CODIGO),
                    CONSTRAINT UQ_VEHICULO_MATRICULA UNIQUE (MATRICULA),
                    CONSTRAINT CK_VEHICULO_COMBUSTIBLE CHECK (COMBUSTIBLE IN ('G','D','E'))
                )
            """);

            // CORRECCION: IDENTITY, NOT NULL y CHECK
            st.addBatch("""
                CREATE TABLE VEHICULO_PROPIO (
                    CODIGO INT,
                    DATA_COMPRA DATE,
                    PREZO_PAGADO DECIMAL(10,2),

                    CONSTRAINT PK_VEHICULO_PROPIO PRIMARY KEY (CODIGO),
                    CONSTRAINT FK_VP_VEHICULO
                        FOREIGN KEY (CODIGO)
                        REFERENCES VEHICULO(CODIGO)
                )
            """);

            st.addBatch("""
                CREATE TABLE VEHICULO_RENTING (
                    CODIGO INT,
                    DATA_INICIO DATE,
                    PREZO_MENSUAL DECIMAL(10,2),
                    MESES_CONTRATADOS INT,

                    CONSTRAINT PK_VEHICULO_RENTING PRIMARY KEY (CODIGO),
                    CONSTRAINT FK_VR_VEHICULO
                        FOREIGN KEY (CODIGO)
                        REFERENCES VEHICULO(CODIGO)
                )
            """);

            st.executeBatch(); // si una falla ya no hace ninguna

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
