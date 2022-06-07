/*
 * Copyright (c) 2022 SELAE All rights reserved.
 * ****************************************************************************************************************
 * "Este componente software es propiedad de SELAE; contiene información confidencial y tanto en su totalidad como
 * parcialmente, no puede ser transferido, reproducido o publicado, bien sea directa o indirectamente, sin la aprobación
 * escrita de SELAE."
 */

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
