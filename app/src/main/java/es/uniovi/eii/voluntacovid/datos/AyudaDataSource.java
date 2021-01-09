package es.uniovi.eii.voluntacovid.datos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import es.uniovi.eii.voluntacovid.modelo.Ayuda;
import es.uniovi.eii.voluntacovid.modelo.Usuario;

public class AyudaDataSource {

    private SQLiteDatabase database;

    private MyDBHelper dbHelper;

    private final String[] allColumns = { MyDBHelper.COLUMNA_ID_AYUDA, MyDBHelper.COLUMNA_USUARIO_AYUDA,
            MyDBHelper.COLUMNA_TITULO_AYUDA,MyDBHelper.COLUMNA_DESCRIPCION_AYUDA,MyDBHelper.COLUMNA_FECHA_AYUDA,MyDBHelper.COLUMNA_ESTADO_AYUDA,MyDBHelper.COLUMNA_URGENCIA_AYUDA,MyDBHelper.COLUMNA_VOLUNTARIO_AYUDA};

    public AyudaDataSource(Context context){
        dbHelper = new MyDBHelper(context, null, null, 1);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long createAyuda(Ayuda ayuda) {
        // Establecemos los valores que se insertaran
        ContentValues values = new ContentValues();

        values.put(MyDBHelper.COLUMNA_ID_AYUDA, ayuda.getId());
        values.put(MyDBHelper.COLUMNA_USUARIO_AYUDA, ayuda.getUsuario());
        values.put(MyDBHelper.COLUMNA_TITULO_AYUDA, ayuda.getTitulo());
        values.put(MyDBHelper.COLUMNA_DESCRIPCION_AYUDA, ayuda.getDescripcion());
        values.put(MyDBHelper.COLUMNA_FECHA_AYUDA, ayuda.getFecha());
        values.put(MyDBHelper.COLUMNA_ESTADO_AYUDA, ayuda.getEstado());
        values.put(MyDBHelper.COLUMNA_URGENCIA_AYUDA, ayuda.getUrgencia());
        values.put(MyDBHelper.COLUMNA_VOLUNTARIO_AYUDA,ayuda.getVoluntario());

        // Insertamos la valoracion
        long insertId =
                database.insert(MyDBHelper.TABLA_AYUDA, null, values);
        Log.d("Insertada" + ayuda.getUrgencia(),"HOLAA");

        return insertId;
    }

    public void deleteAyuda(int id){
        database.delete(MyDBHelper.TABLA_AYUDA,"id="+id,null);
    }

    public List<Ayuda> getAllAyuda() {
        // Lista que almacenara el resultado
        List<Ayuda> ayudaList = new ArrayList<Ayuda>();
        //hacemos una query porque queremos devolver un cursor

        Cursor cursor = database.query(MyDBHelper.TABLA_AYUDA, allColumns,
                null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            final Ayuda ayuda = new Ayuda();
            ayuda.setId(cursor.getInt(0));
            ayuda.setUsuario(cursor.getString(1));
            ayuda.setTitulo(cursor.getString(2));
            ayuda.setDescripcion(cursor.getString(3));
            ayuda.setFecha(cursor.getString(4));
            ayuda.setEstado(cursor.getString(5));
            ayuda.setUrgencia(cursor.getInt(6));
            ayuda.setVoluntario(cursor.getString(7));
            ayudaList.add(ayuda);
            cursor.moveToNext();
        }

        cursor.close();
        // Una vez obtenidos todos los datos y cerrado el cursor, devolvemos la
        // lista.

        return ayudaList;
    }

    public List<Ayuda> getAyudaByUser(String usuario) {
        List<Ayuda> ayudaList = new ArrayList<Ayuda>();

        Cursor cursor = database.rawQuery("Select * " +
                " FROM " + MyDBHelper.TABLA_AYUDA +
                " WHERE " + MyDBHelper.TABLA_AYUDA + "." + MyDBHelper.COLUMNA_USUARIO_AYUDA + " = \"" + usuario + "\"", null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Ayuda ayudaa = new Ayuda();
            ayudaa.setId(cursor.getInt(0));
            ayudaa.setUsuario(cursor.getString(1));
            ayudaa.setTitulo(cursor.getString(2));
            ayudaa.setDescripcion(cursor.getString(3));
            ayudaa.setFecha(cursor.getString(4));
            ayudaa.setEstado(cursor.getString(5));
            ayudaa.setUrgencia(cursor.getInt(6));
            ayudaa.setVoluntario(cursor.getString(7));
            ayudaList.add(ayudaa);
            cursor.moveToNext();
        }
        cursor.close();

        return ayudaList;
    }

    public List<Ayuda> getAyudaByUserAndStatus(String usuario,String status) {
        List<Ayuda> ayudaList = new ArrayList<Ayuda>();

        Cursor cursor = database.rawQuery("Select * " +
                " FROM " + MyDBHelper.TABLA_AYUDA +
                " WHERE " + MyDBHelper.TABLA_AYUDA + "." + MyDBHelper.COLUMNA_USUARIO_AYUDA + " = \"" + usuario + "\"" + "AND " +MyDBHelper.TABLA_AYUDA + "." + MyDBHelper.COLUMNA_ESTADO_AYUDA + " = \"" + status + "\"", null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Ayuda ayudaa = new Ayuda();
            ayudaa.setId(cursor.getInt(0));
            ayudaa.setUsuario(cursor.getString(1));
            ayudaa.setTitulo(cursor.getString(2));
            ayudaa.setDescripcion(cursor.getString(3));
            ayudaa.setFecha(cursor.getString(4));
            ayudaa.setEstado(cursor.getString(5));
            ayudaa.setUrgencia(cursor.getInt(6));
            ayudaa.setVoluntario(cursor.getString(7));
            ayudaList.add(ayudaa);
            cursor.moveToNext();
        }
        cursor.close();

        return ayudaList;
    }

    public int getUltimoId(){
        int result;
        Cursor cursor = database.rawQuery("Select Max(id) " +
                " FROM " + MyDBHelper.TABLA_AYUDA,null);
        if(cursor.moveToFirst()){
            result = cursor.getInt(0);
            cursor.close();
            return result;
        }
        cursor.close();
        return 0;
    }

    public List<Ayuda> getAyudaByUsers(List<String> listaUsuarios){
        String query="(";
        for(int i=0;i<listaUsuarios.size();i++){
            if(i==listaUsuarios.size()-1){
                query += "\""+listaUsuarios.get(i)+"\")";
            }else{
                query += "\""+listaUsuarios.get(i)+"\",";
            }
        }
        if(listaUsuarios.size()==0){
            query+=")";
        }
        String query1 = " order by urgencia desc";
        List<Ayuda> listaAyuda = new ArrayList<Ayuda>();
        Cursor cursor = database.rawQuery("Select * " +
                " FROM " + MyDBHelper.TABLA_AYUDA +
                " WHERE " + MyDBHelper.TABLA_AYUDA + "." + MyDBHelper.COLUMNA_ESTADO_AYUDA + "=" + "'PENDIENTE' AND " + MyDBHelper.TABLA_AYUDA + "." + MyDBHelper.COLUMNA_USUARIO_AYUDA + " IN " + query + query1, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Ayuda ayudaa = new Ayuda();
            ayudaa.setId(cursor.getInt(0));
            ayudaa.setUsuario(cursor.getString(1));
            ayudaa.setTitulo(cursor.getString(2));
            ayudaa.setDescripcion(cursor.getString(3));
            ayudaa.setFecha(cursor.getString(4));
            ayudaa.setEstado(cursor.getString(5));
            ayudaa.setUrgencia(cursor.getInt(6));
            ayudaa.setVoluntario(cursor.getString(7));
            listaAyuda.add(ayudaa);
            cursor.moveToNext();
        }
        cursor.close();
        return listaAyuda;
    }

    public void asignarAyudaAVoluntario(int idAyuda,String voluntario){
        ContentValues values = new ContentValues();
        values.put("estado","ASIGNADO");
        values.put("voluntario",voluntario);
        database.update(MyDBHelper.TABLA_AYUDA,values,"id="+idAyuda,null);
    }

    public void marcarAyudaComoCompletada(int idAyuda){
        ContentValues values = new ContentValues();
        values.put("estado","COMPLETADO");
        database.update(MyDBHelper.TABLA_AYUDA,values,"id="+idAyuda,null);
    }

    public List<Ayuda> getAyudasByEstadoAndVolunario(String estado,String voluntario){
        List<Ayuda> ayudaList = new ArrayList<Ayuda>();

        Cursor cursor = database.rawQuery("Select * " +
                " FROM " + MyDBHelper.TABLA_AYUDA +
                " WHERE " + MyDBHelper.TABLA_AYUDA + "." + MyDBHelper.COLUMNA_ESTADO_AYUDA + " = \"" + estado + "\"" + "AND " +MyDBHelper.TABLA_AYUDA + "." + MyDBHelper.COLUMNA_VOLUNTARIO_AYUDA + " = \"" + voluntario + "\"", null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Ayuda ayudaa = new Ayuda();
            ayudaa.setId(cursor.getInt(0));
            ayudaa.setUsuario(cursor.getString(1));
            ayudaa.setTitulo(cursor.getString(2));
            ayudaa.setDescripcion(cursor.getString(3));
            ayudaa.setFecha(cursor.getString(4));
            ayudaa.setEstado(cursor.getString(5));
            ayudaa.setUrgencia(cursor.getInt(6));
            ayudaa.setVoluntario(cursor.getString(7));
            ayudaList.add(ayudaa);
            cursor.moveToNext();
        }
        cursor.close();

        return ayudaList;
    }
}
