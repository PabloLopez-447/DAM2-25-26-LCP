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
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FrgDado extends Fragment {
    final static Random rnd = new Random();
    Button dado;
    Spinner spinner;
    int nSides;
    List<Integer> history = new ArrayList<>();
    int streak = 0;
    OnFrgDadoListener listener;

    public interface OnFrgDadoListener {
        void OnRoll(int numero,int streak, FrgDado d);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dado, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dado = view.findViewById(R.id.dado);
        spinner = view.findViewById(R.id.spinnerTrampa);
        List<Integer> sides = new ArrayList<>();
        for (int i = 1; i <= nSides; i++) {
            sides.add(i);
        }
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, sides);
        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        dado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                roll(rnd.nextInt(nSides) + 1);
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    roll((Integer) spinner.getSelectedItem());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

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

    public void roll(int n) {
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