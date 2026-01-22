package bden26.pablolc.logica;

//Pablo López Couso DNI: 77550221V

import bden26.pablolc.Clases.Fotografia;
import bden26.pablolc.persistencia.FotografosDAO;

import java.sql.Connection;
import java.util.List;

public class GestorFotografias {
    Connection con;

    public GestorFotografias(Connection con) {
        this.con = con;
    }

    public void ejercicio1(){
        FotografosDAO dao = new FotografosDAO(con);

        if (dao.crearTablas()){
            System.out.println("Se añadieron las tablas con exito");
        }
        else {
            System.out.println("Error al crear las tablas");
        }
    }

    public void ejercicio2(String nombreFotografo, String nombreExposicion, List<Fotografia> fotos){
        FotografosDAO dao = new FotografosDAO(con);
        if (dao.insertarFotos(nombreFotografo, nombreExposicion, fotos)){
            System.out.println("Se insertaron las fotos con exito");
        }
        else {
            System.out.println("Error al insertar las fotos");
        }
    }

    public void ejercicio3(String nombreNuevaExposicion, String nombreViejaExposicion){
        FotografosDAO dao = new FotografosDAO(con);

        switch (dao.moverFotos(nombreNuevaExposicion, nombreViejaExposicion)){
            case 1 -> System.out.println("Fotos movidas correctamente");
            case -2 -> System.out.println("No existe la exposicion");
            case -99 -> System.out.println("Error inesperado");
        }
    }
}
