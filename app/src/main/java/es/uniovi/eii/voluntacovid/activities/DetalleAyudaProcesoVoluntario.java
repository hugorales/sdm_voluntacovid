package es.uniovi.eii.voluntacovid.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import es.uniovi.eii.voluntacovid.R;
import es.uniovi.eii.voluntacovid.fragments.InfoUsuarioFragment;
import es.uniovi.eii.voluntacovid.modelo.Ayuda;

public class DetalleAyudaProcesoVoluntario extends AppCompatActivity {

    private TextView edtTitulo,edtDescripcion,edtFecha,edtEstado,edtUrgencia,edtNecesitado;
    private Button btnInformacionNecesitado;
    private Ayuda ayuda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_ayuda_proceso_voluntario);

        edtTitulo = (TextView) findViewById(R.id.txTituloProcesoVoluntarioDetalle1);
        edtDescripcion = (TextView) findViewById(R.id.txDescripcionProcesoVoluntarioDetalle1);
        edtFecha = (TextView) findViewById(R.id.txFechaProcesoVoluntarioDetalle1);
        edtEstado = (TextView) findViewById(R.id.txEstadoProcesoVoluntarioDetalle1);
        edtUrgencia = (TextView) findViewById(R.id.txUrgenciaProcesoVoluntarioDetalle1);
        edtNecesitado = (TextView) findViewById(R.id.txNecesitadoProcesoVoluntarioDetalle1);

        btnInformacionNecesitado = (Button) findViewById(R.id.btnInformacionNecesitado);


        ayuda = (Ayuda) getIntent().getParcelableExtra("ayuda");
        edtTitulo.setText(ayuda.getTitulo());
        edtDescripcion.setText(ayuda.getDescripcion());
        edtFecha.setText(""+ ayuda.getFecha());
        edtEstado.setText(ayuda.getEstado());
        edtUrgencia.setText(""+ayuda.getUrgencia());
        edtNecesitado.setText(ayuda.getUsuario());

        asignarFuncionBotones();
    }

    private void asignarFuncionBotones(){
        btnInformacionNecesitado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InfoUsuarioFragment infoUsuarioFragment = new InfoUsuarioFragment();
                Bundle args = new Bundle();
                args.putString("usuario",ayuda.getUsuario());
                infoUsuarioFragment.setArguments(args);
                infoUsuarioFragment.show(getSupportFragmentManager(),"InfoUsuario");
            }
        });
    }
}