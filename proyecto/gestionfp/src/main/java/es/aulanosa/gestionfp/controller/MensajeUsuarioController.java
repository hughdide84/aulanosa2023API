package es.aulanosa.gestionfp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mensajeUsuario")
public class MensajeUsuarioController {

    @Autowired
    private MensajeUsuarioService servicio;


}
