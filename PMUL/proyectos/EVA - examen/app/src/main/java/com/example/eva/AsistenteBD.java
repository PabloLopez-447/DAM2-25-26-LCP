package com.example.eva;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AsistenteBD extends SQLiteOpenHelper {

    private static final String NOMBRE_BD = "canales.db";
    private static final int VERSION_BD = 5;

    public AsistenteBD(Context context) {
        super(context, NOMBRE_BD, null, VERSION_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreateCanal =
                "CREATE TABLE Canal (" +
                        "codCanal INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "nombre TEXT, " +
                        "precioBase INTEGER, " +
                        "precioPorVisualizacion INTEGER, " +
                        "visibilidad TEXT)";
        db.execSQL(sqlCreateCanal);

        String sqlCreateBar =
                "CREATE TABLE Bar (" +
                        "codBar INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "nombre TEXT)";
        db.execSQL(sqlCreateBar);

        String sqlCreateSuscripcion =
                "CREATE TABLE Suscripcion (" +
                        "codSuscripcion INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "codBar INTEGER, " +
                        "codCanal INTEGER, " +
                        "nomCanal TEXT, " +
                        "precioBase INTEGER, " +
                        "precioPorVisualizacion INTEGER, " +
                        "nVisualizaciones INTEGER)";
        db.execSQL(sqlCreateSuscripcion);

        insertarBar(db, "Bar 1");
        insertarCanal(db, "La 1", 0, 0, "Público");
        insertarCanal(db, "La 2", 0, 0, "Público");
        insertarCanal(db, "F1", 10, 2, "Privado");
        insertarCanal(db, "Moto GP", 5, 1, "Privado");
        insertarCanal(db, "Champions", 50, 6, "Privado");
        insertarCanal(db, "Liga", 120, 5, "Privado");
        insertarCanal(db, "Caza y Pesca", 7, 3, "Privado");
        insertarSuscripcion(db, 1, 3, "F1", 10, 2, 1);
        insertarSuscripcion(db, 1, 4, "Moto GP", 5, 1, 2);
        insertarSuscripcion(db, 1, 7, "Caza y Pesca", 7, 3, 3);
    }

    void insertarCanal(SQLiteDatabase db, String nombre, int precioBase, int precioPorVisualizacion, String visibilidad) {
        ContentValues cv = new ContentValues();
        cv.put("nombre", nombre);
        cv.put("precioBase", precioBase);
        cv.put("precioPorVisualizacion", precioPorVisualizacion);
        cv.put("visibilidad", visibilidad);
        db.insert("Canal", null, cv);
    }

    void insertarBar(SQLiteDatabase db, String nombre) {
        ContentValues cv = new ContentValues();
        cv.put("nombre", nombre);
        db.insert("Bar", null, cv);
    }

    void insertarSuscripcion(SQLiteDatabase db, int codBar, int codCanal, String nombreCanal, int precioBase, int precioPorVisualizacion, int nVisualizaciones) {
        ContentValues cv = new ContentValues();
        cv.put("codBar", codBar);
        cv.put("codCanal", codCanal);
        cv.put("nomCanal", nombreCanal);
        cv.put("nVisualizaciones", nVisualizaciones);
        cv.put("precioBase", precioBase);
        cv.put("precioPorVisualizacion", precioPorVisualizacion);
        db.insert("Suscripcion", null, cv);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        reset(db);
    }

    public void reset(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS Canal");
        db.execSQL("DROP TABLE IF EXISTS Bar");
        db.execSQL("DROP TABLE IF EXISTS Suscripcion");
        onCreate(db);
    }
}
