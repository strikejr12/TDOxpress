package com.beyondthecode.tdoscharff.tdoxpress.modelo;

/**
 * Created by Computer on 15/03/2018.
 */

public class Contact {

    private String nombre;
    private Long telefono;
    private String imagen;
    private String area;

    public Contact(String nombre, Long telefono, String imagen) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.imagen = imagen;
    }


    public Contact() {
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
