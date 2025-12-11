package com.example.eva;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SuscripcionActivity extends AppCompatActivity {
    TextView textNombre, textPB, textPPV;
    EditText etNVs;
    Button btnSuscribirse;
    int codCanal;
    AsistenteBD bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_suscripcion);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        bd = new AsistenteBD(this);
        codCanal = getIntent().getIntExtra("codCanal", 0);

        textNombre = findViewById(R.id.textNomCanal);
        textPB = findViewById(R.id.textPBase);
        textPPV = findViewById(R.id.textPPV);
        etNVs = findViewById(R.id.etNVs);
        btnSuscribirse = findViewById(R.id.btnSuscribirse);

        iniciar();

        btnSuscribirse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                suscribirse();
                finish();
            }
        });
    }

    void iniciar() {
        SQLiteDatabase db = bd.getReadableDatabase();
        String sql = "SELECT nombre, precioBase, precioPorVisualizacion FROM Canal WHERE codCanal = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(codCanal)});

        cursor.moveToFirst();
        textNombre.setText(cursor.getString(0));
        textPB.setText(String.valueOf(cursor.getInt(1)));
        textPPV.setText(String.valueOf(cursor.getInt(2)));
        cursor.close();
        db.close();
    }

    void suscribirse(){
        int pb = Integer.parseInt(textPB.getText().toString());
        int ppv = Integer.parseInt(textPPV.getText().toString());
        int nVisualizaciones = Integer.parseInt(etNVs.getText().toString());
        String nomCanal = textNombre.getText().toString();

        bd.insertarSuscripcion(bd.getReadableDatabase(), 1, codCanal, nomCanal,pb, ppv, nVisualizaciones);
    }


}