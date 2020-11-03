package es.uniovi.eii.voluntacovid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import es.uniovi.eii.voluntacovid.datos.UsuariosDataSource;
import es.uniovi.eii.voluntacovid.modelo.Usuario;

public class MainActivity extends AppCompatActivity {

    private EditText txUsuario,txContraseña,txContraseña2,txTelefono,txDireccion,txCodigoPostal;
    private RadioButton rdNecesitado,rdVoluntario;
    private Button btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txUsuario = (EditText) findViewById(R.id.txUsuario);
        txContraseña = (EditText) findViewById(R.id.txContraseña);
        txContraseña2 = (EditText) findViewById(R.id.txRepetirContraseña);
        txTelefono = (EditText) findViewById(R.id.txTelefono);
        txDireccion = (EditText) findViewById(R.id.txDireccion);
        txCodigoPostal = (EditText) findViewById(R.id.txCodigoPostal);
        rdNecesitado = (RadioButton) findViewById(R.id.rdNecesitado);
        rdVoluntario = (RadioButton) findViewById(R.id.rdVoluntario);
        btnRegistrar = (Button) findViewById(R.id.btnRegistrarse);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(comprobarCampos()){
                    UsuariosDataSource usuariosDataSource = new UsuariosDataSource(getApplicationContext());
                    usuariosDataSource.open();
                    if(usuariosDataSource.getUserByUser(txUsuario.getText().toString()) != null){
                        txUsuario.setError("El nombre de usuario indicado ya existe");
                    }
                    else{
                        Usuario usuarioAñadir = new Usuario();
                        usuarioAñadir.setUsuario(txUsuario.getText().toString());
                        usuarioAñadir.setContraseña(txContraseña.getText().toString());
                        usuarioAñadir.setTelefono(Integer.parseInt(txTelefono.getText().toString()));
                        usuarioAñadir.setDireccion(txDireccion.getText().toString());
                        usuarioAñadir.setCodigoPostal(Integer.parseInt(txCodigoPostal.getText().toString()));
                        if(rdNecesitado.isChecked())
                            usuarioAñadir.setTipo("NECESITADO");
                        else
                            usuarioAñadir.setTipo("VOLUNTARIO");
                        usuariosDataSource.createUser(usuarioAñadir);
                    }
                    usuariosDataSource.close();
                }
            }
        });
    }

    private boolean comprobarCampos(){
        if(txUsuario.getText().toString().isEmpty()){
            txUsuario.setError("Inserte un nombre de usuario");
            return false;
        }
        if(txContraseña.getText().toString().length() < 8){
            txContraseña.setError("La contraseña debe de tener al menos 8 caracteres");
            return  false;
        }
        if(!txContraseña.getText().toString().equals(txContraseña2.getText().toString())){
            txContraseña2.setError("Las contraseñas no coinciden");
            return false;
        }
        if(txTelefono.getText().toString().length() != 9){
            txTelefono.setError("El número de teléfono no es válido");
            return false;
        }
        if(txDireccion.getText().toString().isEmpty()){
            txDireccion.setError("Inserte una dirección");
            return false;
        }
        if(txCodigoPostal.getText().toString().length() != 5){
            txCodigoPostal.setError("El código postal no es válido");
            return false;
        }
        if(!rdVoluntario.isChecked() && !rdNecesitado.isChecked()){
            rdVoluntario.setError("Selecciona un tipo");
            return false;
        }
        return true;
    }
}