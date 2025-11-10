package com.example.vote;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText etPregunta,etNumVotantes;
    ListView lvPreguntas;
    Button bAdd,bEliminar, bComenzar;

    ArrayList<String> listaPreguntas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etPregunta=findViewById(R.id.etPregunta);
        etNumVotantes=findViewById(R.id.etNumVotantes);
        lvPreguntas=findViewById(R.id.lvPreguntas);
        bAdd=findViewById(R.id.bAdd);
        bEliminar=findViewById(R.id.bEliminar);
        bComenzar =findViewById(R.id.bComenzar);

        listaPreguntas=new ArrayList<>();

        bAdd.setOnClickListener(v -> AddPregunta());
        bEliminar.setOnClickListener(v->BorrarPreguntas());
        bComenzar.setOnClickListener(v-> Comenzar());


    }

    private void AddPregunta() {
        //TODO no permitir preguntas iguales
        String pregunta=etPregunta.getText().toString().trim();
        if(!pregunta.equals("")) {
            listaPreguntas.add(pregunta);
            PoblarLista();
            etPregunta.setText("");
        }
    }

    private void BorrarPreguntas() {
        listaPreguntas.clear();
        PoblarLista();
    }

    private void PoblarLista() {
        lvPreguntas.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listaPreguntas));
    }

    private void Comenzar() {
        int numVotantes;
        try { numVotantes=Integer.parseInt(etNumVotantes.getText().toString().trim()); }
        catch (NumberFormatException ex) { return; }


        Intent intent = new Intent(this, VotarActivity.class);
        intent.putStringArrayListExtra("listaPreguntas",listaPreguntas);
        intent.putExtra("numVotantes",numVotantes);
        startActivity(intent);
    }




}
