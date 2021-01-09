package es.uniovi.eii.voluntacovid;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import es.uniovi.eii.voluntacovid.datos.AyudaDataSource;
import es.uniovi.eii.voluntacovid.modelo.Ayuda;

public class DetalleAyudaPendienteVoluntario extends AppCompatActivity {

    private TextView edtTitulo,edtDescripcion,edtFecha,edtEstado,edtUrgencia,edtNecesitado;
    private Button btnAyudar;
    private Ayuda ayuda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_ayuda_pendiente_voluntario);

        edtTitulo = (TextView)findViewById(R.id.txTituloDetalle3);
        edtDescripcion = (TextView) findViewById(R.id.txDescripcionDetalle3);
        edtFecha = (TextView) findViewById(R.id.txFechaDetalle3);
        edtEstado = (TextView) findViewById(R.id.txEstadoDetalle3);
        edtUrgencia = (TextView) findViewById(R.id.txUrgenciaDetalle3);
        edtNecesitado = (TextView)findViewById(R.id.txNecesitadoDetalle3);

        btnAyudar = (Button) findViewById(R.id.btnAyudar);

        ayuda = (Ayuda) getIntent().getParcelableExtra("ayuda");
        edtTitulo.setText(ayuda.getTitulo());
        edtDescripcion.setText(ayuda.getDescripcion());
        edtFecha.setText(""+ ayuda.getFecha());
        edtEstado.setText(ayuda.getEstado());
        edtUrgencia.setText(""+ayuda.getUrgencia());
        edtNecesitado.setText(ayuda.getUsuario());

        btnAyudar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(DetalleAyudaPendienteVoluntario.this);
                alerta.setMessage("¿Seguro que desea ayudar a " + ayuda.getUsuario()+"?");
                alerta.setTitle("CONFIRMAR AYUDA");
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
                        SharedPreferences preferences = getSharedPreferences("usuarioSesion", Context.MODE_PRIVATE);
                        AyudaDataSource ayudaDataSource = new AyudaDataSource(getApplicationContext());
                        ayudaDataSource.open();
                        ayudaDataSource.asignarAyudaAVoluntario(ayuda.getId(),preferences.getString("usuario",""));
                        ayudaDataSource.close();
                        Intent intent = new Intent(DetalleAyudaPendienteVoluntario.this, VoluntarioActivity.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(),"Se te ha asignado la ayuda", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
                AlertDialog dialog = alerta.create();
                dialog.show();
            }
        });

    }
}