package com.example.eva;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FrgDado extends Fragment {
    Button dado;
    int nSides;
    List<Integer> history = new ArrayList<>();
    int streak = 0;
    OnFrgDadoListener listener;

    public interface OnFrgDadoListener {
        void OnRoll(int numero,int streak, FrgDado d);
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

    public int getnSides() {
        return nSides;
    }

    public void setnSides(int nSides) {
        this.nSides = nSides;
    }

    public void setOnFrgDadoListener(OnFrgDadoListener listener) {
        this.listener = listener;
    }

    public void setStreak(int streak) {
        this.streak = streak;
    }

    public void roll() {
        Random rnd = new Random();
        int n = rnd.nextInt(this.nSides) + 1;
        dado.setText(String.valueOf(n));
        checkStreak(n);
        this.listener.OnRoll(n, streak, this);
    }

    private void checkStreak(int n){
        if (history.isEmpty()){
            history.add(n);
        }
        if (history.contains(n)){
            streak++;
        }else {
            history.clear();
            setStreak(1);
        }

    }
}