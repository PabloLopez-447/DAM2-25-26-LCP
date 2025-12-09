package com.example.eva;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;

public class NebActivity extends AppCompatActivity {

    final int MAX_FILAS = 5;
    final int MAX_COLUMNAS = 5;
    TextView textName, textDesc;
    FrgBotonesDir botones;
    AsistenteBD bd;
    int fila, columna;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_neb);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        fila = 1;
        columna = 1;
        textName = findViewById(R.id.textName);
        textDesc = findViewById(R.id.textDesc);
        bd = new AsistenteBD(this);
        buscarLibro(fila, columna);

        FragmentManager fm = getSupportFragmentManager();
        botones = (FrgBotonesDir) fm.findFragmentById(R.id.botones);

        botones.setOnFrgBtnDirListener(new FrgBotonesDir.OnFrgBtnDir() {
            @Override
            public void OnBtnDir(FrgBotonesDir f, FrgBotonesDir.Dirs dir) {
                switch (dir) {
                    case UP -> {
                        if (fila > 1) {
                            fila--;
                            buscarLibro(fila, columna);
                        } else {
                            Toast.makeText(NebActivity.this, "Fuera de los limites", Toast.LENGTH_SHORT).show();
                        }
                    }
                    case DOWN -> {
                        if (fila < MAX_FILAS) {
                            fila++;
                            buscarLibro(fila, columna);
                        } else {
                            Toast.makeText(NebActivity.this, "Fuera de los limites", Toast.LENGTH_SHORT).show();
                        }
                    }
                    case LEFT -> {
                        if (columna > 1) {
                            columna--;
                            buscarLibro(fila, columna);
                        } else {
                            Toast.makeText(NebActivity.this, "Fuera de los limites", Toast.LENGTH_SHORT).show();
                        }
                    }
                    case RIGHT -> {
                        if (columna < MAX_COLUMNAS) {
                            columna++;
                            buscarLibro(fila, columna);
                        } else {
                            Toast.makeText(NebActivity.this, "Fuera de los limites", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }

            @Override
            public void OnBtnIntro(FrgBotonesDir f) {
                finish();
            }
        });
    }

    void buscarLibro(int fila, int columna) {
        SQLiteDatabase db = bd.getReadableDatabase();
        String sql = "SELECT nombre, descripcion FROM Libro WHERE fila = ? AND columna = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(fila), String.valueOf(columna)});

        if (cursor.moveToFirst()) {
            String nombre = cursor.getString(0);
            String descripcion = cursor.getString(1);
            textName.setText(nombre);
            textDesc.setText(descripcion);
        } else {
            textName.setText("Hueco vacio");
            textDesc.setText("No hay libros en esta posicion manin espabila y vete a otro");
        }

        cursor.close();
        db.close();
    }

}
