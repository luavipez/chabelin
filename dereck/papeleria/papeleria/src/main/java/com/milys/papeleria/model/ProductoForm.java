package com.milys.papeleria.model;


import org.springframework.web.multipart.MultipartFile;

public class ProductoForm {
    private String nombre;
    // Use MultipartFile here to match the form input
    private MultipartFile contenido;
    // getters and setters...


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public MultipartFile getContenido() {
        return contenido;
    }

    public void setContenido(MultipartFile contenido) {
        this.contenido = contenido;
    }

}

