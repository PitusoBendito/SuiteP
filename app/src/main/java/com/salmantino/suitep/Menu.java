package com.salmantino.suitep;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

import com.salmantino.suitep.interfaces.IMenu;

/**
 * A simple {@link Fragment} subclass.
 */
public class Menu extends Fragment {

    private final int[] BTN_ID = {R.id.linterna, R.id.nivel, R.id.musica};
    private final int[] BTN_ACTIVO = {R.mipmap.linterna2, R.mipmap.nivel2, R.mipmap.musica2};

    private int btn;

    public Menu() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View menu = inflater.inflate(R.layout.fragment_menu, container, false);

        // ?
        btn = -1;

        if (getArguments() != null) {
            btn = getArguments().getInt("ID");
        }

        ImageButton btnMenu;

        for (int i = 0; i < BTN_ID.length; i++) {
            btnMenu = menu.findViewById(BTN_ID[i]);

            if (btn == i) {
                btnMenu.setImageResource(BTN_ACTIVO[i]);
            }

            // Se necesita un entero final para menu()
            final int ID = i;
            btnMenu.setOnClickListener(view -> {
                Activity activity = requireActivity();
                ((IMenu) activity).menu(ID);
            });
        }

        return menu;
    }

    public void menu(int id) {
        Intent intent = new Intent(requireActivity(), Herramientas.class);
        intent.putExtra("ID", id);
        startActivity(intent);
    }
}