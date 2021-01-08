package es.uniovi.eii.voluntacovid;

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
import android.widget.Button;
import android.widget.Spinner;

import java.util.List;

import es.uniovi.eii.voluntacovid.datos.AyudaDataSource;
import es.uniovi.eii.voluntacovid.datos.UsuariosDataSource;
import es.uniovi.eii.voluntacovid.modelo.Ayuda;
import es.uniovi.eii.voluntacovid.modelo.Usuario;

public class VoluntarioActivity extends AppCompatActivity {
    private RecyclerView recycler;
    private Spinner spinner1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voluntario);

        spinner1 = (Spinner)findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.listavoluntario, android.R.layout.simple_spinner_item);
        spinner1.setAdapter(adapter);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                AyudaDataSource ayudaDataSource = new AyudaDataSource(getApplicationContext());
                ayudaDataSource.open();
                SharedPreferences preferences = getSharedPreferences("usuarioSesion", Context.MODE_PRIVATE);
                if(position == 0){
                    UsuariosDataSource usuariosDataSource = new UsuariosDataSource(getApplicationContext());
                    usuariosDataSource.open();
                    Usuario usuarioSesion = usuariosDataSource.getUserByUser(preferences.getString("usuario",""));
                    List<String> usuarios = usuariosDataSource.getUsersByCP(usuarioSesion.getCodigoPostal());
                    usuariosDataSource.close();
                    final List<Ayuda> listaAyuda = ayudaDataSource.getAyudaByUsers(usuarios);
                    ListaNecesitadoAdaptador adaptador = new ListaNecesitadoAdaptador(listaAyuda);
                    adaptador.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(VoluntarioActivity.this, DetalleAyudaPendienteVoluntario.class);
                            intent.putExtra("ayuda",listaAyuda.get(recycler.getChildAdapterPosition(v)));
                            startActivity(intent);
                        }
                    });
                    recycler.setAdapter(adaptador);
                }else if(position == 1){
                    final List<Ayuda> listaAyuda1 = ayudaDataSource.getAyudasByEstadoAndVolunario("ASIGNADO",preferences.getString("usuario",""));
                    ListaNecesitadoAdaptador adaptador = new ListaNecesitadoAdaptador(listaAyuda1);
                    adaptador.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(VoluntarioActivity.this, DetalleAyudaProcesoVoluntario.class);
                            intent.putExtra("ayuda",listaAyuda1.get(recycler.getChildAdapterPosition(v)));
                            startActivity(intent);
                        }
                    });
                    recycler.setAdapter(adaptador);
                }else {

                }
                ayudaDataSource.close();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        recycler= (RecyclerView) findViewById(R.id.recyclerVoluntario);
        recycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

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
            Intent intent = new Intent(VoluntarioActivity.this, InicioSesionActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}