package UD2.empresa25.actividad4.logica;

import UD2.empresa25.actividad4.persistencia.EmpresaDAO;

public class GestorEmpresa {

    private final EmpresaDAO dao;

    public GestorEmpresa(EmpresaDAO dao) {
        this.dao = dao;
    }

    public void inicializar() {
        try {
            dao.crearObjetos();
            System.out.println("Procedimientos y funciones creados.");
        } catch (Exception e) {
            System.err.println("Error creando objetos: " + e.getMessage());
        }
    }

    public void cambiarDomicilio(String nss, String rua, String num, String piso, String cp, String loc) {
        try {
            dao.cambioDomicilio(nss, rua, num, piso, cp, loc);
            System.out.println("Domicilio actualizado.");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void mostrarDatosProxecto(int num) {
        try {
            String[] d = dao.datosProxecto(num);
            System.out.println("Nome: " + d[0]);
            System.out.println("Lugar: " + d[1]);
            System.out.println("Departamento: " + d[2]);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void departamentosConProxectos(int n) {
        try {
            boolean rs = dao.departamentosConNProxectos(n);
            System.out.println(rs ? "Operación de selección" : "Operación de actualización");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void numeroEmpDepartamento(String nome) {
        try {
            int n = dao.numeroEmpDepartamento(nome);
            System.out.println("Número de empregados: " + n);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
