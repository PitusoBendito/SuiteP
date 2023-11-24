package com.salmantino.suitep.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.salmantino.suitep.R;


public class Linterna extends Fragment {

    private CameraManager camara;
    private String idCamara;
    private ImageView btnCamara;

    private final int[] MIPMAP_LINTERNA = {R.mipmap.linterna1, R.mipmap.linterna2};
    private boolean encendida;

    public Linterna() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Activity activity = requireActivity();
        View fragmento = inflater.inflate(R.layout.fragment_linterna, container, false);

        // Puede producir NullPointerException si no usa requireActivity()
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        btnCamara = fragmento.findViewById(R.id.linterna);
        camara = (CameraManager) activity.getSystemService(Context.CAMERA_SERVICE);

        // Método que selecciona las cámaras
        try {
            idCamara = camara.getCameraIdList()[0];
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Icono de linterna apagada cuando se entra desde otra actividad
        if (!encendida) {
            btnCamara.setImageResource(MIPMAP_LINTERNA[0]);
        }


        btnCamara.setOnClickListener(view -> {
            // Cambia el estado al hacer click
            encendida = !encendida;

            // Dependiendo del estado, pone una foto diferente
            int i = (encendida) ? 1 : 0;
            btnCamara.setImageResource(MIPMAP_LINTERNA[i]);
            setFlash(encendida);
        });

        return fragmento;
    }


    private void setFlash(boolean encendido) {
        try {
            camara.setTorchMode(idCamara, encendido);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!encendido && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Toast.makeText(requireActivity(), "Flash apagado", Toast.LENGTH_SHORT).show();
        } else if (encendido && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Toast.makeText(requireActivity(), "Flash encendido", Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * Para la linterna cuando se cambia de Fragment.
     */
    public void onStop() {
        super.onStop();
        setFlash(false);
    }

}