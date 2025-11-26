package UD1.ficherosBinarios.logica;

import UD1.ficherosBinarios.clases.Equipo;
import UD1.ficherosBinarios.clases.Patrocinador;
import UD1.ficherosBinarios.persistencia.EquipoRandom;

import java.util.HashSet;
import java.util.Set;

public class GestorEquipos {
    private final EquipoRandom archivo;


    public GestorEquipos(String ruta) {
        archivo = new EquipoRandom(ruta);

        try {
            archivo.abrirArchivo();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Equipo buscarEquipoPorNombre(String nombre) {
        int id = 1;
        Equipo equipo = null;
        while ((equipo = archivo.leerEquipo(id)) != null) {
            if (equipo.getNombre().equals(nombre)) {
                return equipo;
            }
            id++;
        }
        return null;
    }

    public boolean guardarEquipo(Equipo equipo) {
        try {
            if (buscarEquipoPorNombre(equipo.getNombre()) != null) {
                System.out.println("El equipo ya existe en el sistema");
                return false;
            }

            int nuevoId = archivo.calcularNuevoId();
            equipo.setIdEquipo(nuevoId);
            equipo.setNumPatrocinadores(equipo.getPatrocinadores().size());

            if (equipo.bytesSerializacionEquipo() > EquipoRandom.TAM_REGISTRO){
                System.out.println("El equipo excede el tamaño maximo de registro");
                return false;
            }
            archivo.escribirEquipo(equipo);
            return true;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Equipo buscarEquipoPorId(int id) {
        Equipo equipo = null;
        if (id <= 0) {
            System.out.println("id invalido");
            return null;
        }

        try {
            return archivo.leerEquipo(id);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void listarEquipos() {
        Equipo equipo = null;
        for (int id = 1; id < archivo.numeroRegistrosConBorrados(); id++) {
            try {
                equipo = archivo.leerEquipo(id);
                if (equipo != null && !equipo.isBorrado()) {
                    System.out.println(equipo);
                }
            }catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void mostrarEquipoPorId(int id) {
        if (id <= 0) {
            System.out.println("id invalido");
            return;
        }

        Equipo equipo = buscarEquipoPorId(id);
        if  (equipo == null) {
            System.out.println("No existe el equipo con el id " + id);
            return;
        }

        System.out.println(equipo);
    }

    public void eliminarEquipoPorId(int id) {
        if (id <= 0) {
            System.out.println("id invalido");
            return;
        }

        try {
            Equipo equipo = buscarEquipoPorId(id);
            if (equipo == null) {
                System.out.println("No existe el equipo con el id " + id);
                return;
            }

            System.out.println("Equipo a borrar: ");
            System.out.println(equipo);
            equipo.setBorrado(true);

            archivo.escribirEquipo(equipo);
            System.out.println("Equipo eliminado");

        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void agregaroActualizarPatrocinador(int idEquipo, Patrocinador p) {
        if (idEquipo <= 0) {
            System.out.println("id invalido");
            return;
        }

        try {
            Equipo equipo = buscarEquipoPorId(idEquipo);
            if (equipo == null) {
                System.out.println("No existe el equipo con el id " + idEquipo);
                return;
            }

            Set<Patrocinador> patrocinadores = equipo.getPatrocinadores();
            if (patrocinadores == null) {
                patrocinadores = new HashSet<>();
            }
            boolean encontrado = false;

            for (Patrocinador pat : patrocinadores) {
                if (pat.getNombre().equals(p.getNombre())) {
                    pat.setDonacion(p.getDonacion());
                    pat.setFechaInicio(p.getFechaInicio());
                    encontrado = true;
                    System.out.println("patrocinador " + pat.getNombre() + " actualizado");
                    break;
                }
            }

            if (!encontrado) {
                patrocinadores.add(p);
                System.out.println("patrocinador " + p.getNombre() + " añadido");
            }
            equipo.setPatrocinadores(patrocinadores);
            equipo.setNumPatrocinadores(equipo.getPatrocinadores().size());

            if (equipo.bytesSerializacionEquipo() > EquipoRandom.TAM_REGISTRO) {
                System.out.println("El registro excede el tamaño");
                return;
            }
            archivo.escribirEquipo(equipo);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void eliminarPatrocinadores(int id, String nombre) {
        if (id <= 0) {
            System.out.println("id invalido");
            return;
        }

        try {
            Equipo equipo = buscarEquipoPorId(id);
            if (equipo == null) {
                System.out.println("No existe el equipo con el id " + id);
                return;
            }

            Set<Patrocinador> patrocinadores = equipo.getPatrocinadores();
            if (patrocinadores == null || patrocinadores.isEmpty()) {
                System.out.println("No hay patrocinadores");
                return;
            }

            boolean eliminado = patrocinadores.removeIf(patrocinador -> patrocinador.getNombre().equals(nombre));

            if (!eliminado) {
                System.out.println("el equipo no tiene un patrocinador con el nombre: " + nombre);
                return;
            }

            equipo.setPatrocinadores(patrocinadores);
            equipo.setNumPatrocinadores(equipo.getPatrocinadores().size());

            archivo.escribirEquipo(equipo);
            System.out.println("Patrocinador " + nombre + " eliminado");
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
