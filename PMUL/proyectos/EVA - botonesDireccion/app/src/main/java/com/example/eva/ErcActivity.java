package com.example.botonedireccion;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;
import java.util.List;

public class ErcActivity extends AppCompatActivity {

    String solucion = "";
    String mioStr = "";
    int max_intentos;
    int intentos = 0;
    FrgBotonesDir frgBotonesDir;
    TextView mio, sol;
    ListView lista;
    AsistenteBD bd;
    List<String> listRooms = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_erc);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        bd = new AsistenteBD(this);
        max_intentos = getIntent().getIntExtra("max_intentos", 0);
        FragmentManager fm = getSupportFragmentManager();
        frgBotonesDir = (FrgBotonesDir) fm.findFragmentById(R.id.botones);
        lista = findViewById(R.id.lista);
        poblarLista();
        mio = findViewById(R.id.mio);
        sol = findViewById(R.id.sol);

        sol.setText(solucion);

        frgBotonesDir.setOnFrgBtnDirListener(new FrgBotonesDir.OnFrgBtnDir() {
            @Override
            public void OnBtnDir(FrgBotonesDir f, FrgBotonesDir.Dirs dir) {
                switch (dir){
                    case UP -> {
                        mioStr += "U";
                        mio.setText(mioStr);
                    }
                    case DOWN -> {
                        mioStr += "D";
                        mio.setText(mioStr);
                    }
                    case LEFT -> {
                        mioStr += "L";
                        mio.setText(mioStr);
                    }
                    case RIGHT -> {
                        mioStr += "R";
                        mio.setText(mioStr);
                    }
                }
            }

            @Override
            public void OnBtnIntro(FrgBotonesDir f) {
                if (verify()){
                    Toast.makeText(ErcActivity.this, "Correcto", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else if (intentos >= max_intentos){
                    Toast.makeText(ErcActivity.this, "Perdistes vaya puto malo xddddd", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else{
                    Toast.makeText(ErcActivity.this, "Fallastes dude" , Toast.LENGTH_SHORT).show();
                    mioStr = "";
                    mio.setText(mioStr);
                    intentos++;
                }
            }
        });

        lista.setOnItemClickListener((parent, view, position, id) -> {
            String nombreSeleccionado = parent.getItemAtPosition(position).toString();
            selectRoom(nombreSeleccionado);
            mioStr = "";
            mio.setText(mioStr);
            intentos = 0;
            lista.setEnabled(false);
        });

    }

    void poblarLista(){
        SQLiteDatabase db = bd.getReadableDatabase();
        String sql = "SELECT nombre FROM EscapeRoom";
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()){
            do {
                listRooms.add(cursor.getString(0));
            } while (cursor.moveToNext());

        }
        else{
            Toast.makeText(this, "No hay datos", Toast.LENGTH_SHORT).show();
        }
        cursor.close();
        db.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listRooms);
        lista.setAdapter(adapter);
    }

    boolean verify(){
        return mioStr.equals(solucion);
    }

    void selectRoom(String nombreRoom){
        SQLiteDatabase db = bd.getReadableDatabase();
        String sql = "SELECT solucion FROM EscapeRoom WHERE nombre = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{nombreRoom});

        if (cursor.moveToFirst()){
            solucion = cursor.getString(0);
            sol.setText(solucion);
        }
        else{
            Toast.makeText(this, "No hay datos", Toast.LENGTH_SHORT).show();
        }

        cursor.close();
        db.close();
    }

}