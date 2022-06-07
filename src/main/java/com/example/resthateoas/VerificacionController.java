package com.example.resthateoas;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/verificaciones")
@EnableHypermediaSupport(type = HypermediaType.HAL)
public class VerificacionController {

    @GetMapping
    public CollectionModel<VerificacionDTO> getAllVerificaciones() {
        List<VerificacionDTO> verificaciones = new ArrayList<VerificacionDTO>();
        verificaciones.add(
                new VerificacionDTO("SELAE_android", 1L, new Date(), "DoB", UUID.randomUUID().toString(), "07319384W"));
        verificaciones.add(
                new VerificacionDTO("SELAE_ios", 0L, new Date(), "DoB", UUID.randomUUID().toString(), "34617583F"));
        verificaciones.add(
                new VerificacionDTO("SELAE_android", 1L, new Date(), "DoB", UUID.randomUUID().toString(), "26261138Z"));
        for (VerificacionDTO verificacion : verificaciones) {
            Link selfLink = linkTo(VerificacionController.class).slash(verificacion.getUid()).withSelfRel();
            verificacion.add(selfLink);
        }
        Link link = linkTo(VerificacionController.class).withSelfRel();
        CollectionModel<VerificacionDTO> result = CollectionModel.of(verificaciones, link);
        return result;
    }

    @GetMapping("/{uid}")
    // public HttpEntity<VerificacionDTO> getVerificacionByUID(
    public VerificacionDTO getVerificacionByUID(@PathVariable(name = "uid", required = true) final String uid) {
        VerificacionDTO verificacion = new VerificacionDTO("SELAE_android", 1L, new Date(), "DoB", uid, "07319384W");
        verificacion.add(linkTo(methodOn(VerificacionController.class).getVerificacionByUID(uid)).withSelfRel());
        verificacion.add(linkTo(methodOn(VerificacionAgregadosController.class).getSelfieByUID(uid)).withRel("selfie"));
        verificacion
                .add(linkTo(methodOn(VerificacionAgregadosController.class).getAnversoByUID(uid)).withRel("anverso"));
        verificacion
                .add(linkTo(methodOn(VerificacionAgregadosController.class).getReversoByUID(uid)).withRel("reverso"));
        verificacion.add(
                linkTo(methodOn(VerificacionAgregadosController.class).getPruebaVidaByUID(uid)).withRel("pruebavida"));
        verificacion.add(
                linkTo(methodOn(VerificacionAgregadosController.class).getResultadoByUID(uid)).withRel("resultado"));
        // return new ResponseEntity<>(verificacion, HttpStatus.OK);
        return verificacion;
    }
}
