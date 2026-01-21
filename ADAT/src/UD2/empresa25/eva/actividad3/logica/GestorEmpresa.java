package UD2.empresa25.eva.actividad3.logica;



import UD2.empresa25.eva.actividad2.model.*;
import UD2.empresa25.eva.actividad2.utiles.Utilidades;
import UD2.empresa25.eva.actividad4.dto.ResultadoBorrado;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GestorEmpresa {

    // region EJERCICIO 1:
    // inserta un familiar para un empleado
    // controla que no se repita y que el numero sea secuencial
    public static int insertarFamiliar(Connection con, Familiar f) {

        try {
            con.setAutoCommit(false);

            String sqlExiste = """
                    SELECT COUNT(*)
                    FROM FAMILIAR
                    WHERE NSSEmpregado = ? AND NSSFamiliar = ?
                    """;

            try (ResultSet rs = Utilidades.ejecutarConsulta(
                    con, sqlExiste,
                    f.getNssEmpregado(), f.getNssFamiliar())) {

                rs.next();
                if (rs.getInt(1) > 0) return -2; // ya existe
            }

            String sqlNumero = """
                    SELECT COALESCE(MAX(NumInterno),0)+1
                    FROM FAMILIAR
                    WHERE NSSEmpregado = ?
                    """;

            int num;
            try (ResultSet rs = Utilidades.ejecutarConsulta(
                    con, sqlNumero, f.getNssEmpregado())) {
                rs.next();
                num = rs.getInt(1);
            }

            f.setNumInterno(num);

            String sqlInsert = """
                    INSERT INTO FAMILIAR
                    (NSSEmpregado, NumInterno, NSSFamiliar, Nome, Apelidos,
                     DataNacemento, Parentesco, Sexo)
                    VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                    """;

            Utilidades.ejecutarSentencia(
                    con, sqlInsert,
                    f.getNssEmpregado(), f.getNumInterno(),
                    f.getNssFamiliar(), f.getNome(),
                    f.getApelidos(), f.getDataNacemento(),
                    f.getParentesco(), String.valueOf(f.getSexo())
            );

            con.commit();
            return 0;

        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException ignored) {
            }

            String msg = e.getMessage().toLowerCase();

            if (msg.contains("foreign")) return -1; // NSSEmpregado no existe
            if (msg.contains("check")) return -3;   // sexo mal
            if (msg.contains("unique") || msg.contains("primary")) return -2;

            System.out.println(msg);
            return -99; // error realmente inesperado

        } finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException ignored) {
            }
        }
    }
    //endregion EJERCICIO 1:

    // region EJERCICIO 2:
    public static int insertarVehiculo(Connection con, Vehiculo v) {

        try {
            con.setAutoCommit(false);

            String sqlExiste = """
                    SELECT COUNT(*) FROM VEHICULO WHERE Matricula = ?
                    """;

            try (ResultSet rs = Utilidades.ejecutarConsulta(
                    con, sqlExiste, v.getMatricula())) {
                rs.next();
                if (rs.getInt(1) > 0) return -2;
            }

            int id = Utilidades.insertarVehiculoYRetornarClave(
                    con,
                    """
                            INSERT INTO VEHICULO (Matricula, Marca, Modelo, Combustible)
                            VALUES (?, ?, ?, ?)
                            """,
                    v.getMatricula(), v.getMarca(),
                    v.getModelo(), String.valueOf(v.getCombustible())
            );

            v.setId(id);

            if (v instanceof VehiculoPropio vp) {
                Utilidades.ejecutarSentencia(
                        con,
                        """
                                INSERT INTO VEHICULO_PROPIO
                                (IDVehiculo, DataCompra, PrezoPagado)
                                VALUES (?, ?, ?)
                                """,
                        vp.getId(), vp.getDataCompra(), vp.getPrezoPagado()
                );
            } else if (v instanceof VehiculoRenting vr) {
                Utilidades.ejecutarSentencia(
                        con,
                        """
                                INSERT INTO VEHICULO_RENTING
                                (IDVehiculo, DataInicio, PrezoMensual, MesesContratados)
                                VALUES (?, ?, ?, ?)
                                """,
                        vr.getId(), vr.getDataInicio(),
                        vr.getPrezoMensual(), vr.getMesesContratados()
                );
            }

            con.commit();
            return 0;

        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException ignored) {
            }
            return -1;
        } finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException ignored) {
            }
        }
    }
    //endregion EJERCICIO 2:

    // region EJERCICIO 3:

    public static int cambiarDepartamentoProyecto(
            Connection con,
            String nomeDepartamento,
            String nomeProxecto) {
        //podría comprobarse que existiesen el departamento y el proyecto

        String sql = """
            UPDATE PROXECTO
            SET NumDepartControla =
                (SELECT NumDepartamento
                 FROM DEPARTAMENTO
                 WHERE NomeDepartamento = ?)
            WHERE NomeProxecto = ?
            """;


        try {
            Utilidades.ejecutarSentencia(
                    con,
                    sql,
                    nomeDepartamento,
                    nomeProxecto
            );

            System.out.println("estoy mal puesto, pero todo ok ");
            return 0;

        } catch (SQLException e) {
            System.out.println("error:" + e.getMessage());
            return -1;
        }
    }

    //endregion EJERCICIO 3:

    // region EJERCICIO 4:
    public static int eliminarProxecto(Connection con, int numProxecto) {

        try {
            String sql = "DELETE FROM PROXECTO WHERE NumProxecto = ?";
            int filas = con.prepareStatement(sql).executeUpdate();
            return filas == 0 ? -2 : 0;

        } catch (SQLException e) {
            if (e.getMessage().toLowerCase().contains("foreign"))
                return -3;
            return -1;
        }
    }
    //endregion EJERCICIO 4:

    // region EJERCICIO 5:
    public static int incrementarSalarios(
            Connection con, double inc, List<String> nss) {

        if (nss.isEmpty()) return -2;

        try {
            con.setAutoCommit(false);
            String sql = """
                    UPDATE EMPREGADOFIXO
                    SET Salario = Salario + ?
                    WHERE NSS = ?
                    """;

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                for (String s : nss) {
                    ps.setDouble(1, inc);
                    ps.setString(2, s);
                    ps.addBatch();
                }
                int[] r = ps.executeBatch();
                con.commit();
                return r.length;
            }

        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException ignored) {
            }
            return -1;
        } finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException ignored) {
            }
        }
    }
    //endregion EJERCICIO 5:

    // region EJERCICIO 6:
    public static int insertarProxecto(Connection con, Proxecto p) {

        try (Statement st = con.createStatement(
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE)) {

            ResultSet rs = st.executeQuery("SELECT * FROM PROXECTO");
            rs.moveToInsertRow();
            rs.updateInt("NumProxecto", p.getNumProxecto());
            rs.updateString("NomeProxecto", p.getNomeProxecto());
            rs.updateString("Lugar", p.getLugar());
            rs.updateInt("NumDepartControla", p.getNumDepartControla());
            rs.insertRow();
            return 0;

        } catch (SQLException e) {
            return -1;
        }
    }
    //endregion EJERCICIO 6:

    // region EJERCICIO 7:
    public static int incrementarSalarioDepartamento(
            Connection con, double inc, int dep) {

        String sql = """
                SELECT f.Salario
                FROM EMPREGADO e
                JOIN EMPREGADOFIXO f ON e.NSS = f.NSS
                WHERE e.NumDepartamentoPertenece = ?
                """;

        int afectados = 0;

        try (PreparedStatement ps = con.prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE)) {

            ps.setInt(1, dep);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) return -2;
            rs.beforeFirst();

            while (rs.next()) {
                rs.updateDouble("Salario", rs.getDouble("Salario") + inc);
                rs.updateRow();
                afectados++;
            }

            return afectados;

        } catch (SQLException e) {
            return -1;
        }
    }
    //endregion EJERCICIO 7:

    // region EJERCICIO 8:
    public static int consultaEmpregados(Connection con, int numProxectos) {

        String sql = """
                SELECT e.NSS,
                       e.Nome + ' ' + e.Apelido1 AS NomeCompleto
                FROM EMPREGADO e
                JOIN EMPREGADO_PROXECTO ep ON e.NSS = ep.NSSEmpregado
                GROUP BY e.NSS, e.Nome, e.Apelido1
                HAVING COUNT(*) > ?
                """;

        int filas = 0;

        try (PreparedStatement ps = con.prepareStatement(
                sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY)) {

            ps.setInt(1, numProxectos);
            ResultSet rs = ps.executeQuery();

            if (!rs.first()) return -2;

            rs.beforeFirst();
            while (rs.next()) {
                System.out.println(rs.getString("NomeCompleto"));
                filas++;
            }

            return filas;

        } catch (SQLException e) {
            return -1;
        }
    }
    //endregion EJERCICIO 8:

    //region EXTRA

    public static boolean existeEmpregado(Connection con, String nss) throws SQLException {
        String sql = """
                    SELECT 1
                    FROM EMPREGADO
                    WHERE NSS = ?
                """;

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nss);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next(); // true si existe al menos una fila
            }
        }
    }

    //no puedes contar las finlas con el ejecutarsentencia porque solo devuelve void
    public static boolean esEmpleadoFijo(Connection con, String nss) throws SQLException {
        String sql = """
            SELECT 1
            FROM EMPREGADOFIXO
            WHERE NSS = ?
            """;

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nss);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }


    //CORREGIDA FIRMA: public ResultadoBorrado borrarEmpregadoCompleto - String nss borrar, String nssSupervisorNuevo, String nssDirectorNuevo
    public static ResultadoBorrado borrarEmpregadoCompleto(
            Connection con,
            String nssBorrar,
            String nssSupervisorNuevo,
            String nssDirectorNuevo) {

        ResultadoBorrado resultado = new ResultadoBorrado();

        try {
            if (!existeEmpregado(con, nssBorrar))
                throw new Exception("O empregado a borrar non existe");

            if (!existeEmpregado(con, nssSupervisorNuevo))
                throw new Exception("O novo supervisor non existe");

            if (!existeEmpregado(con, nssDirectorNuevo))
                throw new Exception("O novo director non existe");

            con.setAutoCommit(false);

            // reasignar supervisor
            String sqlSupervisor = """
                        UPDATE EMPREGADO
                        SET NSSSupervisa = ?
                        WHERE NSSSupervisa = ?
                    """;

            Utilidades.ejecutarSentencia(
                    con, sqlSupervisor,
                    nssSupervisorNuevo, nssBorrar
            );

            // poner nuevo director
            String sqlDirector = """
                        UPDATE DEPARTAMENTO
                        SET NSSDirector = ?
                        WHERE NSSDirector = ?
                    """;

            Utilidades.ejecutarSentencia(
                    con, sqlDirector,
                    nssDirectorNuevo, nssBorrar
            );

            // CORRECCION: borrar familiares
            String sqlFamiliares = """
                        DELETE FROM FAMILIAR
                        WHERE NSSEmpregado = ?
                    """;
            Utilidades.ejecutarSentencia(con, sqlFamiliares, nssBorrar);

            //saber si es fijo o temporal
            //
            boolean eraFijo = esEmpleadoFijo(con, nssBorrar);

            if (eraFijo) {
                String sqlFijo = """
                    DELETE FROM EMPREGADOFIXO
                    WHERE NSS = ?
                    """;
                Utilidades.ejecutarSentencia(con, sqlFijo, nssBorrar);
                resultado.subclaseBorrada = "FIJO";
            } else {
                String sqlTemporal = """
                    DELETE FROM EMPREGADOTEMPORAL
                    WHERE NSS = ?
                    """;
                Utilidades.ejecutarSentencia(con, sqlTemporal, nssBorrar);
                resultado.subclaseBorrada = "TEMPORAL";
            }

            // borrar empleado
            String sqlEmpleado = """
                        DELETE FROM EMPREGADO
                        WHERE NSS = ?
                    """;
            Utilidades.ejecutarSentencia(con, sqlEmpleado, nssBorrar);

            con.commit();

            resultado.ok = true;
            resultado.mensaje = "Empregado borrado correctamente";

        } catch (Exception e) {
            try {
                con.rollback();
            } catch (SQLException ignored) {
            }

            resultado.ok = false;
            resultado.mensaje = e.getMessage();

        } finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException ignored) {
            }
        }

        return resultado;
    }


    public static int borrarEmpleadoRecursivo_sinDTO(
            Connection con,
            String nssBorrar,
            String nssSupervisorNuevo,
            String nssDirectorNuevo) throws Exception {

        try {
            // comprobaciones antes de empezar la transaccion
            if (!existeEmpregado(con, nssBorrar))
                throw new Exception("O empregado a borrar non existe");

            if (!existeEmpregado(con, nssSupervisorNuevo))
                throw new Exception("O novo supervisor non existe");

            if (!existeEmpregado(con, nssDirectorNuevo))
                throw new Exception("O novo director non existe");

            con.setAutoCommit(false);

            // reasignar supervisor
            String sqlSupervisor = """
                UPDATE EMPREGADO
                SET NSSSupervisa = ?
                WHERE NSSSupervisa = ?
                """;
            Utilidades.ejecutarSentencia(con, sqlSupervisor,
                    nssSupervisorNuevo, nssBorrar);

            // reasignar director
            String sqlDirector = """
                UPDATE DEPARTAMENTO
                SET NSSDirector = ?
                WHERE NSSDirector = ?
                """;
            Utilidades.ejecutarSentencia(con, sqlDirector,
                    nssDirectorNuevo, nssBorrar);

            // borrar familiares
            String sqlFamiliares = """
                DELETE FROM FAMILIAR
                WHERE NSSEmpregado = ?
                """;
            Utilidades.ejecutarSentencia(con, sqlFamiliares, nssBorrar);

            //ejecutarsentencia sólo devuelve void
            // fijo o temporal
            boolean eraFijo = esEmpleadoFijo(con, nssBorrar);

            if (eraFijo) {
                String sqlFijo = """
                    DELETE FROM EMPREGADOFIXO
                    WHERE NSS = ?
                    """;
                Utilidades.ejecutarSentencia(con, sqlFijo, nssBorrar);
            } else {
                String sqlTemporal = """
                    DELETE FROM EMPREGADOTEMPORAL
                    WHERE NSS = ?
                    """;
                Utilidades.ejecutarSentencia(con, sqlTemporal, nssBorrar);
            }

            // borrar empleado
            String sqlEmpleado = """
                DELETE FROM EMPREGADO
                WHERE NSS = ?
                """;
            Utilidades.ejecutarSentencia(con, sqlEmpleado, nssBorrar);

            con.commit();

            if (eraFijo) {
                System.out.println("Tipo de empleado: fijo");
                return 1;
            } else {
                System.out.println("tipo de empleado: temporal");
                return 2;

            }

        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException ignored) {
            }

            String msg = e.getMessage().toLowerCase();
            if (msg.contains("foreign")) return -1;
            return -99;

        } finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException ignored) {
            }
        }
    }


    // CORRECCION: se implementa usando DatabaseMetaData.getColumns
    public static List<String> obtenerColumnasTabla(Connection con, String nombreTabla) {

        List<String> columnas = new ArrayList<>();

        try {
            DatabaseMetaData meta = con.getMetaData();

            try (ResultSet rs = meta.getColumns(
                    null,       // catalogo
                    null,       // esquema
                    nombreTabla,
                    null)) {    // todas las columnas

                while (rs.next()) {
                    // COLUMN_NAME es el nombre real de la columna en la BD
                    columnas.add(rs.getString("COLUMN_NAME"));
                }
            }

        } catch (SQLException e) {
            System.out.println("erro ao obter columnas da táboa " + nombreTabla);
        }

        return columnas;
    }

    // CORRECCION: obtiene las claves foráneas importadas por una tabla
    public static List<String> obtenerClavesForaneas(Connection con, String nombreTabla) {

        List<String> fks = new ArrayList<>();

        try {
            DatabaseMetaData meta = con.getMetaData();

            // ImportedKeys = FKs que ESTA tabla tiene hacia otras
            try (ResultSet rs = meta.getImportedKeys(
                    null,   // catalogo
                    null,   // esquema
                    nombreTabla)) {

                while (rs.next()) {

                    String columnaFK = rs.getString("FKCOLUMN_NAME");
                    String tablaPK = rs.getString("PKTABLE_NAME");
                    String columnaPK = rs.getString("PKCOLUMN_NAME");

                    short deleteRule = rs.getShort("DELETE_RULE");

                    String regla;
                    switch (deleteRule) {
                        case DatabaseMetaData.importedKeyCascade -> regla = "CASCADE";
                        case DatabaseMetaData.importedKeyRestrict -> regla = "RESTRICT";
                        case DatabaseMetaData.importedKeySetNull -> regla = "SET NULL";
                        case DatabaseMetaData.importedKeyNoAction -> regla = "NO ACTION";
                        case DatabaseMetaData.importedKeySetDefault -> regla = "SET DEFAULT";
                        default -> regla = "DESCONOCIDA";
                    }

                    // formato legible
                    fks.add(
                            columnaFK + " -> " +
                                    tablaPK + "(" + columnaPK + ") " +
                                    "[DELETE " + regla + "]"
                    );
                }
            }

        } catch (SQLException e) {
            System.out.println("erro ao obter claves foraneas da táboa " + nombreTabla);
        }

        return fks;
    }

    // CORRECCION: obtiene la regla de borrado de la FK reflexiva EMPREGADO -> EMPREGADO
    public static int obtenerReglaBorradoReflexiva(Connection con, String tabla) {

        try {
            DatabaseMetaData meta = con.getMetaData();

            try (ResultSet rs = meta.getImportedKeys(null, null, tabla)) {

                while (rs.next()) {

                    String pkTable = rs.getString("PKTABLE_NAME");
                    String fkTable = rs.getString("FKTABLE_NAME");

                    // FK reflexiva: apunta a la misma tabla
                    if (tabla.equalsIgnoreCase(pkTable)
                            && tabla.equalsIgnoreCase(fkTable)) {

                        return rs.getShort("DELETE_RULE");
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("erro ao consultar regra de borrado reflexiva");
        }

        return -1; // NO HAY FK REFLEXIVA
    }

    // CORRECCION: borrado de empleado usando metadatos y FK reflexiva
    public static int borrarEmpleadoRecursivo_alternativo(
            Connection con,
            String nssBorrar,
            String nssSustituto) {

        try {
            con.setAutoCommit(false);

            int regla = obtenerReglaBorradoReflexiva(con, "EMPREGADO");

            // NOTA: si no hay FK reflexiva, se puede borrar directamente
            if (regla == -1) {

                Utilidades.ejecutarSentencia(
                        con,
                        "DELETE FROM EMPREGADO WHERE NSS = ?",
                        nssBorrar
                );

                con.commit();
                return 0;
            }

            // CASCADE: SQL Server borra solo
            if (regla == DatabaseMetaData.importedKeyCascade) {

                Utilidades.ejecutarSentencia(
                        con,
                        "DELETE FROM EMPREGADO WHERE NSS = ?",
                        nssBorrar
                );

                con.commit();
                return 0;
            }

            // SET NULL: romper relación reflexiva
            if (regla == DatabaseMetaData.importedKeySetNull) {

                Utilidades.ejecutarSentencia(
                        con,
                        "UPDATE EMPREGADO SET NSSSupervisor = NULL WHERE NSSSupervisor = ?",
                        nssBorrar
                );
            }

            // NO ACTION / RESTRICT : reasignar
            if (regla == DatabaseMetaData.importedKeyNoAction
                    || regla == DatabaseMetaData.importedKeyRestrict) {

                Utilidades.ejecutarSentencia(
                        con,
                        "UPDATE EMPREGADO SET NSSSupervisor = ? WHERE NSSSupervisor = ?",
                        nssSustituto, nssBorrar
                );
            }

            // borrar como director si procede
            Utilidades.ejecutarSentencia(
                    con,
                    "UPDATE DEPARTAMENTO SET NSSDirector = ? WHERE NSSDirector = ?",
                    nssSustituto, nssBorrar
            );

            // borrar empleado
            Utilidades.ejecutarSentencia(
                    con,
                    "DELETE FROM EMPREGADO WHERE NSS = ?",
                    nssBorrar
            );

            con.commit();
            return 0;

        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException ignored) {
            }
            System.out.println(e.getMessage());
            return -99;

        } finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException ignored) {
            }
        }
    }


    //endregion EXTRA
}
