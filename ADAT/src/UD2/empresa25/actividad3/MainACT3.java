package UD2.empresa25.actividad3;

import UD2.empresa25.actividad3.clases.*;
import UD2.empresa25.actividad3.logica.GestorEmpresa;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class MainACT3 {

    public static void main(String[] args) {

        GestorEmpresa gestor = new GestorEmpresa();

        System.out.println("===== EJERCICIO 1: ALTA FAMILIAR =====");
        Familiar fam = new Familiar(
                "1111111",
                "9999999",
                "Lucia",
                "Lopez Perez",
                LocalDate.of(2010, 5, 12),
                "Hija",
                'M'
        );
        gestor.altaFamiliar(fam);

        System.out.println("\n===== EJERCICIO 2: ALTA VEHÍCULOS =====");
        VehiculoPropio vp = new VehiculoPropio(
                "1234ABC",
                "Toyota",
                "Corolla",
                "G",
                LocalDate.of(2023, 5, 15),
                15000
        );
        gestor.altaVehiculo(vp);

        VehiculoRenting vr = new VehiculoRenting(
                "5678XYZ",
                "Ford",
                "Fiesta",
                "D",
                LocalDate.of(2023, 7, 1),
                200,
                24
        );
        gestor.altaVehiculo(vr);

        System.out.println("\n===== EJERCICIO 3: CAMBIAR DEPARTAMENTO PROYECTO =====");
        gestor.moverProyecto("INNOVACIÓN", "PROXECTO X");

        System.out.println("\n===== EJERCICIO 4: ELIMINAR PROYECTO =====");
        gestor.borrarProyecto(11);

        System.out.println("\n===== EJERCICIO 5: INCREMENTO SALARIOS (BATCH) =====");
        List<String> nss = Arrays.asList("0010010", "0110010", "0999900");
        gestor.subirSalarios(100, nss);

        System.out.println("\n===== EJERCICIO 6: ALTA PROYECTO CON RESULTSET =====");
        Proxecto p = new Proxecto(20, "NUEVO PROYECTO JDBC", "VIGO", 3);
        gestor.altaProyecto(p);

        System.out.println("\n===== EJERCICIO 7: SUBIDA POR DEPARTAMENTO =====");
        gestor.subirSalariosDepartamento(50, 3);

        System.out.println("\n===== EJERCICIO 8: CONSULTA SCROLLABLE =====");
        gestor.ejecutarConsultaScrollable(1);

        System.out.println("\n--- FIN DE PRUEBAS ---");
    }
}
