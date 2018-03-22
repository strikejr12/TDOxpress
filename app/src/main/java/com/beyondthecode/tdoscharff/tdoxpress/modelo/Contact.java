package com.beyondthecode.tdoscharff.tdoxpress.modelo;

/**
 * Created by Computer on 15/03/2018.
 */

public class Contact {

    private String idContacto;

    private String nombre;
    private Long telefono;
    private String imagen;
    private String area;


    public Contact(String idContacto, String nombre, Long telefono, String imagen, String area) {
        this.idContacto = idContacto;
        this.nombre = nombre;
        this.telefono = telefono;
        this.imagen = imagen;
        this.area = area;
    }

    public Contact() {
    }

    public String getIdContacto() {
        return idContacto;
    }

    public void setIdContacto(String idContacto) {
        this.idContacto = idContacto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getTelefono() {
        return telefono;
    }

    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
