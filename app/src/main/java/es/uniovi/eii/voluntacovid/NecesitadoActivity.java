package es.uniovi.eii.voluntacovid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import es.uniovi.eii.voluntacovid.datos.AyudaDataSource;
import es.uniovi.eii.voluntacovid.modelo.Ayuda;

public class NecesitadoActivity extends AppCompatActivity {

    private RecyclerView recycler;
    private FloatingActionButton btnSolicitarAyuda;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_necesitado);

        recycler= (RecyclerView) findViewById(R.id.recyclerNecesitado);
        recycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.listanecesitado, android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                AyudaDataSource ayudaDataSource = new AyudaDataSource(getApplicationContext());
                ayudaDataSource.open();
                SharedPreferences preferences = getSharedPreferences("usuarioSesion", Context.MODE_PRIVATE);
                final List<Ayuda> listaAyuda = ayudaDataSource.getAyudaByUserAndStatus(preferences.getString("usuario",""),"PENDIENTE");
                if(position==0){
                    ListaNecesitadoAdaptador adaptador = new ListaNecesitadoAdaptador(listaAyuda);
                    adaptador.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(NecesitadoActivity.this, DetalleAyudaPendiente.class);
                            intent.putExtra("ayuda",listaAyuda.get(recycler.getChildAdapterPosition(v)));
                            startActivity(intent);
                        }
                    });
                    recycler.setAdapter(adaptador);
                }else if(position==1){
                    ListaNecesitadoAdaptador adaptador = new ListaNecesitadoAdaptador(ayudaDataSource.getAyudaByUserAndStatus(preferences.getString("usuario",""),"ASIGNADO"));
                    recycler.setAdapter(adaptador);
                }else{
                    ListaNecesitadoAdaptador adaptador = new ListaNecesitadoAdaptador(ayudaDataSource.getAyudaByUserAndStatus(preferences.getString("usuario",""),"COMPLETADO"));
                    recycler.setAdapter(adaptador);
                }
                ayudaDataSource.close();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnSolicitarAyuda = (FloatingActionButton) findViewById(R.id.btnSolicitarAyuda);
        btnSolicitarAyuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(NecesitadoActivity.this, SolicitarAyudaActivity.class);
                startActivity(myIntent);
            }
        });

    }
}