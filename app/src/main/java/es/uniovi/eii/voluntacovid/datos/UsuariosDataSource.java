package es.uniovi.eii.voluntacovid.datos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import es.uniovi.eii.voluntacovid.modelo.Usuario;

public class UsuariosDataSource {

    private SQLiteDatabase database;

    private MyDBHelper dbHelper;

    private final String[] allColumns = { MyDBHelper.COLUMNA_USUARIO_USUARIOS, MyDBHelper.COLUMNA_CONTRASEÑA_USUARIOS,
            MyDBHelper.COLUMNA_TELEFONO_USUARIOS,MyDBHelper.COLUMNA_DIRECCION_USUARIOS,MyDBHelper.COLUMNA_CODIGOPOSTAL_USUARIOS,MyDBHelper.COLUMNA_TIPO_USUARIOS};

    public UsuariosDataSource(Context context){
        dbHelper = new MyDBHelper(context, null, null, 1);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();

    }

    public void close() {
        dbHelper.close();
    }

    public long createUser(Usuario usuario) {
        // Establecemos los valores que se insertaran
        ContentValues values = new ContentValues();

        values.put(MyDBHelper.COLUMNA_USUARIO_USUARIOS, usuario.getUsuario());
        values.put(MyDBHelper.COLUMNA_CONTRASEÑA_USUARIOS, usuario.getContraseña());
        values.put(MyDBHelper.COLUMNA_TELEFONO_USUARIOS, usuario.getTelefono());
        values.put(MyDBHelper.COLUMNA_DIRECCION_USUARIOS, usuario.getDireccion());
        values.put(MyDBHelper.COLUMNA_CODIGOPOSTAL_USUARIOS, usuario.getCodigoPostal());
        values.put(MyDBHelper.COLUMNA_TIPO_USUARIOS, usuario.getTipo());

        // Insertamos la valoracion
        long insertId =
                database.insert(MyDBHelper.TABLA_USUARIOS, null, values);

        return insertId;
    }

    public List<Usuario> getAllUsers() {
        // Lista que almacenara el resultado
        List<Usuario> userList = new ArrayList<Usuario>();
        //hacemos una query porque queremos devolver un cursor

        Cursor cursor = database.query(MyDBHelper.TABLA_USUARIOS, allColumns,
                null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            final Usuario usuario = new Usuario();
            usuario.setUsuario(cursor.getString(0));
            usuario.setContraseña(cursor.getString(1));
            usuario.setTelefono(cursor.getInt(2));
            usuario.setDireccion(cursor.getString(3));
            usuario.setCodigoPostal(cursor.getInt(4));
            usuario.setTipo(cursor.getString(5));
            userList.add(usuario);
            cursor.moveToNext();
        }

        cursor.close();
        // Una vez obtenidos todos los datos y cerrado el cursor, devolvemos la
        // lista.

        return userList;
    }

    public Usuario getUserByUser(String usuario) {
        // Lista que almacenara el resultado
        Usuario user = null;
        //hacemos una query porque queremos devolver un cursor

        Cursor cursor = database.rawQuery("Select * " +
                " FROM " + MyDBHelper.TABLA_USUARIOS +
                " WHERE " + MyDBHelper.TABLA_USUARIOS + "." + MyDBHelper.COLUMNA_USUARIO_USUARIOS + " = \"" + usuario + "\"", null);

        if(cursor.moveToFirst()){
            final Usuario usuarioo = new Usuario();
            usuarioo.setUsuario(cursor.getString(0));
            usuarioo.setContraseña(cursor.getString(1));
            usuarioo.setTelefono(cursor.getInt(2));
            usuarioo.setDireccion(cursor.getString(3));
            usuarioo.setCodigoPostal(cursor.getInt(4));
            usuarioo.setTipo(cursor.getString(5));
            user = usuarioo;
        }
        cursor.close();

        return user;
    }
}
