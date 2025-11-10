package com.example.eva;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.List;

public class FrgEditTextCNI extends Fragment {

    private EditText editTextCNI;
    private List<String> listaNegra;
    OnFrgEditTextCNI listener;

    interface OnFrgEditTextCNI{
        String onTextoEncontrado();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_frg_edit_text_c_n_i, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editTextCNI = view.findViewById(R.id.editTextCNI);
        editTextCNI.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                encontrar();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void setListaNegra(List<String> listaNegra) {
        this.listaNegra = listaNegra;
    }

    public void setListener(OnFrgEditTextCNI listener) {
        this.listener = listener;
    }

    public void encontrar(){
        for (String palabra: listaNegra) {
            if (editTextCNI.getText().toString().trim().contains(palabra)){
                listener.onTextoEncontrado();
            }
        }
    }
}