package com.example.botonedireccion;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FrgBotonesDir extends Fragment {

    Button up, down, left, right, intro;
    OnFrgBtnDir listener;

    public enum Dirs{
        UP, DOWN, LEFT, RIGHT
    }
    public interface OnFrgBtnDir {
        void OnBtnDir(FrgBotonesDir f, Dirs dir);
        void OnBtnIntro(FrgBotonesDir f);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_frg_botones_dir, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        up = view.findViewById(R.id.up);
        down = view.findViewById(R.id.down);
        left = view.findViewById(R.id.left);
        right = view.findViewById(R.id.right);
        intro = view.findViewById(R.id.intro);

        up.setOnClickListener(v -> listener.OnBtnDir(this, Dirs.UP));
        down.setOnClickListener(v -> listener.OnBtnDir(this, Dirs.DOWN));
        left.setOnClickListener(v -> listener.OnBtnDir(this, Dirs.LEFT));
        right.setOnClickListener(v -> listener.OnBtnDir(this, Dirs.RIGHT));
        intro.setOnClickListener(v -> listener.OnBtnIntro(this));
    }

    public void setOnFrgBtnDirListener(OnFrgBtnDir listener) {
        this.listener = listener;
    }
}