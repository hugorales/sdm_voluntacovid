package es.uniovi.eii.voluntacovid;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import es.uniovi.eii.voluntacovid.datos.AyudaDataSource;
import es.uniovi.eii.voluntacovid.modelo.Ayuda;

public class DetalleAyudaPendiente extends AppCompatActivity {

    private TextView edtTitulo,edtDescripcion,edtFecha,edtEstado,edtUrgencia,edtVoluntario;
    private Button btnEliminarSolicitud;
    private Ayuda ayuda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_ayuda_pendiente);

        edtTitulo = (TextView) findViewById(R.id.txTituloDetalle1);
        edtDescripcion = (TextView) findViewById(R.id.txDescripcionDetalle1);
        edtFecha = (TextView) findViewById(R.id.txFechaDetalle1);
        edtEstado = (TextView) findViewById(R.id.txEstadoDetalle1);
        edtUrgencia = (TextView) findViewById(R.id.txUrgenciaDetalle1);
        edtVoluntario = (TextView) findViewById(R.id.txVoluntarioDetalle1);
        btnEliminarSolicitud = (Button) findViewById(R.id.btnEliminarSolicitud);

        ayuda = (Ayuda) getIntent().getParcelableExtra("ayuda");
        edtTitulo.setText(ayuda.getTitulo());
        edtDescripcion.setText(ayuda.getDescripcion());
        edtFecha.setText(""+ ayuda.getFecha());
        edtEstado.setText(ayuda.getEstado());
        edtUrgencia.setText(""+ayuda.getUrgencia());
        edtVoluntario.setText(ayuda.getVoluntario());

        btnEliminarSolicitud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(DetalleAyudaPendiente.this);
                alerta.setMessage("¿Seguro que desea eliminar la solicitud de ayuda?");
                alerta.setTitle("Eliminar solicitud de ayuda");
                alerta.setCancelable(false);
                alerta.setPositiveButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alerta.setNegativeButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AyudaDataSource ayudaDataSource = new AyudaDataSource(getApplicationContext());
                        ayudaDataSource.open();
                        ayudaDataSource.deleteAyuda(ayuda.getId());
                        ayudaDataSource.close();
                        Intent intent = new Intent(DetalleAyudaPendiente.this, NecesitadoActivity.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(),"¡Has eliminado la solicitud de ayuda", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
                AlertDialog dialog = alerta.create();
                dialog.show();
            }
        });

    }
}