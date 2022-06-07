package com.example.resthateoas;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/verificaciones")
@EnableHypermediaSupport(type = HypermediaType.HAL)
public class VerificacionAgregadosController {

    @GetMapping("/{uid}/selfies")
    public FicheroBase64DTO getSelfieByUID(@PathVariable(name = "uid", required = true) final String uid) {
        FicheroBase64DTO selfie = new FicheroBase64DTO(encodeFileToBase64(getFileAsIOStream("selfie.jpg")),
                "selfie.jpg", "image/jpeg");
        selfie.add(linkTo(methodOn(VerificacionAgregadosController.class).getSelfieByUID(uid)).withSelfRel());
        return selfie;
    }

    @GetMapping("/{uid}/anversos")
    public FicheroBase64DTO getAnversoByUID(@PathVariable(name = "uid", required = true) final String uid) {
        FicheroBase64DTO selfie = new FicheroBase64DTO(encodeFileToBase64(getFileAsIOStream("dni_front.jpg")),
                "dni_front.jpg", "image/jpeg");
        selfie.add(linkTo(methodOn(VerificacionAgregadosController.class).getAnversoByUID(uid)).withSelfRel());
        return selfie;
    }

    @GetMapping("/{uid}/reversos")
    public FicheroBase64DTO getReversoByUID(@PathVariable(name = "uid", required = true) final String uid) {
        FicheroBase64DTO selfie = new FicheroBase64DTO(encodeFileToBase64(getFileAsIOStream("dni_back.jpg")),
                "dni_back.jpg", "image/jpeg");
        selfie.add(linkTo(methodOn(VerificacionAgregadosController.class).getReversoByUID(uid)).withSelfRel());
        return selfie;
    }

    @GetMapping("/{uid}/pruebasvida")
    public FicheroBase64DTO getPruebaVidaByUID(@PathVariable(name = "uid", required = true) final String uid) {
        FicheroBase64DTO selfie = new FicheroBase64DTO(encodeFileToBase64(getFileAsIOStream("pruebavida.webm")),
                "pruebavida.webm", "video/webm");
        selfie.add(linkTo(methodOn(VerificacionAgregadosController.class).getPruebaVidaByUID(uid)).withSelfRel());
        return selfie;
    }

    @GetMapping("/{uid}/resultados")
    public FicheroBase64DTO getResultadoByUID(@PathVariable(name = "uid", required = true) final String uid) {
        FicheroBase64DTO selfie = new FicheroBase64DTO(encodeFileToBase64(getFileAsIOStream("resultado.json")),
                "resultado.json", "application/json");
        selfie.add(linkTo(methodOn(VerificacionAgregadosController.class).getResultadoByUID(uid)).withSelfRel());
        return selfie;
    }

    private InputStream getFileAsIOStream(String fileName) {
        InputStream ioStream = this.getClass().getClassLoader().getResourceAsStream(fileName);
        if (ioStream == null) {
            throw new IllegalArgumentException(fileName + " is not found");
        }
        return ioStream;
    }

    private static String encodeFileToBase64(InputStream is) {
        try {
            byte[] targetArray = new byte[is.available()];
            is.read(targetArray);
            return Base64.getEncoder().encodeToString(targetArray);
        } catch (IOException e) {
            throw new IllegalStateException("could not read file", e);
        }
    }
}
