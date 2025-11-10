package com.example.eva;

import android.content.Context;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.content.Context;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AsistenteBD extends SQLiteOpenHelper {
    private static final String NOMBRE_BD = "elecciones.db";
    private static final int VERSION_BD = 2;

    public AsistenteBD(Context context) {
        super(context, NOMBRE_BD, null, VERSION_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sqlCreatePartido =
                "CREATE TABLE Partido (" +
                        "codPartido INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "nombre TEXT)";
        db.execSQL(sqlCreatePartido);


        String sqlCreateCandidato =
                "CREATE TABLE Candidato (" +
                        "codCandidato INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "nombre TEXT, " +
                        "codPartido INTEGER, " +
                        "totVotos INTEGER)";
        db.execSQL(sqlCreateCandidato);


        String sqlCreateVotante =
                "CREATE TABLE Votante (" +
                        "NIF TEXT PRIMARY KEY, " +
                        "nombre TEXT, " +
                        "numVotos INTEGER, " +
                        "haVotado INTEGER)";
        db.execSQL(sqlCreateVotante);


        ContentValues cv = new ContentValues();
        cv.put("nombre", "Partido Azul");
        db.insert("Partido", null, cv);
        cv.put("nombre", "Partido Rojo");
        db.insert("Partido", null, cv);
        cv.put("nombre", "Partido Verde");
        db.insert("Partido", null, cv);
        cv.put("nombre", "Partido Amarillo");
        db.insert("Partido", null, cv);


        String[] partidos = {"Azul", "Rojo", "Verde", "Amarillo"};
        int codPartido = 1;

        for (String partido : partidos) {
            for (int i = 1; i <= 3; i++) {
                cv.clear();
                cv.put("nombre", "Candidato " + partido + " " + i);
                cv.put("codPartido", codPartido);
                cv.put("totVotos", 0); // inicializa con 0 votos
                db.insert("Candidato", null, cv);
            }
            codPartido++;
        }

        cv.clear();
        cv.put("NIF", "12345678A");
        cv.put("nombre", "Carlos Gómez");
        cv.put("numVotos", 3);
        cv.put("haVotado", 0);
        db.insert("Votante", null, cv);

        cv.clear();
        cv.put("NIF", "87654321B");
        cv.put("nombre", "Lucía Fernández");
        cv.put("numVotos", 3);
        cv.put("haVotado", 0);
        db.insert("Votante", null, cv);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Candidato");
        db.execSQL("DROP TABLE IF EXISTS Partido");
        db.execSQL("DROP TABLE IF EXISTS Votante");
        onCreate(db);
    }
}

