package es.uniovi.eii.voluntacovid.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import es.uniovi.eii.voluntacovid.R;
import es.uniovi.eii.voluntacovid.datos.UsuariosDataSource;
import es.uniovi.eii.voluntacovid.modelo.Ayuda;
import es.uniovi.eii.voluntacovid.modelo.Usuario;


public class InfoUsuarioFragment extends DialogFragment {

    Activity actividad;

    ImageButton btnLlamar,btnCerrar;
    TextView txUsuario,txDireccion,txCodigoPostal,txTelefono;
    private Usuario usuario;

    public InfoUsuarioFragment() {
        // Required empty public constructor
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return crearDialogoInformacion();
    }

    private Dialog crearDialogoInformacion(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.fragment_info_usuario,null);
        builder.setView(v);

        UsuariosDataSource usuariosDataSource = new UsuariosDataSource(actividad);
        usuariosDataSource.open();
        usuario = usuariosDataSource.getUserByUser(getArguments().getString("usuario"));

        txUsuario = v.findViewById(R.id.txInformacionUsuario);
        txDireccion = v.findViewById(R.id.txInformacionDireccion);
        txCodigoPostal = v.findViewById(R.id.txInformacionCodigoPostal);
        txTelefono = v.findViewById(R.id.txInformacionTelefono);
        btnLlamar = v.findViewById(R.id.imageButtonLlamar);
        btnCerrar = v.findViewById(R.id.imageButtonCerrar);

        btnLlamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        btnLlamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call(String.valueOf(usuario.getTelefono()));
            }
        });

        txUsuario.setText(usuario.getUsuario());
        txDireccion.setText(usuario.getDireccion());
        txCodigoPostal.setText(usuario.getCodigoPostal()+"");
        txTelefono.setText(usuario.getTelefono()+"");
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof Activity){
            this.actividad = (Activity) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void call(String numero){
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + numero)));
    }
}