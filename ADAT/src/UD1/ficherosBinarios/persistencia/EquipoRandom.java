package UD1.ficherosBinarios.persistencia;

import UD1.ficherosBinarios.UtilidadesFechas;
import UD1.ficherosBinarios.clases.Equipo;
import UD1.ficherosBinarios.clases.Patrocinador;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashSet;
import java.util.Set;

public class EquipoRandom extends Archivo {
    private RandomAccessFile raf;
    public static final int TAM_REGISTRO = 200; //Tamaño fijo del registro en bytes

    public EquipoRandom(String ruta) {
        super(ruta);
    }

    @Override
    public void abrirArchivo() {
        try {
            raf = new RandomAccessFile(this.ruta, "rw");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void cerrarArchivo() {
        try {
            raf.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void escribirEquipo(Equipo equipo) {
        try {
            if (raf == null) {
                abrirArchivo();
            }
            long posicion = (equipo.getIdEquipo() - 1L) * TAM_REGISTRO;
            raf.seek(posicion);
            raf.writeBoolean(equipo.isBorrado());
            raf.writeInt(equipo.getIdEquipo());
            raf.writeUTF(equipo.getNombre());
            raf.writeInt(equipo.getPatrocinadores().size());

            for (Patrocinador p : equipo.getPatrocinadores()) {
                raf.writeUTF(p.getNombre());
                raf.writeFloat(p.getDonacion());
                raf.writeLong(UtilidadesFechas.toLongFecha(p.getFechaInicio()));
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Equipo leerEquipo(int idEquipo) {
        if (idEquipo <= 0) {
            System.out.println("id invalido");
            return null;
        }
        Equipo equipo = null;
        try {


            if (raf == null) {
                abrirArchivo();
            }
            long posicion = (idEquipo - 1L) * TAM_REGISTRO;
            if (posicion >= raf.length()) {
                return null;
            }
            raf.seek(posicion);

            boolean borrado = raf.readBoolean();
            if (borrado) {
                return null;
            }

            int id = raf.readInt();
            String nombre = raf.readUTF();
            int numPatrocinadores = raf.readInt();

            equipo = new Equipo(id, nombre);

            equipo.setBorrado(borrado);
            equipo.setNumPatrocinadores(numPatrocinadores);
            Set<Patrocinador> patrocinadores = new HashSet<Patrocinador>();

            for (int i = 0; i < numPatrocinadores; i++) {
                patrocinadores.add(new Patrocinador(raf.readUTF(), raf.readFloat(), UtilidadesFechas.fromLongFecha(raf.readLong())));
            }
            equipo.setPatrocinadores(patrocinadores);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return equipo;
    }

    public int calcularNuevoId() {
        if (raf == null) {
            abrirArchivo();
        }
        return numeroRegistrosConBorrados() + 1;
    }

    public int numeroRegistrosConBorrados() {
        try {
            return (int) Math.ceil((double) raf.length() / TAM_REGISTRO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int calcularNuevoIdReutilizado() {
        if (raf == null) {
            abrirArchivo();
        }
        try {
            long totalRegistros = numeroRegistrosConBorrados();

            for (int i = 1; i <= totalRegistros; i++) {
                long posicion = i * TAM_REGISTRO;
                raf.seek(posicion);
                boolean borrado = raf.readBoolean();

                if (borrado) {
                    int id = raf.readInt();
                    return id;
                }
            }
            return (int) totalRegistros + 1;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
