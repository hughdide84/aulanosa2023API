package es.aulanosa.gestionfp.controller;

import es.aulanosa.gestionfp.model.Mensaje;
import es.aulanosa.gestionfp.model.MensajeUsuario;
import es.aulanosa.gestionfp.model.Usuario;
import es.aulanosa.gestionfp.service.MensajeUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mensajeUsuario")
public class MensajeUsuarioController {

    @Autowired
    //Con esto le decimos que inyecte el servicio
    private MensajeUsuarioService servicio;

    @GetMapping("/creadoPor/{id}")
    //Hacemos un getmapping para que nos devuelva los mensajes que ha creado el usuario
    public ResponseEntity<?> listarPorAutor(@PathVariable Integer id){
        List<Mensaje> mensajes = servicio.listarPorAutor(id);
        return ResponseEntity.status(HttpStatus.OK).body(mensajes);
    }

    @GetMapping("/destinatario/{id}")
    //Hacemos un getmapping para que nos devuelva los mensajes a quien va destinado
    public ResponseEntity<?> listarPorDestinatario(@PathVariable Integer id){
        List<Usuario> mensajes = servicio.listarPorDestinario(id);
        return ResponseEntity.status(HttpStatus.OK).body(mensajes);
    }

    @PutMapping("")
    //Hacemos un putmapping para que nos modifique el mensaje
    public ResponseEntity<?> modificarMensajeUsuario(@RequestBody MensajeUsuario mensajeUsuario){
        try {
            MensajeUsuario mensajeUsuarioModificado = servicio.modificar(mensajeUsuario);
            return ResponseEntity.status(HttpStatus.OK).body(mensajeUsuarioModificado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("")
    //Hacemos un postmapping para que nos cree un mensaje
    public ResponseEntity<?> altaMensajeUsuario(@RequestBody MensajeUsuario mensajeUsuario){
        try {
            MensajeUsuario mensajeUsuarioGuardado = servicio.insertar(mensajeUsuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(mensajeUsuarioGuardado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
