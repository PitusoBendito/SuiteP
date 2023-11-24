package com.salmantino.suitep;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.salmantino.suitep.interfaces.IMenu;

public class MainActivity extends AppCompatActivity implements IMenu {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setStatusBarColor(getResources().getColor(R.color.red, getTheme()));
    }

    @Override
    public void menu(int id) {
        Intent intent = new Intent(this, Herramientas.class);
        intent.putExtra("ID", id);
        startActivity(intent);
    }

}