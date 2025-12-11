package com.example.eva;

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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class FrgTv extends Fragment {

    Spinner spinner;
    Button btnCambiarCanal, btnSub;
    TextView pantalla;
    OnFrgTV listener;

    Canal canalSeleccionado;
    List<Canal> listaCanales = new ArrayList<>();


    public interface OnFrgTV{
        void onCambiarCanal(FrgTv frgTv, Canal canal);
        void onBotonSuscripcion(FrgTv frgTv, Canal canal);
        void onAdd1Sub(FrgTv frgTv, Canal canal);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_frg_tv, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        spinner = view.findViewById(R.id.spinner);
        btnCambiarCanal = view.findViewById(R.id.buttonVer);
        btnSub = view.findViewById(R.id.btnSub);
        btnSub.setEnabled(false);
        pantalla = view.findViewById(R.id.textCanal);

        spinner.setAdapter(new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, listaCanales));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) return;
                Canal canal = listaCanales.get(i);
                ponerCanalEnPantalla(canal);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnCambiarCanal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i = spinner.getSelectedItemPosition();
                if (i == 0) return;
                Canal canal = listaCanales.get(i);
                ponerCanalEnPantalla(canal);
            }
        });

        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onBotonSuscripcion(FrgTv.this, canalSeleccionado);
            }
        });

        pantalla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onAdd1Sub(FrgTv.this, canalSeleccionado);
            }
        });
    }

    public void setTextoPantalla(String texto) {
        pantalla.setText(texto);
    }

    public String getTextoPantalla(){
        return pantalla.getText().toString();
    }

    public void setOnFrgTvListener(OnFrgTV listener) {
        this.listener = listener;
    }

    public void setListaCanales(List<Canal> listaCanales) {
        this.listaCanales.add(new Canal(0, "Seleccione un canal", ""));
        this.listaCanales.addAll(listaCanales);
    }

    void ponerCanalEnPantalla(Canal canal) {
        canalSeleccionado = canal;
        listener.onCambiarCanal(this, canal);
    }

    public Canal getCanalSeleccionado() {
        return canalSeleccionado;
    }

    public void setEstadoBtnSub(){
        btnSub.setEnabled(!btnSub.isEnabled());
    }
}