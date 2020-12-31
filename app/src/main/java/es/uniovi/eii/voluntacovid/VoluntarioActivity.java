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

import es.uniovi.eii.voluntacovid.datos.AyudaDataSource;

public class VoluntarioActivity extends AppCompatActivity {
    private RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voluntario);

        recycler= (RecyclerView) findViewById(R.id.recyclerVoluntario);
        recycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        AyudaDataSource ayudaDataSource = new AyudaDataSource(getApplicationContext());
        ayudaDataSource.open();
        ListaNecesitadoAdaptador adaptador = new ListaNecesitadoAdaptador(ayudaDataSource.getAllAyuda());
        ayudaDataSource.close();
        recycler.setAdapter(adaptador);
    }
}