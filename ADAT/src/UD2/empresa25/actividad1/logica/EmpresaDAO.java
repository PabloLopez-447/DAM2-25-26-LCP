package UD2.empresa25.actividad1.logica;

import UD2.empresa25.actividad1.clases.Departamento;
import UD2.empresa25.actividad1.clases.Proyecto;
import UD2.empresa25.TipoSGBD;
import UD2.empresa25.ordenar.persistencia.GestorConexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpresaDAO {

    private Connection con;
    private TipoSGBD tipo;

    public EmpresaDAO(Connection con, TipoSGBD tipo) {
        this.con = con;
        this.tipo = tipo;
    }

    // EJERCICIO 6 → Mostrar departamentos
    public List<Departamento> obtenerDepartamentos() throws SQLException {

        List<Departamento> lista = new ArrayList<>();
        String sql = "SELECT * FROM DEPARTAMENTO";

        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Departamento(
                        rs.getInt("NumDepartamento"),
                        rs.getString("NomeDepartamento"),
                        rs.getString("NSSDirector")
                ));
            }
        }
        return lista;
    }

    // EJERCICIO 6 → Insertar proyecto si no existe
    public boolean insertarProyectoSiNoExiste(Proyecto p) throws SQLException {

        String comprobar = "SELECT COUNT(*) FROM PROXECTO WHERE NomeProxecto=?";
        String insertar = "INSERT INTO PROXECTO VALUES (?,?,?,?)";

        try (PreparedStatement ps = con.prepareStatement(comprobar)) {
            ps.setString(1, p.getNombre());
            ResultSet rs = ps.executeQuery();
            rs.next();

            if (rs.getInt(1) > 0) return false;
        }

        try (PreparedStatement ps = con.prepareStatement(insertar)) {
            ps.setInt(1, p.getNumProyecto());
            ps.setString(2, p.getNombre());
            ps.setString(3, p.getLugar());
            ps.setInt(4, p.getDepartamento());
            ps.executeUpdate();
        }
        return true;
    }

    // =========================
    // EJERCICIO 7 → FAMILIARES
    // =========================
    public void crearTablaFamiliares() throws SQLException {

        String drop = "DROP TABLE IF EXISTS FAMILIAR";

        String create = """
                CREATE TABLE FAMILIAR (
                    NSS_EMP VARCHAR(15),
                    NUM_FAM INTEGER,
                    NSS_FAM VARCHAR(15),
                    NOMBRE VARCHAR(50),
                    APELLIDOS VARCHAR(80),
                    FECHA_NAC DATE, my nigga 
                    PARENTESCO VARCHAR(30),
                    SEXO CHAR(1) DEFAULT 'M',
                    CONSTRAINT PK_FAMILIAR PRIMARY KEY (NSS_EMP, NUM_FAM),
                    CONSTRAINT CK_FAMILIAR_SEXO CHECK (SEXO IN ('H','M'))
                )
                """;


        try (Statement st = con.createStatement()) {
            st.execute(drop);
            st.execute(create);
        }
    }

    // =========================
    // EJERCICIO 7 → VEHÍCULOS
    // =========================
    public void crearTablasVehiculos() throws SQLException {

        activarFK();

        String vehiculo = switch (tipo) {
            case SQLSERVER -> """
                    CREATE TABLE VEHICULO (
                      ID INT IDENTITY(1,1) PRIMARY KEY,
                      MATRICULA VARCHAR(10) UNIQUE,
                      MARCA VARCHAR(30),
                      MODELO VARCHAR(30),
                      COMBUSTIBLE VARCHAR(20)
                    )
                    """;
            case MYSQL -> """
                    CREATE TABLE VEHICULO (
                      ID INT AUTO_INCREMENT PRIMARY KEY,
                      MATRICULA VARCHAR(10) UNIQUE,
                      MARCA VARCHAR(30),
                      MODELO VARCHAR(30),
                      COMBUSTIBLE VARCHAR(20)
                    )
                    """;
            case SQLITE -> """
                    CREATE TABLE VEHICULO (
                      ID INTEGER PRIMARY KEY AUTOINCREMENT,
                      MATRICULA TEXT UNIQUE,
                      MARCA TEXT,
                      MODELO TEXT,
                      COMBUSTIBLE TEXT
                    )
                    """;
        };

        String vehiculoPropio = """
                CREATE TABLE VEHICULO_PROPIO (
                    ID INTEGER PRIMARY KEY,
                    FECHA_COMPRA DATE,
                    PRECIO DECIMAL(10,2),
                    FOREIGN KEY (ID) REFERENCES VEHICULO(ID)
                )
                """;

        String vehiculoRenting = """
                CREATE TABLE VEHICULO_RENTING (
                    ID INTEGER PRIMARY KEY,
                    FECHA_INICIO DATE,
                    PRECIO_MENSUAL DECIMAL(8,2),
                    MESES INTEGER,
                    FOREIGN KEY (ID) REFERENCES VEHICULO(ID)
                )
                """;

        GestorConexion.ejecutarLoteTransaccion(con,
                "DROP TABLE IF EXISTS VEHICULO_RENTING",
                "DROP TABLE IF EXISTS VEHICULO_PROPIO",
                "DROP TABLE IF EXISTS VEHICULO",
                vehiculo,
                vehiculoPropio,
                vehiculoRenting
        );
    }

    // =========================
    // ACTIVAR CLAVES FORÁNEAS
    // =========================
    private void activarFK() throws SQLException {
        try (Statement st = con.createStatement()) {
            if (tipo == TipoSGBD.SQLITE) {
                st.execute("PRAGMA foreign_keys = ON");
            }
            if (tipo == TipoSGBD.MYSQL) {
                st.execute("SET FOREIGN_KEY_CHECKS=1");
            }
        }
    }
}

