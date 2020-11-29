package es.uniovi.eii.voluntacovid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.AlteredCharSequence;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import es.uniovi.eii.voluntacovid.datos.AyudaDataSource;
import es.uniovi.eii.voluntacovid.datos.UsuariosDataSource;
import es.uniovi.eii.voluntacovid.modelo.Ayuda;

public class SolicitarAyudaActivity extends AppCompatActivity {

    private Button btnSolicitar;
    private Button btnCancelar;
    private EditText edtTitulo,edtDescripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitar_ayuda);

        edtTitulo = (EditText) findViewById(R.id.edtTitulo);
        edtDescripcion = (EditText) findViewById(R.id.edtDescripcion);
        btnSolicitar = (Button) findViewById(R.id.btnSolicitar);
        btnCancelar = (Button) findViewById(R.id.btnCancelar);

        btnSolicitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(comprobarCampos()){
                    AyudaDataSource ayudaDataSource = new AyudaDataSource(getApplicationContext());
                    ayudaDataSource.open();
                    Ayuda ayudaAñadir = new Ayuda();
                    ayudaAñadir.setId(ayudaDataSource.getUltimoId()+1);
                    SharedPreferences preferences = getSharedPreferences("usuarioSesion", Context.MODE_PRIVATE);
                    ayudaAñadir.setUsuario(preferences.getString("usuario","Sin identificar"));
                    ayudaAñadir.setTitulo(edtTitulo.getText().toString());
                    ayudaAñadir.setDescripcion(edtDescripcion.getText().toString());
                    Date d = new Date();
                    SimpleDateFormat fecc=new SimpleDateFormat("d MMMM 'del' yyyy");
                    ayudaAñadir.setFecha(fecc.format(d));
                    ayudaAñadir.setEstado("NO ASIGNADO");
                    ayudaDataSource.createAyuda(ayudaAñadir);
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
            return  false;
        }
        return true;
    }
}