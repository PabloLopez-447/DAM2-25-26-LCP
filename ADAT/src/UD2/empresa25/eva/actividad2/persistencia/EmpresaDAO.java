package UD2.empresa25.eva.actividad2.persistencia;

import UD2.empresa25.eva.actividad2.dto.*;
import UD2.empresa25.eva.actividad2.logica.GestorEmpresa;

import java.sql.Connection;
import java.util.List;

public class EmpresaDAO {

    private Connection con;

    public EmpresaDAO(Connection con) {
        this.con = con;
    }

    // EJERCICIO 1
    public void ejercicio1() {
        System.out.println("EJERCICIO 1");
        List<DepartamentoDTO> lista = GestorEmpresa.ejercicio1(con);
        for (DepartamentoDTO d : lista) {
            System.out.println(d);
        }
    }

    // EJERCICIO 2
    public void ejercicio2() {
        System.out.println("EJERCICIO 2");
        try {
            String sgbd = con.getMetaData().getDatabaseProductName().toUpperCase();
            List<DepartamentoDirectorDTO> lista;

            if (sgbd.contains("SQLITE")) {
                lista = GestorEmpresa.ejercicio2_sqlite(con);
            } else {
                lista = GestorEmpresa.ejercicio2_mysql_sqlserver(con);
            }

            for (DepartamentoDirectorDTO d : lista) {
                System.out.println(d);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // EJERCICIO 3
    public void ejercicio3() {
        System.out.println("EJERCICIO 3");
        try {
            String sgbd = con.getMetaData().getDatabaseProductName().toUpperCase();
            List<EmpregadoIdadeDTO> lista;

            if (sgbd.contains("MYSQL")) {
                lista = GestorEmpresa.ejercicio3_mysql(con);
            } else if (sgbd.contains("SQLITE")) {
                lista = GestorEmpresa.ejercicio3_sqlite(con);
            } else {
                lista = GestorEmpresa.ejercicio3_sqlserver(con);
            }

            for (EmpregadoIdadeDTO e : lista) {
                System.out.println(e);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // EJERCICIO 4
    public void ejercicio4(String departamento) {
        System.out.println("EJERCICIO 4");
        try {
            String sgbd = con.getMetaData().getDatabaseProductName().toUpperCase();
            List<EmpregadoResumoDTO> lista;

            if (sgbd.contains("MYSQL")) {
                lista = GestorEmpresa.ejercicio4_mysql(con, departamento);
            } else {
                lista = GestorEmpresa.ejercicio4_sqlite_sqlserver(con, departamento);
            }

            for (EmpregadoResumoDTO e : lista) {
                System.out.println(e);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // EJERCICIO 5
    public void ejercicio5(String proxecto, String lugar) {
        System.out.println("EJERCICIO 5");
        try {
            String sgbd = con.getMetaData().getDatabaseProductName().toUpperCase();
            List<EmpregadoResumoDTO> lista;

            if (sgbd.contains("SQLITE")) {
                lista = GestorEmpresa.ejercicio5_sqlite(con, proxecto, lugar);
            } else {
                lista = GestorEmpresa.ejercicio5_mysql_sqlserver(con, proxecto, lugar);
            }

            for (EmpregadoResumoDTO e : lista) {
                System.out.println(e);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // EJERCICIO 6
    public void ejercicio6() {
        System.out.println("EJERCICIO 6");
        try {
            String sgbd = con.getMetaData().getDatabaseProductName().toUpperCase();
            List<DepartamentoEmpleadosDTO> lista;

            if (sgbd.contains("MYSQL")) {
                lista = GestorEmpresa.ejercicio6_mysql(con);
            } else {
                lista = GestorEmpresa.ejercicio6_sqlite_sqlserver(con);
            }

            for (DepartamentoEmpleadosDTO d : lista) {
                System.out.println(d);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // EJERCICIO 7
    public void ejercicio7(int n) {
        System.out.println("EJERCICIO 7");
        List<DepartamentoDTO> lista = GestorEmpresa.ejercicio7(con, n);
        for (DepartamentoDTO d : lista) {
            System.out.println(d);
        }
    }

    // EJERCICIO 8
    public void ejercicio8(double salario) {
        System.out.println("EJERCICIO 8");
        try {
            String sgbd = con.getMetaData().getDatabaseProductName().toUpperCase();
            List<EmpregadoResumoDTO> lista;

            if (sgbd.contains("SQLITE")) {
                lista = GestorEmpresa.ejercicio8_sqlite(con, salario);
            } else {
                lista = GestorEmpresa.ejercicio8_mysql_sqlserver(con, salario);
            }

            for (EmpregadoResumoDTO e : lista) {
                System.out.println(e);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // EJERCICIO 9
    public void ejercicio9() {
        System.out.println("EJERCICIO 9");
        try {
            String sgbd = con.getMetaData().getDatabaseProductName().toUpperCase();

            if (!sgbd.contains("SQLITE")) {
                List<String> lista = GestorEmpresa.ejercicio9_mysql_sqlserver(con);
                for (String s : lista) {
                    System.out.println(s);
                }
            } else {
                System.out.println("Ejercicio 9 no va en SQLite");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // EJERCICIO 10
    public void ejercicio10() {
        System.out.println("EJERCICIO 10");
        try {
            String sgbd = con.getMetaData().getDatabaseProductName().toUpperCase();

            if (!sgbd.contains("SQLITE")) {
                List<DepartamentoDTO> lista =
                        GestorEmpresa.ejercicio10_mysql_sqlserver(con);

                for (DepartamentoDTO d : lista) {
                    System.out.println(d);
                }
            } else {
                System.out.println("Ejercicio 10 no va en SQLite");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
