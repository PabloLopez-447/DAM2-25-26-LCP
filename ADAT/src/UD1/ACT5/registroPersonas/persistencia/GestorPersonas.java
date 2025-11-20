package UD1.ACT5.registroPersonas.persistencia;



import UD1.ACT5.registroPersonas.clases.*;
import UD1.ACT5.registroPersonas.utiles.XMLJAXBUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GestorPersonas {
    public void generarXML() {
        ArrayList<String> categorias = new ArrayList<> (Arrays.asList("Estudios", "Trabajo", "Deporte"));
        //hay manera de hacerlo menos ortopedico?

        try {
            Registro registro = new Registro();

            registro.setVersion("1.0");
            registro.setFechaCreacion(LocalDate.now());

            //asi?
            registro.setCategorias(categorias);
            //

            //jabx no crea los objetos ni los guarda, hay que crear la lista
            List<Persona> personas = new ArrayList<>();
            //registro.setCategorias(List.of("Estudios", "Trabajo", "Deporte"));

            //vas metiendo la gente
            //trabajador
            Trabajador t1 = new Trabajador();
            t1.setNombre("Carlos Alba");
                //ojo pasarle el formatter o no chupa
            t1.setFechaNacimiento(LocalDate.parse("12-03-99",DateTimeFormatter.ofPattern("dd-MM-yy")));

            // Crear telefonos
            Telefonos tel1 = new Telefonos();
            tel1.getTelefonos().add(new Telefono("movil", "111111111"));
            tel1.getTelefonos().add(new Telefono("fijo", "222222222"));
            t1.setContacto(tel1); // contacto = Telefonos

            t1.setEmpresa(new Empresa("Director", "LaColmena"));
            t1.setSalario(8000);
            personas.add(t1);

            //estudiante
            Estudiante e1 = new Estudiante();
            e1.setNombre("Angel Rodríguez");
            e1.setFechaNacimiento(LocalDate.parse("21-07-01",DateTimeFormatter.ofPattern("dd-MM-yy")));;
            //e1.setContacto("angel.rodriguez@example.com");
            e1.setUniversidad("Santiago");
            e1.setCarrera("Informatica");
            personas.add(e1);

            // trabajador 2
            Trabajador t2 = new Trabajador();
            t2.setNombre("Manuel Sánchez");
            t2.setFechaNacimiento(LocalDate.parse("04-11-79",DateTimeFormatter.ofPattern("dd-MM-yy")));;
            Email email2 = new Email("Manuel.Sánchez@example.com");
            t2.setContacto(email2);
            t2.setEmpresa(new Empresa("ElTornillo", "Gerente"));
            t2.setSalario(2000);
            personas.add(t2);

            registro.setPersonas(personas);

            XMLJAXBUtils.marshall(registro, "src/registro2.xml");

            System.out.println("Archivo XML generado correctamente.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
