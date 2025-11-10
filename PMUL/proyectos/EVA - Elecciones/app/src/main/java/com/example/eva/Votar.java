package com.example.eva;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Votar extends AppCompatActivity {

    Spinner spinnerCandidatos;
    TextView txtPartido;
    Button btnVotar;
    AsistenteBD asistenteBD;
    int votosRestantes = 3;
    Set<Integer> candidatosVotados = new HashSet<>();
    ArrayList<Candidato> listaCandidatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_votar);

        spinnerCandidatos = findViewById(R.id.spinnerCandidatos);
        txtPartido = findViewById(R.id.txtPartido);
        btnVotar = findViewById(R.id.btnVotar);

        asistenteBD = new AsistenteBD(this);

        poblarListaCandidatos(this);

        spinnerCandidatos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Candidato c = listaCandidatos.get(position);
                txtPartido.setText(c.getPartido());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        btnVotar.setOnClickListener(v -> votarCandidato());
    }


    private void poblarListaCandidatos(Context context) {
        listaCandidatos = new ArrayList<>();

        SQLiteDatabase db = asistenteBD.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT C.codCandidato, C.nombre, P.nombre AS partido, C.totVotos " +
                        "FROM Candidato C JOIN Partido P ON C.codPartido = P.codPartido", null);

        if (cursor.moveToFirst()) {
            do {
                int cod = cursor.getInt(0);
                String nombre = cursor.getString(1);
                String partido = cursor.getString(2);
                int votos = cursor.getInt(3);
                listaCandidatos.add(new Candidato(cod, nombre, partido, votos));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        ArrayAdapter<String> adaptador = new ArrayAdapter<>(
                context,
                android.R.layout.simple_spinner_item,
                getNombresMostrar(listaCandidatos)
        );
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCandidatos.setAdapter(adaptador);
    }


    private ArrayList<String> getNombresMostrar(ArrayList<Candidato> lista) {
        ArrayList<String> nombres = new ArrayList<>();
        for (Candidato c : lista) {
            nombres.add(c.getNombre() + " (" + c.getPartido() + ")");
        }
        return nombres;
    }


    private void votarCandidato() {
        int pos = spinnerCandidatos.getSelectedItemPosition();
        Candidato candidatoSeleccionado = listaCandidatos.get(pos);

        if (candidatosVotados.contains(candidatoSeleccionado.getCodCandidato())) {
            Toast.makeText(this, "Ya votaste a este candidato", Toast.LENGTH_SHORT).show();
            return;
        }

        candidatosVotados.add(candidatoSeleccionado.getCodCandidato());
        votosRestantes--;

        SQLiteDatabase db = asistenteBD.getWritableDatabase();
        db.execSQL("UPDATE Candidato SET totVotos = totVotos + 1 WHERE codCandidato = ?",
                new Object[]{candidatoSeleccionado.getCodCandidato()});
        db.close();

        if (votosRestantes > 0) {
            Toast.makeText(this, "Voto registrado. Te quedan " + votosRestantes + " votos.", Toast.LENGTH_SHORT).show();
        } else {
            btnVotar.setEnabled(false);
            mostrarGanadores();
        }
    }

    private void mostrarGanadores() {
        SQLiteDatabase db = asistenteBD.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT nombre, totVotos FROM Candidato ORDER BY totVotos DESC LIMIT 3", null);

        StringBuilder mensaje = new StringBuilder("Votación cerrada. Top 3 candidatos:\n");
        int pos = 1;
        while (cursor.moveToNext()) {
            mensaje.append(pos++)
                    .append(". ")
                    .append(cursor.getString(0))
                    .append(" - ")
                    .append(cursor.getInt(1))
                    .append(" votos\n");
        }

        cursor.close();
        db.close();

        Toast.makeText(this, mensaje.toString(), Toast.LENGTH_LONG).show();
    }
}