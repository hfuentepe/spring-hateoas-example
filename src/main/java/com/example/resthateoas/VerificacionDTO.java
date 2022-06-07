package com.example.resthateoas;

import java.util.Date;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonCreator;

public class VerificacionDTO extends RepresentationModel<VerificacionDTO> {

    private final String uid;
    private final String proveedor;
    private final String docIdentidad;
    private final Date fechaVerificacion;
    private final Long estado;
    private final String aplicacion;

    @JsonCreator
    public VerificacionDTO(String aplicacion, Long estado, Date fechaVerificacion, String proveedor, String uid,
            String docIdentidad) {
        this.aplicacion = aplicacion;
        this.estado = estado;
        this.fechaVerificacion = fechaVerificacion;
        this.proveedor = proveedor;
        this.uid = uid;
        this.docIdentidad = docIdentidad;
    }

    public String getUid() {
        return uid;
    }

    public String getProveedor() {
        return proveedor;
    }

    public String getDocIdentidad() {
        return docIdentidad;
    }

    public Date getFechaVerificacion() {
        return fechaVerificacion;
    }

    public Long getEstado() {
        return estado;
    }

    public String getAplicacion() {
        return aplicacion;
    }
}
