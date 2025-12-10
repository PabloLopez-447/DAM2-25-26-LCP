package com.example.dadosclase;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FrgDado extends Fragment {

    Random rnd = new Random();
    Button dado;
    Spinner spinner;
    int numero;
    int racha;
    int ultimoNumero;
    int nCaras;
    OnFrgDado listener;

    boolean debug = true;

    public interface OnFrgDado {
        void onTirada(FrgDado dado, int numero, int racha);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_frg_dado, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dado = view.findViewById(R.id.dado);
        spinner = view.findViewById(R.id.spinner);
        List<String> caras = new ArrayList<>();
        caras.add("Seleccionar...");
        for (int i = 1; i <= nCaras; i++) {
            caras.add(String.valueOf(i));
        }
        spinner.setAdapter(new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, caras));

        dado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tiraDado(rnd.nextInt(nCaras) + 1);
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) return;
                tiraDado(Integer.parseInt(spinner.getSelectedItem().toString()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void setOnFrgDadoListener(OnFrgDado listener) {
        this.listener = listener;
    }

    public void setnCaras(int nCaras) {
        this.nCaras = nCaras;
    }

    public int getNumero() {
        return numero;
    }

    public int getnCaras() {
        return nCaras;
    }

    public void tiraDado(int numero) {
        this.numero = numero;
        checkRacha(numero);
        if (debug) {
            dado.setText(String.valueOf(numero) + " (" + String.valueOf(racha) + ")");
        }else{
            dado.setText(String.valueOf(numero));
        }
        listener.onTirada(this, numero, racha);
    }

    public void checkRacha(int numero) {
        if (numero == ultimoNumero) {
            racha++;
        } else {
            racha = 1;
        }
        ultimoNumero = numero;
    }
}