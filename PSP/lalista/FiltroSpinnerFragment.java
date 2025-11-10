package com.example.filtrarspinner;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class FiltroSpinnerFragment extends Fragment {

    private EditText editTextFiltro;
    private Spinner spinner;
    private ArrayAdapter<String> adapter;
    private List<String> listaOriginal = new ArrayList<>();
    private List<String> listaFiltrada = new ArrayList<>();

    private OnItemSelectedListener listener;

    /**
     * Interfaz para comunicar el fragment con la Activity o contenedor
     */
    public interface OnItemSelectedListener {
        void onItemSelected(String item);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnItemSelectedListener) {
            listener = (OnItemSelectedListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " debe implementar FiltroSpinnerFragment.OnItemSelectedListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filtro_spinner, container, false);

        editTextFiltro = view.findViewById(R.id.editTextFiltro);
        spinner = view.findViewById(R.id.spinner);

        adapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_spinner_dropdown_item,
                listaFiltrada);
        spinner.setAdapter(adapter);

        // Listener del Spinner
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (listener != null && position >= 0 && position < listaFiltrada.size()) {
                    listener.onItemSelected(listaFiltrada.get(position));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        // TextWatcher para filtrar dinámicamente
        editTextFiltro.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filtrar(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        return view;
    }

    /**
     * Establece la lista de datos a mostrar en el Spinner
     */
    public void setListaDatos(List<String> datos) {
        listaOriginal.clear();
        listaOriginal.addAll(datos);
        filtrar(""); // Mostrar todos inicialmente
    }

    /**
     * Aplica el filtro sobre la lista original
     */
    private void filtrar(String texto) {
        listaFiltrada.clear();

        if (texto.isEmpty()) {
            listaFiltrada.addAll(listaOriginal);
        } else {
            for (String item : listaOriginal) {
                if (item.toLowerCase().contains(texto.toLowerCase())) {
                    listaFiltrada.add(item);
                }
            }
        }

        adapter.notifyDataSetChanged();
    }
}
