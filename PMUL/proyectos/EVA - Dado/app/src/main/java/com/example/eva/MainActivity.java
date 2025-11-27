package com.example.eva;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity {
    FrgDado dado1,dado2;
    TextView nTiradas;
    Button btnStart;
    int numTiradas;

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
        nTiradas = findViewById(R.id.textView);
        btnStart = findViewById(R.id.buttonStart);
        FragmentManager fm = getSupportFragmentManager();
        dado1 = (FrgDado) fm.findFragmentById(R.id.dado1);
        dado2 = (FrgDado) fm.findFragmentById(R.id.dado2);

        dado1.setnSides(6);
        dado2.setnSides(6);

        FrgDado.OnFrgDadoListener listener = new FrgDado.OnFrgDadoListener() {
            @Override
            public void OnRoll(int numero, int streak, FrgDado d) {
                numTiradas++;
                nTiradas.setText(String.valueOf(numTiradas - 2));
            }
        };

        dado1.setOnFrgDadoListener(listener);
        dado2.setOnFrgDadoListener(listener);

    }
}