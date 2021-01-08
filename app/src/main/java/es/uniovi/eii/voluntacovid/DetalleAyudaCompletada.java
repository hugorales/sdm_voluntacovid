package es.uniovi.eii.voluntacovid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import es.uniovi.eii.voluntacovid.modelo.Ayuda;

public class DetalleAyudaCompletada extends AppCompatActivity {

    private TextView edtTitulo,edtDescripcion,edtFecha,edtEstado,edtUrgencia,edtVoluntario;
    private Ayuda ayuda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_ayuda_completada);

        edtTitulo = (TextView) findViewById(R.id.txTituloDetalleCompletada1);
        edtDescripcion = (TextView) findViewById(R.id.txDescripcionDetalleCompletada1);
        edtFecha = (TextView) findViewById(R.id.txFechaDetalleCompletada1);
        edtEstado = (TextView) findViewById(R.id.txEstadoDetalleCompletada1);
        edtUrgencia = (TextView) findViewById(R.id.txUrgenciaDetalleCompletada1);
        edtVoluntario = (TextView) findViewById(R.id.txVoluntarioDetalleCompletada1);

        ayuda = (Ayuda) getIntent().getParcelableExtra("ayuda");
        edtTitulo.setText(ayuda.getTitulo());
        edtDescripcion.setText(ayuda.getDescripcion());
        edtFecha.setText(""+ ayuda.getFecha());
        edtEstado.setText(ayuda.getEstado());
        edtUrgencia.setText(""+ayuda.getUrgencia());
        edtVoluntario.setText(ayuda.getVoluntario());
    }
}