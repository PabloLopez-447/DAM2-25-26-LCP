package UD1.examen.parte2.persistencia;



import UD1.examen.parte2.clases.Fotografia;
import UD1.examen.parte2.clases.Fotografo;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

//Pablo López Couso DNI:77550221V

public class FotografoRandom extends Archivo {
    private RandomAccessFile raf;
    public static final int TAM_REGISTRO = 1024; //Tamaño fijo del registro en bytes

    public FotografoRandom(String ruta) {
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

    public void escribirFotografo(Fotografo f) {
        try {
            if (raf == null) {
                abrirArchivo();
            }
            long posicion = (f.getCodigo() + 1L) * TAM_REGISTRO;
            raf.seek(posicion);
            raf.writeBoolean(f.isBorrado());
            raf.writeInt(f.getCodigo());
            raf.writeUTF(f.getNumLicencia());
            raf.writeUTF(f.getNombre());
            raf.writeUTF(f.getEstudio());
            raf.writeInt(f.getFotografias().size());

            for (Fotografia fg : f.getFotografias()) {
                raf.writeUTF(fg.getTitulo());
                raf.writeLong(UtilidadesFechas.toLongFecha(fg.getFechaToma()));
                raf.writeUTF(fg.getTipo());
                raf.writeDouble(fg.getTamanoMB());
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Fotografo leerFotografo(int codigo) {
        if (codigo <= 0) {
            System.out.println("codigo invalido");
            return null;
        }
        Fotografo fotografo = null;
        try {


            if (raf == null) {
                abrirArchivo();
            }
            long posicion = (codigo + 1L) * TAM_REGISTRO;
            if (posicion >= raf.length()) {
                return null;
            }
            raf.seek(posicion);

            boolean borrado = raf.readBoolean();
            if (borrado) {
                return null;
            }

            int codFotografo = raf.readInt();
            String numLicencia = raf.readUTF();
            String nombre = raf.readUTF();
            String estudio = raf.readUTF();
            int numFotografia = raf.readInt();

            fotografo = new Fotografo();

            fotografo.setBorrado(borrado);
            fotografo.setCodigo(codigo);
            fotografo.setNumLicencia(numLicencia);
            fotografo.setNombre(nombre);
            fotografo.setEstudio(estudio);
            fotografo.setNumFotografia(numFotografia);
            List<Fotografia> patrocinadores = new ArrayList<>();

            for (int i = 0; i < numFotografia; i++) {
                patrocinadores.add(new Fotografia
                        (raf.readUTF(),
                                UtilidadesFechas.fromLongFecha(raf.readLong()),
                                raf.readUTF(),
                                raf.readDouble()
                                ));
            }
            fotografo.setFotografias(patrocinadores);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fotografo;
    }

    public int numeroRegistrosConBorrados() {
        try {
            return (int) Math.ceil((double) raf.length() / TAM_REGISTRO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int calcularNuevoId() {
        if (raf == null) {
            abrirArchivo();
        }
        try {
            long totalRegistros = numeroRegistrosConBorrados();
            return (int) totalRegistros + 1;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
