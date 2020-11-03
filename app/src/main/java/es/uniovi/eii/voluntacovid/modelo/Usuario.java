package es.uniovi.eii.voluntacovid.modelo;

import android.os.Parcel;
import android.os.Parcelable;

public class Usuario implements Parcelable {

    private String usuario;
    private String contraseña;
    private int telefono;
    private String direccion;
    private int codigoPostal;
    private String tipo;

    public Usuario(String usuario, String contraseña, int telefono, String direccion, int codigoPostal, String tipo) {
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.telefono = telefono;
        this.direccion = direccion;
        this.codigoPostal = codigoPostal;
        this.tipo = tipo;
    }

    public Usuario(){

    }

    protected Usuario(Parcel in) {
        usuario = in.readString();
        contraseña = in.readString();
        telefono = in.readInt();
        direccion = in.readString();
        codigoPostal = in.readInt();
        tipo = in.readString();
    }

    public static final Creator<Usuario> CREATOR = new Creator<Usuario>() {
        @Override
        public Usuario createFromParcel(Parcel in) {
            return new Usuario(in);
        }

        @Override
        public Usuario[] newArray(int size) {
            return new Usuario[size];
        }
    };

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(int codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(usuario);
        dest.writeString(contraseña);
        dest.writeInt(telefono);
        dest.writeString(direccion);
        dest.writeInt(codigoPostal);
        dest.writeString(tipo);
    }
}
