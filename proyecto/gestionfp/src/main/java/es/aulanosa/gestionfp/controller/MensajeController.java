package es.aulanosa.gestionfp.controller;

import es.aulanosa.gestionfp.dto.MensajeDTO;
import es.aulanosa.gestionfp.dto.UsuarioDTO;
import es.aulanosa.gestionfp.model.Mensaje;
import es.aulanosa.gestionfp.model.Usuario;
import es.aulanosa.gestionfp.service.MensajeService;
import es.aulanosa.gestionfp.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/mensaje")
public class MensajeController {

    @Autowired
    MensajeService service;

    // Crea un nuevo mensaje
    @PostMapping("")
    public ResponseEntity<?> crear(@RequestBody MensajeDTO mensajeDTO) {
        Optional<Mensaje> mensajeConsultado = service.consultarPorIdMensaje(mensajeDTO.getId());

        if(!mensajeConsultado.isPresent()){
            Mensaje mensajeGuardado = mensajeDTO.toModel();
            service.insertarMensaje(mensajeGuardado);
            return ResponseEntity.status(HttpStatus.CREATED).body(mensajeDTO.toDTO(mensajeGuardado));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario ya ha sido añadido");
        }

    }

    // Devuelve el usuario cuyo id coincide con el introducido
    @GetMapping("/{id}")
    public ResponseEntity<?> getUsuarioById(@PathVariable Integer id) {
        Optional<Mensaje> mensajeConsultado = service.consultarPorIdMensaje(id);

        MensajeDTO mensajeDTO = 

        if (mensajeConsultado.isPresent()) {
            return ResponseEntity.ok(mensajeConsultado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe ningún mensaje con ese ID");
        }
    }

}
