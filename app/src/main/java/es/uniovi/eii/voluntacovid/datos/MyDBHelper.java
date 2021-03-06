package es.uniovi.eii.voluntacovid.datos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * MyDHelper
 */
public class MyDBHelper extends SQLiteOpenHelper {

    /**
     * Nombre y version de la base de datos
     */
    private static final String DATABASE_NAME = "voluntacovid.db";
    private static final int DATABASE_VERSION = 1;

    /**
     * Nombre de la tabla usuario y sus columnas
     * usuario;contraseña;telefono;direccion;codigoPostal;tipo
     */
    public static final String TABLA_USUARIOS = "tabla_usuarios";

    public static final String COLUMNA_USUARIO_USUARIOS = "usuario";
    public static final String COLUMNA_CONTRASEÑA_USUARIOS = "contraseña";
    public static final String COLUMNA_TELEFONO_USUARIOS = "telefono";
    public static final String COLUMNA_DIRECCION_USUARIOS = "direccion";
    public static final String COLUMNA_CODIGOPOSTAL_USUARIOS = "codigoPostal";
    public static final String COLUMNA_TIPO_USUARIOS = "tipo";



    /**
     * Nombre de la tabla ayuda y sus columnas
     * usuario;titulo;descripcion;fecha;estado
     */

    public static final String TABLA_AYUDA = "tabla_ayuda";

    public static final String COLUMNA_ID_AYUDA = "id";
    public static final String COLUMNA_USUARIO_AYUDA ="usuario";
    public static final String COLUMNA_TITULO_AYUDA = "titulo";
    public static final String COLUMNA_DESCRIPCION_AYUDA = "descripcion";
    public static final String COLUMNA_FECHA_AYUDA = "fecha";
    public static final String COLUMNA_ESTADO_AYUDA = "estado";
    public static final String COLUMNA_URGENCIA_AYUDA = "urgencia";
    public static final String COLUMNA_VOLUNTARIO_AYUDA = "voluntario";


    /**
     * Script para crear la base datos en SQL
     */
    private static final String CREATE_TABLA_USUARIOS = "create table if not exists " + TABLA_USUARIOS
            + "( " +
            COLUMNA_USUARIO_USUARIOS + " " + "text primary key, " +
            COLUMNA_CONTRASEÑA_USUARIOS + " text not null, " +
            COLUMNA_TELEFONO_USUARIOS + " integer not null, " +
            COLUMNA_DIRECCION_USUARIOS + " text not null, " +
            COLUMNA_CODIGOPOSTAL_USUARIOS + " integer not null, " +
            COLUMNA_TIPO_USUARIOS + " text not null" +
             ");";


    private static final String CREATE_TABLA_AYUDA = "create table if not exists " + TABLA_AYUDA
            + "( " +
            COLUMNA_ID_AYUDA + " " + "integer primary key, " +
            COLUMNA_USUARIO_AYUDA + " " + "text not null, " +
            COLUMNA_TITULO_AYUDA + " text not null, " +
            COLUMNA_DESCRIPCION_AYUDA + " text not null, " +
            COLUMNA_FECHA_AYUDA + " text not null, " +
            COLUMNA_ESTADO_AYUDA + " text not null, " +
            COLUMNA_URGENCIA_AYUDA + " integer not null, " +
            COLUMNA_VOLUNTARIO_AYUDA + " text not null" +
            ");";


    /**
     * Script para borrar la base de datos (SQL)
     */
    private static final String DATABASE_DROP_USUARIOS = "DROP TABLE IF EXISTS " + TABLA_USUARIOS;

    private static final String DATABASE_DROP_AYUDA = "DROP TABLE IF EXISTS " + TABLA_AYUDA;





    public MyDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                      int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //invocamos execSQL pq no devuelve ningún tipo de dataset

        db.execSQL(CREATE_TABLA_AYUDA);
        db.execSQL(CREATE_TABLA_USUARIOS);

        Log.i("ONCREATE", "EJECUTO CREACION");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DATABASE_DROP_USUARIOS);
        db.execSQL(DATABASE_DROP_AYUDA);
        this.onCreate(db);

    }
}
