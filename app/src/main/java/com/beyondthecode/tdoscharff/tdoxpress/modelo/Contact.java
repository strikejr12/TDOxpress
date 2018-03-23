package com.beyondthecode.tdoscharff.tdoxpress.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Computer on 15/03/2018.
 */

public class Contact {

    private Long idContacto;
    private String nombre;
    private Long telefono;
    private String area;
    private String imagen;
    private String sede;


    public Contact(Long idContacto, String nombre, Long telefono,  String area, String imagen, String sede) {
        this.idContacto = idContacto;
        this.nombre = nombre;
        this.telefono = telefono;
        this.area = area;
        this.imagen = imagen;
        this.sede = sede;
    }

    public Contact() {
    }

    public Long getIdContacto() {
        return idContacto;
    }

    public void setIdContacto(Long idContacto) {
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

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }


}