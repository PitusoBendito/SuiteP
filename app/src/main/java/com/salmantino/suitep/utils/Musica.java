package com.salmantino.suitep.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.salmantino.suitep.R;
import com.salmantino.suitep.ServicioMusica;

/**
 * A simple {@link Fragment} subclass.
 */
public class Musica extends Fragment {

    private ImageView btnMusica;

    private boolean encendido;
    private final int[] MIPMAP_MUSICA = {R.mipmap.musica1, R.mipmap.musica2};

    public Musica() {
        // Required empty public constructor
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Activity activity = requireActivity();
        View fragmento = inflater.inflate(R.layout.fragment_musica, container, false);

        btnMusica = fragmento.findViewById(R.id.musica);

        if (!encendido)
            btnMusica.setImageResource(MIPMAP_MUSICA[0]);

        // Puede producir NullPointerException si no usa requireActivity()
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);



        btnMusica.setOnClickListener(view -> {
            // Cambia el estado al hacer click
            encendido = !encendido;

            // Dependiendo del estado, pone una foto diferente
            int i = (encendido) ? 1 : 0;
            btnMusica.setImageResource(MIPMAP_MUSICA[i]);
            setMusica(encendido);
        });

        return fragmento;
    }


    private void setMusica(boolean encendido) {
        Activity activity = requireActivity();
        Intent reproductor = new Intent(activity, ServicioMusica.class);

        if (!encendido) {
            activity.stopService(reproductor);
        } else {
            activity.startService(reproductor);
        }
    }


    /*
     */
    public void onPause() {
        super.onPause();

        Intent reproductor = new Intent(requireActivity(), ServicioMusica.class);
        requireActivity().stopService(reproductor);
    }

}