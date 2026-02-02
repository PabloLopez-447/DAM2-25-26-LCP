package com.example.dadosclase;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class ResultadosActivity extends AppCompatActivity {
    int dificultad, nTiradas, rachaMax;
    AsistenteBD bd;
    ListView listView;
    Button btnBorrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_resultados);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        bd = new AsistenteBD(this);
        listView = findViewById(R.id.list);
        btnBorrar = findViewById(R.id.btnBorrar);

        nTiradas = getIntent().getIntExtra("nTiradas", 0);
        rachaMax = getIntent().getIntExtra("rachaMax", 0);
        dificultad = getIntent().getIntExtra("dificultad", 0);

        bd.insertarPartida(bd.getReadableDatabase(), dificultad, nTiradas, rachaMax);

        poblarLista();

        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bd.borrarPartidaPorDificultad(bd.getReadableDatabase(), dificultad);
                poblarLista();
            }
        });

    }

    void poblarLista(){
        SQLiteDatabase db = bd.getReadableDatabase();
        String sql = "SELECT dificultad, nTiradas, rachaMax FROM Partida WHERE dificultad = ?";
        Cursor c = db.rawQuery(sql, new String[]{String.valueOf(dificultad)});
        List<Partida> partidas = new ArrayList<>();

        if(c.moveToFirst()){
            do{
                int dificultad = c.getInt(0);
                int nTiradas = c.getInt(1);
                int rachaMax = c.getInt(2);

                partidas.add(new Partida(dificultad, nTiradas, rachaMax));

            }while (c.moveToNext());

        }
        c.close();
        db.close();
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,  partidas));
    }
}