package es.uniovi.eii.voluntacovid.modelo;

import android.os.Parcel;
import android.os.Parcelable;

public class Ayuda implements Parcelable {

    private int id;
    private String usuario;
    private String titulo;
    private String descripcion;
    private String fecha;
    private String estado;

    public Ayuda(int id, String usuario, String titulo, String descripcion, String fecha, String estado) {
        this.id = id;
        this.usuario = usuario;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.estado = estado;
    }

    public Ayuda(){

    }


    protected Ayuda(Parcel in) {
        id = in.readInt();
        usuario = in.readString();
        titulo = in.readString();
        descripcion = in.readString();
        fecha = in.readString();
        estado = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(usuario);
        dest.writeString(titulo);
        dest.writeString(descripcion);
        dest.writeString(fecha);
        dest.writeString(estado);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Ayuda> CREATOR = new Creator<Ayuda>() {
        @Override
        public Ayuda createFromParcel(Parcel in) {
            return new Ayuda(in);
        }

        @Override
        public Ayuda[] newArray(int size) {
            return new Ayuda[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
