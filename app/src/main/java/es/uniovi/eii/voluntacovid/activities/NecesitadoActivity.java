package es.uniovi.eii.voluntacovid.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import es.uniovi.eii.voluntacovid.R;
import es.uniovi.eii.voluntacovid.adaptadores.ListaNecesitadoAdaptador;
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
                if(position==0){
                    final List<Ayuda> listaAyuda = ayudaDataSource.getAyudaByUserAndStatus(preferences.getString("usuario",""),"PENDIENTE");
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
                    final List<Ayuda> listaAyuda1 = ayudaDataSource.getAyudaByUserAndStatus(preferences.getString("usuario",""),"ASIGNADO");
                    ListaNecesitadoAdaptador adaptador = new ListaNecesitadoAdaptador(listaAyuda1);
                    adaptador.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(NecesitadoActivity.this, DetalleAyudaAsignada.class);
                            intent.putExtra("ayuda",listaAyuda1.get(recycler.getChildAdapterPosition(v)));
                            startActivity(intent);
                        }
                    });
                    recycler.setAdapter(adaptador);
                }else{
                    final List<Ayuda> listaAyuda2 = ayudaDataSource.getAyudaByUserAndStatus(preferences.getString("usuario",""),"COMPLETADO");
                    ListaNecesitadoAdaptador adaptador = new ListaNecesitadoAdaptador(listaAyuda2);
                    adaptador.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(NecesitadoActivity.this, DetalleAyudaCompletada.class);
                            intent.putExtra("ayuda",listaAyuda2.get(recycler.getChildAdapterPosition(v)));
                            startActivity(intent);
                        }
                    });
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_desconectar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id==R.id.desconectarse){
            Intent intent = new Intent(NecesitadoActivity.this, InicioSesionActivity.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(),"Te has desconectado",Toast.LENGTH_SHORT).show();
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}