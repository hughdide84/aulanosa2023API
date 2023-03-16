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

    // Devuelve el mensaje cuyo id coincide con el introducido
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerUsuarioPorId(@PathVariable Integer id) {
        Optional<Mensaje> mensajeConsultado = service.consultarPorIdMensaje(id);

        if (mensajeConsultado.isPresent()) {
            return ResponseEntity.ok(mensajeConsultado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe ningún mensaje con ese ID");
        }
    }

    @PutMapping
    public ResponseEntity<?> editarMensaje(@RequestBody MensajeDTO mensajeDTO) {
        Optional<Mensaje> mensajeConsultar = service.consultarPorIdMensaje(mensajeDTO.getId());

        if (mensajeConsultar.isPresent()) {
            Mensaje mensajeActualizar = mensajeDTO.toModel();
            service.insertarMensaje(mensajeActualizar);
            return ResponseEntity.status(HttpStatus.CREATED).body(mensajeDTO.toDTO(mensajeActualizar));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado el mensaje a modificar.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarMensajePorId(@PathVariable Integer id) {
        Optional<Mensaje> mensajeConsultado = service.consultarPorIdMensaje(id);

        if (mensajeConsultado.isPresent()) {
            service.eliminarMensaje(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El mensaje que desea eliminar no existe");
        }
    }

}
