package UD2.empresa25.actividad4.persistencia;

import java.sql.*;

public class EmpresaDAO {

    private final Connection con;

    public EmpresaDAO(Connection con) {
        this.con = con;
    }

    // =========================
    // CREACIÓN DE OBJETOS SQL
    // =========================
    public void crearObjetos() throws SQLException {
        try (Statement st = con.createStatement()) {

            st.execute("""
                IF OBJECT_ID('pr_CambioDomicilio', 'P') IS NOT NULL DROP PROCEDURE pr_CambioDomicilio
            """);

            st.execute("""
                CREATE PROCEDURE pr_CambioDomicilio
                    @NSS VARCHAR(15),
                    @Rua VARCHAR(60),
                    @Numero VARCHAR(10),
                    @Piso VARCHAR(10),
                    @CP VARCHAR(10),
                    @Localidade VARCHAR(40)
                AS
                BEGIN
                    UPDATE EMPREGADO
                    SET Rua=@Rua, Numero=@Numero, Piso=@Piso, CodPostal=@CP, Localidade=@Localidade
                    WHERE NSS=@NSS
                END
            """);

            st.execute("""
                IF OBJECT_ID('pr_DatosProxectos', 'P') IS NOT NULL DROP PROCEDURE pr_DatosProxectos
            """);

            st.execute("""
                CREATE PROCEDURE pr_DatosProxectos
                    @Num INT,
                    @Nome VARCHAR(60) OUT,
                    @Lugar VARCHAR(40) OUT,
                    @Depto VARCHAR(60) OUT
                AS
                BEGIN
                    SELECT @Nome = p.NomeProxecto,
                           @Lugar = p.Lugar,
                           @Depto = d.NomeDepartamento
                    FROM PROXECTO p
                    JOIN DEPARTAMENTO d ON p.NumDepartControla = d.NumDepartamento
                    WHERE p.NumProxecto = @Num
                END
            """);

            st.execute("""
                IF OBJECT_ID('pr_DepartControlaProxec', 'P') IS NOT NULL DROP PROCEDURE pr_DepartControlaProxec
            """);

            st.execute("""
                CREATE PROCEDURE pr_DepartControlaProxec
                    @N INT
                AS
                BEGIN
                    SELECT d.NumDepartamento, d.NomeDepartamento, COUNT(*) AS Total
                    FROM DEPARTAMENTO d
                    JOIN PROXECTO p ON d.NumDepartamento = p.NumDepartControla
                    GROUP BY d.NumDepartamento, d.NomeDepartamento
                    HAVING COUNT(*) >= @N
                END
            """);

            st.execute("""
                IF OBJECT_ID('fn_nEmpDepart', 'FN') IS NOT NULL DROP FUNCTION fn_nEmpDepart
            """);

            st.execute("""
                CREATE FUNCTION fn_nEmpDepart(@NomeDepto VARCHAR(60))
                RETURNS INT
                AS
                BEGIN
                    DECLARE @n INT;
                    SELECT @n = COUNT(*)
                    FROM EMPREGADO e
                    JOIN DEPARTAMENTO d ON e.NumDepartamento = d.NumDepartamento
                    WHERE d.NomeDepartamento = @NomeDepto;
                    RETURN ISNULL(@n,0);
                END
            """);
        }
    }

    // =========================
    // EJERCICIO 1
    // =========================
    public void cambioDomicilio(String nss, String rua, String num, String piso, String cp, String loc) throws SQLException {
        try (CallableStatement cs = con.prepareCall("{call pr_CambioDomicilio(?,?,?,?,?,?)}")) {
            cs.setString(1, nss);
            cs.setString(2, rua);
            cs.setString(3, num);
            cs.setString(4, piso);
            cs.setString(5, cp);
            cs.setString(6, loc);
            cs.execute();
        }
    }

    // =========================
    // EJERCICIO 2
    // =========================
    public String[] datosProxecto(int num) throws SQLException {
        try (CallableStatement cs = con.prepareCall("{call pr_DatosProxectos(?,?,?,?)}")) {
            cs.setInt(1, num);
            cs.registerOutParameter(2, Types.VARCHAR);
            cs.registerOutParameter(3, Types.VARCHAR);
            cs.registerOutParameter(4, Types.VARCHAR);
            cs.execute();
            return new String[]{
                    cs.getString(2),
                    cs.getString(3),
                    cs.getString(4)
            };
        }
    }

    // =========================
    // EJERCICIO 3
    // =========================
    public boolean departamentosConNProxectos(int n) throws SQLException {
        try (CallableStatement cs = con.prepareCall("{call pr_DepartControlaProxec(?)}")) {
            cs.setInt(1, n);
            boolean hasRS = cs.execute();
            if (hasRS) {
                try (ResultSet rs = cs.getResultSet()) {
                    while (rs.next()) {
                        System.out.printf("%d - %s (%d)%n",
                                rs.getInt(1),
                                rs.getString(2),
                                rs.getInt(3));
                    }
                }
            }
            return hasRS;
        }
    }

    // =========================
    // EJERCICIO 4
    // =========================
    public int numeroEmpDepartamento(String nome) throws SQLException {
        try (CallableStatement cs = con.prepareCall("{? = call fn_nEmpDepart(?)}")) {
            cs.registerOutParameter(1, Types.INTEGER);
            cs.setString(2, nome);
            cs.execute();
            return cs.getInt(1);
        }
    }
}
