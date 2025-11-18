package com.example.eva;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Random;

public class Dado extends Fragment {
    Button dado;
    int nCaras;
    OnFrgDadoListener listener;

    public interface OnFrgDadoListener {
        void OnRoll(int numero, Dado d);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        dado = new Button(this.getContext());
        dado.setText("");
        return dado;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                roll();
            }
        });
    }

    public int getnCaras() {
        return nCaras;
    }

    public void setnCaras(int nCaras) {
        this.nCaras = nCaras;
    }

    public OnFrgDadoListener getListener() {
        return listener;
    }

    public void setOnFrgDadoListener(OnFrgDadoListener listener) {
        this.listener = listener;
    }

    public void roll() {
        Random rnd = new Random();
        int n = rnd.nextInt(this.nCaras);
        this.listener.OnRoll(n, this);
    }
}