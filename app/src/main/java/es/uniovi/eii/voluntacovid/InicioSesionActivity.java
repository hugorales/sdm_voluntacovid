package es.uniovi.eii.voluntacovid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import es.uniovi.eii.voluntacovid.datos.UsuariosDataSource;
import es.uniovi.eii.voluntacovid.modelo.Usuario;

public class InicioSesionActivity extends AppCompatActivity {

    private EditText txtUsuario, txtContraseña;
    private Button btnInicioSesion, btnCrearCuenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio_sesion);

        txtUsuario = (EditText) findViewById(R.id.txtUsuario);
        txtContraseña = (EditText) findViewById(R.id.txtContraseña);
        btnInicioSesion = (Button) findViewById(R.id.btnInicioSesion);
        btnCrearCuenta = (Button) findViewById(R.id.btnCrearCuenta);

        clickInicioSesion();
        clickCrearCuenta();
    }

    private void clickCrearCuenta() {
        btnCrearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(InicioSesionActivity.this, RegistroActivity.class);
                myIntent.putExtra("key", 2); //Optional parameters
                InicioSesionActivity.this.startActivity(myIntent);
            }
        });
    }

    private void clickInicioSesion() {
        btnInicioSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(comprobarCampos()){
                    UsuariosDataSource usuariosDataSource = new UsuariosDataSource(getApplicationContext());
                    usuariosDataSource.open();
                    Usuario usuario = usuariosDataSource.getUserByUser(txtUsuario.getText().toString());
                    usuariosDataSource.close();
                    if(usuario == null || !usuario.getContraseña().equals(txtContraseña.getText().toString())) {
                        txtUsuario.setError("El usuario no existe o contraseña no corresponde, en caso de que no tengas una cuenta, dale a crear cuenta");
                    }
                    else {
                        Intent myIntent = new Intent(InicioSesionActivity.this, PaginaPrincipalActivity.class);
                        myIntent.putExtra("key", 1); //Optional parameters
                        InicioSesionActivity.this.startActivity(myIntent);
                    }
                }
            }
        });
    }

    private boolean comprobarCampos(){
        if(txtUsuario.getText().toString().isEmpty()){
            txtUsuario.setError("Inserte un nombre de usuario");
            return false;
        }
        if(txtContraseña.getText().toString().length() < 8){
            txtContraseña.setError("La contraseña debe de tener al menos 8 caracteres");
            return  false;
        }
        return true;
    }
}