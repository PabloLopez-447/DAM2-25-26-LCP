package com.example.eva;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements FrgTv.OnFrgTV {

    int[] ids = {R.id.tv1, R.id.tv2, R.id.tv3};
    FrgTv[] frgTvs = new FrgTv[ids.length];
    ListView list;
    Button btnReset;
    AsistenteBD bd;
    ActivityResultLauncher<Intent> resultLauncher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        list = findViewById(R.id.list);
        btnReset = findViewById(R.id.btnReset);
        bd = new AsistenteBD(this);

        int i = 0;
        for (int id : ids) {
            FrgTv frgTv = (FrgTv) getSupportFragmentManager().findFragmentById(id);
            frgTvs[i++] = frgTv;
            frgTv.setListaCanales(poblarCanales());
            frgTv.setOnFrgTvListener(this);
        }
        poblarListaSuscripciones();
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bd.reset(bd.getReadableDatabase());
                poblarListaSuscripciones();
            }
        });

        resultLauncher =registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        poblarListaSuscripciones();
                    }
                });
    }

    @Override
    public void onCambiarCanal(FrgTv frgTv, Canal canal) {
        switch (checkCanal(canal)){
            case "Correcto" :
                frgTv.setTextoPantalla("Viendo " + canal.getNombre());
                break;
            case "No suscrito":
                frgTv.setTextoPantalla("No suscrito");
                frgTv.setEstadoBtnSub();
                break;
            case "TVS Ocupadas":
                frgTv.setTextoPantalla("Apague otra televisión o haga click para añadir una suscripción");
                break;
        }
    }

    @Override
    public void onBotonSuscripcion(FrgTv frgTv, Canal canal) {
        launcSuscipcion(canal.getCodCanal());
        poblarListaSuscripciones();
    }

    @Override
    public void onAdd1Sub(FrgTv frgTv, Canal canal) {
        if (frgTv.getTextoPantalla().equals("Apague otra televisión o haga click para añadir una suscripción")){
            addSub(canal);
            poblarListaSuscripciones();
        }
    }

    public String checkCanal(Canal canal) {
        SQLiteDatabase db = bd.getReadableDatabase();
        String sql = "SELECT nVisualizaciones FROM Suscripcion WHERE codCanal=? AND codBar=1";
        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(canal.getCodCanal())});
        int nVisualizaciones = 0;
        if (cursor.moveToFirst()) {
            nVisualizaciones = cursor.getInt(0);
        }
        cursor.close();
        db.close();

        if (canal.getVisibilidad().equals("Público")) {
            return "Correcto";
        }

        if (nVisualizaciones == 0){
            return "No suscrito";
        }

        int contador = 0;
        for (FrgTv frgTv : frgTvs) {
            if (frgTv.getCanalSeleccionado() != null && frgTv.getCanalSeleccionado().getCodCanal() == canal.getCodCanal()) {
                contador++;
            }
        }
        if (contador <= nVisualizaciones) {
            return "Correcto";
        }
        return "TVS Ocupadas";
    }

    public List<Canal> poblarCanales() {
        List<Canal> canales = new ArrayList<>();
        SQLiteDatabase db = bd.getReadableDatabase();
        String sql = "SELECT codCanal, nombre, visibilidad FROM Canal";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                int codCanal = cursor.getInt(0);
                String nombre = cursor.getString(1);
                String visibilidad = cursor.getString(2);
                Canal canal = new Canal(codCanal, nombre, visibilidad);
                canales.add(canal);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return canales;
    }

    void poblarListaSuscripciones() {
        List<Suscripcion> suscripciones = new ArrayList<>();
        SQLiteDatabase db = bd.getReadableDatabase();
        String sql = "SELECT codSuscripcion, codBar, codCanal, nomCanal, nVisualizaciones, precioBase, precioPorVisualizacion FROM Suscripcion";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                int codSuscripcion = cursor.getInt(0);
                int codBar = cursor.getInt(1);
                int codCanal = cursor.getInt(2);
                String nomCanal = cursor.getString(3);
                int nVisualizaciones = cursor.getInt(4);
                int precioBase = cursor.getInt(5);
                int precioPorVisualizacion = cursor.getInt(6);

                Suscripcion suscripcion = new Suscripcion(codSuscripcion, codBar, codCanal, nomCanal, precioBase, precioPorVisualizacion, nVisualizaciones);
                suscripciones.add(suscripcion);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, suscripciones);
        list.setAdapter(adapter);
    }

    public void launcSuscipcion(int codCanal){
        Intent intent = new Intent(MainActivity.this, SuscripcionActivity.class);
        intent.putExtra("codCanal", codCanal);
        resultLauncher.launch(intent);
    }

    public void addSub(Canal canal){
        SQLiteDatabase db = bd.getReadableDatabase();
        String sql = "UPDATE Suscripcion SET nVisualizaciones=nVisualizaciones + 1 WHERE codCanal=? AND codBar=1";
        db.execSQL(sql, new Object[]{canal.getCodCanal()});
    }
}