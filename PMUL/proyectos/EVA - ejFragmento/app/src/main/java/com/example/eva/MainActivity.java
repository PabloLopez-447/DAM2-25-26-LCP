package com.example.eva;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity {

    private static final int MAX_INTENTOS = 3;
    EditText etUsuario, etPassword;
    FrgBotonLimitado bEntrar;


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

        etUsuario = findViewById(R.id.etUsuario);
        etPassword = findViewById(R.id.etPassword);
        FragmentManager fm = getSupportFragmentManager();
        bEntrar = (FrgBotonLimitado) fm.findFragmentById(R.id.bEntrar);
        bEntrar.setMaxClics(MAX_INTENTOS);
        bEntrar.setOnFrgBotonLimitadoListener(new FrgBotonLimitado.OnFrgBLClickListener() {
            @Override
            public boolean onClick(int numClics, int maxClics) {
                return entrar();
            }
            @Override
            public void onLastClick(){

            }
        });

    }

    public boolean entrar() {
        String user = etUsuario.getText().toString().trim();
        String pass = etPassword.getText().toString().trim();

        if (user.equals("") || pass.equals("")) {
            Toast.makeText(this, "No cuenta", Toast.LENGTH_LONG).show();
            return false;
        } else {
            if (user.equals("pablo") && pass.equals("niga")) {
                Toast.makeText(this, "Entra", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "MAL", Toast.LENGTH_LONG).show();
            }
            return true;
        }
    }
}