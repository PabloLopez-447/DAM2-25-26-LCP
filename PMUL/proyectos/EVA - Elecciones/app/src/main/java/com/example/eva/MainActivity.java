package com.example.eva;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView nif, passwd;
    Button login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        nif = findViewById(R.id.editTextNIF);
        passwd = findViewById(R.id.editTextTextPassword);
        login = findViewById(R.id.buttonLI);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        login.setOnClickListener(v -> logIn());
    }

    public void logIn() {
        SQLiteDatabase db = new AsistenteBD(this).getReadableDatabase();
        Intent intent = new Intent(this, Votar.class);
        Cursor cursor = db.rawQuery("SELECT * FROM Votante WHERE NIF=?", new String[]{nif.getText().toString().trim()});
        if (cursor.moveToFirst()) {
            startActivity(intent);
        } else {
            Toast.makeText(this, getString(R.string.nif_no_registrado), Toast.LENGTH_LONG).show();
        }
        cursor.close();
    }
}
