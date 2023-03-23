package es.aulanosa.gestionfp.controller;

import es.aulanosa.gestionfp.dto.ErrorEstudiosDto;
import es.aulanosa.gestionfp.model.Mensaje;
import es.aulanosa.gestionfp.model.MensajeUsuario;
import es.aulanosa.gestionfp.model.Usuario;
import es.aulanosa.gestionfp.service.MensajeUsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
/**
 * Clase que implementa la interfaz MensajeUsuarioService
 */
@RestController
@RequestMapping("/api/mensajeUsuario")
public class MensajeUsuarioController {

    @Autowired
    //Con esto le decimos que inyecte el servicio
    private MensajeUsuarioService servicio;

    /**
     * Devuelve una lista de mensajes que ha enviado un usuario
     * @param id
     * @return una lista de mensajes
     */
    @GetMapping("/creadoPor/{id}")
    @Operation(summary = "Devuelve una lista de mensajes que ha enviado un usuario")
    //Hacemos un getmapping para que nos devuelva los mensajes que ha creado el usuario
    public ResponseEntity<?> listarPorAutor(@PathVariable Integer id){
        List<Mensaje> mensajes = servicio.listarPorAutor(id);
        return ResponseEntity.status(HttpStatus.OK).body(mensajes);
    }
    /**
     * Devuelve una lista de mensajes que ha recibido un usuario
     * @param id
     * @return una lista de mensajes
     */
    @GetMapping("/destinatario/{id}")
    @Operation(summary = "Devuelve una lista de mensajes que ha recibido un usuario")
    //Hacemos un getmapping para que nos devuelva los mensajes a quien va destinado
    public ResponseEntity<?> listarPorDestinatario(@PathVariable Integer id){
        List<Usuario> mensajes = servicio.listarPorDestinario(id);
        return ResponseEntity.status(HttpStatus.OK).body(mensajes);
    }
    /**
     * Modifica un mensajeUsuario en la base de datos
     * @return una lista de mensajes
     */
    @PutMapping("")
    @Operation(summary = "Modifica un mensajeUsuario en la base de datos")
    //Hacemos un putmapping para que nos modifique el mensaje
    public ResponseEntity<?> modificarMensajeUsuario(@RequestBody MensajeUsuario mensajeUsuario){
        try {
            MensajeUsuario mensajeUsuarioModificado = servicio.modificar(mensajeUsuario);
            return ResponseEntity.status(HttpStatus.OK).body(mensajeUsuarioModificado);
        } catch (Exception e) {
            List<ErrorEstudiosDto> errores = new ArrayList<>();
            errores.add(new ErrorEstudiosDto("E007", e.getMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
        }
    }

    /**
     * Crea un mensajeUsuario en la base de datos
     * @param mensajeUsuario
     * @return el mensajeUsuario creado
     */
    @PostMapping("")
    @Operation(summary = "Crea un mensajeUsuario en la base de datos")
    //Hacemos un postmapping para que nos cree un mensaje
    public ResponseEntity<?> altaMensajeUsuario(@RequestBody MensajeUsuario mensajeUsuario){
        try {
            MensajeUsuario mensajeUsuarioGuardado = servicio.insertar(mensajeUsuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(mensajeUsuarioGuardado);
        } catch (Exception e) {
            List<ErrorEstudiosDto> errores = new ArrayList<>();
            errores.add(new ErrorEstudiosDto("E007", e.getMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
        }
    }

}
