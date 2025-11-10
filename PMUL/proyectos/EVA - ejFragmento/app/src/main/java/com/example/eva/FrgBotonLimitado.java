package com.example.eva;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FrgBotonLimitado extends Fragment {
    private OnFrgBLClickListener listener;
    private int maxClics;
    int numClics = 0;

    public void setMaxClics(int maxClics) {
        this.maxClics = maxClics;
    }

    public void deshacerClic() {
        numClics--;
    }

    interface OnFrgBLClickListener {
        boolean onClick(int numClics, int maxClics);
        void onLastClick();
    }

    Button boton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = LayoutInflater.from(getActivity()).inflate(R.layout.frg_boton_limitado, null);
        boton = layout.findViewById(R.id.boton);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click();
            }
        });
        return layout;
    }

    public void setText(String texto) {
        boton.setText(texto);
    }

    public void setOnFrgBotonLimitadoListener(OnFrgBLClickListener listener) {
        this.listener = listener;

    }

    public void click() {
        numClics++;
        if (numClics <= maxClics) {
            listener.onClick(numClics, maxClics);

        }
    }


}
