package com.example.eva;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AsistenteBD extends SQLiteOpenHelper {

    private static final String NOMBRE_BD = "juegosBtnDir.db";
    private static final int VERSION_BD = 2;

    public AsistenteBD(Context context) {
        super(context, NOMBRE_BD, null, VERSION_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sqlCreateEscapeRoom =
                "CREATE TABLE EscapeRoom (" +
                        "codRoom INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "nombre TEXT, " +
                        "solucion TEXT)";
        db.execSQL(sqlCreateEscapeRoom);

        String sqlCreateLibro =
                "CREATE TABLE Libro (" +
                        "codLibro INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "nombre TEXT, " +
                        "fila INTEGER, " +
                        "columna INTEGER, " +
                        "descripcion TEXT)";
        db.execSQL(sqlCreateLibro);

        ContentValues cv = new ContentValues();
        cv.put("nombre", "Salir por el ascensor");
        cv.put("solucion", "UR");
        db.insert("EscapeRoom", null, cv);

        cv.clear();
        cv.put("nombre", "Camino del baño");
        cv.put("solucion", "ULLRL");
        db.insert("EscapeRoom", null, cv);

        insertarLibro(db, "El Quijote", 1, 1, "Novela clásica de Cervantes");
        insertarLibro(db, "Cien años de soledad", 1, 2, "Obra de Gabriel García Márquez");
        insertarLibro(db, "La sombra del viento", 1, 3, "Novela de Carlos Ruiz Zafón");
        insertarLibro(db, "1984", 2, 1, "Distopía de George Orwell");
        insertarLibro(db, "Rebelión en la granja", 2, 2, "Sátira política");
        insertarLibro(db, "El principito", 2, 3, "Cuento filosófico");
        insertarLibro(db, "Harry Potter", 3, 1, "Saga de magia");
        insertarLibro(db, "El señor de los anillos", 3, 2, "Fantasía épica");
        insertarLibro(db, "Crónica de una muerte anunciada", 3, 3, "Novela corta");
        insertarLibro(db, "Drácula", 4, 1, "Novela de terror");
    }

    private void insertarLibro(SQLiteDatabase db, String nombre, int fila, int columna, String descripcion) {
        ContentValues cv = new ContentValues();
        cv.put("nombre", nombre);
        cv.put("fila", fila);
        cv.put("columna", columna);
        cv.put("descripcion", descripcion);
        db.insert("Libro", null, cv);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS EscapeRoom");
        db.execSQL("DROP TABLE IF EXISTS Libro");
        onCreate(db);
    }
}
