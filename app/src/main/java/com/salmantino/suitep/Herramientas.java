package com.salmantino.suitep;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.salmantino.suitep.utils.Linterna;
import com.salmantino.suitep.utils.Musica;
import com.salmantino.suitep.utils.Nivel;


public class Herramientas extends AppCompatActivity {

    private Fragment[] fragmentos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_herramientas);

        fragmentos = new Fragment[3];
        fragmentos[0] = new Linterna();
        fragmentos[1] = new Nivel();
        fragmentos[2] = new Musica();

        Bundle extras = getIntent().getExtras();
        menu(extras.getInt("ID"));
    }


    public void menu(int id) {
        // Deprecado desde la API 28
        // FragmentManager fManager = getFragmentManager();

        FragmentManager fManager = getSupportFragmentManager();
        FragmentTransaction fTransaccion = fManager.beginTransaction();

        Menu fMenu = new Menu();
        Bundle datos = new Bundle();
        datos.putInt("ID", id);

        fMenu.setArguments(datos);
        fTransaccion.replace(R.id.menu, fMenu);
        fTransaccion.replace(R.id.herramientas, fragmentos[id]);
        fTransaccion.commit();
    }


    public void onBackPressed() {
        super.onBackPressed();
        finish();
        // moveTaskToBack(true);
    }
}