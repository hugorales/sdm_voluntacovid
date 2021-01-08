package es.uniovi.eii.voluntacovid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.button.MaterialButtonToggleGroup;

import es.uniovi.eii.voluntacovid.modelo.Ayuda;

public class DetalleAyudaAsignada extends AppCompatActivity {

    private TextView edtTitulo,edtDescripcion,edtFecha,edtEstado,edtUrgencia,edtVoluntario;
    private Button btnVerInformacionVoluntario,btnMarcarRealizada;
    private Ayuda ayuda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_ayuda_asignada);

        edtTitulo = (TextView) findViewById(R.id.txTituloDetalle10);
        edtDescripcion = (TextView) findViewById(R.id.txDescripcionDetalle10);
        edtFecha = (TextView) findViewById(R.id.txFechaDetalle10);
        edtEstado = (TextView) findViewById(R.id.txEstadoDetalle10);
        edtUrgencia = (TextView) findViewById(R.id.txUrgenciaDetalle10);
        edtVoluntario = (TextView)findViewById(R.id.txVoluntarioDetalle10);
        btnVerInformacionVoluntario = (Button)findViewById(R.id.btnVerInformacionVoluntario);
        btnMarcarRealizada = (Button)findViewById(R.id.btnMarcarRealizada);


        ayuda = (Ayuda) getIntent().getParcelableExtra("ayuda");
        edtTitulo.setText(ayuda.getTitulo());
        edtDescripcion.setText(ayuda.getDescripcion());
        edtFecha.setText(""+ ayuda.getFecha());
        edtEstado.setText(ayuda.getEstado());
        edtUrgencia.setText(""+ayuda.getUrgencia());
        edtVoluntario.setText(ayuda.getVoluntario());

        asignarFuncionBotones();


    }

    private void asignarFuncionBotones(){
        btnVerInformacionVoluntario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnMarcarRealizada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}