package com.example.eva;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;


public class MainActivity extends AppCompatActivity implements FrgDado.OnFrgDado {
    int[] ids = {R.id.dado1, R.id.dado2};
    FrgDado[] dados = new FrgDado[ids.length];
    TextView textTiradas;
    Button btnEmpezar;
    boolean empezar = false;
    int nTiradas;
    int dificultad;
    int rachaMax;




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
        int i = 0;
        FragmentManager fm = getSupportFragmentManager();
        for(int id : ids){
            FrgDado dado = (FrgDado) fm.findFragmentById(id);
            dado.setnCaras(6);
            dado.setOnFrgDadoListener(this);
            dados[i++] = dado;
        }
        textTiradas = findViewById(R.id.textTiradas);
        btnEmpezar = findViewById(R.id.btnEmpezar);

        btnEmpezar.setOnClickListener(v -> {
            empezar = !empezar;
        });

        for (FrgDado d: dados) {
            dificultad+=d.getnCaras();
        }
    }

    @Override
    public void onTirada(FrgDado dado, int numero, int racha) {
        if (empezar){
            nTiradas++;
            textTiradas.setText(String.valueOf(String.valueOf(nTiradas)));
            for (FrgDado d: dados) {
                if (racha > rachaMax){
                    rachaMax = racha;
                }
                if (d.getNumero() != numero){
                    return;
                }
            }
            Toast.makeText(this, "Has ganado!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, ResultadosActivity.class);
            intent.putExtra("nTiradas", nTiradas);
            intent.putExtra("rachaMax", rachaMax);
            intent.putExtra("dificultad", dificultad);
            startActivity(intent);
            empezar = false;
            nTiradas = 0;
            rachaMax = 0;
            textTiradas.setText(String.valueOf(""));
        }
    }

}