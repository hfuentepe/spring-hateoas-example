package com.example.resthateoas;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonCreator;

public class FicheroBase64DTO extends RepresentationModel<FicheroBase64DTO> {

    private final String contenido;
    private final String nombre;
    private final String tipo;

    @JsonCreator
    public FicheroBase64DTO(String contenido, String nombre, String tipo) {
        this.contenido = contenido;
        this.nombre = nombre;
        this.tipo = tipo;
    }

    public String getContenido() {
        return contenido;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

}
