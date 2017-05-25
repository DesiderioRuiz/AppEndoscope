package com.example.zumba.appendoscope;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Desiderio Ruiz Aguilar.
 */

public class ParcelableClass implements Parcelable {
    /**
     * MIEMBROS PUBLICOS DE LA CLASE
     */

    public String nombre;
    public String usuario;
    public String contrasenia;

    /**
     * Constructor por defecto
     */
    public ParcelableClass() {
        nombre = "";
        usuario = "";
        contrasenia = "";
    }

    public String getNombre() {
        return nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    /**
     * Constructor a partir de un objeto Parcel
     *
     * @param in entrada de informacion
     */
    protected ParcelableClass(Parcel in) {
        nombre = in.readString();
        usuario = in.readString();
        contrasenia = in.readString();
    }

    /**
     * Método requerido para construir Listas de Objetos
     */
    public static final Creator<ParcelableClass> CREATOR = new Creator<ParcelableClass>() {
        @Override
        public ParcelableClass createFromParcel(Parcel in) {
            return new ParcelableClass(in);
        }

        @Override
        public ParcelableClass[] newArray(int size) {
            return new ParcelableClass[size];
        }
    };

    // Métodos propios de la Interfaz Parcelable
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Método que Inicializa un objeto Parcel con los miembros de la clase
     *
     * @param parcel objeto parcel destino
     * @param i
     */
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nombre);
        parcel.writeString(usuario);
        parcel.writeString(contrasenia);
    }

}
