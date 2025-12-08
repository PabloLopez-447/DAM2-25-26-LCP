package com.example.botonedireccion;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity {

    EditText errs;
    Button erc, neb;


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

        errs = findViewById(R.id.errs);
        erc = findViewById(R.id.ERC);
        neb = findViewById(R.id.NEB);

        erc.setOnClickListener(v -> {
            launchERC();
        });
        neb.setOnClickListener(v -> {
            launchNEB();
        });
    }

    public void launchERC() {
        try {
            int n = Integer.parseInt(errs.getText().toString());
            if (n < 0) {
                Toast.makeText(MainActivity.this, "Introduce un numero valido bobo", Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent = new Intent(MainActivity.this, ErcActivity.class);
            intent.putExtra("max_intentos", n);
            startActivity(intent);
        } catch (NumberFormatException e) {
            Toast.makeText(MainActivity.this, "Introduce un numero valido bobo", Toast.LENGTH_SHORT).show();
        }
    }

    public void launchNEB() {
        Intent intent = new Intent(MainActivity.this, NebActivity.class);
        startActivity(intent);
    }
}
