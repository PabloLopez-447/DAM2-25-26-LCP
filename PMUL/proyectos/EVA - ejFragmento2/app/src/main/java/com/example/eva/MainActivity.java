package com.example.eva;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    FrgEditTextCNI frgCNIPara, frgCNIAsunto;
    ListView list;
    List<String> paraBan = new ArrayList<>();
    List<String> asuntoBan = new ArrayList<>();
    List<String> encontradas = new ArrayList<>();

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
        paraBan.add("petersancius@gmail.com");
        paraBan.add("manin@gmail.com");
        asuntoBan.add("bomba");
        asuntoBan.add("fuego");

        list = (ListView) findViewById(R.id.listView);
        FragmentManager fm = getSupportFragmentManager();
        frgCNIPara = (FrgEditTextCNI) fm.findFragmentById(R.id.frgCNIPara);
        frgCNIAsunto = (FrgEditTextCNI) fm.findFragmentById(R.id.frgCNIAsunto);

        frgCNIPara.setListaNegra(paraBan);
        frgCNIAsunto.setListaNegra(asuntoBan);

        frgCNIPara.setListener(new FrgEditTextCNI.OnFrgEditTextCNI() {
            @Override
            public void onTextoEncontrado(String palabra) {
                poblarLista(palabra);
            }
        });

        frgCNIAsunto.setListener(new FrgEditTextCNI.OnFrgEditTextCNI() {
            @Override
            public void onTextoEncontrado(String palabra) {
                poblarLista(palabra);
            }
        });


    }

    public void poblarLista(String palabra) {
        if (!encontradas.contains(palabra)) {
            encontradas.add(palabra);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, encontradas);
            list.setAdapter(adapter);
        }
    }


}