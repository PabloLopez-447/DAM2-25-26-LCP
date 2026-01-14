package UD2.empresa25.actividad3.logica;

import UD2.empresa25.actividad3.clases.*;
import UD2.empresa25.actividad3.persistencia.EmpresaDAO;

import java.sql.ResultSet;
import java.util.List;

public class GestorEmpresa {

    private final EmpresaDAO dao = new EmpresaDAO();

    // =========================
    // EJERCICIO 1
    // =========================
    public void altaFamiliar(Familiar f) {
        try {
            dao.insertarFamiliar(f);
            System.out.println("Familiar insertado correctamente. Nº familiar: " + f.getNumFam());
        } catch (Exception e) {
            System.err.println("Error al insertar familiar: " + e.getMessage());
        }
    }

    // =========================
    // EJERCICIO 2
    // =========================
    public void altaVehiculo(Vehiculo v) {
        try {
            int id = dao.insertarVehiculo(v);
            System.out.println("Vehículo insertado con ID: " + id);
        } catch (Exception e) {
            System.err.println("Error al insertar vehículo: " + e.getMessage());
        }
    }

    // =========================
    // EJERCICIO 3
    // =========================
    public void moverProyecto(String depto, String proyecto) {
        try {
            dao.cambiarDepartamentoProyecto(depto, proyecto);
            System.out.println("Proyecto actualizado correctamente.");
        } catch (Exception e) {
            System.err.println("Error al cambiar departamento del proyecto: " + e.getMessage());
        }
    }

    // =========================
    // EJERCICIO 4
    // =========================
    public void borrarProyecto(int num) {
        try {
            dao.eliminarProyecto(num);
            System.out.println("Proyecto eliminado correctamente.");
        } catch (Exception e) {
            System.err.println("Error al eliminar proyecto: " + e.getMessage());
        }
    }

    // =========================
    // EJERCICIO 5
    // =========================
    public void subirSalarios(double inc, List<String> nss) {
        try {
            int n = dao.incrementarSalarios(inc, nss);
            System.out.println("Empleados actualizados: " + n);
        } catch (Exception e) {
            System.err.println("Error al incrementar salarios: " + e.getMessage());
        }
    }

    // =========================
    // EJERCICIO 6
    // =========================
    public void altaProyecto(Proxecto p) {
        try {
            if (dao.existeProyecto(p.getNum(), p.getNome())) {
                System.err.println("El proyecto ya existe.");
                return;
            }
            if (!dao.existeDepartamento(p.getNumDepartamento())) {
                System.err.println("El departamento no existe.");
                return;
            }

            dao.insertarProyectoRS(p);
            System.out.println("Proyecto insertado correctamente mediante ResultSet.");
        } catch (Exception e) {
            System.err.println("Error al insertar proyecto: " + e.getMessage());
        }
    }

    // =========================
    // EJERCICIO 7
    // =========================
    public void subirSalariosDepartamento(double inc, int depto) {
        try {
            dao.incrementarSalariosDepartamento(inc, depto);
            System.out.println("Salarios del departamento actualizados.");
        } catch (Exception e) {
            System.err.println("Error al actualizar salarios del departamento: " + e.getMessage());
        }
    }

    // =========================
    // EJERCICIO 8
    // =========================
    public void ejecutarConsultaScrollable(int minProyectos) {
        try {
            ResultSet rs = dao.consultaScrollable(minProyectos);

            if (!rs.first()) {
                System.out.println("No hay resultados.");
                return;
            }

            System.out.println("Primera fila:");
            imprimirFila(rs);

            rs.last();
            System.out.println("\nÚltima fila:");
            imprimirFila(rs);

            if (rs.getRow() > 2) {
                rs.absolute(rs.getRow() - 2);
                System.out.println("\nAntepenúltima fila:");
                imprimirFila(rs);
            }

            System.out.println("\nRecorrido inverso:");
            rs.afterLast();
            while (rs.previous()) {
                imprimirFila(rs);
            }

            rs.getStatement().getConnection().close();

        } catch (Exception e) {
            System.err.println("Error en consulta scrollable: " + e.getMessage());
        }
    }

    private void imprimirFila(ResultSet rs) throws Exception {
        System.out.println(
                rs.getString("NSS") + " | " +
                        rs.getString("NomeCompleto") + " | " +
                        rs.getString("Localidade") + " | " +
                        rs.getDouble("Salario")
        );
    }
}
