package es.uniovi.eii.voluntacovid.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import es.uniovi.eii.voluntacovid.R;
import es.uniovi.eii.voluntacovid.modelo.Ayuda;

public class DetalleAyudaCompletadaVoluntario extends AppCompatActivity {

    private TextView edtTitulo,edtDescripcion,edtFecha,edtEstado,edtUrgencia,edtNecesitado;
    private Ayuda ayuda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_ayuda_completada_voluntario);


        edtTitulo = (TextView) findViewById(R.id.txTituloCompletadaVoluntarioDetalle1);
        edtDescripcion = (TextView) findViewById(R.id.txDescripcionCompletadaVoluntarioDetalle1);
        edtFecha = (TextView) findViewById(R.id.txFechaCompletadaVoluntarioDetalle1);
        edtEstado = (TextView) findViewById(R.id.txEstadoCompletadaVoluntarioDetalle1);
        edtUrgencia = (TextView) findViewById(R.id.txUrgenciaCompletadaVoluntarioDetalle1);
        edtNecesitado = (TextView) findViewById(R.id.txNecesitadoCompletadaVoluntarioDetalle1);

        ayuda = (Ayuda) getIntent().getParcelableExtra("ayuda");
        edtTitulo.setText(ayuda.getTitulo());
        edtDescripcion.setText(ayuda.getDescripcion());
        edtFecha.setText(""+ ayuda.getFecha());
        edtEstado.setText(ayuda.getEstado());
        edtUrgencia.setText(""+ayuda.getUrgencia());
        edtNecesitado.setText(ayuda.getUsuario());
    }
}