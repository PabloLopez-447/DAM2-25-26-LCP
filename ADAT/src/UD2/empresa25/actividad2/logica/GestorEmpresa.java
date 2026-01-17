package UD2.empresa25.actividad2.logica;

import UD2.empresa25.actividad2.persistencia.EmpresaDAO;

public class GestorEmpresa{

    private final EmpresaDAO dao;

    public GestorEmpresa(EmpresaDAO dao) {
        this.dao = dao;
    }

    public void ejecutarTodo() throws Exception {
        System.out.println("E1 " + dao.departamentosConProyectos());
        System.out.println("E2 " + dao.departamentosConDirector());
        System.out.println("E3 " + dao.empregadosConIdade());
        System.out.println("E4 " + dao.empregadosPorDepartamento("TÉCNICO"));
        System.out.println("E5 " + dao.empregadosFixosProxectoLocalidade("PORTAL", "Vigo"));
        System.out.println("E6 " + dao.contadoresPorDepartamento());
        System.out.println("E7 " + dao.departamentosConMasDeN(5));
        System.out.println("E8 " + dao.fixosConSalarioMaior(2000));

        var rs = dao.mellorPagadosPorDepartamento();
        if (rs.last()) {
            do {
                System.out.println(rs.getString(1) + " -> " + rs.getString(3));
            } while (rs.previous());
        }

        System.out.println("E10 " + dao.departamentosMaxProxectos());
    }
}
