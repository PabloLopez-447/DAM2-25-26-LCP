package com.example.dadosclase;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AsistenteBD extends SQLiteOpenHelper {

    private static final String NOMBRE_BD = "dados.db";
    private static final int VERSION_BD = 1;

    public AsistenteBD(Context context) {
        super(context, NOMBRE_BD, null, VERSION_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sqlCreatePartida =
                "CREATE TABLE Partida (" +
                        "codPartida INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "dificultad INTEGER, " +
                        "nTiradas INTEGER, " +
                        "rachaMax INTEGER) ";
        db.execSQL(sqlCreatePartida);
    }

     void insertarPartida(SQLiteDatabase db, int dificultad, int nTiradas, int rachaMax) {
        ContentValues cv = new ContentValues();
        cv.put("dificultad", dificultad);
        cv.put("nTiradas", nTiradas);
        cv.put("rachaMax", rachaMax);
        db.insert("Partida", null, cv);
    }

    void borrarPartidaPorDificultad(SQLiteDatabase db, int dificultad){
        db.delete("Partida", "dificultad = ?", new String[]{String.valueOf(dificultad)});
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Partida");
        onCreate(db);
    }
}
