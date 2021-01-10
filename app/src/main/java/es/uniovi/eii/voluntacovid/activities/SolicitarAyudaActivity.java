package es.uniovi.eii.voluntacovid.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import es.uniovi.eii.voluntacovid.R;
import es.uniovi.eii.voluntacovid.datos.AyudaDataSource;
import es.uniovi.eii.voluntacovid.modelo.Ayuda;

public class SolicitarAyudaActivity extends AppCompatActivity {

    private Button btnSolicitar;
    private Button btnEliminar;
    private EditText edtTitulo,edtDescripcion,edtUrgencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitar_ayuda);

        edtTitulo = (EditText) findViewById(R.id.edtTitulo);
        edtDescripcion = (EditText) findViewById(R.id.edtDescripcion);
        edtUrgencia = (EditText) findViewById(R.id.edtUrgencia);
        btnSolicitar = (Button) findViewById(R.id.btnSolicitar);
        // btnCancelar = (Button) findViewById(R.id.btnCancelar);

        btnSolicitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(comprobarCampos()){
                    AyudaDataSource ayudaDataSource = new AyudaDataSource(getApplicationContext());
                    Ayuda ayudaAñadir = new Ayuda();
                    ayudaDataSource.open();
                    ayudaAñadir.setId(ayudaDataSource.getUltimoId()+1);
                    ayudaDataSource.close();
                    SharedPreferences preferences = getSharedPreferences("usuarioSesion", Context.MODE_PRIVATE);
                    ayudaAñadir.setUsuario(preferences.getString("usuario","Sin identificar"));
                    ayudaAñadir.setTitulo(edtTitulo.getText().toString());
                    ayudaAñadir.setDescripcion(edtDescripcion.getText().toString());
                    Date d = new Date();
                    SimpleDateFormat fecc=new SimpleDateFormat("d MMMM 'del' yyyy");
                    ayudaAñadir.setFecha(fecc.format(d));
                    ayudaAñadir.setEstado("PENDIENTE");
                    ayudaAñadir.setUrgencia(Integer.parseInt(edtUrgencia.getText().toString()));
                    ayudaAñadir.setVoluntario("NO ASIGNADO");
                    ayudaDataSource.open();
                    ayudaDataSource.createAyuda(ayudaAñadir);
                    ayudaDataSource.close();

                    Intent myIntent = new Intent(SolicitarAyudaActivity.this, NecesitadoActivity.class);
                    SolicitarAyudaActivity.this.startActivity(myIntent);
                    finish();
                    Toast toast1 =
                            Toast.makeText(getApplicationContext(),
                                    "Solicitud añadida", Toast.LENGTH_SHORT);

                    toast1.show();
            }
        }});
    }

    private boolean comprobarCampos(){
        if(edtTitulo.getText().toString().isEmpty()){
            edtTitulo.setError("Inserte un título");
            return false;
        }
        if(edtDescripcion.getText().toString().isEmpty()){
            edtDescripcion.setError("Inserte una descripción");
            return false;
        }
        if(edtUrgencia.getText().toString().isEmpty()){
            edtUrgencia.setError("Inserte un valor entre 1 y 10");
            return false;
        }
        if(Integer.parseInt(edtUrgencia.getText().toString()) < 1 || Integer.parseInt(edtUrgencia.getText().toString()) > 10){
            edtUrgencia.setError("Inserte un valor entre 1 y 10");
            return false;
        }
        return true;
    }
}