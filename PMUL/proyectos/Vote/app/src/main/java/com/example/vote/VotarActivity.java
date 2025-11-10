package com.example.vote;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class VotarActivity extends AppCompatActivity {

    ArrayList<String> listaPreguntas;
    int[] resultados;
    int numVotantes,numVotantesTmp=0;

    private LinearLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_votar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle == null) finish();
        numVotantes=bundle.getInt("numVotantes");
        listaPreguntas = bundle.getStringArrayList("listaPreguntas");
        if (listaPreguntas == null) finish();
        resultados=new int[listaPreguntas.size()];
        mainLayout = findViewById(R.id.mainLayout);
        generarBotones(listaPreguntas);
    }

    private void generarBotones(ArrayList<String> listaPreguntas) {
        mainLayout.removeAllViews();
        int i=0;
        for(String pregunta:listaPreguntas) {
            Button button=new Button(this);
            button.setText(pregunta);
            int finalI=i++;
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Voto(finalI);
                    Toast.makeText(VotarActivity.this,"Gracias por su voto",Toast.LENGTH_SHORT).show();
                }
            });
            mainLayout.addView(button);
        }
    }

    private void Voto(int numPregunta) {
        resultados[numPregunta]++;
        if(++numVotantesTmp==numVotantes) {
            StringBuilder sb=new StringBuilder();
            int i=0;
            for(String pregunta:listaPreguntas)
                sb.append(resultados[i++]).append(" ").append(pregunta).append("\n");

            int textSize=30;
            mainLayout.removeAllViews();
            TextView tvResultados=new TextView(this);
            tvResultados.setText(sb.toString());
            tvResultados.setTextSize(textSize);
            Button bSalir=new Button(this);
            bSalir.setText("Salir");
            bSalir.setOnClickListener(v -> finish());
            bSalir.setTextSize(textSize);
            mainLayout.addView(tvResultados);
            mainLayout.addView(bSalir);
        }
    }

}
