package es.uniovi.eii.voluntacovid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import es.uniovi.eii.voluntacovid.datos.AyudaDataSource;
import es.uniovi.eii.voluntacovid.modelo.Ayuda;

public class NecesitadoActivity extends AppCompatActivity {

    private RecyclerView recycler;
    private Button btnSolicitarAyuda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_necesitado);

        btnSolicitarAyuda = (Button) findViewById(R.id.btnSolicitarAyuda);
        btnSolicitarAyuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(NecesitadoActivity.this, SolicitarAyudaActivity.class);
                NecesitadoActivity.this.startActivity(myIntent);
            }
        });



        recycler= (RecyclerView) findViewById(R.id.recyclerNecesitado);
        recycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        AyudaDataSource ayudaDataSource = new AyudaDataSource(getApplicationContext());
        ayudaDataSource.open();
        SharedPreferences preferences = getSharedPreferences("usuarioSesion", Context.MODE_PRIVATE);
        ListaNecesitadoAdaptador adaptador = new ListaNecesitadoAdaptador(ayudaDataSource.getAyudaByUser(preferences.getString("usuario","")));
        ayudaDataSource.close();
        recycler.setAdapter(adaptador);
    }
}